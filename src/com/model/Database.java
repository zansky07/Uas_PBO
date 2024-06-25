/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author hp
 */
public class Database implements Serializable{
    public static Database instance;

    private final String url = "jdbc:sqlite:pbo_uas.db";
    private Database(){
    }

    public static synchronized Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
            return instance;
    }
    
    /**
     *
     * @return Connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
