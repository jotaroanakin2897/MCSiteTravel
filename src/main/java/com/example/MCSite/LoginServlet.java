package com.example.MCSite;

import Model.Citta;
import Model.Email;
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

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    private HttpSession session;
    static Connection connection= DatabaseConnection.getInstance().getConnection();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.err.println("Errore");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        ResultSet resultSet = null;
        session = request.getSession();
        String username,password,email,checkedUsername,checkedEmail;
        username=request.getParameter("username");
        email=request.getParameter("email");
        password=request.getParameter("password");
        password= Hashing.hashSHA256(password);

        checkedUsername=request.getParameter("userUsed");
        checkedEmail=request.getParameter("emailUsed");


        session.setAttribute("username",username);

        try {
            Statement statement=connection.createStatement();

            if(checkedEmail!=null)
            {
                Email e1=new Email(email);
                User user1= new User.UserBuilder(e1,password).buildEmail(e1,password);
                System.out.println(user1.getEmail().getEmail());
                String Useremail=user1.getEmail().getEmail();

                System.out.println(Useremail+" "+user1.getPassword());


                String query="SELECT email,password,username from utente where utente.email = '"+Useremail+"'";
                resultSet=statement.executeQuery(query);
                resultSet.next();

                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                session.setAttribute("username",resultSet.getString(3));

                if(Useremail.equals(resultSet.getString(1)) && user1.getPassword().equals(resultSet.getString(2)))
                {
                    ArrayList<Citta> citta=GeneraCitta(request,response);
                    request.setAttribute("citta",citta);
                    request.getRequestDispatcher("/play.jsp").forward(request,response);
                    System.out.println("accesso valido");
                }
                else
                {
                    request.setAttribute("error","Errore, Riprova!");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                    System.out.println("errore username o password");

                }
            }
            else if(checkedUsername!=null)
            {
                User user1= new User.UserBuilder(username,password).buildUser(username,password);

                String query="SELECT username,password from utente where utente.username = '"+user1.getUsername()+"'";
                System.out.println(query);
                resultSet=statement.executeQuery(query);
                resultSet.next();

                if(user1.getUsername().equals(resultSet.getString(1)) && user1.getPassword().equals(resultSet.getString(2)))
                {

                    ArrayList<Citta> citta=GeneraCitta(request,response);
                    request.setAttribute("citta",citta);
                    request.getRequestDispatcher("/play.jsp").forward(request,response);
                    System.out.println("accesso valido");
                }
                else
                {
                    request.setAttribute("error","Errore, Riprova!");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                    System.out.println("errore username o password");

                }
            }
        } catch (SQLException throwables) {
            System.out.println("Eccezione");
            request.setAttribute("error","Errore, Riprova!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            System.out.println("errore username o password");

            throwables.printStackTrace();
        }


    }


    public void destroy() {
    }

    public ArrayList<Citta> GeneraCitta(HttpServletRequest request, HttpServletResponse response)
    {
        ArrayList<Citta> citta = null;

        session = request.getSession();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        ResultSet resultSet = null;


        String username = request.getParameter("username");
        request.setAttribute("username", username);

        Connection connection = null;
        try {
             connection= DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String query="SELECT citta from quiz";
            resultSet=statement.executeQuery(query);

            citta=new ArrayList<Citta>();

            ResultSetMetaData rsmd = resultSet.getMetaData();
            //scorro il resultset e metto le citta in un array
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    citta.add(new Citta(columnValue));
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return citta;

    }






}