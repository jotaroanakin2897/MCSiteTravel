package Model;

public class GivenReply extends Reply
{
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    private Question question;
    public GivenReply(Question question,String reply, boolean esatto)
    {
        super(reply, esatto);
        this.question=question;
    }

    public GivenReply() {
    }
}
