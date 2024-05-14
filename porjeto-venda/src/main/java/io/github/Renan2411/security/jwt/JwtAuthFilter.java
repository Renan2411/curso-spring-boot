package io.github.Renan2411.security.jwt;

import io.github.Renan2411.domain.services.impl.UsuarioServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioServiceImp usuarioServiceImp;

    public JwtAuthFilter(JwtService jwtService, UsuarioServiceImp usuarioServiceImp) {
        this.jwtService = jwtService;
        this.usuarioServiceImp = usuarioServiceImp;
    }

    //Aqui interceptamos uma requisição, pegando as informações, e antes de mandar a requisição adiante, colocamos um usuário autenticado dentro da sessão so security
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        //Pegando o token passado por requisição
        String authorization = httpServletRequest.getHeader("Authorization");

        //Verificando o header Authorization
        if (Objects.nonNull(authorization) && authorization.startsWith("Bearer")) {

            /**
             * Aqui pegamos o token
             * Bearer token => ["Bearer", "token"]
             */
            String token = authorization.split(" ")[1];

            if (jwtService.tokenValido(token)) {
                String login = jwtService.obterLoginUsuario(token);
                UserDetails usuario = usuarioServiceImp.loadUserByUsername(login);

                //Fazemos isso para dizer que é uma autenticação de aplicação web
                UsernamePasswordAuthenticationToken usuarioAutenticado = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                usuarioAutenticado.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                //Aqui conseguimos pegar o contexto de segurança da aplicação
                SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);
            }

        }
            filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}
