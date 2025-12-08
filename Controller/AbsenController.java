package Controller;

import Model.Absen;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class AbsenController {

    public Statement stm;
    public ResultSet res;
    public String sql;

    DefaultTableModel dtm = new DefaultTableModel();

    public AbsenController() {
        Koneksi db = new Koneksi();
        db.config();
        this.stm = db.stm;
    }

    public DefaultTableModel createTable() {
        this.dtm.addColumn("ID");
        this.dtm.addColumn("ID Karyawan");
        this.dtm.addColumn("Nama");
        this.dtm.addColumn("Tanggal");
        return this.dtm;
    }

    public void tampilkanAbsen() {
        try {
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();

            this.sql = "SELECT * FROM tb_absen ORDER BY tanggal_absen DESC";
            this.res = this.stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[4];
                obj[0] = res.getInt("id_absen");
                obj[1] = res.getString("id_karyawan");
                obj[2] = res.getString("nama");
                obj[3] = res.getString("tanggal_absen");
                this.dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Gagal query tampilkanAbsen: " + e.getMessage());
        }
    }

    // tampilkan absen hanya untuk karyawan tertentu (dipakai saat pegawai login)
    public void tampilkanAbsenByKaryawan(String idKaryawan) {
        try {
            this.dtm.getDataVector().removeAllElements();
            this.dtm.fireTableDataChanged();

            this.sql = "SELECT * FROM tb_absen WHERE id_karyawan='" + idKaryawan + "' ORDER BY tanggal_absen DESC";
            this.res = this.stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[4];
                obj[0] = res.getInt("id_absen");
                obj[1] = res.getString("id_karyawan");
                obj[2] = res.getString("nama");
                obj[3] = res.getString("tanggal_absen");
                this.dtm.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("Gagal query tampilkanAbsenByKaryawan: " + e.getMessage());
        }
    }

    public boolean tambahAbsen(String idKaryawan, String nama) {
        Absen ab = new Absen();
        ab.setId_karyawan(idKaryawan);
        ab.setNama(nama);

        try {
            // tanggal_absen otomatis di-handle oleh DB (current_timestamp)
            this.sql = "INSERT INTO tb_absen(id_karyawan,nama) VALUES ('" + ab.getId_karyawan() + "','" + ab.getNama() + "')";
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hapusAbsen(int id) {
        Absen ab = new Absen();
        ab.setId_absen(id);
        try {
            this.sql = "DELETE FROM tb_absen WHERE id_absen=" + ab.getId_absen();
            this.stm.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
