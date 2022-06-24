package com.example.filrouge_loubna.config;

import com.example.filrouge_loubna.dto.model.UserDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.serializers.UserSerializer;
import com.example.filrouge_loubna.services.UserService.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // auorisation & authentification :
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
                .permitAll()
                .antMatchers("/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html**",
                        "/webjars/**",
                        "/swagger.json",
                        "/token/login",
                        "/user/villes",
                        "/user/save",
                        "/userExtraInfo/specialites",
                        "/userExtraInfo/save",
                        "/message/save",
                        "/medicalOffice/search"
                )
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/token/login");
        //-------------------
        filter.setAuthenticationSuccessHandler(this::successHandler);
        return filter;
    }

    //-----------------------------------------------------------------------------------------------
    private void successHandler(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException {
        String email=((User) auth.getPrincipal()).getUsername();
        String password=((User) auth.getPrincipal()).getPassword();

        com.example.filrouge_loubna.entities.User u=userService.getByEmail(email);

        req.getSession(true).setAttribute("connectedUser",u);
        res.addHeader("UserId",u.getId().toString());

        //-----------------------------------------------------------------------------------------------

    }
}
