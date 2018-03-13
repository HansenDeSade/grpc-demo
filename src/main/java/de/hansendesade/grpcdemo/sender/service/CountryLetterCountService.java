package de.hansendesade.grpcdemo.sender.service;

import de.hansendesade.grpcdemo.sender.models.Country;
import de.hansendesade.grpcdemo.sender.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CountryLetterCountService {

    @Autowired
    private CountryRepository repository;

    public int countLetters() {
        Iterable<Country> all = repository.findAll();
        return StreamSupport.stream(all.spliterator(), true).mapToInt(c -> c.getName().length()).sum();
    }
}
