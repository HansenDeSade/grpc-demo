package de.hansendesade.grpcdemo.receiver.repository;

import de.hansendesade.grpcdemo.receiver.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
