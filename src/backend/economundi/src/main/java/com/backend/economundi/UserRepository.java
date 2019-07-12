package com.backend.economundi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.economundi.entity.User;
/*
 * Crud Repository Hibernate banco de funções para manipulação do Banco  
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);// Função para buscar o usuario pelo email 

}
