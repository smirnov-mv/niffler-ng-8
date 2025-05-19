package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverConditions;
import guru.qa.niffler.config.Config;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;

public class LoginPage {
  public static final String URL = Config.getInstance().authUrl() + "login";

  private final SelenideElement usernameInput = $("input[name='username']");
  private final SelenideElement passwordInput = $("input[name='password']");
  private final SelenideElement submitBtn = $("button[type='submit']");
  private final SelenideElement errorContainer = $(".form__error-container");

  public MainPage doLogin(String username, String password) {
    setUserName(username);
    setPassword(password);
    btnLogInClick();
    return new MainPage();
  }

  public LoginPage btnLogInClick() {
    submitBtn.click();
    return this;
  }

  public LoginPage setPassword(String password) {
    passwordInput.setValue(password);
    return this;
  }

  public LoginPage setUserName(String username) {
    usernameInput.setValue(username);
    return this;
  }

  public LoginPage shouldBeVisibleLoginErrorContainer() {
    errorContainer.shouldBe(visible);
    return this;
  }

  public LoginPage shouldHaveErrorUrl() {
    webdriver().shouldHave(WebDriverConditions.url(URL + "?error"));
    return this;
  }
}
