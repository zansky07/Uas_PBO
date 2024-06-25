/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author hp
 */
public class User {
    private String idUser;
    private String email;
    private String nama;
    private String password;
    public Validator validasi;

    public User() {
        idUser = null;
        email  = null;
        nama   = null;
        validasi = new ValidatorUser(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Validator getValidasi() {
        return validasi;
    }

    public void setValidasi(Validator validasi) {
        this.validasi = validasi;
    }
    
}
