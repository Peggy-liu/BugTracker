package com.bug.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{
	
	//we are using the same user detail service here
	//expose beans for the use of spring security

	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserDetailsService userDetailsService;
		
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(encoder);
		return auth;
	}


	@Configuration
	@Order(2)
	public static class UserConfigurationAdapter extends WebSecurityConfigurerAdapter {


	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	          .antMatcher("/user/**")      
	          .authorizeRequests()
	            .antMatchers("/user/register","/user/checkName").permitAll()
	          	.anyRequest().hasAnyRole("USER", "ADMIN")

	          .and()
	          .formLogin()
	          .loginPage("/user/login")
	          .permitAll()           //have to add this otherwise will end up in endless redirected loop!
	          .defaultSuccessUrl("/user/home")
	          .and()
	          .logout()
	          .permitAll()
	          .logoutUrl("/user/logout")
	          .logoutSuccessUrl("/logoutSuccess")
	          .and()
	          .exceptionHandling().accessDeniedPage("/accessDenied");
	          
	           
	          
	    }
	}
	
	@Configuration
	@Order(1)
	public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {
	 
	    public AdminConfigurationAdapter() {
	        super();
	    }

	    protected void configure(HttpSecurity http) throws Exception {
	        http
	          .antMatcher("/admin/**")
	          .authorizeRequests()
	          .antMatchers("/admin/**").hasRole("ADMIN")
	          	.and()
	          .formLogin()
	          .loginPage("/admin/login")
	          .permitAll()
	          .and()
	          .logout()
	          .permitAll()
	          .logoutUrl("/admin/logout")
	          .logoutSuccessUrl("/logoutSuccess")
	          .permitAll()
	          .and()
	          .exceptionHandling().accessDeniedPage("/accessDenied");
	          
	          
	    }
	}
}