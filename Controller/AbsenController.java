package Controller;

import Model.Absen;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class AbsenController {

    public Statement stm;
    public ResultSet res;
    public String sql;

    public AbsenController() {
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }

    private DefaultTableModel createTableAbsen() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID Absen");
        dtm.addColumn("ID Karyawan");
        dtm.addColumn("Nama");
        dtm.addColumn("Tanggal");
        return dtm;
    }


    public DefaultTableModel tampilkanAbsenByKaryawan(String idKaryawan) {
        DefaultTableModel dtm = createTableAbsen();
        try {
            sql = "SELECT * FROM tb_absen WHERE id_karyawan='" + idKaryawan +
                  "' ORDER BY tanggal_absen DESC";
            res = stm.executeQuery(sql);

            while (res.next()) {
                dtm.addRow(new Object[]{
                    res.getInt("id_absen"),
                    res.getString("id_karyawan"),
                    res.getString("nama"),
                    res.getString("tanggal_absen")
                });
            }

        } catch (Exception e) {
            System.out.println("Gagal query tampilkanAbsenByKaryawan: " + e.getMessage());
        }
        return dtm;
    }

    public boolean tambahAbsen(String idKaryawan, String nama) {
        try {
            sql = "INSERT INTO tb_absen(id_karyawan,nama) VALUES ('" + idKaryawan + "','" + nama + "')";
            stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal insert absen: " + e.getMessage());
            return false;
        }
    }

    // public boolean hapusAbsen(int id) {
    //     try {
    //         sql = "DELETE FROM tb_absen WHERE id_absen=" + id;
    //         stm.executeUpdate(sql);
    //         return true;
    //     } catch (Exception e) {
    //         System.out.println("Gagal hapus absen: " + e.getMessage());
    //         return false;
    //     }
    // }
}
