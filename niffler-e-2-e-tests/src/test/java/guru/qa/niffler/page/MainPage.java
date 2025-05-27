package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.component.StatComponent;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@ParametersAreNonnullByDefault
public class MainPage extends BasePage<MainPage> {

  private final SelenideElement header = $("#root header");
  private final SelenideElement headerMenu = $("ul[role='menu']");
  private final ElementsCollection tableRows = $("#spendings tbody").$$("tr");
  private final SelenideElement spendingTable = $("#spendings");
  private final SelenideElement menu = $("ul[role='menu']");
  private final ElementsCollection menuItems = menu.$$("li");

  private final StatComponent statComponent = new StatComponent();

  @Nonnull
  public StatComponent getStatComponent() {
    return statComponent;
  }

  @Nonnull
  public FriendsPage friendsPage() {
    header.$("button").click();
    menuItems.find(text("Friends")).click();
    return new FriendsPage();
  }

  @Nonnull
  public PeoplePage allPeoplesPage() {
    header.$("button").click();
    menuItems.find(text("All People")).click();
    return new PeoplePage();
  }

  @Nonnull
  public EditSpendingPage editSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).$$("td").get(5).click();
    return new EditSpendingPage();
  }

  @Nonnull
  public void checkThatTableContainsSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).should(visible);
  }

  @Nonnull
  public MainPage checkThatPageLoaded() {
    statComponent.self.should(visible).shouldHave(text("Statistics"));
    spendingTable.should(visible).shouldHave(text("History of Spendings"));
    return this;
  }
}
