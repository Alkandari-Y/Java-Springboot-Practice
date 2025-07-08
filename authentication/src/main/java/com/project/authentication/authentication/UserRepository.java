package com.project.authentication.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByUsername(String username);
}