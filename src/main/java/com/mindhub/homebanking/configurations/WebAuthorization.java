package com.mindhub.homebanking.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity

@Configuration

//Esta cookie ( JSESSIONIDde forma predeterminada) es un token para sus detalles de autenticación para Spring
public class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override

    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/web/index.html","/web/assets/**","/web/index.js","/web/home.html","/web/home.js").permitAll()
                .antMatchers("/web/**").hasAnyAuthority("CLIENT","ADMIN" )
                .antMatchers(HttpMethod.POST, "/api/clients").permitAll()
                .antMatchers("/admin/**","/rest/**","/h2-console/**").hasAuthority("ADMIN");

        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");



        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        http.csrf().disable();



        //disabling frameOptions so h2-console can be accessed
        //deshabilitar frameOptions para que se pueda acceder a h2-console

        http.headers().frameOptions().disable();

        // if user is not authenticated, just send an authentication failure response
        //si el usuario no está autenticado, simplemente envíe una respuesta de falla de autenticación

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication
        //si el inicio de sesión es exitoso, simplemente borre las banderas que solicitan autenticación

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response
        //si el inicio de sesión falla, simplemente envíe una respuesta de falla de autenticación

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response
        //si el cierre de sesión es exitoso, simplemente envíe una respuesta exitosa

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }





}