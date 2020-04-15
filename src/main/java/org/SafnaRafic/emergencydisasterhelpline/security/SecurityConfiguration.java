package org.SafnaRafic.emergencydisasterhelpline.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPrincipalDetailsService userPrincipalDetailsService;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService){
        this.userPrincipalDetailsService=userPrincipalDetailsService;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth. authenticationProvider(authenticationProvider());
//                inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin"))
//
//                .authorities("ACCESS_TEST1","ACCESS_TEST2","ROLE_ADMIN")
//                .and()
//                .withUser("coordinator").password(passwordEncoder().encode("coordinator"))
//
//                .authorities("ACCESS_TEST1","ROLE_COORDINATOR")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("user")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
//                .anyRequest().authenticated()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/coordinator/**").hasAnyRole("COORDINATOR","ADMIN")
                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
                .antMatchers("/api/public/users").hasRole("ADMIN")

                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/login").permitAll()
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .rememberMe().tokenValiditySeconds(259200).key("mySecret!").rememberMeParameter("checkRememberMe");
//                .httpBasic();
    }
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }

}