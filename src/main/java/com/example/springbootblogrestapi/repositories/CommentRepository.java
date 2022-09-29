package com.example.springbootblogrestapi.repositories;

import com.example.springbootblogrestapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
