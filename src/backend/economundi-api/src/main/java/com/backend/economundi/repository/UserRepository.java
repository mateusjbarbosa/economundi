/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.repository;

import com.backend.economundi.database.dao.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
   
    UserEntity findByEmail(String email); 
    
    UserEntity findByEmailVerificationToken(String token);
}
