package Controller;

import Model.Pegawai;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class PegawaiController {

    public Statement stm;
    public ResultSet res;
    public String sql;

    //model tabel/bentuk virtual sebelum di apply di view
    DefaultTableModel dtm = new DefaultTableModel();

    //konstruktor
    public PegawaiController() {
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }

    // buat struktur table virtual
    public DefaultTableModel createTable() {
        this.dtm.addColumn("ID");
        this.dtm.addColumn("Nama");
        this.dtm.addColumn("No.Telp");
        this.dtm.addColumn("Email");
        this.dtm.addColumn("Status");
        return this.dtm;
    }

    //cek login
    public boolean cekLogin(String un, String pw) {
        //dipetakan dengan model
        Pegawai pgw = new Pegawai();
        pgw.setNama(un);
        pgw.setPassword(pw);
        boolean status = false;

        //query ke database + cek
        try {
            //sql query
            this.sql = "SELECT * FROM tb_pegawai WHERE nama = '" + pgw.getNama() + "'AND password = '" + pgw.getPassword() + "'";

            //menjalankan query
            //khusus select gunakan 'executeQuery'
            this.res = this.stm.executeQuery(sql);

            //pengecekan
            if (res.next()) {
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            System.out.println("Query Gagal");
        }
        return status;

    }

    // tampilkan semua pegawai
    public void tampilkanPegawai() {
        try {
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();

            this.sql = "SELECT * FROM tb_pegawai";
            this.res = this.stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_pegawai");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("no_telp");
                obj[3] = res.getString("email");
                obj[4] = res.getString("status");
                this.dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Gagal query tampilkanPegawai: " + e.getMessage());
        }
    }

    // tampilkan data pegawai berdasarkan nama (dipakai untuk tampilkan data yang login)
    public void tampilkanPegawaiByName(String nama) {
        try {
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();

            this.sql = "SELECT * FROM tb_pegawai WHERE nama = '" + nama + "'";
            this.res = this.stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_pegawai");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("no_telp");
                obj[3] = res.getString("email");
                obj[4] = res.getString("status");
                this.dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Gagal query tampilkanPegawaiByName: " + e.getMessage());
        }
    }

    // tampilkan data pegawai berdasarkan id_pegawai
    public void tampilkanPegawaiById(String id) {
        try {
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();

            this.sql = "SELECT * FROM tb_pegawai WHERE id_pegawai = '" + id + "'";
            this.res = this.stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_pegawai");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("no_telp");
                obj[3] = res.getString("email");
                obj[4] = res.getString("status");
                this.dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Gagal query tampilkanPegawaiById: " + e.getMessage());
        }
    }

    // update data pegawai hanya untuk akun yang login (no hp, email, password)
    public boolean updatePegawaiLogin(String id, String noTelp, String email, String password) {
        try {
            this.sql = "UPDATE tb_pegawai SET no_telp='" + noTelp + "', email='" + email + "', password='" + password + "' WHERE id_pegawai='" + id + "'";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ambil gaji: menandai gaji telah diambil dan set gaji menjadi 0 untuk baris yang belum diambil
    public boolean ambilGaji(String idKaryawan) {
        try {
            this.sql = "UPDATE tb_gaji SET gaji=0, status_pengambilan_gaji=1 WHERE id_karyawan='" + idKaryawan + "' AND status_pengambilan_gaji=0";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // dapatkan jumlah gaji saat ini (belum diambil) untuk karyawan
    public double getGaji(String idKaryawan) {
        double gaji = 0.0;
        try {
            this.sql = "SELECT gaji FROM tb_gaji WHERE id_karyawan='" + idKaryawan + "' AND status_pengambilan_gaji=0 ORDER BY id_gaji DESC LIMIT 1";
            this.res = this.stm.executeQuery(sql);
            if (res.next()) {
                gaji = res.getDouble("gaji");
            }
        } catch (Exception e) {
            System.out.println("Gagal getGaji: " + e.getMessage());
        }
        return gaji;
    }

    // helper: buat role identifier sederhana dari id dan nama, e.g. K001 + Deni -> k_deni
    public String getRoleFromId(String idPegawai, String nama) {
        if (idPegawai == null || nama == null) {
            return "";
        }
        String prefix = idPegawai.length() > 0 ? idPegawai.substring(0, 1).toUpperCase() : "";
        String lname = nama.trim().toLowerCase().replaceAll("\\s+", "_");
        if (prefix.equals("K")) {
            return "k_" + lname;
        }
        if (prefix.equals("M")) {
            return "m_" + lname;
        }
        return "user_" + lname;
    }

    // tambah pegawai (biasanya oleh admin)
    public boolean tambahPegawai(String id, String nama, String noTelp, String email, String status) {
        Pegawai pg = new Pegawai();
        pg.setId_pegawai(id);
        pg.setNama(nama);
        pg.setNo_telp(noTelp);
        pg.setEmail(email);
        pg.setStatus(status);

        try {
            this.sql = "INSERT INTO tb_pegawai(id_pegawai,nama,no_telp,email,status) VALUES ('" + pg.getId_pegawai() + "','" + pg.getNama() + "','" + pg.getNo_telp() + "','" + pg.getEmail() + "','" + pg.getStatus() + "')";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ubah data pegawai
    public boolean ubahPegawai(String id, String nama, String noTelp, String email, String status) {
        Pegawai pg = new Pegawai();
        pg.setId_pegawai(id);
        pg.setNama(nama);
        pg.setNo_telp(noTelp);
        pg.setEmail(email);
        pg.setStatus(status);

        try {
            this.sql = "UPDATE tb_pegawai SET nama='" + pg.getNama() + "', no_telp='" + pg.getNo_telp() + "', email='" + pg.getEmail() + "', status='" + pg.getStatus() + "' WHERE id_pegawai='" + pg.getId_pegawai() + "'";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // hapus pegawai berdasarkan id
    public boolean hapusPegawai(String id) {
        Pegawai pg = new Pegawai();
        pg.setId_pegawai(id);
        try {
            this.sql = "DELETE FROM tb_pegawai WHERE id_pegawai='" + pg.getId_pegawai() + "'";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // method cekLogin untuk pegawai (menggunakan id_pegawai dan password)
//    public boolean cekLogin(String id, String pw) {
//        Pegawai pgw = new Pegawai();
//        pgw.setId_pegawai(id);
//        pgw.setPassword(pw);
//        boolean status = false;
//
//        try {
//            this.sql = "SELECT * FROM tb_pegawai WHERE id_pegawai = '" + pgw.getId_pegawai() + "' AND password = '" + pgw.getPassword() + "'";
//            this.res = this.stm.executeQuery(sql);
//            if (res.next()) status = true;
//            else status = false;
//        } catch (Exception e) {
//            System.out.println("Query cekLogin gagal: " + e.getMessage());
//        }
//        return status;
//    }
}
