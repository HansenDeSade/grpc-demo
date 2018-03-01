package de.hansendesade.grpcdemo.sender.web;

import de.hansendesade.grpcdemo.sender.apimodel.Countries;
import de.hansendesade.grpcdemo.sender.apimodel.Country;
import de.hansendesade.grpcdemo.sender.converter.CountryConverter;
import de.hansendesade.grpcdemo.sender.repository.CountryRepository;
import io.grpc.stub.StreamObserver;
import io.hansendesade.examples.CountryProviderGrpc;
import io.hansendesade.examples.CountryProviderOuterClass;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/sender")
@GRpcService
public class MyExampleSenderController extends CountryProviderGrpc.CountryProviderImplBase {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private CountryConverter converter;

    @RequestMapping(path = "/rest")
    public HttpEntity<?> getByRest() {
        Iterable<de.hansendesade.grpcdemo.sender.models.Country> countries = repository.findAll();
        List<Country> countryList = StreamSupport.stream(countries.spliterator(), true).map(c -> converter.convert(c)).collect(Collectors.toList());
        return new ResponseEntity(new Countries(countryList), HttpStatus.OK);
    }

    private Country createCountry() {
        return new Country(1337l, "Principality of Hutt River");
    }

    @Override
    public void getCountry(CountryProviderOuterClass.CountryRequest request, StreamObserver<CountryProviderOuterClass.CountryReply> responseObserver) {
        Iterable<de.hansendesade.grpcdemo.sender.models.Country> countries = repository.findAll();
        StreamSupport.stream(countries.spliterator(), false).forEach(country -> {
            CountryProviderOuterClass.CountryReply.Builder builder = CountryProviderOuterClass.CountryReply.newBuilder();
            builder.setId(country.getId());
            builder.setName(country.getName());
            responseObserver.onNext(builder.build());
        });
        responseObserver.onCompleted();
    }
}
