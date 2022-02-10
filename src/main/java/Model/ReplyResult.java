package Model;

public class ReplyResult
{
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    private String question;
    private String rightreply;
    private GivenReply givenreply;

    public String getRightreply() {
        return rightreply;
    }

    public void setRightreply(String rightreply) {
        this.rightreply = rightreply;
    }

    public GivenReply getGivenreply() {
        return givenreply;
    }

    public void setGivenreply(GivenReply givenreply) {
        this.givenreply = givenreply;
    }

    public boolean isEsatto() {
        return esatto;
    }

    public void setEsatto(boolean esatto) {
        this.esatto = esatto;
    }

    private boolean esatto;
    public ReplyResult(String question, GivenReply givenreply,String rightreply,  boolean esatto)
    {
        this.givenreply=givenreply;
        this.esatto=esatto;
        this.question=question;
        this.rightreply=rightreply;
    }

    public ReplyResult() {
    }
}
