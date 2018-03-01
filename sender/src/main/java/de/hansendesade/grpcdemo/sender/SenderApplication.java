package de.hansendesade.grpcdemo.sender;

import de.hansendesade.grpcdemo.sender.web.MyExampleSenderController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }

    //@Bean
    //public MyExampleSenderController countryProvider() {
    //    return new MyExampleSenderController();
    //}
}
