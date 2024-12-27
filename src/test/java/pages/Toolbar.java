package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Toolbar {

    public void openSignInMenu() {
        $("#menu-signin").shouldBe(visible).click();
    }

    public void openAccountMenu() {
        $("#menu-account").shouldBe(visible).click();
    }

    public void closeCurrentMenu() {
        $("#sidebar-overlay").click();
    }

}
