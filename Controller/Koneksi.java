/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
/**
 *
 * @author LENOVO
 */

//kelas ini berfungsi untuk membangun Koneksi ke database
public class Koneksi {
    public static Connection con;
    public static Statement stm;
    
    //metot konfigurasi
    public void config(){
    try{
    String url ="jdbc:mysql://127.0.0.1/db_bengkel";
    String user ="root";
    String pass ="";
    Class.forName("com.mysql.cj.jdbc.Driver");
    
    //menghubungkan
    con= DriverManager.getConnection(url, user, pass);
    
    stm =(Statement)con.createStatement();
        System.out.println("Koneksi berhasil");
    }catch(Exception e){
        System.out.println("koneksi gagal");
        System.out.println(e.getMessage()); //meliat eror dimana
    }
   }
}
