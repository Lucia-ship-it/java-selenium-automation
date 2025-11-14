package cz.czechitas.automation.assertion;

import cz.czechitas.automation.ElementFinder;
import org.openqa.selenium.WebDriver;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Assertion facade for user-friendly assertions
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@ParametersAreNonnullByDefault
public final class AssertionFacade {

    private final ElementFinder elementFinder;
    public final ApplicationAssertion applicationSection;
    public final ApplicationDetailAssertion applicationDetailAction;

    public AssertionFacade(WebDriver webDriver)
    {
        this.elementFinder = new ElementFinder(webDriver);
        this.applicationSection = new ApplicationAssertion(elementFinder);
        this.applicationDetailAction = new ApplicationDetailAssertion(elementFinder);
    }

    public void checkPageUrl(String url) {
        var urlElement = elementFinder.findByXPath("//a[text()='www.czechitas.cz']");
        assertThat(urlElement.getText()).isEqualTo(url);
    }

    public void checkIsLoggedIn() {
        var loggedInText = elementFinder.findByCssSelector(".navbar-right span");
        assertThat(loggedInText.getText()).isEqualTo("Přihlášen");
    }

    public void checkProgrammingSectionPresense() {
        var programmingText = elementFinder.findByCssSelector(".main_content .card-img-overlay");
        assertThat(programmingText.getText().trim()).isEqualTo("Programování");
    }

    public void checkRegistrationButtonPresense() {
        var registerButton = elementFinder.findByCssSelector(".btn-secondary");
        assertThat(registerButton.getText().trim()).isEqualTo("Zaregistrujte se");
    }

    public void checkPagePrihlasky() {
        var pageElement = elementFinder.findByXPath("/html/body/div/header/div/h1");
        assertThat(pageElement.getText()).contains("Přihlášky");
    }

    public void checkChangingPassword() {
        var pageElementText = elementFinder.findByXPath("/div/button");
        assertThat(pageElementText.getText()).contains("úspěšně");
    }
    public void checkCautionLogin() {
        var nespravnyLogin = elementFinder.findByXPath("/html/body/div/div/div/div/div/div/form/div[1]/div/span/strong");
        assertThat(nespravnyLogin.getText()).contains("přihlašovací údaje");
    }
    public void checkNumberOfOrders(int applicationsNumber) {
        var orderCountElement = elementFinder.findByXPath("//*[@id=\"DataTables_Table_0_wrapper\"]/div[2]/div");
        assertThat(orderCountElement.getText()).contains("Zobrazeno " + applicationsNumber + " až " + applicationsNumber +
                " záznamů z " + applicationsNumber);
    }

    public void checkOrderAccept() {
        var potvrdenieObjednavky = elementFinder.findByXPath("/html/body/div/div/div/div/div/div/p");
        assertThat(potvrdenieObjednavky.getText()).contains("úspěšně");
    }
}
