package no.demo.application.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public record Soker(String fnr, String navn) {

    public Soker(
        @NotNull(message = "fnr kan ikke være null for søker") @JsonProperty String fnr,
        @NotNull(message = "navn kan ikke være null for søker") @JsonProperty String navn) {
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