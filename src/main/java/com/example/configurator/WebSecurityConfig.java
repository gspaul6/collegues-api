package com.example.configurator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import dev.spring.security.filter.JWTAuthorizationFilter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	// @Autowired
	// JWTAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// toutes les requêtes sont permises
		// => aucune n'est soumise à authentification
		// Step1: http.authorizeRequests().anyRequest().permitAll();
		// step2:http.csrf().disable().authorizeRequests().anyRequest().permitAll();

		http
				// désactivation de la protection CSRF
				// non utilisée dans le cadre d'une Web API
				.csrf().disable().authorizeRequests()

				// un GET /exemples n'est pas soumise à authentification
				.antMatchers(HttpMethod.GET, "/collegues").permitAll().antMatchers("/h2-console/**").permitAll()
				.antMatchers("/auth").permitAll().antMatchers("/me").hasRole("ADMIN")
				// Les autres requêtes sont soumises à authentification
				.anyRequest().authenticated()

				// accès à la console h2 sans authentification
				.and().headers().frameOptions().disable().and()
				// .addFilterBefore(jwtAuthorizationFilter,
				// UsernamePasswordAuthenticationFilter.class)
				// Gestion de la déconnexion
				// /POST /logout
				.logout()
				// en cas de succès un OK est envoyé (à la place d'une
				// redirection vers /login)
				.logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
				// suppression du cookie d'authentification
				.deleteCookies(TOKEN_COOKIE);

	}

}