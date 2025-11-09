package cz.czechitas.automation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Example test class for functionality showcase
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class ExampleTest extends TestRunner {

    @Test
    void contactsPageUrlTest() {
        browser.headerMenu.goToContactsSection();
        asserter.checkPageUrl("www.czechitas.cz");
    }

    @Test
    void successfulLoginTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("da-app.admin@czechitas.cz");
        browser.loginSection.insertPassword("Czechitas123");
        browser.loginSection.clickLoginButton();
        asserter.checkIsLoggedIn();
    }

    // paramertized test - find out what is wrong with this test
    @ParameterizedTest()
    @ValueSource(strings = {"123456789", "ASDFBVC", "123"})
    void icoFieldTest(String icoValue) {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(icoValue);
    }


    @Test
    void registration() {
        var name = "Luci Kob";
        var email = "l.a@gmail.com";
        //generovany email pre repetitivnost
        //var email = browser.generateRandomName(2)+"@.gmail.com";

        var password = "Heslo112";

        browser.loginSection.clickLoginMenuLink();
        asserter.checkRegistrationButtonPresense();

        browser.loginSection.clickRegistrationButton();
        browser.waitFor(1);

        browser.loginSection.insertNameRegistration(name);
        browser.loginSection.insertEmailRegistration(email);
        browser.loginSection.insertPasswordRegistration(password);
        browser.loginSection.insertVerificationPasswordRegistration(password);
        browser.loginSection.clickFinalRegistrationButton();

        browser.waitFor(2);
        asserter.checkIsLoggedIn();
        browser.loginSection.logout();
    }

    void login (String email,String password) {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(email);
        browser.loginSection.insertPassword(password);
        browser.loginSection.clickLoginButton();
    }
    @Test
    void pageAfterLogin() {
        login("l.a@gmail.com", "Heslo112");
        browser.waitFor(1);
        asserter.checkIsLoggedIn();
        asserter.checkPagePrihlasky();
    }

    @Test
    void changePassword(){
        var puvodniHeslo = "Heslo112";
        var noveHeslo = "Nove911";

        login("l.a@gmail.com", puvodniHeslo);
        browser.waitFor(1);
        browser.profileSection.goToProfilePage();
        browser.profileSection.insertPassword(noveHeslo);
        browser.profileSection.insertPasswordVerification(noveHeslo);
        browser.profileSection.clickChangeButton();
        browser.waitFor(1);

        browser.headerMenu.goToHomePage();
        browser.loginSection.logout();
        browser.waitFor(2);

        login("l.a@gmail.com", noveHeslo);
        browser.waitFor(1);
        asserter.checkIsLoggedIn();

        browser.profileSection.goToProfilePage();
        browser.profileSection.insertPassword(puvodniHeslo);
        browser.profileSection.insertPasswordVerification(puvodniHeslo);
        browser.profileSection.clickChangeButton();
        browser.waitFor(1);
        browser.headerMenu.goToHomePage();
        browser.loginSection.logout();
    }
}
