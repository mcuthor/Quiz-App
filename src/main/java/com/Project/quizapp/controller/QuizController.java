package com.Project.quizapp.controller;

import com.Project.quizapp.model.QuestionWrapper;
import com.Project.quizapp.model.Response;
import com.Project.quizapp.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("quiz")

public class QuizController {
    private final QuizService quizService;
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category,numQ,title);
    }
     @GetMapping("get/{id}")
      public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        return  quizService.getQuizQuestion(id);
      }
      @PostMapping("submit/{id}")
     public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
          return quizService.calculateResult(id,responses);
      }



}
