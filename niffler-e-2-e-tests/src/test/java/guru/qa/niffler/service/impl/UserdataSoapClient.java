package guru.qa.niffler.service.impl;

import guru.qa.jaxb.userdata.AcceptInvitationRequest;
import guru.qa.jaxb.userdata.AllUsersPageRequest;
import guru.qa.jaxb.userdata.AllUsersRequest;
import guru.qa.jaxb.userdata.CurrentUserRequest;
import guru.qa.jaxb.userdata.DeclineInvitationRequest;
import guru.qa.jaxb.userdata.FriendsPageRequest;
import guru.qa.jaxb.userdata.FriendsRequest;
import guru.qa.jaxb.userdata.RemoveFriendRequest;
import guru.qa.jaxb.userdata.SendInvitationRequest;
import guru.qa.jaxb.userdata.UpdateUserRequest;
import guru.qa.jaxb.userdata.UserResponse;
import guru.qa.jaxb.userdata.UsersResponse;
import guru.qa.niffler.api.UserdataSoapApi;
import guru.qa.niffler.api.core.RestClient;
import guru.qa.niffler.api.core.converter.SoapConverterFactory;
import guru.qa.niffler.config.Config;
import io.qameta.allure.Step;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class UserdataSoapClient extends RestClient {

  private static final Config CFG = Config.getInstance();
  private final UserdataSoapApi userdataSoapApi;

  public UserdataSoapClient() {
    super(CFG.userdataUrl(), false, SoapConverterFactory.create("niffler-userdata"), HttpLoggingInterceptor.Level.BODY);
    this.userdataSoapApi = create(UserdataSoapApi.class);
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: currentUserRequest")
  @Nullable
  public UserResponse currentUser(CurrentUserRequest currentUserRequest) throws Exception {
    return userdataSoapApi.currentUserRequest(currentUserRequest)
        .execute()
        .body();
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: updateUserRequest")
  @Nullable
  public UserResponse updateUserInfo(UpdateUserRequest updateUserRequest) throws Exception {
    return userdataSoapApi.updateUserRequest(updateUserRequest)
        .execute()
        .body();
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: allUsersRequest")
  @Nullable
  public UsersResponse allUsersRequest(AllUsersRequest allUsersRequest) throws Exception {
    return userdataSoapApi.allUsersRequest(allUsersRequest)
        .execute()
        .body();
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: allUsersPageRequest")
  @Nullable
  public UsersResponse allUsersPageRequest(AllUsersPageRequest allUsersPageRequest) throws Exception {
    return userdataSoapApi.allUsersPageRequest(allUsersPageRequest)
        .execute()
        .body();
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: friendsRequest")
  @Nullable
  public UsersResponse friendsRequest(FriendsRequest friendsRequest) throws Exception {
    return userdataSoapApi.friendsRequest(friendsRequest)
        .execute()
        .body();
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: friendsPageRequest")
  @Nullable
  public UsersResponse friendsPageRequest(FriendsPageRequest friendsPageRequest) throws Exception {
    return userdataSoapApi.friendsPageRequest(friendsPageRequest)
        .execute()
        .body();
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: removeFriendRequest")
  public void removeFriendRequest(RemoveFriendRequest removeFriendRequest) throws Exception {
    userdataSoapApi.removeFriendRequest(removeFriendRequest)
        .execute();
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: acceptInvitationRequest")
  @Nullable
  public UserResponse acceptInvitationRequest(AcceptInvitationRequest acceptInvitationRequest) throws Exception {
    return userdataSoapApi.acceptInvitationRequest(acceptInvitationRequest)
        .execute()
        .body();
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: declineInvitationRequest")
  @Nullable
  public UserResponse declineInvitationRequest(DeclineInvitationRequest declineInvitationRequest) throws Exception {
    return userdataSoapApi.declineInvitationRequest(declineInvitationRequest)
        .execute()
        .body();
  }

  @Step("Send SOAP POST('/ws') request to niffler-userdata, endpoint: sendInvitationRequest")
  @Nullable
  public UserResponse sendInvitationRequest(SendInvitationRequest sendInvitationRequest) throws Exception {
    return userdataSoapApi.sendInvitationRequest(sendInvitationRequest)
        .execute()
        .body();
  }
}
