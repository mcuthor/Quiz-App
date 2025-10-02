package com.Project.quizapp.dao;

import com.Project.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface Quizdao extends JpaRepository<Quiz,Integer> {
}
