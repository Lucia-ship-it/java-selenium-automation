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

    @Test
    void createApplication() {
        var puvodniHeslo = "Heslo112";
        var randomPrijmeni = browser.generateRandomName(6);

        login("l.a@gmail.com", puvodniHeslo);
        browser.headerMenu.goToApplicationsSection();
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreatePythonApplicationButton();

        browser.applicationDetailsSection.selectTerm("02.02. - 06.02.2026");
        browser.applicationDetailsSection.insertStudentFirstName("Franta");
        browser.applicationDetailsSection.insertStudentLastName(randomPrijmeni);
        browser.applicationDetailsSection.insertBirthdate("28.12.2008");
        browser.applicationDetailsSection.insertNote("je to zivy klucina");
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection.clickCreateApplicationButton();

        browser.waitFor(2);
        asserter.applicationDetailAction.checkFirstName("Franta");
        asserter.applicationDetailAction.checkLastName(randomPrijmeni);
        asserter.applicationDetailAction.checkDateOfBirth("28.12.2008");
        asserter.applicationDetailAction.checkPaymentMethod("Hotově");
        asserter.applicationDetailAction.checkNote("je to zivy klucina");

        browser.headerMenu.goToApplicationsSection();
        browser.applicationSection.search(randomPrijmeni);
        browser.applicationSection.openFirstApplicationDetailsPage();
        asserter.applicationDetailAction.checkMessageContainsStudentLastName(randomPrijmeni);

    }

    @Test
    void negativeScenarioLogIn() {
        var nahodneHeslo = browser.generateRandomName(9);
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("l.a@gmail.com");
        browser.loginSection.insertPassword(nahodneHeslo);
        browser.loginSection.clickLoginButton();
        asserter.checkCautionLogin();
    }
    //storka 28
    @Test
    void orderForm() {
        var randomAdresa = browser.generateRandomName(13);
        var randomMeno = "Kvetka " + browser.generateRandomName(4);

        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO("22834958");
        browser.waitFor(1);
        browser.orderSection.insertOdberatel("ZS U obloucku");

        browser.orderSection.insertAdresa(randomAdresa);
        browser.orderSection.insertPrincipal("Vzdelana");
        browser.orderSection.insertName(randomMeno);
        browser.orderSection.insertTel(555555555);
        browser.orderSection.insertEmail("konkretny@email.com");
        browser.orderSection.insertStartDate("05.11.2026");
        browser.orderSection.insertEndDate("12.11.2026");
        browser.orderSection.selectSuburbanCampOption();
        browser.orderSection.insertChildrenCount(6);
        browser.orderSection.insertAGE(14);
        browser.orderSection.insertadult(3);
        browser.orderSection.clickFroSave();
        browser.waitFor(1);
        asserter.checkOrderAccept();
        }

    @Test
    void orderInOrders() {
        var randomAdresa = browser.generateRandomName(13);
        var randomMeno = "Mima " + browser.generateRandomName(4);

        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO("22834958");
        browser.waitFor(1);
        browser.orderSection.insertOdberatel("ZS U obloucku");

        browser.orderSection.insertAdresa(randomAdresa);
        browser.orderSection.insertPrincipal("Organizovana");
        browser.orderSection.insertName(randomMeno);
        browser.orderSection.insertTel(333333333);
        browser.orderSection.insertEmail("oficial@email.com");
        browser.orderSection.insertStartDate("10.10.2026");
        browser.orderSection.insertEndDate("23.10.2026");
        browser.orderSection.selectSuburbanCampOption();
        browser.orderSection.insertChildrenCount(8);
        browser.orderSection.insertAGE(11);
        browser.orderSection.insertadult(4);
        browser.orderSection.clickFroSave();
        browser.waitFor(1);
        asserter.checkOrderAccept();

        browser.headerMenu.goToHomePage();
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("da-app.admin@czechitas.cz");
        browser.loginSection.insertPassword("Czechitas123");
        browser.loginSection.clickLoginButton();

        browser.waitFor(1);
        browser.internalMenu.goToOrdersSection();
        browser.orderSection.findName(randomMeno);
        asserter.checkNumberOfOrders(1);
    }


}



