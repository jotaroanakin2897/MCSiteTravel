package com.example.MCSite;

import Model.Quiz;
import Model.ReplyResult;
import Model.Svolgimento_Quiz;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "Classifica", value = "/classifica")
public class ClassificaServlet extends HttpServlet {
    private HttpSession session;
    static Connection connection= DatabaseConnection.getInstance().getConnection();




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ResultSet resultSet = null;


        try
        {
            Statement statement = connection.createStatement();


            String query = "SELECT username,citta,punteggio FROM svolgimento_quiz inner join utente on " +
                    "utente.id=svolgimento_quiz.idutente inner join quiz on quiz.id=svolgimento_quiz.idquiz where punteggio!=0 order by punteggio DESC";

            resultSet=statement.executeQuery(query);

            ResultSetMetaData rsmd2 = resultSet.getMetaData();
            int columnsNumber = rsmd2.getColumnCount();

            ArrayList<Svolgimento_Quiz> records =new ArrayList<>();

            while (resultSet.next())
            {
                Svolgimento_Quiz svolg_quiz=new Svolgimento_Quiz();
                for (int i = 1; i <= columnsNumber; i++)
                {
                    String columnname=rsmd2.getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    if(columnname.equals("username"))
                    {
                        User u=new User();
                        u.setUsername(columnValue);
                        svolg_quiz.setUser(u);
                    }

                    if(columnname.equals("citta"))
                    {
                        Quiz q=new Quiz();
                        q.setCity(columnValue);
                        svolg_quiz.setQ(q);
                    }
                    if(columnname.equals("punteggio"))
                    {
                        svolg_quiz.setPunteggio(Integer.parseInt(columnValue));
                    }
                }
                records.add(svolg_quiz);
            }
/*
        for(Svolgimento_Quiz record:records)
        {
            System.out.println("Username "+record.getUser().getUsername());
            System.out.println("Citta "+record.getQ().getCity());
            System.out.println("Punteggio "+record.getPunteggio());
        }

 */
        request.setAttribute("records",records);

        } catch (
        SQLException throwables) {
            throwables.printStackTrace();
        }




        request.getRequestDispatcher("/classifica.jsp").forward(request,response);


    }


    public void destroy() {
    }
}