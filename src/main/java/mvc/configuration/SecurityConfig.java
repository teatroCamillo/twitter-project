package mvc.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/user-page").hasAuthority("USER")
                    .antMatchers("/addadmin","/admins","/admin-success")
                        .hasAnyAuthority("ROLE_ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/users")
                        .hasAnyAuthority("ROLE_ADMIN")
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(jdbcTemplate.getDataSource())
                .usersByUsernameQuery("SELECT login, password, 'true' as enable FROM users WHERE login = ?")
                .authoritiesByUsernameQuery("SELECT login, role FROM users WHERE login = ?")
                .passwordEncoder(passwordEncoder);

//        auth.jdbcAuthentication()
//                .usersByUsernameQuery("SELECT LOGIN, PASSWORD, 1 FROM ADMINS WHERE LOGIN=?")
//                .authoritiesByUsernameQuery("SELECT A.LOGIN, 'ROLE_ADMIN', 1 FROM ADMINS A WHERE A.LOGIN=?")
//                .dataSource(jdbcTemplate.getDataSource())
//                .passwordEncoder(passwordEncoder);
    }

}
