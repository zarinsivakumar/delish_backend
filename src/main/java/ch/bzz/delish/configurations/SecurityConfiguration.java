package ch.bzz.delish.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static ch.bbcag.fourchefs_backend.security.SecurityConstants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LoginRepository loginRepository;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, LoginRepository loginRepository) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginRepository = loginRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, API_DOCUMENTATION_URLS).permitAll()
                .antMatchers(HttpMethod.GET, API_PERMITTED_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), loginRepository))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.PUT.name(),
                HttpMethod.POST.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.PATCH.name()
        ));
        configuration.addAllowedOrigin("http://localhost:3000");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
        return source;
    }

    @Bean
    public BasicAuthenticationEntryPoint basicAuthenticationEntryPoint() {

        BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();
        basicAuthenticationEntryPoint.setRealmName("home-project");
        return basicAuthenticationEntryPoint;
    }
}
