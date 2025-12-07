/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Admin;
import java.sql.Statement;
import java.sql.ResultSet;

public class AdminController {
    public Statement stm;
    public ResultSet res;
    public String sql;
    
    //konstruktor
    public AdminController(){
        //objek koneksi
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }
    
    //method cekLogin(select)
    public boolean cekLogin(String un, String pw){
        //dipetakan dengan model
        Admin adm = new Admin();
        adm.setNama(un);
        adm.setPassword(pw);
        boolean status=false;
        
        //query ke database + cek
        try {
            //sql query
            this.sql = "SELECT * FROM tb_admin WHERE nama = '"+adm.getNama()+"'AND password = '"+adm.getPassword()+"'";
            
            //menjalankan query
            //khusus select gunakan 'executeQuery'
            this.res = this.stm.executeQuery(sql);
            
            //pengecekan
            if(res.next()) status = true;
            else status = false;
        } catch (Exception e) {
            System.out.println("Query Gagal");
        }
        return status;
    }
    
    
}