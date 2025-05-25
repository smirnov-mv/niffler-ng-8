package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.github.javafaker.Faker;
import guru.qa.niffler.jupiter.extension.BrowserExtension;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.RegisterPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Locale;

@ExtendWith({BrowserExtension.class, TextReportExtension.class})
public class RegistrationTest {
  private final Faker faker = new Faker(Locale.ENGLISH);

  @Test
  void shouldRegisterNewUser() {
    String username = faker.name().username() + System.currentTimeMillis();
    String password = faker.internet().password(3, 12);
    Selenide.open(RegisterPage.URL, RegisterPage.class)
        .doRegister(username, password)
        .clickSignIn();

    new LoginPage()
        .doLogin(username, password)
        .shouldBeLoaded();
  }

  @Test
  void shouldNotRegisterUserWithExistingUsername() {
    String username = faker.name().username() + System.currentTimeMillis();
    String password = faker.internet().password(3, 12);
    Selenide.open(RegisterPage.URL, RegisterPage.class)
        .doRegister(username, password)
        .clickSignIn();

    Selenide.open(RegisterPage.URL, RegisterPage.class)
        .doRegister(username, password)
        .shouldHaveUserAlreadyExistsError(username);
  }

  @Test
  void shouldShowErrorIfPasswordAndConfirmPasswordAreNotEqual() {
    String username = faker.name().username() + System.currentTimeMillis();
    Selenide.open(RegisterPage.URL, RegisterPage.class)
        .setUsername(username)
        .setPassword(faker.internet().password(3, 6))
        .setPasswordSubmit(faker.internet().password(7, 12))
        .clickSignUp()
        .shouldHavePasswordsNotEqualError();
  }
}
