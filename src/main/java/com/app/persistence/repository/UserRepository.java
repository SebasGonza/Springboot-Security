package com.app.persistence.repository;

import com.app.persistence.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UsuarioEntity, Long>{


    Optional<UsuarioEntity> findUsuarioEntityByUsername(String username);

    // Las dos formas sirven para lo mismo solo que una lo hace automaticamente con el nombre mientras la otra lo hace con el query

/*    @Query("SELECT U FROM UsuarioEntity  u where  u.username = ?")
    Optional<UsuarioEntity> findUsuario(String username);*/
}
