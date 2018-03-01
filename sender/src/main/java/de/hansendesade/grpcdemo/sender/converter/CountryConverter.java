package de.hansendesade.grpcdemo.sender.converter;

import de.hansendesade.grpcdemo.sender.models.Country;
import org.springframework.stereotype.Service;

@Service
public class CountryConverter {

    public de.hansendesade.grpcdemo.sender.apimodel.Country convert(final Country country) {
        return new de.hansendesade.grpcdemo.sender.apimodel.Country(country.getId(), country.getName());
    }
}
