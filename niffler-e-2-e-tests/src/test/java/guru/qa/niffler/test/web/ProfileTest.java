package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.TextReportExtension;
import guru.qa.niffler.jupiter.BrowserExtension;
import guru.qa.niffler.jupiter.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.ProfilePage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BrowserExtension.class, TextReportExtension.class})
public class ProfileTest {

  @Category(
      username = "with_archive",
      archived = true
  )
  @Test
  void archivedCategoryShouldPresentInCategoriesList(CategoryJson category) {
    Selenide.open(LoginPage.URL, LoginPage.class)
        .doLogin(category.username(), category.username())
        .shouldBeLoaded();

    Selenide.open(ProfilePage.URL, ProfilePage.class)
        .enableSwitchShowArchived()
        .categoryShouldBeArchived(category.name());
  }

  @Category(
      username = "with_active",
      archived = false)
  @Test
  void activeCategoryShouldPresentInCategoriesList(CategoryJson category) {
    Selenide.open(LoginPage.URL, LoginPage.class)
        .doLogin(category.username(), category.username())
        .shouldBeLoaded();

    Selenide.open(ProfilePage.URL, ProfilePage.class)
        .enableSwitchShowArchived()
        .categoryShouldBeActive(category.name());
  }
}
