package com.api.wissenapi.repositories;

import com.api.wissenapi.models.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {

     boolean existsByEmail(String email);


    UserModel findFirstByEmail(String email);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByName(String name);
}
