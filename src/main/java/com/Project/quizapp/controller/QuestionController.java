package com.Project.quizapp.controller;

import com.Project.quizapp.model.Questions;
import com.Project.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
        @GetMapping("AllQuestions")
          public List<Questions> getAllQuestions(){
              return questionService.getAllQuestions();
          }
          @GetMapping("category/{category}")
          public List<Questions> getByCategory( @PathVariable  String category){
            return questionService.getByCategory(category);

          }
    @PostMapping("add")
    public Questions addQuestion(@RequestBody Questions question) {

        return questionService.addQuestion(question);
    }
}
