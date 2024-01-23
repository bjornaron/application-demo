package no.demo.boligapplication.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "Boligsøknads-API", description = "API for å sende inn boligsøknader. Demo laget av Bjørn Aron Josefsen.")
class BoligApiController {

    private final BoligApiService boligApiService;

    @Autowired
    BoligApiController(BoligApiService boligApiService) {
        this.boligApiService = boligApiService;
    }

    @PostMapping("/sendBoligSoknad")
    @Operation(summary = "Send inn en boligsøknad")
    String sendBoligSoknad(@Valid @RequestBody BoligSoknadRequest boligSoknadRequest) {
        return boligApiService.sendBoligSoknad(boligSoknadRequest);
    }

    @GetMapping("/hentBoligSoknadsStatus/{soknadsReferanse}")
    @Operation(summary = "Hent status for en boligsøknad")
    @Parameter(name = "soknadsReferanse", description = "Søknadsreferanse for søknaden som skal hentes", required = true)
    String hentBoligSoknadsStatus(@PathVariable String soknadsReferanse) {
        return boligApiService.hentBoligSoknadStatus(soknadsReferanse);
    }
}