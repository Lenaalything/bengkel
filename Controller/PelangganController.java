/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Pegawai;
import Model.Pelanggan;
import java.sql.Statement;
import java.sql.ResultSet;

public class PelangganController {
    public Statement stm;
    public ResultSet res;
    public String sql;
    
    //konstruktor
    public PelangganController(){
        //objek koneksi
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }
    
    //method cekLogin(select)
    public boolean cekLogin(String un, String pw){
        //dipetakan dengan model
        Pelanggan plg = new Pelanggan();
        plg.setNama(un);
        plg.setPassword(pw);
        boolean status=false;
        
        //query ke database + cek
        try {
            //sql query
            this.sql = "SELECT * FROM tb_pelanggan WHERE nama = '"+plg.getNama()+"'AND password = '"+plg.getPassword()+"'";
            
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