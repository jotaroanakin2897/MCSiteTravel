package com.example.MCSite;

import Model.Email;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static com.example.MCSite.Hashing.hashSHA256;
import static java.sql.Types.NULL;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {
    private HttpSession session;
    static Connection connection= DatabaseConnection.getInstance().getConnection();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.err.println("Errore");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        ResultSet resultSet = null;
        session = request.getSession();
        String username,password,email;
        username=request.getParameter("username");
        password=request.getParameter("password");
        email=request.getParameter("email");
        session.setAttribute("username",username);


        if (checkusernameexists(connection,username))
        {
            request.setAttribute("error", "username già presente");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }


        try
        {
            Email e1=new Email(email);

            password=hashSHA256(password);

            User u=new User(username,e1,password);

            if (!checkusernameexists(connection,username))
            {
                PreparedStatement st = connection
                        .prepareStatement("insert into utente values(?, ?, ?, ?)");

                st.setInt(1, NULL);
                st.setString(2, u.getUsername());
                st.setString(3, u.getEmail().getEmail());
                st.setString(4, u.getPassword());

                st.executeUpdate();
                st.close();
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }


    }


    public void destroy() {
    }


    public boolean checkusernameexists(Connection connection, String username) {
        //controllo se username già presente
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT username from utente where utente.username = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);
            boolean notexists=resultSet.next();

            if (notexists)
            {
                return true;
            }
            else
            {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("Eccezione MYSQL");
            return false;
        }
    }



}