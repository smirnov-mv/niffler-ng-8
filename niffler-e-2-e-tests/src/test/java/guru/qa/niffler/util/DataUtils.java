package guru.qa.niffler.util;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataUtils {
  private final static Faker FAKER = new Faker(Locale.of("ru"));

  public static String randomCategoryName() {
    return FAKER.harryPotter().spell() + System.currentTimeMillis();
  }
}
