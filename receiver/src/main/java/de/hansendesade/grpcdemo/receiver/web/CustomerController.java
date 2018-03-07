package de.hansendesade.grpcdemo.receiver.web;

import de.hansendesade.grpcdemo.receiver.apimodel.Customers;
import de.hansendesade.grpcdemo.receiver.converter.CustomerConverter;
import de.hansendesade.grpcdemo.receiver.model.Customer;
import de.hansendesade.grpcdemo.receiver.repository.CustomerRepository;
import de.hansendesade.grpcdemo.receiver.service.CustomerBusinessValueCalculation;
import de.hansendesade.grpcdemo.receiver.util.ReceiverConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerConverter converter;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> getAll(@RequestHeader(value = ReceiverConstants.MODE_HEADER, defaultValue = ReceiverConstants.REST_MODE) String mode) {
        logger.info("using " + mode + " - mode");
        Iterable<Customer> allCustomer = repository.findAll();
        List<de.hansendesade.grpcdemo.receiver.apimodel.Customer> convertedCustomers = StreamSupport.stream(allCustomer.spliterator(), true).map(c -> converter.convert(c, mode)).collect(Collectors.toList());
        return ResponseEntity.ok(new Customers(convertedCustomers));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public HttpEntity<?> getSingle(@PathVariable("id") long id, @RequestHeader(value = ReceiverConstants.MODE_HEADER, defaultValue = ReceiverConstants.REST_MODE) String mode) {
        logger.info("using " + mode + " - mode");
        Customer theOne = repository.findOne(id);
        return ResponseEntity.ok(converter.convert(theOne, mode));
    }
}
