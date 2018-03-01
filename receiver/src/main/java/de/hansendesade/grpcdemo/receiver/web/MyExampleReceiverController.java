package de.hansendesade.grpcdemo.receiver.web;

import de.hansendesade.grpcdemo.receiver.apimodel.Countries;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/receiver")
public class MyExampleReceiverController {

    public static final RestTemplate REST_TEMPLATE = new RestTemplate();
    public static final ManagedChannel CHANNEL = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext(true).build();
    public static final CountryProviderGrpc.CountryProviderBlockingStub CLIENT = CountryProviderGrpc.newBlockingStub(CHANNEL);

    @RequestMapping(method = RequestMethod.GET, path = "/rest")
    public HttpEntity<?> getAllByRest() {
        Countries countries = REST_TEMPLATE.getForObject("http://localhost:8081/sender/rest", Countries.class);
        // do your business logic
        String result = countries.getCountries().parallelStream().map(c -> c.getId() + " - " + c.getName()).collect(Collectors.joining("; "));
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/grpc")
    public HttpEntity<?> getAllByGrpc() throws ExecutionException, InterruptedException {
        CountryProviderOuterClass.CountryRequest request = CountryProviderOuterClass.CountryRequest.newBuilder().build();
        Iterator<CountryProviderOuterClass.CountryReply> reply = CLIENT.getCountry(request);
        ArrayList<String> countryList = new ArrayList<>();
        reply.forEachRemaining(countryReply -> countryList.add(countryReply.getId() + " - " + countryReply.getName()));
        return new ResponseEntity(countryList.stream().collect(Collectors.joining("; ")), HttpStatus.OK);
    }
}
