package io.github.Renan2411.security.jwt;

import io.github.Renan2411.VendasApplication;
import io.github.Renan2411.domain.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao; //O tempo que o token fica válido após gerado

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura; //Uma chave que irá servir par gerar o token e decodificá-lo

    public String gerarToken(Usuario usuario){
        long expString = Long.parseLong(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts
                .builder()
                .setSubject(usuario.getLogin()) //Setando o Subject
                .setExpiration(data) //Setando a experiração
                //.setClaims() Adiciona mais informações no token
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura) //Assina o token, com a chave e o algoritimo utilizado;
                .compact(); //Retorna a String, o nosso token
    }

    //Esse método pega as claims, informações do token. Pode lançar o erro de token expirado
    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()//Ele quem decodifica o token
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token){
        try{
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }

//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class);
//        JwtService jwt = context.getBean(JwtService.class);
//        Usuario usuario = Usuario.builder().login("Renan").build();
//        String token = jwt.gerarToken(usuario);
//        System.out.println(token);
//
//
//        System.out.println("O token é valido: " + jwt.tokenValido(token));
//        System.out.println("Usuário: " + jwt.obterLoginUsuario(token));
//
//    }

}
