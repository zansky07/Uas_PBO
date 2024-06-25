/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.client.view.HelpDeskPanel;
import com.client.view.HomePanel;
import com.model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.SwingUtilities;

/**
 *
 * @author hp
 */
public class HelpDeskController {
    
    private HelpDeskPanel hdp;
    private ServerSocket serverSocket;
    private boolean isServerRunning;
    private User user;
    
    public HelpDeskController(HelpDeskPanel hdp, User user) {
        this.hdp = hdp;
        
        startServer();
        this.hdp.getBackButton().addActionListener(e -> handleBack(user));
        this.hdp.getSendButton().addActionListener(e -> handleSend());
    }
    
    private void handleBack(User user){
        stopServer();
        hdp.dispose();
        HomePanel homePanel = new HomePanel(user);
        homePanel.setVisible(true);
    }
    
    private void stopServer() {
        isServerRunning = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server has been stopped.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void handleSend(){
        String message = hdp.getPesanTextField().getText().trim();
        sendMessageToServer(message);
        hdp.getPesanTextField().setText("");
    }
    
    private void sendMessageToServer(String message) {
        try {
            Socket clientSocket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
            appendToChatArea("You: " + message );
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     private void appendToChatArea(String message) {
        SwingUtilities.invokeLater(() -> {
            hdp.getResponseTextArea().append(message + System.lineSeparator());
            hdp.getResponseTextArea().setCaretPosition(hdp.getResponseTextArea().getDocument().getLength()); // Memastikan agar scroll ke bawah
        });
    }
     
    private void startServer() {
        isServerRunning = true;
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(12345);
                System.out.println("Server is listening on port 12345");

                while (isServerRunning) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Client connected: " + clientSocket);

                        new Thread(() -> {
                            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                                String inputLine;
                                while ((inputLine = in.readLine()) != null) {
                                    System.out.println("Client: " + inputLine);
                                    String response = generateResponse(inputLine);
                                    out.println(response);
                                    // Tampilkan respons server di sisi server untuk logging, tidak perlu di chat area
                                    System.out.println("Server: " + response);

                                    appendToChatArea("Help Center: " + response);

                                    // Setelah mengirimkan respons, keluar dari loop untuk memastikan satu respons per pesan
                                    break;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    clientSocket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    } catch (SocketException e) {
                        // SocketException terjadi ketika serverSocket ditutup
                        if (!isServerRunning) {
                            System.out.println("Server socket closed, stopping server.");
                            break;
                        } else {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    private String generateResponse(String message) {
        switch (message.toLowerCase()) {
            case "exit":
                return "";
            case "1":
                return "Berikut langkah-langkah melaporkan kerusakan di LaporStat:\n"
                     + "1. Pada halaman awal pilih menu buat laporan\n"
                     + "2. Isi informasi yang diperlukan seperti judul, barang, lokasi, dan deskripsi\n"
                     + "3. Tekan tombol kirim untuk mengirim laporan\n";
            case "2":
                return "Laporan Anda akan ditindaklanjuti oleh admin.\n"
                     + "Admin akan memastikan kerusakan yang Anda laporkan.\n"
                     + "Setelah kerusakan telah diperbaiki, Admin akan mengirim balasan kepada Anda.\n";          
            case "3":
                return "Ya semua laporan diterima oleh sistem, namun\n"
                     + " Admin akan melakukan proses pemeriksaan sebelum kerusakan\n"
                     + " ditindaklanjuti. Apabila laporan dianggap tidak benar, maka\n"
                     + " Admin akan menolak laporan tersebut.\n";
            case "4":
                return "Berikut langkah-langkah melihat progres laporan:\n"
                     + "1. Pada halaman awal pilih menu lihat laporan\n"
                     + "2. Akan terlihat sebuah tabel yang menunjukkan status laporan Anda\n"
                     + "3. Dapat dilihat pula balasan admin terhadap laporan Anda\n";
            default:
                return "Pastikan pertanyaan anda sesuai dengan list pertanyaan\n";
        }
    }
    
    
    
    
}
