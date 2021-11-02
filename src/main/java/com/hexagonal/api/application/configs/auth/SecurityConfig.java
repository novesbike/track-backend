package com.hexagonal.api.application.configs.auth;

import com.hexagonal.api.application.adapters.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String LOGIN_URL = "/auth/login";
  private static final String[] PUBLIC_MATCHERS = {
          "/reset_password/**",
          "/swagger-ui.html",
          "/v1/activities/stats/**",
          "/v1/**"
  };
  private static final String[] PUBLIC_MATCHERS_POST = {
          "/v1/users/register",
          "/v1/users/activate",
          "auth/forgot-password/**"
  };
  private final Environment env;
  private final UserDetailsService userDetailsService;
  private final JWTUtil jwtUtil;

  @Autowired
  public SecurityConfig(Environment env, UserDetailServiceImpl userDetailsService, JWTUtil jwtUtil) {
    this.env = env;
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
  }


  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/api/swagger-ui/**", "/api/v3/api-docs/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
      http.headers().frameOptions().disable().and()
              .authorizeRequests().antMatchers("/h2-console/**").permitAll();
    }

    http
            .cors().and().csrf().disable().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            .authorizeRequests()
            .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
            .antMatchers(PUBLIC_MATCHERS).permitAll()
            .anyRequest().authenticated()
            .and()

            .addFilter(jwtAuthorizationFilter())
            .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }

  public JWTAuthenticationFilter jwtAuthorizationFilter() throws Exception {
    JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager(), jwtUtil);
    jwtAuthenticationFilter.setFilterProcessesUrl(LOGIN_URL);
    return jwtAuthenticationFilter;
  }


  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}