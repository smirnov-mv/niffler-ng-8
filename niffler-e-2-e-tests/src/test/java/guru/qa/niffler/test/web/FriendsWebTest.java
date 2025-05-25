package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.TextReportExtension;
import guru.qa.niffler.jupiter.extension.BrowserExtension;
import guru.qa.niffler.jupiter.extension.UsersQueueExtension;
import guru.qa.niffler.page.AllPeoplePage;
import guru.qa.niffler.page.FriendsPage;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static guru.qa.niffler.jupiter.extension.UsersQueueExtension.*;
import static guru.qa.niffler.jupiter.extension.UsersQueueExtension.UserType.Type.*;

@ExtendWith({BrowserExtension.class, TextReportExtension.class})
public class FriendsWebTest {

  @Test
  @ExtendWith(UsersQueueExtension.class)
  void friendShouldBePresentInFriendsTable(@UserType(WITH_FRIEND) StaticUser user) {
    Selenide.open(LoginPage.URL, LoginPage.class)
        .doLogin(user.username(), user.password())
        .shouldBeLoaded();

    Selenide.open(FriendsPage.URL, FriendsPage.class)
        .shouldBeLoaded()
        .shouldHaveFriendWithName("111");
  }

  @Test
  @ExtendWith(UsersQueueExtension.class)
  void friendsTableShouldBeEmptyForNewUser(@UserType(EMPTY) StaticUser user) {
    Selenide.open(LoginPage.URL, LoginPage.class)
        .doLogin(user.username(), user.password())
        .shouldBeLoaded();

    Selenide.open(FriendsPage.URL, FriendsPage.class)
        .shouldBeLoaded()
        .shouldBeEmptyFriendsTable();
  }

  @Test
  @ExtendWith(UsersQueueExtension.class)
  void incomeInvitationBePresentInFriendsTable(@UserType(WITH_INCOME_REQUEST) StaticUser user) {
    Selenide.open(LoginPage.URL, LoginPage.class)
        .doLogin(user.username(), user.password())
        .shouldBeLoaded();

    Selenide.open(FriendsPage.URL, FriendsPage.class)
        .shouldBeLoaded()
        .shouldHaveRequestWithName("with_outcome_request");
  }

  @Test
  @ExtendWith(UsersQueueExtension.class)
  void outcomeInvitationBePresentInAllPeoplesTable(@UserType(WITH_OUTCOME_REQUEST) StaticUser user) {
    Selenide.open(LoginPage.URL, LoginPage.class)
        .doLogin(user.username(), user.password())
        .shouldBeLoaded();

    Selenide.open(AllPeoplePage.URL, AllPeoplePage.class)
        .shouldBeLoaded()
        .shouldHaveInvitationWithName("with_income_request");
  }
}
