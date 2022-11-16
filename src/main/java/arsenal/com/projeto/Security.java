package arsenal.com.projeto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Security  extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email as email, senha as pass, 1 as enable from usuarios where email=?")
                .authoritiesByUsernameQuery(
                        "select email as email, 'usuario' as authority from usuarios where email=?")
                .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/sessao/**")
                .hasAnyAuthority("usuario").antMatchers("/sessao/**").authenticated().and().formLogin()
                .loginPage("/login").failureUrl("/login?error=true").loginProcessingUrl("/sessao/inicio")
                .defaultSuccessUrl("/sessao/inicio").usernameParameter("email").passwordParameter("pass").and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/sessao/logout"))
                .logoutSuccessUrl("/login?logout=true").deleteCookies("JSESSIONID").and().exceptionHandling()
                .accessDeniedPage("/negado").and().csrf().disable();
    }
    
    




}