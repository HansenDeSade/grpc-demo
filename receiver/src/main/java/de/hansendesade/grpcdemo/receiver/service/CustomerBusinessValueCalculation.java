package de.hansendesade.grpcdemo.receiver.service;

import de.hansendesade.grpcdemo.receiver.apimodel.Country;
import de.hansendesade.grpcdemo.receiver.model.Customer;
import de.hansendesade.grpcdemo.receiver.util.ReceiverConstants;
import io.hansendesade.countryAPI.CountryProviderGrpc;
import io.hansendesade.countryAPI.CountryProviderOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerBusinessValueCalculation {

    @Value("${country.rest.target}")
    private String countryServiceEndpoint;

    @Autowired
    private CountryProviderGrpc.CountryProviderBlockingStub countryProvider;

    @Autowired
    private RestTemplate restClient;

    public int calculateBusinessValue(Customer source, String mode) {
        if (StringUtils.hasLength(mode) && ReceiverConstants.GRPC_MODE.equalsIgnoreCase(mode)) {
            CountryProviderOuterClass.CalculationReply result = countryProvider.calculateValue(source.toCalculationRequest());
            return result.getCalculationResult();
        } else {
            Country country = restClient.getForObject(countryServiceEndpoint + "/" + source.getCountryId(), Country.class);
            return source.getCustomerValue() * country.getFactor();
        }
    }
}
