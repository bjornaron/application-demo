package no.demo.application.api;

public interface BoligApiService {

    String sendBoligSoknad(BoligSoknadRequest boligSoknadRequest);

    String hentBoligSoknadStatus(String applicationReference);
}