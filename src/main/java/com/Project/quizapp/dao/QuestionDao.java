package com.Project.quizapp.dao;

import com.Project.quizapp.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions,Integer> {


    List<Questions> findByCategory(String category);

    @Query(value = "SELECT * FROM questions q WHERE q.category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<Questions> findRandomQuestions(String category, int numQ);


}
