package com.Project.quizapp.service;

import com.Project.quizapp.dao.QuestionDao;
import com.Project.quizapp.dao.Quizdao;
import com.Project.quizapp.model.QuestionWrapper;
import com.Project.quizapp.model.Questions;
import com.Project.quizapp.model.Quiz;
import com.Project.quizapp.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service


public class QuizService {

//    @Autowired
//    Quizdao quizdao;
//    @Autowired
//     QuestionDao questionDao;
private final Quizdao quizdao;
    private final QuestionDao questionDao;
    public QuizService(Quizdao quizdao, QuestionDao questionDao) {
        this.quizdao = quizdao;
        this.questionDao = questionDao;
    }


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Questions> questions = questionDao.findRandomQuestions(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizdao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizdao.findById(id);
        List<Questions> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for(Questions q : questionsFromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }
        return  new ResponseEntity<>(questionForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizdao.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int right = 0;
        int i =0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
