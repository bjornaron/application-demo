package no.demo.application.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record BoligSoknadRequest(List<Soker> sokere, int inntekt, String behov) {

    public BoligSoknadRequest(
        @NotNull(message = "sokere cannot be null") @JsonProperty List<Soker> sokere,
        @NotNull(message = "inntekt cannot be null") @JsonProperty int inntekt,
        @NotNull(message = "behov cannot be null") @JsonProperty String behov) {
        this.sokere = sokere;
        this.inntekt = inntekt;
        this.behov = behov;
    }

    @Override
    public String toString() {
        return "BoligSoknadRequest{" +
               "sokere='" + sokere + '\'' +
               ", inntekt=" + inntekt +
               '}';
    }
}