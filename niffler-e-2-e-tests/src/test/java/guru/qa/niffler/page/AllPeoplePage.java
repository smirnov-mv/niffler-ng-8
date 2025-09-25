package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.config.Config;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class AllPeoplePage {
  public static final String URL = Config.getInstance().frontUrl() + "people/all";

  private final SelenideElement allPeopleTable = $("#all");

  public AllPeoplePage shouldBeLoaded() {
    allPeopleTable.shouldBe(visible);
    return this;
  }

  public AllPeoplePage shouldHaveInvitationWithName(String name) {
    SelenideElement rowWithInvitedUser = allPeopleTable.$$("tr").find(text(name));
    System.out.println("Row with invited user: " + rowWithInvitedUser.innerHtml());
    rowWithInvitedUser.$(".MuiChip-root").shouldHave(exactTextCaseSensitive("Waiting..."));
    return this;
  }
}
