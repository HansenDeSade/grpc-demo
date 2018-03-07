package de.hansendesade.grpcdemo.receiver.converter;

import de.hansendesade.grpcdemo.receiver.model.Customer;
import de.hansendesade.grpcdemo.receiver.service.CustomerBusinessValueCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerConverter {

    @Autowired
    private CustomerBusinessValueCalculation calculator;

    public de.hansendesade.grpcdemo.receiver.apimodel.Customer convert(Customer source, String mode) {
        de.hansendesade.grpcdemo.receiver.apimodel.Customer result = new de.hansendesade.grpcdemo.receiver.apimodel.Customer();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setBusinessValue(calculator.calculateBusinessValue(source, mode));
        return result;
    }

}
