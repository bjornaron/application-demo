package no.demo.boligapplication;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Evaluerer {@link InternalPackage} annotasjoner og sjekker at disse pakkene ikke er aksessert fra utsiden.
 */
@AnalyzeClasses(packages = "no.demo.boligapplication")
class ArkitekturTests {

    @ArchTest
    public static final ArchRule validereNavngivingOgAnnoteringPåControllere = classes()
        .that().areAnnotatedWith(RestController.class)
        .or().haveSimpleNameEndingWith("Controller")
        .should().beAnnotatedWith(RestController.class)
        .andShould().haveSimpleNameEndingWith("Controller")
        .because("Controllere skal ha navn som ender med 'Controller' og være annotert med @RestController");
    private static final String BASE_PACKAGE = "no.demo.boligapplication";
    private final JavaClasses analyzedClasses = new ClassFileImporter().importPackages(BASE_PACKAGE);

    @Test
    void internePakkerSkalIkkeKunneAksesseresFraUtsiden() throws IOException {

        // Assert som sjekker at pakkenavnet stemmer med det vi tester
        assertPackageExists(BASE_PACKAGE);

        List<String> internalPackages = internalPackages(BASE_PACKAGE);

        for (String internalPackage : internalPackages) {
            assertPackageIsNotAccessedFromOutside(internalPackage);
        }
    }

    /**
     * Finner alle pakker annotert med @{@link InternalPackage}.
     */
    private List<String> internalPackages(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        return reflections.getTypesAnnotatedWith(InternalPackage.class).stream()
            .map(c -> c.getPackage().getName())
            .collect(Collectors.toList());
    }

    void assertPackageIsNotAccessedFromOutside(String internalPackage) {
        noClasses().that().resideOutsideOfPackage(packageMatcher(internalPackage))
            .should().dependOnClassesThat().resideInAPackage(packageMatcher(internalPackage))
            .check(analyzedClasses);
    }

    void assertPackageExists(String packageName) {
        assertThat(analyzedClasses.containPackage(packageName))
            .as("package %s exists", packageName)
            .isTrue();
    }

    private String packageMatcher(String fullyQualifiedPackage) {
        return fullyQualifiedPackage + "..";
    }

}
