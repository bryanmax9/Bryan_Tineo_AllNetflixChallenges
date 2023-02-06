package com.company.summativeproject.Model;

import java.util.Random;

public class Answer {


    private int id;

    private String question;

    private String answer;






    //Possible answers of the 8 ball stored in a list
    String[] answers = {"It is certain.", "Without a doubt.", "Yes, definitely.", "You may rely on it.", "As I see it, yes.", "Most likely.", "Outlook good.", "Yes.", "Signs point to yes.", "Reply hazy, try again."};

    Random rand = new Random();
    int index = rand.nextInt(answers.length);

    public Answer() {
    }

    public Answer(int id, String question) {
        this.id = id;
        this.question = question;
        this.answer = answers[index];

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

