package guru.qa.niffler.service.impl;

import guru.qa.niffler.model.UserJson;
import guru.qa.niffler.service.UsersClient;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class UsersApiClient implements UsersClient {
  @Nonnull
  @Override
  public UserJson createUser(String username, String password) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<UserJson> addIncomeInvitation(UserJson targetUser, int count) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<UserJson> addOutcomeInvitation(UserJson targetUser, int count) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<UserJson> addFriend(UserJson targetUser, int count) {
    throw new UnsupportedOperationException();
  }
}
