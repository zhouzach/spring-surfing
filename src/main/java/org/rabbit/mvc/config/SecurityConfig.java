package org.rabbit.mvc.config;

import org.rabbit.mvc.data.SpitterRepository;
import org.rabbit.mvc.security.SpitterUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //    @Autowired
    SpitterRepository spitterRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new SpitterUserService(spitterRepository));
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutSuccessUrl("/").logoutUrl("/signout")
                .and()
                .rememberMe().tokenValiditySeconds(2419200).key("spittrKey")
                .and()
                .httpBasic().realmName("Spittr")
                .and()
                .authorizeRequests()
                .antMatchers("/spitters/me").authenticated()
                .regexMatchers("/spitters/.*").authenticated()
                .antMatchers(HttpMethod.POST, "/spittles")
                .access("hasRole('ROLE_SPITTER') and hasIpAddress('192.168.1.2')")
//                .hasRole("SPITTER")
//                .hasAuthority("ROLE_SPITTER")
                .anyRequest().permitAll()
                .and()
                .requiresChannel()
                .antMatchers("/spitter/form").requiresSecure()
                .antMatchers("/").requiresInsecure();
    }

}
