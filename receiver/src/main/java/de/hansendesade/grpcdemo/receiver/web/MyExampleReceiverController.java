package de.hansendesade.grpcdemo.receiver.web;

import de.hansendesade.grpcdemo.receiver.apimodel.Country;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.hansendesade.examples.CountryProviderGrpc;
import io.hansendesade.examples.CountryProviderOuterClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/receiver")
public class MyExampleReceiverController {

    public static final RestTemplate REST_TEMPLATE = new RestTemplate();
    public static final ManagedChannel CHANNEL = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext(true).build();
    public static final CountryProviderGrpc.CountryProviderBlockingStub CLIENT = CountryProviderGrpc.newBlockingStub(CHANNEL);

    @RequestMapping(method = RequestMethod.GET, path = "/rest")
    public HttpEntity<?> getAllByRest() {
        Country country = REST_TEMPLATE.getForObject("http://localhost:8081/sender/rest", Country.class);
        // do your business logic
        return new ResponseEntity(country.getId() + " - " + country.getName(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/grpc")
    public HttpEntity<?> getAllByGrpc() throws ExecutionException, InterruptedException {
        CountryProviderOuterClass.CountryRequest request = CountryProviderOuterClass.CountryRequest.newBuilder().build();
        CountryProviderOuterClass.CountryReply reply = CLIENT.getCountry(request);
        return new ResponseEntity(reply.getId() + " - " + reply.getName(), HttpStatus.OK);
    }
}
