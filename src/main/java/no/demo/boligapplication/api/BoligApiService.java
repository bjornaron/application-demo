package no.demo.boligapplication.api;

public interface BoligApiService {

    String sendBoligSoknad(BoligSoknadRequest boligSoknadRequest);

    String hentBoligSoknadStatus(String applicationReference);
}