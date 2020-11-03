package com.example.demo1.security;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo1.auth.ApplicationUserService;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	
	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
									 ApplicationUserService applicationUserService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
	}
	


	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
				.csrf().disable()
				.authorizeRequests()
					.antMatchers(HttpMethod.DELETE, "/pedido/*","/produto/*", "/cliente/*").hasRole("ADMIN")
					.antMatchers(HttpMethod.POST, "/pedido/enviar", "/cliente/enviar", "/produto/enviar").hasRole("ADMIN")
					.antMatchers(HttpMethod.PUT, "/pedido/*","/cliente/*","/procuto/*").hasRole("ADMIN")
					.antMatchers(HttpMethod.GET, "/pedido/pedidos").hasRole("ADMIN")
					.antMatchers(HttpMethod.GET, "/cliente/clientes","/produto/produtos").hasAnyRole("ADMIN","ADMINTRAINEE")
					//.permitAll()
					.anyRequest()
					.authenticated()	
				.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.defaultSuccessUrl("/home",true)
					.passwordParameter("password")
					.usernameParameter("username")
				.and()
				.logout()
					.logoutUrl("/logout")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
					.clearAuthentication(true)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

	
	
}

