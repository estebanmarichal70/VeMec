package com.vemec.api.services;

import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.usuario.Usuario;
import com.vemec.api.models.usuario.UsuarioRepository;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class VemecUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> u = usuarioRepository.findByUsername(username);

        if(u.isPresent()){
            return new User(u.get().getUsername(), u.get().getPassword(),
                    new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("No se encontro el usuario: "+ username);
        }
    }

    public Usuario registerNewUser(Map<String, String> payload) throws Exception {
        try {
            Usuario usuario = new Usuario();
            Mappers.mapToUsuario(payload, usuario);

            if(usuario.getPassword() != null && usuario.getUsername() != null){
                String pass = usuario.getPassword();
                usuario.setPassword(passwordEncoder.encode(pass));
                usuarioRepository.save(usuario);
                return usuario;
            }else{
                throw new Exception("El campo username y password no deben ser vacios");
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
}