/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Kasir;
import java.sql.Statement;
import java.sql.ResultSet;

public class KasirController {
    public Statement stm;
    public ResultSet res;
    public String sql;
    
    //konstruktor
    public KasirController(){
        //objek koneksi
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }
    
    //method cekLogin(select)
    public boolean cekLogin(String un, String pw){
        //dipetakan dengan model
        Kasir ksr = new Kasir();
        ksr.setNama(un);
        ksr.setPassword(pw);
        boolean status=false;
        
        //query ke database + cek
        try {
            //sql query
            this.sql = "SELECT * FROM tb_kasir WHERE nama = '"+ksr.getNama()+"'AND password = '"+ksr.getPassword()+"'";
            
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