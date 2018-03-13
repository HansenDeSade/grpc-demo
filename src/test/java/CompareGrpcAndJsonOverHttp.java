import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Empty;
import de.hansendesade.grpcdemo.sender.SenderApplication;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import io.hansendesade.countryAPI.CountryProviderGrpc;
import io.hansendesade.countryAPI.CountryProviderOuterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = {SenderApplication.class })
public class CompareGrpcAndJsonOverHttp {

    private static final Empty EMPTY_REQUEST = Empty.newBuilder().build();
    private Channel channel = ManagedChannelBuilder.forTarget("localhost:6565")
            .usePlaintext(true)
                .build();

    private CountryProviderGrpc.CountryProviderBlockingStub blockingStub = CountryProviderGrpc.newBlockingStub(channel);
    private CountryProviderGrpc.CountryProviderFutureStub futureStub = CountryProviderGrpc.newFutureStub(channel);
    private CountryProviderGrpc.CountryProviderStub stub = CountryProviderGrpc.newStub(channel);

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testGetAllRest() throws Exception {
        Integer lastContentLength = null;
        for(int i = 0; i < 10; i++) {
            final MvcResult result = mockMvc.perform(get("/countries")).andExpect(status().isOk()).andReturn();
            final Integer newContentLength = result.getResponse().getContentLength();
            if (lastContentLength != null) {
                Assert.assertTrue(newContentLength.equals(lastContentLength));
            }
            lastContentLength = newContentLength;
        }
    }

    @Test
    public void testGetConcreteRest() throws Exception {
        Integer lastContentLength = null;
        for(int i = 0; i < 10; i++) {
            final MvcResult result = mockMvc.perform(get("/countries/2")).andExpect(status().isOk()).andReturn();
            final Integer newContentLength = result.getResponse().getContentLength();
            if (lastContentLength != null) {
                Assert.assertTrue(newContentLength.equals(lastContentLength));
            }
            lastContentLength = newContentLength;
        }
    }

    @Test
    public void testGetLetterCountRest() throws Exception {
        Integer lastContentLength = null;
        for(int i = 0; i < 10; i++) {
            final MvcResult result = mockMvc.perform(get("/countries/countLetters")).andExpect(status().isOk()).andReturn();
            final Integer newContentLength = result.getResponse().getContentLength();
            if (lastContentLength != null) {
                Assert.assertTrue(newContentLength.equals(lastContentLength));
            }
            lastContentLength = newContentLength;
        }
    }

    @Test
    public void testGetAllGrpc() throws Exception {
        Integer lastResponseCount = null;
        for(int i = 0; i < 10; i++) {
            final Collection<CountryProviderOuterClass.CountryReply> responses = new ArrayList<>();
            stub.getCountries(EMPTY_REQUEST, new StreamObserver<CountryProviderOuterClass.CountryReply>() {
                @Override
                public void onNext(CountryProviderOuterClass.CountryReply value) {
                    responses.add(value);
                }

                @Override
                public void onError(Throwable t) {
                    Assert.fail(t.getMessage());
                }

                @Override
                public void onCompleted() {}
            });
            final Integer newCountryCount = responses.size();
            if (lastResponseCount != null) {
                Assert.assertTrue(newCountryCount.equals(lastResponseCount));
            }
            lastResponseCount = newCountryCount;
        }
    }

    @Test
    public void testGetConcreteGrpc() throws Exception {
        String lastCountryName = null;
        for(int i = 0; i < 10; i++) {
            CountryProviderOuterClass.CountryReply reply = blockingStub.getCountry(CountryProviderOuterClass.GetCountryRequest.newBuilder().setId(2).build());
            final String newCountryName = reply.getName();
            if (lastCountryName != null) {
                Assert.assertTrue(newCountryName.equals(lastCountryName));
            }
            lastCountryName = newCountryName;
        }
    }

    @Test
    public void testGetLetterCountGrpc() throws Exception {
        Integer lastResult = null;
        for(int i = 0; i < 10; i++) {
            ListenableFuture<CountryProviderOuterClass.CalculationResponse> futureResponse = futureStub.countLettersOfAllCounties(EMPTY_REQUEST);

            while (!futureResponse.isDone()) {
                Thread.sleep(1);
            }

            final Integer newResult = futureResponse.get().getNumberOfLetters();
            if (lastResult != null) {
                Assert.assertTrue(newResult.equals(lastResult));
            }
            lastResult = newResult;
        }
    }

    @Test
    public void testInvokeUnimplementedFunction() {
        try {
            blockingStub.unimplementedFuction(EMPTY_REQUEST);
        } catch (StatusRuntimeException e) {
            Assert.assertTrue(e.getStatus().getCode() == Status.Code.UNIMPLEMENTED);
        }
    }

    @Test
    public void testGetUncaughtException() {
        try {
            blockingStub.uncaughtException(EMPTY_REQUEST);
        } catch (StatusRuntimeException e) {
            Assert.assertTrue(e.getStatus().getCode() == Status.Code.UNKNOWN);
        }
    }

    @Test
    public void testDeadlineExceeded() {
        try {
            blockingStub.withDeadlineAfter(1, TimeUnit.MILLISECONDS).letDeadlineExceed(EMPTY_REQUEST);
        } catch (StatusRuntimeException e) {
            Assert.assertTrue(e.getStatus().getCode() == Status.Code.DEADLINE_EXCEEDED);
        }
    }
}
