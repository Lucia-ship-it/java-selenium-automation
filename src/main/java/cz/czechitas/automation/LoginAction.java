package cz.czechitas.automation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

/**
 * Login/logout specific selenium actions
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@ParametersAreNonnullByDefault
final class LoginAction {

    private final ElementFinder elementFinder;

    LoginAction(ElementFinder elementFinder)
    {
        this.elementFinder = Objects.requireNonNull(elementFinder);
    }

    void clickLoginMenuLink() {
        var loginButton = elementFinder.findByCssSelector(".navbar-right .nav-item");
        loginButton.click();
    }

    void insertEmail(String email) {
        Objects.requireNonNull(email);

        var emailInputBox = elementFinder.findByXPath("//*[@id='email']");
        emailInputBox.sendKeys(email);
    }

    void insertPassword(String password) {
        Objects.requireNonNull(password);

        var passwordInputBox = elementFinder.findByXPath("//*[@id='password']");
        passwordInputBox.sendKeys(password);
    }

    void clickLoginButton() {
        var loginButton = elementFinder.findByXPath("//button[@type='submit']");
        loginButton.click();
    }

    void logout() {
        var signedInUserElement = elementFinder.findByXPath("//*[@id='navbarSupportedContent']/div[2]/div/a");
        signedInUserElement.click();
        var logoutButton = elementFinder.findByXPath("//*[@id='logout-link']");
        logoutButton.click();
    }

    void clickRegistrationButton() {
        var registrationButton = elementFinder.findByXPath("/html/body/div/div/div/div/div/div/form/div[4]/div/a");
        registrationButton.click();
    }

    void insertNameRegistration(String name) {
        Objects.requireNonNull(name);

        var registrationName = elementFinder.findByXPath("//*[@id=\"name\"]");
        registrationName.sendKeys(name);
    }

    void insertEmailRegistration(String email) {
        Objects.requireNonNull(email);

        var registrationEmail = elementFinder.findByXPath("//*[@id=\"email\"]");
        registrationEmail.sendKeys(email);
    }

    void insertPasswordRegistration(String password) {
        Objects.requireNonNull(password);

        var registrationPassword = elementFinder.findByXPath("//*[@id=\"password\"]");
        registrationPassword.sendKeys(password);
    }

    void insertVerificationPasswordRegistration(String passwordCheck) {
        Objects.requireNonNull(passwordCheck);

        var registrationPasswordVer = elementFinder.findByXPath("//*[@id=\"password-confirm\"]");
        registrationPasswordVer.sendKeys(passwordCheck);
    }

    void clickFinalRegistrationButton() {
        var zaregistrovatButton = elementFinder.findByXPath("/html/body/div/div/div/div/div/div[2]/form/div[5]/div/button");
        zaregistrovatButton.click();
    }
}
