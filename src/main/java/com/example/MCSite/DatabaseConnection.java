package com.example.MCSite;

import org.hibernate.dialect.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static Connection con;
    private static DatabaseConnection dbinstance;
    private static Statement stmt;
    public static final String dbUrl = "jdbc:mysql://localhost:3306/mcsite";
    public static final String dbname = "root";
    public static final String dbPassword = "";
    public static final String dbDriver = "com.mysql.cj.jdbc.Driver";

    private DatabaseConnection()
    {

    }

    public static DatabaseConnection getInstance()
    {
        if(dbinstance==null)
        {
            dbinstance=new DatabaseConnection();
        }
        return dbinstance;
    }

    public Connection getConnection()
    {
        try {
            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbUrl, dbname, dbPassword);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}