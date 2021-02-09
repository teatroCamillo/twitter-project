package mvc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfigurerAdapter
 * This is a class that allows customization to WebSecurity and HttpSecurity.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * It allows configuring web based security for specific http requests.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/user-page", "/follow").hasAuthority("USER")
                    .antMatchers("/addadmin","/admins","/admin-success")
                        .hasAnyAuthority("ADMIN")
                    .and()
                        .csrf().disable()
                        .headers().frameOptions().disable()
                    .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/user-page")
                        .and()
                    .logout()
                        .deleteCookies("JSESSIONID");
    }

    /**
     * Allows for easily building in memory authentication, LDAP authentication, JDBC based authentication, adding UserDetailsService,      * and adding AuthenticationProvider's.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.eraseCredentials(false);
        auth.jdbcAuthentication()
                .dataSource(jdbcTemplate.getDataSource())
                .usersByUsernameQuery("SELECT login, password, 'true' as enable FROM users WHERE login = ?")
                .authoritiesByUsernameQuery("SELECT login, role FROM users WHERE login = ?")
                .passwordEncoder(passwordEncoder);
    }

}
