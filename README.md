# Introduksjon

Dette er en demo-applikasjon laget med Spring Boot 3.

### Forutsetninger

Prosjektet er satt opp med Java 21. Du behøver også en nyere versjon av Maven installert.

### Bygg prosjektet

For å bygge prosjektet kjør følgende kommando i terminalen eller start direkte i fra din IDE:

```mvn clean install```

### Start applikasjonen

For å starte applikasjonen kjør følgende kommando i terminalen eller start direkte fra din IDE:

```mvn spring-boot:run```

### Dokumentasjon av API (OpenAPI / Swagger)

For å se API-dokumentasjonen når applikasjonen kjører lokalt kan man gå til følgende URL i nettleseren:

http://localhost:8080/swagger-ui.html

Det er også mulig å kjøre tester API-et direkte fra Swagger-grensesnittet.

### Automatiserte arkitekturtester

For å kjøre arkitekturtester kjør følgende kommando i terminalen:

```mvn test```