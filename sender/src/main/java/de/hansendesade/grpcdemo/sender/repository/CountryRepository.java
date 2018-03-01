package de.hansendesade.grpcdemo.sender.repository;

import de.hansendesade.grpcdemo.sender.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
