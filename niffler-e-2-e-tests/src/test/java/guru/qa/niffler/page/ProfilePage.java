package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.config.Config;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {
  public static final String URL = Config.getInstance().frontUrl() + "profile";

  private final SelenideElement switchShowArchived = $(".MuiSwitch-root");
  private final ElementsCollection categories = $$(".MuiChip-label");

  public ProfilePage enableSwitchShowArchived() {
    switchShowArchived.click();
    switchShowArchived.$(".MuiSwitch-switchBase").shouldHave(Condition.cssClass("Mui-checked"));
    return this;
  }

  public ProfilePage shouldBeActiveCategory(String categoryName) {
    categories.findBy(Condition.text(categoryName)).closest(".MuiButtonBase-root").shouldHave(Condition.cssClass("MuiChip-colorPrimary"));
    return this;
  }

  public ProfilePage shouldBeArchivedCategory(String categoryName) {
    categoryChips(categoryName).shouldHave(Condition.cssClass("MuiChip-colorDefault"));
    return this;
  }

  private SelenideElement categoryChips(String categoryName) {
    return categories.findBy(Condition.text(categoryName)).closest(".MuiButtonBase-root");
  }
}
