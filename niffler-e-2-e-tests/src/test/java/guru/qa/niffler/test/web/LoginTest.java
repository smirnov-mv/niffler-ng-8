package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.TextReportExtension;
import guru.qa.niffler.jupiter.extension.BrowserExtension;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BrowserExtension.class, TextReportExtension.class})
public class LoginTest {

  @Test
  void mainPageShouldBeDisplayedAfterSuccessLogin() {
    Selenide.open(LoginPage.URL, LoginPage.class)
        .doLogin("111", "111")
        .shouldBeLoaded();
  }

  @Test
  void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {
    Selenide.open(LoginPage.URL, LoginPage.class)
        .setUserName("111")
        .setPassword("222")
        .clickOnLogInButton()
        .shouldBeVisibleLoginErrorContainer()
        .shouldHaveErrorUrl();
  }
}
