package no.demo.application.internal;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import no.demo.application.api.BoligApiService;
import no.demo.application.api.BoligSoknadRequest;

@Service
class BoligApiServiceImpl implements BoligApiService {

    private final Map<String, BoligSoknadRequest> soknaderMap = new ConcurrentHashMap<>();

    @Override
    public String sendBoligSoknad(BoligSoknadRequest boligSoknad) {
        // Sjekk om noen tidligere BoligSoknadRequest-er med det samme fnr finnes
        if (harEksisterendeSoknadMedSammeFnr(boligSoknad)) {
            return "En eksisterende søknad med oppgitt fnr finnes allerede.";
        }

        String soknadsReferanse = genererSoknadsReferanse();

        // Bruk søknadsreferansen for å lagre boligsøknaden i et map
        soknaderMap.put(soknadsReferanse, boligSoknad);

        return "Søknad mottatt. Søknadsreferansen er: " + soknadsReferanse;
    }

    @Override
    public String hentBoligSoknadStatus(String applicationReference) {
        BoligSoknadRequest request = soknaderMap.getOrDefault(applicationReference, null);
        if (request != null) {
            return "Søknad funnet. " + request;
        } else {
            return "Ukjent";
        }
    }

    private String genererSoknadsReferanse() {
        return "SoknadsRef" + System.currentTimeMillis();
    }

    private boolean harEksisterendeSoknadMedSammeFnr(BoligSoknadRequest nyBoligSoknad) {
        // Sjekk om det finnes eksisterende søknad for oppgitt(e) fnr
        return soknaderMap.values().stream()
            .anyMatch(previousRequest -> previousRequest.sokere().stream()
                .anyMatch(soker -> nyBoligSoknad.sokere().stream()
                    .anyMatch(newSoker -> newSoker.fnr().equals(soker.fnr()))));
    }
}