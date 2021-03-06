package com.recomendacao.investimento.configuration;

import com.recomendacao.investimento.security.FiltroAutenticacaoJWT;
import com.recomendacao.investimento.security.FiltroAutorizacaoJWT;
import com.recomendacao.investimento.security.JWTUtil;
import com.recomendacao.investimento.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    private static final String[] PUBLIC_MEATCHERS_GET = {
            "/investimentos",
            "/investimentos/**",
            "/investidores",
            "/questionario",
            "/questionario/**",
            "/resposta",
            "/resposta/**"
    };
    private static final String[] PUBLIC_MEATCHERS_POST = {
            "/investidores",
            "/investidores/**",
            "/userinvest/**",
            "/questionario",
            "/questionario/**",
            "/resposta",
            "/resposta/**"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.cors().and().csrf().disable();//desabilitar validação de token nos metodos de post e utiliza a configuração de URL definidas mais abaixo
        // http.csrf().disable();//desabilitar validação de token nos metodos de post

        http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MEATCHERS_GET).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_MEATCHERS_POST).permitAll()
                .anyRequest().authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//informa que a aplicação é STATELESS, evitando a geração de token CSRF, já que nao será usado

        http.addFilter(new FiltroAutenticacaoJWT(jwtUtil,authenticationManager()));
        http.addFilter(new FiltroAutorizacaoJWT(authenticationManager(), usuarioService, jwtUtil));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(bCryptPasswordEncoder());

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues()); //informa ao sistema quais urls estão disponíveis para serem acessadas por outros domínios
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
