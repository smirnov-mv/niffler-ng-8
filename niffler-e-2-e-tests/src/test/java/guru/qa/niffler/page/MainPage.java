package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.config.Config;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MainPage {
  public static final String URL = Config.getInstance().frontUrl() + "main";

  private final ElementsCollection tableRows = $$("#spendings tbody tr");
  private final SelenideElement spendings = $("#spendings");
  private final SelenideElement statistics = $("#stat");

  public EditSpendingPage editSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription))
        .$$("td")
        .get(5)
        .click();
    return new EditSpendingPage();
  }

  public void checkThatTableContains(String spendingDescription) {
    tableRows.find(text(spendingDescription))
        .should(visible);
  }

  public MainPage shouldBeLoaded() {
    assertAll(
        () -> spendings.shouldBe(visible),
        () -> statistics.shouldBe(visible)
    );
    return this;
  }
}
