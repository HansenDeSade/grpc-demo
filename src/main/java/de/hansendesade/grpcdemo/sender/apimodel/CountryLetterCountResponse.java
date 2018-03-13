package de.hansendesade.grpcdemo.sender.apimodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryLetterCountResponse {
    private int letterCount;

    public CountryLetterCountResponse(int letterCount) {
        this.letterCount = letterCount;
    }

    public int getLetterCount() {
        return letterCount;
    }

    public void setLetterCount(int letterCount) {
        this.letterCount = letterCount;
    }
}
