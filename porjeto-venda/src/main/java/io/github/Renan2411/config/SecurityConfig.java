package io.github.Renan2411.config;

import io.github.Renan2411.domain.services.impl.UsuarioServiceImp;
import io.github.Renan2411.security.jwt.JwtAuthFilter;
import io.github.Renan2411.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImp usuarioServiceImp;

    @Autowired
    private JwtService jwtService;


    //O passwordEnconder é a classe que codifica e descodifica as senhas dos usuários
    @Bean
    public PasswordEncoder passwordEncoder() {
        //Essa classe é capaz de gerar um hash de uma senha, sempre diferentes, mesmo que as senhas sejam iguais
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioServiceImp);
    }


    //Aqui vemos as configurações de autentição, para o usuário logar na aplicação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //Usuário do banco
        auth.userDetailsService(usuarioServiceImp)
                .passwordEncoder(passwordEncoder());

        //Usuário em memória
//        auth.inMemoryAuthentication()
//                .passwordEncoder(this.passwordEncoder())
//                .withUser("Renan")
//                .password(this.passwordEncoder().encode("ramd1122"))
//                .roles("USER", "ADMIN");
    }

    //Aqui verificamos as permissões do usuário logado
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/pedidos/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/produtos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        ;

        //.csrf().disable() //Permite que haja uma segurança entre aplicação em api, necesśario quanto há esse tipo de conexão
        //.authorizeRequests()//Diz que as requisições precissão de atorização
        //.antMatchers("/api/clientes/**").hasRole("ADMIN")//Indica que para acessar a rota api/clientes/** o usuário precisa ter a role ADMIN
        //.antMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN")//Indica que para acessar a rota api/clientes/** o usuário precisa ter qualquer uma das roles passadas por parâmetro
        //.antMatchers("api/clientes/**").hasAnyAuthority("MANTER_USUARIO")//Indica que para acessar a rota api/clientes/** o usuário precisa ter a authority MANTER_USUARIO
        //.antMatchers("/api/clientes/**").permitAll()//Indica que a rota permite qualquer acesso, independente se tiver role ou authority
        //.antMatchers("/api/clientes/**").authenticated()//Indica que para acessar a rota, apenas é necessário estar autenticado
        //.and()//Volta para a raiz do objeto, para o HttpSecurity
        //.formLogin();//Cria o formulário de login padrão do spring security. É possível criar outro formulário de login se necessário
        //.httpBasic();//Permite que façamos uma requisição HTTP passando no header um Authorization com as credenciais, possibilitando chamada de serviços para uma api REST
        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Indica que não temos mais sessão, agora toda a requisição deverá a ter um token de autenticação em seu header
        //.addFilterBefore(); //Adiciona um filtro a ser executado antes da requisição
    }

    //Ignorando URLs do Swagger
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/condiguration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
