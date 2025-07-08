package com.project.kfhpractice.authors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {

    public List<AuthorEntity> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
