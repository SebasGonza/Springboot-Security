package com.app.service;

import com.app.persistence.entities.UsuarioEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioEntity usuarioEntity = userRepository.findUsuarioEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        usuarioEntity.getRoles()
                .forEach(roleEntity -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(roleEntity.getRoleEnum().name()))));

        usuarioEntity.getRoles().stream().flatMap(roleEntity -> roleEntity.getPermissionsList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(
                usuarioEntity.getUsername(),
                usuarioEntity.getPassword(),
                usuarioEntity.isEnable(),
                usuarioEntity.isAccountNonExpired(),
                usuarioEntity.isCredentialsNonExpired(),
                usuarioEntity.isAccountNonLocked(),
                authorityList
        );
    }
}
