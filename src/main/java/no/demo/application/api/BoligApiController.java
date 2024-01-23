package no.demo.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class BoligApiController {

    private final BoligApiService boligApiService;

    @Autowired
    BoligApiController(BoligApiService boligApiService) {
        this.boligApiService = boligApiService;
    }

    @PostMapping("/sendBoligSoknad")
    String sendBoligSoknad(@RequestBody BoligSoknadRequest boligSoknadRequest) {
        return boligApiService.sendBoligSoknad(boligSoknadRequest);
    }

    @GetMapping("/hentBoligSoknadsStatus/{soknadsReferanse}")
    String hentBoligSoknadsStatus(@PathVariable String soknadsReferanse) {
        return boligApiService.hentBoligSoknadStatus(soknadsReferanse);
    }
}