package de.hansendesade.grpcdemo.sender.web;

import de.hansendesade.grpcdemo.sender.repository.CountryRepository;
import io.grpc.stub.StreamObserver;
import io.hansendesade.countryAPI.CountryProviderGrpc;
import io.hansendesade.countryAPI.CountryProviderOuterClass;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class CountryProviderImpl extends CountryProviderGrpc.CountryProviderImplBase {

    @Autowired
    private CountryRepository repository;

    @Override
    public void calculateValue(CountryProviderOuterClass.CalculationRequest request, StreamObserver<CountryProviderOuterClass.CalculationReply> responseObserver) {
        de.hansendesade.grpcdemo.sender.models.Country one = repository.findOne(request.getCountryId());
        CountryProviderOuterClass.CalculationReply.Builder builder = CountryProviderOuterClass.CalculationReply.newBuilder();
        builder.setCalculationResult(one.getFactor() * request.getMultiplier());
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
