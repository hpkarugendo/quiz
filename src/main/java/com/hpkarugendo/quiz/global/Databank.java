package com.hpkarugendo.quiz.global;

import java.util.ArrayList;
import java.util.List;

import com.hpkarugendo.quiz.models.Question;
import com.hpkarugendo.quiz.models.Quiz;

public abstract class Databank {
	public static List<Question> qiestionsBank = new ArrayList<>();
	public static List<Quiz> quizes = new ArrayList<>();
	public static List<Question> quizQuestions = new ArrayList<Question>();
}
