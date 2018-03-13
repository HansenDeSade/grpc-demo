package de.hansendesade.grpcdemo.sender.web;

import com.google.protobuf.Empty;
import de.hansendesade.grpcdemo.sender.models.Country;
import de.hansendesade.grpcdemo.sender.repository.CountryRepository;
import de.hansendesade.grpcdemo.sender.service.CountryLetterCountService;
import io.grpc.stub.StreamObserver;
import io.hansendesade.countryAPI.CountryProviderGrpc;
import io.hansendesade.countryAPI.CountryProviderOuterClass;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@GRpcService
public class CountryProviderImpl extends CountryProviderGrpc.CountryProviderImplBase {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private CountryLetterCountService service;

    @Override
    public void getCountries(Empty request, StreamObserver<CountryProviderOuterClass.CountryReply> responseObserver) {
        Iterable<Country> all = repository.findAll();
        StreamSupport.stream(all.spliterator(), true).forEach(c ->
                responseObserver.onNext(CountryProviderOuterClass.CountryReply.newBuilder()
                        .setId(c.getId())
                        .setName(c.getName())
                        .build()));
        responseObserver.onCompleted();
    }

    @Override
    public void getCountry(CountryProviderOuterClass.GetCountryRequest request, StreamObserver<CountryProviderOuterClass.CountryReply> responseObserver) {
        final Country one = repository.findOne(request.getId());
        responseObserver.onNext(CountryProviderOuterClass.CountryReply.newBuilder()
                .setId(one.getId())
                .setName(one.getName())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void countLettersOfAllCounties(Empty request, StreamObserver<CountryProviderOuterClass.CalculationResponse> responseObserver) {
        int result = service.countLetters();
        responseObserver.onNext(CountryProviderOuterClass.CalculationResponse.newBuilder()
                .setNumberOfLetters(result)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void uncaughtException(Empty request, StreamObserver<Empty> responseObserver) {
        throw new NullPointerException("something happened");
    }

    @Override
    public void letDeadlineExceed(Empty request, StreamObserver<Empty> responseObserver) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}
