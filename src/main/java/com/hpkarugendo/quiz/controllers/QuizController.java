package com.hpkarugendo.quiz.controllers;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hpkarugendo.quiz.global.Databank;
import com.hpkarugendo.quiz.models.Answer;
import com.hpkarugendo.quiz.models.Question;
import com.hpkarugendo.quiz.models.Quiz;

@Controller
public class QuizController {
	
	@GetMapping("/get-quiz")
	public String getQuiz(Model m) {
		if(Databank.quizes.size() < 5) {
			m.addAttribute("quizNo", (Databank.quizQuestions.size()+1));
			Question q = Databank.qiestionsBank.get(new Random().nextInt(Databank.qiestionsBank.size()));
			Quiz qz = new Quiz();
			qz.setQuestion(q.getId());
			for(Answer a : q.getAnswers()) {
				if(a.getCorrect()) {
					qz.setCorrect(a.getLabel());
				}
			}
			m.addAttribute("question", q);
			Databank.quizQuestions.add(q);
			Databank.qiestionsBank.remove(q);
			m.addAttribute("quiz", qz);
			return "quiz";
		}
		
		return "redirect:/result";
	}
	
	@PostMapping("/post-quiz")
	public String postQuiz(Quiz quiz) {
		Quiz posted = quiz;
		Databank.quizes.add(posted);
		System.out.println(quiz.toString());
		return "redirect:/get-quiz";
	}
	
	@GetMapping("/result")
	public String getResult(Model m) {
		double total = 0;
		double score = 0;
		for(Quiz q: Databank.quizes) {
			if(q.getAnswer() == q.getCorrect()) {
				total = total + 1.0;
			}
		}
		score = (total/5*100);
		m.addAttribute("score", score);
		Databank.quizes.clear();
		Databank.quizQuestions.clear();
		
		return "result";
	}

}
