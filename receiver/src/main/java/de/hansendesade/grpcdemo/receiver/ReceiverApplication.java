package de.hansendesade.grpcdemo.receiver;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.hansendesade.countryAPI.CountryProviderGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

    // GRPC - Configuration

    @Value("${country.service.target}")
    private String countryGrpcTarget;

    @Bean
    Channel channel() {
        return ManagedChannelBuilder.forTarget(countryGrpcTarget)
                .usePlaintext(true)
                .build();
    }

    @Bean
    CountryProviderGrpc.CountryProviderBlockingStub countryProviderBlockingStub(Channel channel) {
        return CountryProviderGrpc.newBlockingStub(channel);
    }

    // REST - Configuration
    @Bean
    public RestTemplate restClient() {
        return new RestTemplate();
    }
}
