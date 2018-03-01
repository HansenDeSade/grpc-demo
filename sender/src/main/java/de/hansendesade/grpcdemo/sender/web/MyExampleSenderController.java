package de.hansendesade.grpcdemo.sender.web;

import de.hansendesade.grpcdemo.sender.apimodel.Country;
import io.grpc.stub.StreamObserver;
import io.hansendesade.examples.CountryProviderGrpc;
import io.hansendesade.examples.CountryProviderOuterClass;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sender")
@GRpcService
public class MyExampleSenderController extends CountryProviderGrpc.CountryProviderImplBase {

    @RequestMapping(path = "/rest")
    public HttpEntity<?> getByRest() {
        Country result = createCountry();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    private Country createCountry() {
        return new Country(1337l, "Principality of Hutt River");
    }

    @Override
    public void getCountry(CountryProviderOuterClass.CountryRequest request, StreamObserver<CountryProviderOuterClass.CountryReply> responseObserver) {
        Country result = createCountry();
        CountryProviderOuterClass.CountryReply.Builder builder = CountryProviderOuterClass.CountryReply.newBuilder();
        builder.setId(result.getId());
        builder.setName(result.getName());
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
