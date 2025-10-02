package com.Project.quizapp.service;

import com.Project.quizapp.dao.QuestionDao;
import com.Project.quizapp.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionService {
    @Autowired
   QuestionDao  questionDao;
    public List<Questions> getAllQuestions() {
         return  questionDao.findAll();
    }

    public List<Questions> getByCategory(String category) {
        return  questionDao.findByCategory(category);
    }


    public Questions addQuestion(Questions question) {
        return questionDao.save(question);
    }
}
