package Model;

import java.util.ArrayList;

public class Question
{

    public Question(int id, String question, ArrayList<Reply> replies)
    {
        this.id = id;
        this.question=question;
        this.replies=replies;
    }

    public Question(int id, String question)
    {
        this.id = id;
        this.question=question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String question;

    public Question() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Reply> replies) {
        this.replies = replies;
    }

    private ArrayList<Reply> replies;
}
