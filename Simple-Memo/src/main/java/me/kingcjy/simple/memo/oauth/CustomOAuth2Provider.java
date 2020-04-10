package me.kingcjy.simple.memo.oauth;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public enum CustomOAuth2Provider {

    SIMPLE {
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(
                    registrationId,
                    ClientAuthenticationMethod.POST,
                    "http://local.simple-memo.com:8082/login/oauth2/code/{registrationId}"
            );

            builder.scope("read");
            builder.authorizationUri("http://local.simple.com:8081/oauth/authorize");
            builder.tokenUri("http://local.simple.com:8081/oauth/token");
            builder.userInfoUri("http://local.simple-user.com:5000/v1/users/profile");
            builder.userNameAttributeName("id");
            builder.clientName("simple");
            builder.clientSecret("simple-user-secret");
            return builder;
        }
    };

    protected final ClientRegistration.Builder getBuilder(String registrationId, ClientAuthenticationMethod method, String redirectUrl) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUriTemplate(redirectUrl);
        return builder;
    }

    public abstract ClientRegistration.Builder getBuilder(String registrationId);
}
