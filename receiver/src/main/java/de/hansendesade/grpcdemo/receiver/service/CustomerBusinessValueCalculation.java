package de.hansendesade.grpcdemo.receiver.service;

import de.hansendesade.grpcdemo.receiver.apimodel.Country;
import de.hansendesade.grpcdemo.receiver.model.Customer;
import de.hansendesade.grpcdemo.receiver.util.ReceiverConstants;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.hansendesade.countryAPI.CountryProviderGrpc;
import io.hansendesade.countryAPI.CountryProviderOuterClass;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerBusinessValueCalculation {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final String SERVICE_URL = "http://localhost:8081/countries";

    private static final ManagedChannel CHANNEL = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext(true).build();
    private static final CountryProviderGrpc.CountryProviderBlockingStub CLIENT = CountryProviderGrpc.newBlockingStub(CHANNEL);

    public int calculateBusinessValue(Customer source, String mode) {
        if (StringUtils.hasLength(mode) && ReceiverConstants.GRPC_MODE.equalsIgnoreCase(mode)) {
            CountryProviderOuterClass.CalculationRequest request = CountryProviderOuterClass.CalculationRequest.newBuilder().setCountryId(source.getCountryId()).setMultiplier(source.getCustomerValue()).build();
            CountryProviderOuterClass.CalculationReply result = CLIENT.calculateValue(request);
            return result.getCalculationResult();
        } else {
            Country country = REST_TEMPLATE.getForObject(SERVICE_URL + "/" + source.getCountryId(), Country.class);
            return source.getCustomerValue() * country.getFactor();
        }
    }
}
