package cz.czechitas.automation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Public order selenium actions
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@ParametersAreNonnullByDefault
final class OrderAction {

    private final ElementFinder elementFinder;

    OrderAction(ElementFinder elementFinder)
    {
        this.elementFinder = Objects.requireNonNull(elementFinder);
    }

    void selectSuburbanCampOption() {
        var suburbanCampButton = elementFinder.findByXPath("//*[@id='nav-home-tab']");
        suburbanCampButton.click();
    }

    void selectSchoolInNatureOption() {
        var schoolInNatureButton = elementFinder.findByXPath("//*[@id='nav-profile-tab']");
        schoolInNatureButton.click();
    }

    void insertICO(String ico) {
        Objects.requireNonNull(ico);

        var icoInputBox = elementFinder.findByXPath("//*[@id=\"ico\"]");
        icoInputBox.sendKeys(ico);
        var klikDalej = elementFinder.findByXPath("//*[@id=\"client\"]");
        klikDalej.click();
    }

    void insertChildrenCount(int childrenCount) {
        var natureStudentsInput = elementFinder.findByXPath("//*[@id=\"camp-students\"]");
        natureStudentsInput.sendKeys(String.valueOf(childrenCount));
    }
    void insertOdberatel(String Odberatel) {
        var odberatel = elementFinder.findByXPath("//*[@id=\"client\"]");
        odberatel.sendKeys(Odberatel);
    }


    void insertAdresa(String Adresa) {
        Objects.requireNonNull(Adresa);
        var fullAddressElement = elementFinder.findByXPath("//*[@id='address']");
        fullAddressElement.click();
        var insertAdresa = elementFinder.findByCssSelector("#address");
        insertAdresa.sendKeys(Adresa);

    }

    void insertPrincipal(String Principal) {
        Objects.requireNonNull(Principal);
        var clickPrincipal = elementFinder.findByCssSelector("#substitute");
        clickPrincipal.click();
        var inputPrincipal = elementFinder.findByCssSelector("#substitute");
        inputPrincipal.sendKeys(Principal);
    }

    void insertName(String Name) {
        var nameInput = elementFinder.findByXPath("//*[@id=\"contact_name\"]");
        nameInput.sendKeys(Name);
    }

    void insertEmail(String email) {
        var nameEmail = elementFinder.findByXPath("//*[@id=\"contact_mail\"]");
        nameEmail.sendKeys(String.valueOf(email));
    }

    void insertStartDate(String start) {
        var startDate = elementFinder.findByXPath("//*[@id=\"start_date_1\"]");
        startDate.sendKeys(String.valueOf(start));
    }

    void insertEndDate(String end) {
        var endDate = elementFinder.findByXPath("//*[@id=\"end_date_1\"]");
        endDate.sendKeys(String.valueOf(end));
    }

    void insertAGE(int childrenCount) {
        var StudentsAge = elementFinder.findByXPath("//*[@id=\"camp-age\"]");
        StudentsAge.sendKeys(String.valueOf(childrenCount));
    }

    void insertadult(int adult) {
        var Adult = elementFinder.findByXPath("//*[@id=\"camp-adults\"]");
        Adult.sendKeys(String.valueOf(adult));
    }

    void chooseTime() {
        var schoolInNatureTimeAdd= elementFinder.findByXPath("//*[@id=\"camp-date_part\"]/option[1]");
        schoolInNatureTimeAdd.click();
    }

    void clickFroSave() {
        var saveButton= elementFinder.findByXPath("//*[@id=\"nav-home\"]/div[2]/input");
        saveButton.click();
    }

    void insertTel(int tel) {
        var telNumero = elementFinder.findByXPath("//*[@id=\"contact_tel\"]");
        telNumero.sendKeys(String.valueOf(tel));
    }

    void findName(String tel) {
        var FindName = elementFinder.findByXPath("//*[@id=\"DataTables_Table_0_filter\"]/label/input");
        FindName.sendKeys(String.valueOf(tel));
    }



}



