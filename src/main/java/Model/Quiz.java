package Model;

import java.util.ArrayList;

public class Quiz {

    public Quiz (int id,String city)
    {
        this.id=id;
        this.city=city;
    }

    public Quiz() {

    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    private String city;
    private ArrayList<Question> questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
