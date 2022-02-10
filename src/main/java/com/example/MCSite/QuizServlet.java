package com.example.MCSite;

import Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static java.sql.Types.NULL;

@WebServlet(name = "quiz", value = "/quiz")
public class QuizServlet extends HttpServlet {
    private HttpSession session;
    static Connection connection= DatabaseConnection.getInstance().getConnection();




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ResultSet resultSet = null;
        ResultSet resultSetQuiz = null;
        ResultSet resultSetRisposte = null;
        ResultSet resultSetcheck = null;



        String citta=request.getParameter("cittaselect");
        String username=request.getParameter("username");
        request.setAttribute("citta",citta);


        try {
            Statement statement = connection.createStatement();

            String query="SELECT id,username,email from utente where utente.username = '"+username+"'";
            resultSet=statement.executeQuery(query);
            resultSet.next();
            Email email=new Email(resultSet.getString(3));


            int idutente=resultSet.getInt(1);


            User u =new User(idutente,resultSet.getString(2),email);


            String queryQuiz="SELECT id from quiz where quiz.citta = '"+citta+"'";
            resultSetQuiz=statement.executeQuery(queryQuiz);
            resultSetQuiz.next();

            int idquiz=resultSetQuiz.getInt(1);

            Quiz q=new Quiz(idquiz,citta);

            String checkInsertString="select * from Svolgimento_Quiz where "+"idutente = "+ idutente+" and idquiz = "+idquiz+"";

            resultSetcheck=statement.executeQuery(checkInsertString);
            if(!resultSetcheck.next())
            {

                String querySvolg = "insert into Svolgimento_Quiz values(?, ?,?,?) ON DUPLICATE KEY UPDATE " +
                        "idutente = " + idutente + " and idquiz = " + idquiz + "";

                PreparedStatement st = connection
                        .prepareStatement(querySvolg);

                System.out.println(querySvolg);

                st.setInt(1, NULL);
                st.setInt(2, u.getId());
                st.setInt(3, q.getId());
                st.setInt(4, 0);

                st.executeUpdate();
                st.close();
            }

            request.setAttribute("idutente",u.getId());
            request.setAttribute("idquiz",q.getId());

            //selezionare le domande di una determinata citta (con id)

            String queryDomande="SELECT DISTINCT domanda.domanda, domanda.id FROM utente inner join svolgimento_quiz " +
                    "on svolgimento_quiz.idutente=utente.id inner join quiz on quiz.id=svolgimento_quiz.idquiz inner" +
                    " join domanda on quiz.id=domanda.idquiz " +
                    "where svolgimento_quiz.idutente='"+idutente +"'and svolgimento_quiz.idquiz='"+idquiz+"'";


            resultSetQuiz=statement.executeQuery(queryDomande);
            ResultSetMetaData rsmd2 = resultSetQuiz.getMetaData();
            int columnsNumber = rsmd2.getColumnCount();
            ArrayList<Question> questions=new ArrayList<>();

            while (resultSetQuiz.next())
            {
                Question domanda=new Question();
                for (int i = 1; i <= columnsNumber; i++)
                {
                    String columnname=rsmd2.getColumnName(i);
                    String columnValue = resultSetQuiz.getString(i);
                    if(columnname.equals("id"))
                    {
                        domanda.setId((Integer.parseInt(columnValue)));
                    }

                    if(columnname.equals("domanda"))
                    {
                        domanda.setQuestion(columnValue);

                    }

                }
                questions.add(domanda);

            }

            //selezionare tutte le risposte + esatto (e poi filtrate attraverso question.id con Java)

            String queryRisposte="SELECT DISTINCT domanda.id,reply.risposta,reply.esatta from reply " +
                    "inner join domanda on domanda.id=reply.iddomanda";


            resultSetRisposte=statement.executeQuery(queryRisposte);
            ResultSetMetaData rsmd3 = resultSetRisposte.getMetaData();
            int columnsNumberRisposte = rsmd3.getColumnCount();
            ArrayList<Reply> replies=new ArrayList<>();

            while (resultSetRisposte.next())
            {
                Reply reply=new Reply();
                for (int i = 1; i <= columnsNumberRisposte; i++)
                {
                    String columnname=rsmd3.getColumnName(i);
                    String columnValue = resultSetRisposte.getString(i);
                    if(columnname.equals("id"))
                    {
                        reply.setQuestionId(Integer.parseInt(columnValue));
                    }
                    if(columnname.equals("risposta"))
                    {
                        reply.setReply(columnValue);
                    }

                    if(columnname.equals("esatta"))
                    {
                        if(Integer.parseInt(columnValue)==1)
                            reply.setEsatto(true);
                        if(Integer.parseInt(columnValue)==0)
                            reply.setEsatto(false);
                    }
                }
                replies.add(reply);
            }
/*
            for(int i=0;i<questions.size();i++)
            {
                System.out.println("Domanda "+questions.get(i).getId()+" "+questions.get(i).getQuestion());
            }

            for(int j=0;j<replies.size();j++)
            {
                System.out.println("Risposta "+replies.get(j).getId()+" "+replies.get(j).getReply()+" "+replies.get(j).isEsatto());

            }

 */

            for(int i=0;i<questions.size();i++)
            {
                ArrayList<Reply> repliesPerQuestion = new ArrayList<>();
                for(int j=0;j<replies.size();j++)
                {

                    //System.out.println("Confronto "+questions.get(i).getId()+" con "+replies.get(j).getId());
                    //System.out.println("Confronto "+questions.get(i).getQuestion()+" con "+replies.get(j).getReply());
                    //System.out.println();
                    Reply r=new Reply();
                    r.setQuestionId(replies.get(j).getId());
                    r.setReply(replies.get(j).getReply());
                    r.setEsatto(replies.get(j).isEsatto());
                    if(questions.get(i).getId()==r.getId())
                    {
                        repliesPerQuestion.add(r);

                    }
                    //System.out.println(replies.get(i).getReply() + " " + replies.get(i).isEsatto());
                }
                questions.get(i).setReplies(repliesPerQuestion);
            }




/*
            for(int i=0;i<questions.size();i++)
            {
                System.out.println("Domanda "+questions.get(i).getQuestion()+" ");
                for(int j=0;j<questions.get(i).getReplies().size();j++)
                {
                    System.out.println(questions.get(i).getReplies().get(j).getReply() + " " + questions.get(i).getReplies().get(j).isEsatto());
                }
            }

 */

            q.setQuestions(questions);
            request.setAttribute("quiz",q);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        request.getRequestDispatcher("/quiz.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


    }


    public void destroy() {
    }
}