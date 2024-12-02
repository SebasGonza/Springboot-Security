package com.app;

import com.app.persistence.entities.PermissionEntity;
import com.app.persistence.entities.RoleEntity;
import com.app.persistence.entities.RoleEnum;
import com.app.persistence.entities.UsuarioEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			/*Creación de permisos*/
			PermissionEntity createPermission = new PermissionEntity().builder().
					name("CREATE").build();

			PermissionEntity readPermission = new PermissionEntity().builder().
					name("READ").build();

			PermissionEntity updatePermission = new PermissionEntity().builder().
					name("UPDATE").build();

			PermissionEntity deletePermission = new PermissionEntity().builder().
					name("DELETE").build();

			PermissionEntity refactorPermission = new PermissionEntity().builder().
					name("REFACTOR").build();

			/*Creacion de roles*/

			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionsList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionsList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionsList(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper= RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionsList(Set.of(createPermission,readPermission, updatePermission, deletePermission, refactorPermission))
					.build();


			/*Creacion de Usuarios*/

			UsuarioEntity userPirlo = UsuarioEntity.builder()
					.username("pirlo")
					.password("$2a$10$rr.LYOJ6WhfHa8PkP5jOvOk2jx0QOAxU033sege2lR5bKwUkdk3CO")
					.isEnable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UsuarioEntity userDaniel= UsuarioEntity.builder()
					.username("daniel")
					.password("$2a$10$rr.LYOJ6WhfHa8PkP5jOvOk2jx0QOAxU033sege2lR5bKwUkdk3CO")
					.isEnable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UsuarioEntity userInvitado= UsuarioEntity.builder()
					.username("invitado1")
					.password("$2a$10$rr.LYOJ6WhfHa8PkP5jOvOk2jx0QOAxU033sege2lR5bKwUkdk3CO")
					.isEnable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UsuarioEntity userAnyi= UsuarioEntity.builder()
					.username("anyi")
					.password("$2a$10$rr.LYOJ6WhfHa8PkP5jOvOk2jx0QOAxU033sege2lR5bKwUkdk3CO")
					.isEnable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userPirlo, userDaniel, userInvitado, userAnyi));
		};
	}

}
