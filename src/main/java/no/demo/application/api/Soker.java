package no.demo.application.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Soker(String fnr, String navn) {

    public Soker(
        @NotNull(message = "fnr kan ikke være null for søker") @Pattern(regexp = "\\d{11}", message = "fnr må være en gyldig 11-sifret numerisk verdi") @JsonProperty String fnr,
        @NotNull(message = "navn kan ikke være null for søker") @Size(min = 2) @JsonProperty String navn) {
        this.fnr = fnr;
        this.navn = navn;
    }

    public String toString() {
        return "Soker{" +
               "fnr='" + fnr + '\'' +
               ", navn=" + navn +
               '}';
    }
}