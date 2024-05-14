package io.github.Renan2411.domain.services.impl;

import io.github.Renan2411.domain.entities.Usuario;
import io.github.Renan2411.domain.repositories.UsuarioRepository;
import io.github.Renan2411.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//UserDetailsService é uma interface do spring security, e sua implementaçao define como será feito o carregamento de usuários da base de dados
//basicamente carrega o usuário para dentro da aplicação
@Service
public class UsuarioServiceImp implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        return this.usuarioRepository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());

        //Aqui verificamos a senha, o 1° é a senha comparada, e o 2° é a senha hasheada
        boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());

        if (!senhasBatem) {
            throw new SenhaInvalidaException();
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

        String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();

    }
}
