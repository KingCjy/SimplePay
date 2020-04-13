package me.kingcjy.simple.memo.config;

import lombok.extern.java.Log;
import me.kingcjy.simple.memo.domain.SocialType;
import me.kingcjy.simple.memo.domain.User;
import me.kingcjy.simple.memo.domain.UserRepository;
import me.kingcjy.simple.memo.oauth.CustomOAuth2Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        http
                .authorizeRequests()
                    .antMatchers("/oauth2/**", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**").permitAll()
                    .antMatchers("/simple").hasAnyAuthority(SocialType.SIMPLE.getRoleType())
                    .antMatchers("/api/v1/memos/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .oauth2Login()
                    .successHandler(new LoginSuccessHandler(userRepository))
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()
                .formLogin().loginPage("/login").successForwardUrl("/dashboard")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/dashboard")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                .and()
                .addFilterBefore(filter, CsrfFilter.class)
                .csrf().disable();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(@Value("${custom.oauth2.simple.client-id}") String simpleClientId) {
        OAuth2ClientProperties oAuth2ClientProperties = new OAuth2ClientProperties();

        List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet()
                .stream()
                .map(client -> getRegistration(oAuth2ClientProperties, client))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        registrations.add(CustomOAuth2Provider.SIMPLE.getBuilder("simple")
                .clientId("simple-user")
                .clientSecret("simple-user-secret")
                .jwkSetUri("test").build());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(OAuth2ClientProperties oAuth2ClientProperties, String client) {

        return null;
    }

    static class LoginSuccessHandler implements AuthenticationSuccessHandler {

        private final UserRepository userRepository;

        public LoginSuccessHandler(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            String email = defaultOAuth2User.getAttribute("uid");
            String name = defaultOAuth2User.getAttribute("name");

            if(userRepository.findByEmail(email).isPresent() == false) {
                User user = User.builder()
                        .email(email)
                        .name(name)
                        .build();

                userRepository.save(user);
            }

            response.sendRedirect("/dashboard");
        }
    }
}
