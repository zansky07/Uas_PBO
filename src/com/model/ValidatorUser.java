/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.regex.Pattern;

/**
 *
 * @author hp
 */
public class ValidatorUser implements Validator{
    public User user; // Objek Person yang akan divalidasi

    public ValidatorUser(User user) {
        this.user = user;
    }

    @Override
    public boolean validate() throws ValidatorException {
        // Lakukan validasi untuk semua atribut User
        // Validasi untuk nama
        if (!isValidString(user.getNama()) || !user.getNama().matches("[a-zA-Z ]+")) {
            throw new ValidatorException("Nama tidak boleh kosong");
        }
        
        // Validasi untuk idUser
        if (!isValidString(user.getIdUser()) || !user.getIdUser().matches("\\d+")) {
            throw new ValidatorException("ID User hanya boleh mengandung angka");
        }

        // Validasi untuk email
        if (!isValidString(user.getEmail()) || !isValidEmail(user.getEmail())) {
            throw new ValidatorException("Format email tidak valid");
        }

        // Validasi untuk password
        if (!isValidString(user.getPassword()) || user.getPassword().length() > 50) {
            throw new ValidatorException("Password tidak boleh kosong dan tidak boleh lebih dari 50 karakter");
        }
      
        
      
        
        // Jika semua validasi berhasil, kembalikan true
        return true;
    }
    
    // Metode bantuan untuk memeriksa apakah string tidak kosong
    protected boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }
    
    // Metode bantuan untuk memeriksa format email
    protected boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
