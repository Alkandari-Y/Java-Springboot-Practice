package com.project.kfhpractice.books;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long> {

}
