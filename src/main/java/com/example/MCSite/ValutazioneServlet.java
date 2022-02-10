package com.example.MCSite;

import Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

import static java.sql.Types.NULL;

@WebServlet(name = "valutazione", value = "/valutazione")
public class ValutazioneServlet extends HttpServlet {
    private HttpSession session;
    static Connection connection= DatabaseConnection.getInstance().getConnection();




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        doPost(request,response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String[] replies =request.getParameterValues("check");
        ResultSet resultSet = null;
        String citta=request.getParameter("citta");
        String idutente=request.getParameter("idutente");
        String idquiz=request.getParameter("idquiz");




        int punteggio=0;
        int punteggiocomplessivo=0;
        ArrayList<GivenReply> risposte_date=new ArrayList<>();

        for(String reply:replies)
        {
            String[] replyparts = reply.split(":");
            String question = replyparts[0];
            Question q1=new Question();
            q1.setQuestion(question);
            String replyString = replyparts[1];
            String esatto = replyparts[2];
            GivenReply gr=new GivenReply(q1,replyString,Boolean.parseBoolean(esatto));
            if(Boolean.parseBoolean(esatto))
            {
                punteggio++;
            }
            risposte_date.add(gr);
        }

        punteggiocomplessivo=(int)(((float)punteggio/(float)(replies.length-1))*10);

     



        try
        {
            Statement statement = connection.createStatement();

            String query="SELECT punteggio from svolgimento_quiz where" +
                    " svolgimento_quiz.idutente='"+idutente+"' and" +
                    " svolgimento_quiz.idquiz='" + idquiz+"' order by punteggio DESC";

            System.out.println(query);

            resultSet=statement.executeQuery(query);
            resultSet.next();

            if(resultSet!=null && (Integer.parseInt(resultSet.getString(1)))<punteggiocomplessivo)
            {
                System.out.println(resultSet.getString(1));
                try
                {

                    PreparedStatement st = connection
                            .prepareStatement("UPDATE Svolgimento_Quiz SET punteggio = ? where idutente =? and idquiz = ?");
                    st.setInt(1, punteggiocomplessivo);
                    st.setInt(2, Integer.parseInt(idutente));
                    st.setInt(3, Integer.parseInt(idquiz));
                    st.executeUpdate();
                    st.close();

                } catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }

            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }



        request.setAttribute("punteggio",punteggiocomplessivo);

        System.out.println("Punteggio complessivo: "+punteggiocomplessivo +"/10");





        try {
            Statement statement=connection.createStatement();

            String query="SELECT domanda,risposta FROM `reply` inner join domanda on domanda.id=reply.iddomanda" +
                    " inner join quiz on quiz.id=domanda.idquiz where esatta=1 and quiz.citta='"+citta+"'";
            resultSet=statement.executeQuery(query);


            ResultSetMetaData rsmd2 = resultSet.getMetaData();
            int columnsNumber = rsmd2.getColumnCount();
            ArrayList<ReplyResult> repliesResult=new ArrayList<>();

            while (resultSet.next())
            {
                ReplyResult replyresult=new ReplyResult();
                for (int i = 1; i <= columnsNumber; i++)
                {
                    String columnname=rsmd2.getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    if(columnname.equals("domanda"))
                    {
                        replyresult.setQuestion(columnValue);
                    }

                    if(columnname.equals("risposta"))
                    {
                        replyresult.setRightreply(columnValue);
                    }
                }
                repliesResult.add(replyresult);
            }


            for(GivenReply risposta_data: risposte_date)
            {
                for(ReplyResult risultato_risposta: repliesResult)
                {
                    //System.out.println(risposta_data.getQuestion()+" "+risultato_risposta.getQuestion());

                    if(risposta_data.getQuestion().getQuestion().equals(risultato_risposta.getQuestion()))
                    {
                        risultato_risposta.setGivenreply(risposta_data);
                        if(risposta_data.getReply().equals(risultato_risposta.getRightreply()))
                        {
                            risultato_risposta.setEsatto(true);
                        }
                        else
                        {
                            risultato_risposta.setEsatto(false);
                        }
                    }
                }

            }

            /*
            for(ReplyResult risultato_risposta: repliesResult)
            {
                System.out.println("Domanda: "+risultato_risposta.getQuestion());
                System.out.println("Risposta data: "+risultato_risposta.getGivenreply().getReply());
                System.out.println("Risposta esatta: "+risultato_risposta.getRightreply());
                System.out.println("Esatto?: "+risultato_risposta.isEsatto());
            }

             */

        request.setAttribute("risultati",repliesResult);

        } catch (SQLException throwables) {
            System.out.println("Eccezione");

            throwables.printStackTrace();
        }





        request.getRequestDispatcher("/risultati.jsp").forward(request,response);

    }


    public void destroy() {
    }
}