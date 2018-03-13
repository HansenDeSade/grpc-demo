package de.hansendesade.grpcdemo.sender.web;

import de.hansendesade.grpcdemo.sender.apimodel.Countries;
import de.hansendesade.grpcdemo.sender.apimodel.Country;
import de.hansendesade.grpcdemo.sender.apimodel.CountryLetterCountResponse;
import de.hansendesade.grpcdemo.sender.converter.CountryConverter;
import de.hansendesade.grpcdemo.sender.repository.CountryRepository;
import de.hansendesade.grpcdemo.sender.service.CountryLetterCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private CountryConverter converter;

    @Autowired
    private CountryLetterCountService service;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> findAll() {
        Iterable<de.hansendesade.grpcdemo.sender.models.Country> countries = repository.findAll();
        List<Country> countryList = StreamSupport.stream(countries.spliterator(), true).map(c -> converter.convert(c)).collect(Collectors.toList());
        return ResponseEntity.ok(new Countries(countryList));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public HttpEntity<?> getCountry(@PathVariable("id") long id) {
        de.hansendesade.grpcdemo.sender.models.Country one = repository.findOne(id);
        Country result = converter.convert(one);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/countLetters")
    public HttpEntity<?> countLetters() {
        int letters = service.countLetters();
        return ResponseEntity.ok(new CountryLetterCountResponse(letters));
    }
}
