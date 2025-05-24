package guru.qa.niffler.jupiter;

import guru.qa.niffler.api.SpendApiClient;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.util.DataUtils;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;
import org.slf4j.Logger;

import java.util.Locale;

import static org.slf4j.LoggerFactory.getLogger;

public class CategoryExtension implements ParameterResolver, BeforeEachCallback, AfterTestExecutionCallback {
  private static final Logger LOGGER = getLogger(CategoryExtension.class);

  public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CategoryExtension.class);
  private final SpendApiClient spendApiClient = new SpendApiClient();

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Category.class)
        .ifPresent(anno -> {
          CategoryJson category = new CategoryJson(
              null,
              DataUtils.randomCategoryName(),
              anno.username(),
              false);

          LOGGER.info("Creating category with json {}", category);
          CategoryJson created = spendApiClient.addCategory(category);
          if (anno.archived()) {
            CategoryJson archivedCategory = new CategoryJson(
                created.id(),
                created.name(),
                created.username(),
                true
            );
            created = spendApiClient.updateCategory(archivedCategory);
          }
          LOGGER.info("Category created {}", created);
          context.getStore(NAMESPACE).put(context.getUniqueId(), created);
        });
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType().isAssignableFrom(CategoryJson.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), CategoryJson.class);
  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    CategoryJson category = context.getStore(NAMESPACE).get(context.getUniqueId(), CategoryJson.class);
    if (!category.archived()) {
      CategoryJson archivedCategory = new CategoryJson(
          category.id(),
          category.name(),
          category.username(),
          true
      );
      CategoryJson categoryJson = spendApiClient.updateCategory(archivedCategory);
      LOGGER.info("Category archived {}", categoryJson);
    }
  }
}
