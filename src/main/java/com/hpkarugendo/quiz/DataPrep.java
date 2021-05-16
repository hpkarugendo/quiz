package com.hpkarugendo.quiz;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.hpkarugendo.quiz.global.Databank;
import com.hpkarugendo.quiz.models.Answer;
import com.hpkarugendo.quiz.models.Question;

@Component
public class DataPrep implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		try {
            File f = new ClassPathResource("static/files/data.txt").getFile();
            String data = "";
            System.out.println("FILE SIZE IS: " + f.length());
            System.out.println("PREPARING DATA...");
            data = new String(Files.readAllBytes(Paths.get(f.toURI())));
            String[] breakUpQuestions = data.split("[|]");
            Question q0;
            Answer a0, b0, c0, d0;
            for (int n = 0; n < breakUpQuestions.length; n++) {
                if (n % 2 != 0) {
                    String[] eachQuestion = breakUpQuestions[n].split("[:]");
                    String q, a, b, c, d, an;
                    q = eachQuestion[1];
                    a = eachQuestion[2];
                    b = eachQuestion[3];
                    c = eachQuestion[4];
                    d = eachQuestion[5];
                    an = eachQuestion[6];
                    q0 = new Question();
                    q0.setQuestion(q.substring(0, q.length() - 2));
                    a0 = new Answer();
                    a0.setAnswer(a.substring(0, a.length() - 2).toUpperCase());
                    a0.setLabel('A');
                    b0 = new Answer();
                    b0.setAnswer(b.substring(0, b.length() - 2).toUpperCase());
                    b0.setLabel('B');
                    c0 = new Answer();
                    c0.setAnswer(c.substring(0, c.length() - 2).toUpperCase());
                    c0.setLabel('C');
                    d0 = new Answer();
                    d0.setAnswer(d.substring(0, d.length() - 7).toUpperCase());
                    d0.setLabel('D');
                    switch (an) {
                        case "A":
                            a0.setCorrect(true);
                            break;
                        case "B":
                            b0.setCorrect(true);
                            break;
                        case "C":
                            c0.setCorrect(true);
                            break;
                        case "D":
                            d0.setCorrect(true);
                            break;
                    }
                    q0.setId(n);
                    q0.getAnswers().add(a0);
                    q0.getAnswers().add(b0);
                    q0.getAnswers().add(c0);
                    q0.getAnswers().add(d0);
                    Databank.qiestionsBank.add(q0);
                }
            }

            System.out.println("DATA BANK SIZE IS: " + Databank.qiestionsBank.size());
            System.out.println("FINISHED PREPARING DATA!");
            System.out.println("Sample Questions from List and DB are:::::");
            Databank.qiestionsBank.get(new Random().nextInt(355)).printAnswer();

        } catch (Exception e) {
            System.out.println("CAN'T READ FILE!");
        }
	}

}
