package ab.diginamic.securityStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //ULR authorizations
                .authorizeRequests()
                //The order of the rules matters and the more specific rules should go first
                .antMatchers("/", "/index.html","/h2-console/**").permitAll()//Allow any user to access path / and /index.html
                .anyRequest().authenticated()

                // Authentication mode
                .and().formLogin()//redirect to /login HTML page and then to the resource after successfull authentication
                .and().httpBasic() //for web API Auth
                .and().csrf().disable();//disable csrf protection

        /**
         * This configuration is needed to view the /h2-console with out any issues.
         * Since H2 Console uses frames to display the UI, we need to allow the frames.
         * Otherwise since by default Spring Security consider X-Frame-Options: DENY
         * to avoid Clickjacking attacks, the /h2-console will not work properly.
         * More details can be found at
         * https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/headers.html#headers-frame-options
         */
        httpSecurity.headers().frameOptions().sameOrigin();
    }

    //InMemoryUser
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(getPasswordEncoder().encode("secret")).authorities("ADMIN", "USER").and()
                .withUser("user").password(getPasswordEncoder().encode("user")).authorities("USER").and()
                .withUser("jdoe").password(getPasswordEncoder().encode("unknown")).disabled(true).authorities("USER");
    }*/

    //H2 Database User
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(getPasswordEncoder()).dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");
    }

    //@Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
