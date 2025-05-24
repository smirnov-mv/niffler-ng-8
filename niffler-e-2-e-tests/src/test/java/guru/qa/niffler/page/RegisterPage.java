package guru.qa.niffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.config.Config;

import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
  public static final String URL = Config.getInstance().authUrl() + "register";

  private final SelenideElement usernameInput = $("#username");
  private final SelenideElement passwordInput = $("#password");
  private final SelenideElement passwordSubmitInput = $("#passwordSubmit");
  private final SelenideElement btnFormSignUp = $("button[type='submit']");
  private final SelenideElement btnFormSignIn = $(".form_sign-in");

  public RegisterPage setUsername(String username) {
    usernameInput.setValue(username);
    return this;
  }

  public RegisterPage setPassword(String password) {
    passwordInput.setValue(password);
    return this;
  }

  public RegisterPage setPasswordSubmit(String password) {
    passwordSubmitInput.setValue(password);
    return this;
  }

  public LoginPage clickSignIn() {
    btnFormSignIn.click();
    return new LoginPage();
  }

  public RegisterPage clickSignUp() {
    btnFormSignUp.click();
    return this;
  }

  public RegisterPage doRegister(String username, String password) {
    setUsername(username);
    setPassword(password);
    setPasswordSubmit(password);
    clickSignUp();
    return this;
  }

  public RegisterPage shouldHaveUserAlreadyExistsError(String username) {
    String expectedError = "Username `%s` already exists".formatted(username);
    usernameInput.closest("label").$(".form__error")
        .shouldHave(Condition.exactTextCaseSensitive(expectedError));
    return this;
  }

  public RegisterPage shouldHavePasswordsNotEqualError() {
    String expectedError = "Passwords should be equal";
    passwordInput.closest("label").$(".form__error")
        .shouldHave(Condition.exactTextCaseSensitive(expectedError));
    return this;
  }
}
