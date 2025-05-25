package guru.qa.niffler.page;

import com.codeborne.selenide.*;
import guru.qa.niffler.config.Config;

import static com.codeborne.selenide.CollectionCondition.exactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class FriendsPage {
  public static final String URL = Config.getInstance().frontUrl() + "people/friends";

  private final SelenideElement friendsTable = $("#friends");
  private final SelenideElement requestsTable = $("#requests");
  private final SelenideElement tabPanel = $("#simple-tabpanel-friends");

  public FriendsPage shouldBeLoaded() {
    tabPanel.shouldBe(visible);
    return this;
  }

  public FriendsPage shouldBeEmptyFriendsTable() {
    friendsTable.shouldNotBe(visible);
    tabPanel.shouldHave(Condition.innerText("There are no users yet"));
    return this;
  }

  public FriendsPage shouldHaveFriendWithName(String name) {
    friendsTable.$$("td .MuiBox-root p.MuiTypography-body1").shouldHave(exactTextsCaseSensitive(name));
    return this;
  }

  public FriendsPage shouldHaveRequestWithName(String name) {
    requestsTable.$$("td .MuiBox-root p.MuiTypography-body1").shouldHave(exactTextsCaseSensitive(name));
    return this;
  }
}
