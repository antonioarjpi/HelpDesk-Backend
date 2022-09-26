package com.devsimple.helpdesk.repository;

import com.devsimple.helpdesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
}
