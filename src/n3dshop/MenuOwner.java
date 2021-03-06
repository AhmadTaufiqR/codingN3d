/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n3dshop;

import BaranagTampil.BarangDash;
import grafikpembelian.grfpembelian;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.List;
import javaswingdev.chart.ModelChart;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import grafikpenjualan.report;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class MenuOwner extends javax.swing.JFrame {

    public String SATUAN;
    private List<ModelChart> list;

    /**
     * Creates new form MenuOwner
     */
    public MenuOwner() {
        initComponents();
        Tabel_Akun();
        Tabel_DataBarang();
        Tabel_Supplier();
        Tabel_TransaksiPenjualan();
        Tabel_TransaksiPembelian();
        Tabel_Returnbarang();
        Tabel_DataPelanggan();
        init();
        init2();
        tblelaporanpenjualanharian();
        
        DashboardMenu.setBackground(new Color(51,102,255));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
        
        barChart3.addLegend("Record Barang", new Color(12, 84, 175), new Color(0,9,87));
    }
    
    private void init(){
        barChart2.addLegend("Transaksi Penjualan", new Color(12, 84, 175), new Color(0,9,87));
        try {
            List<ModelChart> datas = new report().getData();
            for (int i = datas.size() - 1; i >= 0; i--) {
                barChart2.addData(datas.get(i));
            }
            barChart2.start();
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }
    
    private void init2(){
        barChart1.addLegend("Transaksi Pembelian", new Color(12, 84, 175), new Color(0,9,87));
        try {
            List<ModelChart> datas = new grfpembelian().getData();
            for (int i = datas.size() - 1; i >= 0; i--) {
                barChart1.addData(datas.get(i));
            }
            barChart1.start();
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }
 private void Tabel_DataPelanggan() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID PELANGGAN");
        model.addColumn("NAMA PELANGGAN");
        model.addColumn("NO HP");
        model.addColumn("POINT ");

        String cari = cariDataPelanggan.getText();

        try {
            String sql = "SELECT * FROM pelanggan WHERE CONCAT(id_pelanggan,nama_pelanggan)  LIKE '%" + cari + "%'";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_pelanggan"), res.getString("nama_pelanggan"), res.getString("no_hp"),
                    res.getString("poin")});
            }
            tabelDataPelanggan.setModel(model);
        } catch (Exception e) {

        }
    }
    private void Tabel_TransaksiPembelian() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID PEMBELIAN");
        model.addColumn("ID SUPPLIER");
        model.addColumn("USERNAME");
        model.addColumn("HARGA ");

        String cari = cariTransaksiPembelian.getText();

        try {
            String sql = "SELECT * FROM transaksi_pembelian WHERE username LIKE'%" + cari + "%'";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_pembelian"), res.getString("id_supplier"), res.getString("username"),
                    res.getString("Harga_total")});
            }
            tabelTransaksiPembelian.setModel(model);
        } catch (Exception e) {

        }
    }

    private void Tabel_Supplier() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID SUPPLIER");
        model.addColumn("NAMA SUPPLIER");
        model.addColumn("ALAMAT");
        model.addColumn("NO TELEPON");

        String cari = CariSupplier.getText();

        try {
            String sql = "SELECT * FROM supplier WHERE nama_supplier LIKE'%" + cari + "%'";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_supplier"), res.getString("nama_supplier"), res.getString("alamat"),
                    res.getString("no_telepon")});
            }
            Tabel_suplier.setModel(model);
        } catch (Exception e) {

        }
    } 
      public void TabelReturnTahun() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID RETURN");
        model.addColumn("ID PEMBELIAN");
        model.addColumn("ID SUPPLIER");
        model.addColumn("USERNAME ");
        model.addColumn("TANGGAL");
       

        String cari = CariReturn6.getText();

        try {
            String sql = "SELECT id_return,id_pembelian,id_supplier,username, DATE_FORMAT(tanggal,'%Y') AS tanggall FROM return_barang GROUP BY tanggall ORDER BY tanggall DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_return"), res.getString("id_pembelian"), res.getString("id_supplier"),
                    res.getString("username"),
                    res.getString("tanggall")});
            }
            tabelReturnBarang.setModel(model);
        } catch (Exception e) {

        }
    }
      public void TabelReturnBulan() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID RETURN");
        model.addColumn("ID PEMBELIAN");
        model.addColumn("ID SUPPLIER");
        model.addColumn("USERNAME ");
        model.addColumn("TANGGAL");
       

        String cari = CariReturn6.getText();

        try {
            String sql = "SELECT id_return,id_pembelian,id_supplier,username, DATE_FORMAT(tanggal,'%M') AS tanggall FROM return_barang GROUP BY tanggall ORDER BY tanggall DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_return"), res.getString("id_pembelian"), res.getString("id_supplier"),
                    res.getString("username"),
                    res.getString("tanggall")});
            }
            tabelReturnBarang.setModel(model);
        } catch (Exception e) {

        }
    }
     private void Tabel_Returnbarang() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID RETURN");
        model.addColumn("ID PEMBELIAN");
        model.addColumn("ID SUPPLIER");
        model.addColumn("USERNAME ");
        model.addColumn("TANGGAL");
       

        String cari = CariReturn6.getText();

        try {
            String sql = "SELECT * FROM return_barang WHERE tanggal LIKE'%" + cari + "%'";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_return"), res.getString("id_pembelian"), res.getString("id_supplier"),
                    res.getString("username"),
                    res.getString("tanggal")});
            }
            tabelReturnBarang.setModel(model);
        } catch (Exception e) {

        }
    }

    private void Tabel_TransaksiPenjualan() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO FAKTUR");
        model.addColumn("NAMA KASIR");
        model.addColumn("HARGA TOTAL");
        model.addColumn("BAYAR");
        model.addColumn("KEMBALI");
        model.addColumn("ID PELANGGAN ");
        model.addColumn("POINT");

        String cari = cariTransaksiPenjualan.getText();

        try {
            String sql = "SELECT * FROM transaksi_penjualan WHERE no_faktur LIKE'%" + cari + "%'";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("no_faktur"), res.getString("username"), res.getString("harga_total"),
                    res.getString("bayar"),
                    res.getString("kembali"), res.getString("id_pelanggan"), res.getString("point")});
            }
            tabelTransaksiPenjualan.setModel(model);
        } catch (Exception e) {

        }
    }
    
    private void laporan() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO FAKTUR");
        model.addColumn("NAMA KASIR");
        model.addColumn("TANGGAL");
        model.addColumn("HARGA TOTAL");
        model.addColumn("BAYAR");
        model.addColumn("KEMBALIAN");

        String cari = cariTransaksiPenjualan.getText();

        try {
            String sql = "SELECT * FROM transaksi_penjualan WHERE tanggal LIKE'%" + cari + "%'";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("no_faktur"), res.getString("username"), res.getString("tanggal"),
                    res.getString("harga_total"),
                    res.getString("bayar"), res.getString("kembali")});
            }
            tabelTransaksiPenjualan.setModel(model);
        } catch (Exception e) {

        }
    }

    private void Tabel_Akun() {
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("USERNAME");
        model.addColumn("NAMA");
        model.addColumn("LEVEL");
        model.addColumn("NO HP");
        model.addColumn("ALAMAT");
        model.addColumn("PASSWORD");

        String cari = cariAkun.getText();

        try {
            String sql = "SELECT username,nama,alamat,level,no_telepon,password FROM petugas WHERE CONCAT(username,nama) LIKE'%" + cari + "%'";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("username"), res.getString("nama"), res.getString("level"),
                    res.getString("no_telepon"), res.getString("alamat"), res.getString("password")});
            }
            tabelAkun.setModel(model);
        } catch (Exception e) {

        }
    }

    private void Tabel_DataBarang() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("STOK ECER");
        model.addColumn("STOK GROSIR");
        model.addColumn("HARGA ECER");
        model.addColumn("HARGA GROSIR");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT id_barang,nama_barang,stok_ecer,stok_grosir,harga_eceran,harga_grosir FROM barang WHERE nama_barang LIKE'%" + cari + "%'";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    
                    res.getString("id_barang"), res.getString("nama_barang"), res.getString("stok_ecer"),
                    res.getString("stok_grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
            }
            data_barang.setModel(model);
        } catch (Exception e) {

        }
    }

    private void tblelaporanpenjualanbulanan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jumlah Transaksi");
        model.addColumn("Haraga Total");
        model.addColumn("Jumlah Pelanggan");
        model.addColumn("Bulan");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT COUNT(transaksi_penjualan.no_faktur) AS jm, SUM(transaksi_penjualan.Harga_Total) AS totl, COUNT(transaksi_penjualan.Id_pelanggan) AS jumlahid, DATE_FORMAT(transaksi_penjualan.tanggal, '%M') AS tgl FROM transaksi_penjualan GROUP by tgl DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    res.getString("jm"), res.getString("totl"), res.getString("jumlahid"),
                    res.getString("tgl")});
            }
            tabelDataLaporan.setModel(model);
        } catch (Exception e) {

        }
    }
    private void tblelaporanpenjualantahunan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jumlah Transaksi");
        model.addColumn("Haraga Total");
        model.addColumn("Jumlah Pelanggan");
        model.addColumn("Tahun");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT COUNT(transaksi_penjualan.no_faktur) AS jm, SUM(transaksi_penjualan.Harga_Total) AS totl, COUNT(transaksi_penjualan.Id_pelanggan) AS jumlahid, DATE_FORMAT(transaksi_penjualan.tanggal, '%Y') AS tgl FROM transaksi_penjualan GROUP by tgl DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    
                    res.getString("jm"), res.getString("totl"), res.getString("jumlahid"),
                    res.getString("tgl")});
            }
            tabelDataLaporan.setModel(model);
        } catch (Exception e) {

        }
        
    }
    private void tblelaporanpenjualanharian(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jumlah Transaksi");
        model.addColumn("Haraga Total");
        model.addColumn("Jumlah Pelanggan");
        model.addColumn("Hari");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT COUNT(transaksi_penjualan.no_faktur) AS jm, SUM(transaksi_penjualan.Harga_Total) AS totl, COUNT(transaksi_penjualan.Id_pelanggan) AS jumlahid, DATE_FORMAT(transaksi_penjualan.tanggal, '%d') AS tgl FROM transaksi_penjualan GROUP by tgl DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    
                    res.getString("jm"), res.getString("totl"), res.getString("jumlahid"),
                    res.getString("tgl")});
            }
            tabelDataLaporan.setModel(model);
        } catch (Exception e) {

        }
    }
    
    private void tblelaporanpembelianharian(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jumlah Transaksi");
        model.addColumn("Haraga Total");
        model.addColumn("hari");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT COUNT(transaksi_pembelian.Id_pembelian) AS jm, SUM(transaksi_pembelian.Harga_Total) AS totl, DATE_FORMAT(transaksi_pembelian.tanggal, '%d') AS tgl FROM transaksi_pembelian GROUP by tgl DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    
                    res.getString("jm"), res.getString("totl"),
                    res.getString("tgl")});
            }
            tabelDataLaporan.setModel(model);
        } catch (Exception e) {

        }
    }
    
    private void tblelaporanpembelianbulanan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jumlah Transaksi");
        model.addColumn("Haraga Total");
        model.addColumn("hari");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT COUNT(transaksi_pembelian.Id_pembelian) AS jm, SUM(transaksi_pembelian.Harga_Total) AS totl, DATE_FORMAT(transaksi_pembelian.tanggal, '%M') AS tgl FROM transaksi_pembelian GROUP by tgl DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    
                    res.getString("jm"), res.getString("totl"),
                    res.getString("tgl")});
            }
            tabelDataLaporan.setModel(model);
        } catch (Exception e) {

        }
    }
    
    
    private void tblelaporanpembeliantahunan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jumlah Transaksi");
        model.addColumn("Jumlah Id Pembelian");
        model.addColumn("hari");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT COUNT(transaksi_pembelian.Id_pembelian) AS jm, SUM(transaksi_pembelian.Harga_Total) AS totl, DATE_FORMAT(transaksi_pembelian.tanggal, '%Y') AS tgl FROM transaksi_pembelian GROUP by tgl DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    
                    res.getString("jm"), res.getString("totl"),
                    res.getString("tgl")});
            }
            tabelDataLaporan.setModel(model);
        } catch (Exception e) {

        }
    }
    
    private void tblelaporanreturnbulanan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jumlah Transaksi");
        model.addColumn("Jumlah Id Pembelian");
        model.addColumn("Bulanan");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT COUNT(return_barang.id_return) AS jm, COUNT(return_barang.id_pembelian) AS totl, DATE_FORMAT(return_barang.tanggal, '%M') AS tgl FROM return_barang GROUP by tgl DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    
                    res.getString("jm"), res.getString("totl"),
                    res.getString("tgl")});
            }
            tabelDataLaporan.setModel(model);
        } catch (Exception e) {

        }
    }
    
    private void tblelaporanreturntahunan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jumlah Transaksi");
        model.addColumn("jumlah Idpembelian");
        model.addColumn("tahun");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT COUNT(return_barang.id_return) AS jm, COUNT(return_barang.id_pembelian) AS totl, DATE_FORMAT(return_barang.tanggal, '%d') AS tgl FROM return_barang GROUP by tgl DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    
                    res.getString("jm"), res.getString("totl"),
                    res.getString("tgl")});
            }
            tabelDataLaporan.setModel(model);
        } catch (Exception e) {

        }
    }
    
    private void tblelaporanreturnharian(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jumlah Transaksi");
        model.addColumn("jumlah Idpembelian");
        model.addColumn("tahun");

        String cari = caridatabarang.getText();

        try {
            String sql = "SELECT COUNT(return_barang.id_return) AS jm, COUNT(return_barang.id_pembelian) AS totl, DATE_FORMAT(return_barang.tanggal, '%d') AS tgl FROM return_barang GROUP by tgl DESC;";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                model.addRow(new Object[]{
                    
                    res.getString("jm"), res.getString("totl"),
                    res.getString("tgl")});
            }
            tabelDataLaporan.setModel(model);
        } catch (Exception e) {

        }
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OWNER = new javax.swing.JPanel();
        MENUSAMPING = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DashboardMenu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        AKUNMENU = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TRANSAKSIMENU = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        DATABARANGMENU = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        SUPPLIERMENU = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        DATALAPORANMENU = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        DATAPELANGGAN = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        DATARETURNMENU = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        KeluarMenu = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        navbar = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        KONTEN = new javax.swing.JPanel();
        Dashboard = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        barChart2 = new javaswingdev.chart.BarChart();
        barChart1 = new javaswingdev.chart.BarChart();
        barangdashboard = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        barChart3 = new javaswingdev.chart.BarChart();
        DataBarang = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        caridatabarang = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        data_barang = new javax.swing.JTable();
        editdatabarang = new javax.swing.JButton();
        hapusdatabarang = new javax.swing.JButton();
        IdBarang = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        NamaDataBarang = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        HargaDataBarang = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        memilihsatuan = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        TRANSAKSI = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        TransaksiPenjualan1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cariTransaksiPenjualan = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelTransaksiPenjualan = new javax.swing.JTable();
        unduhTransaksiPenjualan = new javax.swing.JButton();
        TransaksiPembelian1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cariTransaksiPembelian = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelTransaksiPembelian = new javax.swing.JTable();
        unduhTransaksiPembelian = new javax.swing.JButton();
        DataLaporan = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        pilihDataLaporan = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelDataLaporan = new javax.swing.JTable();
        Simpan5 = new javax.swing.JButton();
        PilihLaporan = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        Profil = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_nohpProfil = new javax.swing.JTextField();
        simpanProfil = new javax.swing.JButton();
        gambarProfil = new javax.swing.JLabel();
        txt_gambarprofil = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        Suplier = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        CariSupplier = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txt_idSupplier = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Tabel_suplier = new javax.swing.JTable();
        btn_simpansupplier = new javax.swing.JButton();
        btn_edit_supplier = new javax.swing.JButton();
        btn_hapusSupplier = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        txt_nohpSupplier = new javax.swing.JTextField();
        txt_namaSupplier = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        alamatSupplier = new javax.swing.JTextArea();
        Akun = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cariAkun = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        namaAkun = new javax.swing.JTextField();
        usernameAkun = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabelAkun = new javax.swing.JTable();
        simpanAkun = new javax.swing.JButton();
        editAkun = new javax.swing.JButton();
        hapusAkun = new javax.swing.JButton();
        pilihanAkun = new javax.swing.JComboBox<>();
        noHpAkun = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        gambar = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        passwordAkun = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        btn_unggah = new javax.swing.JButton();
        txt_filename = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamatAkun = new javax.swing.JTextArea();
        DataReturn = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        pilihReturn = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        CariReturn6 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelReturnBarang = new javax.swing.JTable();
        Simpan7 = new javax.swing.JButton();
        DataPelanggan = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        cariDataPelanggan = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelDataPelanggan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OWNER.setBackground(new java.awt.Color(196, 196, 196));
        OWNER.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MENUSAMPING.setBackground(new java.awt.Color(0, 9, 87));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Angkringan__1_-removebg-preview 7baruuuuuuu.jpg"))); // NOI18N

        DashboardMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DashboardMenu.setEnabled(false);
        DashboardMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardMenuMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/dasboardbarubaru.png"))); // NOI18N
        jLabel2.setText(" DASHBOARD");
        jLabel2.setToolTipText("");
        jLabel2.setAutoscrolls(true);
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout DashboardMenuLayout = new javax.swing.GroupLayout(DashboardMenu);
        DashboardMenu.setLayout(DashboardMenuLayout);
        DashboardMenuLayout.setHorizontalGroup(
            DashboardMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardMenuLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        DashboardMenuLayout.setVerticalGroup(
            DashboardMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        AKUNMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AKUNMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AKUNMENUMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/baruuuuuuuuuuuuuu.png"))); // NOI18N
        jLabel3.setText("  AKUN");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setRequestFocusEnabled(false);
        jLabel3.setVerifyInputWhenFocusTarget(false);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout AKUNMENULayout = new javax.swing.GroupLayout(AKUNMENU);
        AKUNMENU.setLayout(AKUNMENULayout);
        AKUNMENULayout.setHorizontalGroup(
            AKUNMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AKUNMENULayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        AKUNMENULayout.setVerticalGroup(
            AKUNMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AKUNMENULayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TRANSAKSIMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TRANSAKSIMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TRANSAKSIMENUMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/dashboardbaru.png"))); // NOI18N
        jLabel4.setText("  TRANSAKSI");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout TRANSAKSIMENULayout = new javax.swing.GroupLayout(TRANSAKSIMENU);
        TRANSAKSIMENU.setLayout(TRANSAKSIMENULayout);
        TRANSAKSIMENULayout.setHorizontalGroup(
            TRANSAKSIMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TRANSAKSIMENULayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        TRANSAKSIMENULayout.setVerticalGroup(
            TRANSAKSIMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TRANSAKSIMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        DATABARANGMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DATABARANGMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DATABARANGMENUMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/dataBarangBruuuu.png"))); // NOI18N
        jLabel5.setText("  DATA BARANG");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout DATABARANGMENULayout = new javax.swing.GroupLayout(DATABARANGMENU);
        DATABARANGMENU.setLayout(DATABARANGMENULayout);
        DATABARANGMENULayout.setHorizontalGroup(
            DATABARANGMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATABARANGMENULayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DATABARANGMENULayout.setVerticalGroup(
            DATABARANGMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATABARANGMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        SUPPLIERMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SUPPLIERMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SUPPLIERMENUMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/supplierbaruuuu.png"))); // NOI18N
        jLabel6.setText("  SUPLIER");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout SUPPLIERMENULayout = new javax.swing.GroupLayout(SUPPLIERMENU);
        SUPPLIERMENU.setLayout(SUPPLIERMENULayout);
        SUPPLIERMENULayout.setHorizontalGroup(
            SUPPLIERMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SUPPLIERMENULayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        SUPPLIERMENULayout.setVerticalGroup(
            SUPPLIERMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SUPPLIERMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        DATALAPORANMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DATALAPORANMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DATALAPORANMENUMouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/datalaporanbaruuuuuuu.png"))); // NOI18N
        jLabel33.setText("  DATA LAPORAN");
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout DATALAPORANMENULayout = new javax.swing.GroupLayout(DATALAPORANMENU);
        DATALAPORANMENU.setLayout(DATALAPORANMENULayout);
        DATALAPORANMENULayout.setHorizontalGroup(
            DATALAPORANMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATALAPORANMENULayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel33)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        DATALAPORANMENULayout.setVerticalGroup(
            DATALAPORANMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATALAPORANMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        DATAPELANGGAN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DATAPELANGGAN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DATAPELANGGANMouseClicked(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/dataPelanggan.png"))); // NOI18N
        jLabel43.setText("  PELANGGAN");
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout DATAPELANGGANLayout = new javax.swing.GroupLayout(DATAPELANGGAN);
        DATAPELANGGAN.setLayout(DATAPELANGGANLayout);
        DATAPELANGGANLayout.setHorizontalGroup(
            DATAPELANGGANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATAPELANGGANLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel43)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DATAPELANGGANLayout.setVerticalGroup(
            DATAPELANGGANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATAPELANGGANLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        DATARETURNMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DATARETURNMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DATARETURNMENUMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/returnbaruuuuu.png"))); // NOI18N
        jLabel7.setText(" DATA RETURN");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout DATARETURNMENULayout = new javax.swing.GroupLayout(DATARETURNMENU);
        DATARETURNMENU.setLayout(DATARETURNMENULayout);
        DATARETURNMENULayout.setHorizontalGroup(
            DATARETURNMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATARETURNMENULayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DATARETURNMENULayout.setVerticalGroup(
            DATARETURNMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATARETURNMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        KeluarMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        KeluarMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KeluarMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                KeluarMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KeluarMenuMouseExited(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/logoutdiowner.png"))); // NOI18N
        jLabel42.setText("KELUAR");
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel42MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel42MouseExited(evt);
            }
        });

        javax.swing.GroupLayout KeluarMenuLayout = new javax.swing.GroupLayout(KeluarMenu);
        KeluarMenu.setLayout(KeluarMenuLayout);
        KeluarMenuLayout.setHorizontalGroup(
            KeluarMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KeluarMenuLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KeluarMenuLayout.setVerticalGroup(
            KeluarMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KeluarMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MENUSAMPINGLayout = new javax.swing.GroupLayout(MENUSAMPING);
        MENUSAMPING.setLayout(MENUSAMPINGLayout);
        MENUSAMPINGLayout.setHorizontalGroup(
            MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MENUSAMPINGLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(MENUSAMPINGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KeluarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DATABARANGMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MENUSAMPINGLayout.createSequentialGroup()
                        .addGroup(MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DATALAPORANMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DashboardMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AKUNMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TRANSAKSIMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SUPPLIERMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DATARETURNMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(DATAPELANGGAN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        MENUSAMPINGLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {AKUNMENU, DATALAPORANMENU, DashboardMenu, SUPPLIERMENU, TRANSAKSIMENU});

        MENUSAMPINGLayout.setVerticalGroup(
            MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MENUSAMPINGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DashboardMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AKUNMENU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TRANSAKSIMENU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DATABARANGMENU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SUPPLIERMENU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DATALAPORANMENU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DATARETURNMENU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DATAPELANGGAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(KeluarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        MENUSAMPINGLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {DATALAPORANMENU, SUPPLIERMENU, TRANSAKSIMENU});

        OWNER.add(MENUSAMPING, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 720));

        navbar.setBackground(new java.awt.Color(240, 225, 89));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Profil1.png"))); // NOI18N
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navbarLayout.createSequentialGroup()
                .addContainerGap(1161, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(60, 60, 60))
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        OWNER.add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 80));

        KONTEN.setBackground(new java.awt.Color(196, 196, 196));
        KONTEN.setLayout(new java.awt.CardLayout());

        Dashboard.setPreferredSize(new java.awt.Dimension(650, 350));

        jLabel8.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/ICONDASHBOARDOWNER.png"))); // NOI18N
        jLabel8.setText(" DASHBOARD");

        barChart2.setForeground(new java.awt.Color(0, 0, 0));

        barChart1.setForeground(new java.awt.Color(0, 0, 0));

        barangdashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barangdashboardActionPerformed(evt);
            }
        });
        barangdashboard.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                barangdashboardKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("RECORD BARANG");

        barChart3.setBackground(new java.awt.Color(255, 255, 255));
        barChart3.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(barangdashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addComponent(barChart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(55, 55, 55)
                        .addComponent(barChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
            .addGroup(DashboardLayout.createSequentialGroup()
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8))
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(barChart3, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barChart2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barangdashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barChart3, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        KONTEN.add(Dashboard, "card2");

        jLabel14.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Databarang1.png"))); // NOI18N
        jLabel14.setText(" DATA BARANG");
        jLabel14.setToolTipText("");

        caridatabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caridatabarangActionPerformed(evt);
            }
        });
        caridatabarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                caridatabarangKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel15.setText("Cari : ");

        data_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        data_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                data_barangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(data_barang);

        editdatabarang.setBackground(new java.awt.Color(240, 225, 89));
        editdatabarang.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        editdatabarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/edit.png"))); // NOI18N
        editdatabarang.setText("EDIT");
        editdatabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editdatabarangActionPerformed(evt);
            }
        });

        hapusdatabarang.setBackground(new java.awt.Color(240, 225, 89));
        hapusdatabarang.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        hapusdatabarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/delete (1).png"))); // NOI18N
        hapusdatabarang.setText("HAPUS");
        hapusdatabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusdatabarangActionPerformed(evt);
            }
        });

        IdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdBarangActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel16.setText("ID BARANG");

        jLabel17.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel17.setText("NAMA BARANG");

        HargaDataBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HargaDataBarangActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel18.setText("HARGA");

        memilihsatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Satuan", "ECERAN", "GROSIR" }));

        jLabel20.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel20.setText("SATUAN");

        javax.swing.GroupLayout DataBarangLayout = new javax.swing.GroupLayout(DataBarang);
        DataBarang.setLayout(DataBarangLayout);
        DataBarangLayout.setHorizontalGroup(
            DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataBarangLayout.createSequentialGroup()
                .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataBarangLayout.createSequentialGroup()
                        .addGap(566, 566, 566)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caridatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DataBarangLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DataBarangLayout.createSequentialGroup()
                                .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(DataBarangLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 334, Short.MAX_VALUE))
                                    .addGroup(DataBarangLayout.createSequentialGroup()
                                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(DataBarangLayout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(IdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(DataBarangLayout.createSequentialGroup()
                                                .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(jLabel20)
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(NamaDataBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(memilihsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(HargaDataBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(DataBarangLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(editdatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(hapusdatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DataBarangLayout.setVerticalGroup(
            DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(23, 23, 23)
                .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caridatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataBarangLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NamaDataBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(memilihsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HargaDataBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addContainerGap(321, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataBarangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editdatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hapusdatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
        );

        KONTEN.add(DataBarang, "card6");

        jLabel9.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/transaksieksen.png"))); // NOI18N
        jLabel9.setText(" TRANSAKSI");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/transaksipenjualan.png"))); // NOI18N
        jButton1.setText("TRANSAKSI PENJUALAN");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setIconTextGap(25);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/transaksipembelian.png"))); // NOI18N
        jButton3.setText("TRANSAKSI PEMBELIAN");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setIconTextGap(23);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TRANSAKSILayout = new javax.swing.GroupLayout(TRANSAKSI);
        TRANSAKSI.setLayout(TRANSAKSILayout);
        TRANSAKSILayout.setHorizontalGroup(
            TRANSAKSILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TRANSAKSILayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(TRANSAKSILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(TRANSAKSILayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        TRANSAKSILayout.setVerticalGroup(
            TRANSAKSILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TRANSAKSILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(79, 79, 79)
                .addGroup(TRANSAKSILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        KONTEN.add(TRANSAKSI, "card3");

        jLabel10.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/transaksieksen.png"))); // NOI18N
        jLabel10.setText("TRANSAKSI PENJUALAN");
        jLabel10.setToolTipText("");

        jLabel11.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel11.setText("Cari : ");

        cariTransaksiPenjualan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTransaksiPenjualanKeyReleased(evt);
            }
        });

        tabelTransaksiPenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabelTransaksiPenjualan);

        unduhTransaksiPenjualan.setBackground(new java.awt.Color(240, 225, 89));
        unduhTransaksiPenjualan.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        unduhTransaksiPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/unduh.png"))); // NOI18N
        unduhTransaksiPenjualan.setText("UNDUH");
        unduhTransaksiPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unduhTransaksiPenjualanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransaksiPenjualan1Layout = new javax.swing.GroupLayout(TransaksiPenjualan1);
        TransaksiPenjualan1.setLayout(TransaksiPenjualan1Layout);
        TransaksiPenjualan1Layout.setHorizontalGroup(
            TransaksiPenjualan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiPenjualan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TransaksiPenjualan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPenjualan1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiPenjualan1Layout.createSequentialGroup()
                        .addGap(0, 509, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(cariTransaksiPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))))
            .addGroup(TransaksiPenjualan1Layout.createSequentialGroup()
                .addGroup(TransaksiPenjualan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPenjualan1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(unduhTransaksiPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransaksiPenjualan1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        TransaksiPenjualan1Layout.setVerticalGroup(
            TransaksiPenjualan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiPenjualan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(34, 34, 34)
                .addGroup(TransaksiPenjualan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariTransaksiPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(unduhTransaksiPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        KONTEN.add(TransaksiPenjualan1, "card4");

        jLabel12.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/transaksieksen.png"))); // NOI18N
        jLabel12.setText("TRANSAKSI PEMBELIAN");
        jLabel12.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel13.setText("Cari : ");

        cariTransaksiPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariTransaksiPembelianActionPerformed(evt);
            }
        });
        cariTransaksiPembelian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTransaksiPembelianKeyReleased(evt);
            }
        });

        tabelTransaksiPembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tabelTransaksiPembelian);

        unduhTransaksiPembelian.setBackground(new java.awt.Color(240, 225, 89));
        unduhTransaksiPembelian.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        unduhTransaksiPembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/unduh.png"))); // NOI18N
        unduhTransaksiPembelian.setText("UNDUH");
        unduhTransaksiPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unduhTransaksiPembelianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransaksiPembelian1Layout = new javax.swing.GroupLayout(TransaksiPembelian1);
        TransaksiPembelian1.setLayout(TransaksiPembelian1Layout);
        TransaksiPembelian1Layout.setHorizontalGroup(
            TransaksiPembelian1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiPembelian1Layout.createSequentialGroup()
                .addGroup(TransaksiPembelian1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiPembelian1Layout.createSequentialGroup()
                        .addGap(524, 524, 524)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariTransaksiPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransaksiPembelian1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12))
                    .addGroup(TransaksiPembelian1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransaksiPembelian1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(unduhTransaksiPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        TransaksiPembelian1Layout.setVerticalGroup(
            TransaksiPembelian1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiPembelian1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(39, 39, 39)
                .addGroup(TransaksiPembelian1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariTransaksiPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(unduhTransaksiPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        KONTEN.add(TransaksiPembelian1, "card5");

        jLabel21.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/laporankonten.png"))); // NOI18N
        jLabel21.setText(" DATA LAPORAN");
        jLabel21.setToolTipText("");

        jLabel22.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel22.setText("Pilih Waktu");

        pilihDataLaporan.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        pilihDataLaporan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH", "BULAN", "TAHUN" }));
        pilihDataLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihDataLaporanActionPerformed(evt);
            }
        });

        tabelDataLaporan.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        tabelDataLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tabelDataLaporan);

        Simpan5.setBackground(new java.awt.Color(240, 225, 89));
        Simpan5.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        Simpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/unduh.png"))); // NOI18N
        Simpan5.setText("UNDUH");
        Simpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Simpan5ActionPerformed(evt);
            }
        });

        PilihLaporan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENJUALAN", "PEMBELIAN", "RETURN" }));
        PilihLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PilihLaporanActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel52.setText("Pilih Laporan");

        javax.swing.GroupLayout DataLaporanLayout = new javax.swing.GroupLayout(DataLaporan);
        DataLaporan.setLayout(DataLaporanLayout);
        DataLaporanLayout.setHorizontalGroup(
            DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataLaporanLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE))
            .addGroup(DataLaporanLayout.createSequentialGroup()
                .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataLaporanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21))
                    .addGroup(DataLaporanLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(PilihLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(45, 45, 45)
                .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataLaporanLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(DataLaporanLayout.createSequentialGroup()
                        .addComponent(pilihDataLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 535, Short.MAX_VALUE))))
        );
        DataLaporanLayout.setVerticalGroup(
            DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataLaporanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pilihDataLaporan)
                    .addComponent(PilihLaporan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        KONTEN.add(DataLaporan, "card8");

        Profil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Vector (4).png"))); // NOI18N
        jLabel24.setText("Profil");
        jLabel24.setToolTipText("");
        Profil.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 168, 43));

        jLabel25.setFont(new java.awt.Font("Roboto Slab", 0, 16)); // NOI18N
        jLabel25.setText("Username");
        Profil.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, -1, 29));

        txt_usernameProfil.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        txt_usernameProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameProfilActionPerformed(evt);
            }
        });
        Profil.add(txt_usernameProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 245, 29));

        jLabel26.setFont(new java.awt.Font("Roboto Slab", 0, 16)); // NOI18N
        jLabel26.setText("Level");
        Profil.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, -1, 27));

        jLabel27.setFont(new java.awt.Font("Roboto Slab", 0, 16)); // NOI18N
        jLabel27.setText("Nama");
        Profil.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, -1, 27));

        jLabel28.setFont(new java.awt.Font("Roboto Slab", 0, 16)); // NOI18N
        jLabel28.setText("No.handphone");
        Profil.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, -1, 33));

        txt_levelProfil.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        txt_levelProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_levelProfilActionPerformed(evt);
            }
        });
        Profil.add(txt_levelProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 245, 29));

        txt_namaProfil.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        txt_namaProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaProfilActionPerformed(evt);
            }
        });
        Profil.add(txt_namaProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 245, 29));

        txt_nohpProfil.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        txt_nohpProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nohpProfilActionPerformed(evt);
            }
        });
        Profil.add(txt_nohpProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, 245, 29));

        simpanProfil.setBackground(new java.awt.Color(240, 225, 89));
        simpanProfil.setFont(new java.awt.Font("Roboto Slab", 0, 13)); // NOI18N
        simpanProfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/diskette.png"))); // NOI18N
        simpanProfil.setText(" SIMPAN");
        simpanProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanProfilActionPerformed(evt);
            }
        });
        Profil.add(simpanProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 550, 120, 43));

        gambarProfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gambarProfil.setText("foto");
        gambarProfil.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Profil.add(gambarProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 139, 144));

        txt_gambarprofil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_gambarprofilActionPerformed(evt);
            }
        });
        Profil.add(txt_gambarprofil, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 180, -1));

        jButton4.setBackground(new java.awt.Color(240, 225, 89));
        jButton4.setFont(new java.awt.Font("Roboto Slab", 0, 13)); // NOI18N
        jButton4.setText("UNGGAH");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        Profil.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, -1, -1));

        KONTEN.add(Profil, "card9");

        jLabel46.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Suplier1.png"))); // NOI18N
        jLabel46.setText(" SUPPLIER");
        jLabel46.setToolTipText("");

        jLabel47.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel47.setText("ID SUPPLIER");

        CariSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariSupplierActionPerformed(evt);
            }
        });
        CariSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CariSupplierKeyReleased(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel48.setText("Cari : ");

        txt_idSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idSupplierActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel49.setText("NAMA SUPPLIER");

        jLabel50.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel50.setText("ALAMAT");

        Tabel_suplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabel_suplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel_suplierMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(Tabel_suplier);

        btn_simpansupplier.setBackground(new java.awt.Color(240, 225, 89));
        btn_simpansupplier.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        btn_simpansupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/diskette.png"))); // NOI18N
        btn_simpansupplier.setText("SIMPAN");
        btn_simpansupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpansupplierActionPerformed(evt);
            }
        });

        btn_edit_supplier.setBackground(new java.awt.Color(240, 225, 89));
        btn_edit_supplier.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        btn_edit_supplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/edit.png"))); // NOI18N
        btn_edit_supplier.setText("EDIT");
        btn_edit_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_supplierActionPerformed(evt);
            }
        });

        btn_hapusSupplier.setBackground(new java.awt.Color(240, 225, 89));
        btn_hapusSupplier.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        btn_hapusSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/delete (1).png"))); // NOI18N
        btn_hapusSupplier.setText("HAPUS");
        btn_hapusSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusSupplierActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel51.setText("NO HP");

        txt_nohpSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nohpSupplierActionPerformed(evt);
            }
        });

        txt_namaSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaSupplierActionPerformed(evt);
            }
        });

        alamatSupplier.setColumns(20);
        alamatSupplier.setRows(5);
        jScrollPane10.setViewportView(alamatSupplier);

        javax.swing.GroupLayout SuplierLayout = new javax.swing.GroupLayout(Suplier);
        Suplier.setLayout(SuplierLayout);
        SuplierLayout.setHorizontalGroup(
            SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuplierLayout.createSequentialGroup()
                .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SuplierLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CariSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SuplierLayout.createSequentialGroup()
                            .addGap(79, 79, 79)
                            .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel49)
                                .addComponent(jLabel47)
                                .addComponent(jLabel51)
                                .addComponent(jLabel50))
                            .addGap(61, 61, 61)
                            .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_idSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                .addComponent(txt_namaSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                .addComponent(txt_nohpSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                .addComponent(jScrollPane10)))
                        .addGroup(SuplierLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btn_simpansupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_edit_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_hapusSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SuplierLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SuplierLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel46))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        SuplierLayout.setVerticalGroup(
            SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuplierLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CariSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(11, 11, 11)
                        .addComponent(txt_idSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SuplierLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuplierLayout.createSequentialGroup()
                        .addComponent(txt_namaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_nohpSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SuplierLayout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_edit_supplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_simpansupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_hapusSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        KONTEN.add(Suplier, "card7");

        jLabel34.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/akun.png"))); // NOI18N
        jLabel34.setText(" AKUN");
        jLabel34.setToolTipText("");

        jLabel35.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel35.setText("USERNAME");

        cariAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariAkunActionPerformed(evt);
            }
        });
        cariAkun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariAkunKeyReleased(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel36.setText("Cari : ");

        namaAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaAkunActionPerformed(evt);
            }
        });

        usernameAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameAkunActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel37.setText("NAMA");

        jLabel38.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel38.setText("LEVEL");

        tabelAkun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelAkun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelAkunMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tabelAkun);

        simpanAkun.setBackground(new java.awt.Color(240, 225, 89));
        simpanAkun.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        simpanAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/diskette.png"))); // NOI18N
        simpanAkun.setText("SIMPAN");
        simpanAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanAkunActionPerformed(evt);
            }
        });

        editAkun.setBackground(new java.awt.Color(240, 225, 89));
        editAkun.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        editAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/edit.png"))); // NOI18N
        editAkun.setText("EDIT");
        editAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAkunActionPerformed(evt);
            }
        });

        hapusAkun.setBackground(new java.awt.Color(240, 225, 89));
        hapusAkun.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        hapusAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/delete (1).png"))); // NOI18N
        hapusAkun.setText("HAPUS");
        hapusAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusAkunActionPerformed(evt);
            }
        });

        pilihanAkun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Salah Satu", "OWNER", "KASIR" }));

        noHpAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noHpAkunActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel39.setText("NO HP");

        gambar.setForeground(new java.awt.Color(255, 255, 255));
        gambar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FOTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 10), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel40.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel40.setText("ALAMAT");

        passwordAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordAkunActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel41.setText("PASSWORD");

        btn_unggah.setBackground(new java.awt.Color(240, 225, 89));
        btn_unggah.setText("UNGGAH");
        btn_unggah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_unggahActionPerformed(evt);
            }
        });

        txt_filename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_filenameActionPerformed(evt);
            }
        });

        alamatAkun.setColumns(20);
        alamatAkun.setRows(5);
        jScrollPane1.setViewportView(alamatAkun);

        javax.swing.GroupLayout AkunLayout = new javax.swing.GroupLayout(Akun);
        Akun.setLayout(AkunLayout);
        AkunLayout.setHorizontalGroup(
            AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AkunLayout.createSequentialGroup()
                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(472, 472, 472)
                        .addComponent(jLabel36)
                        .addGap(6, 6, 6)
                        .addComponent(cariAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel37)
                            .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel39)
                                .addComponent(jLabel38)))
                        .addGap(39, 39, 39)
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noHpAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AkunLayout.createSequentialGroup()
                                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namaAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usernameAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pilihanAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel40))
                                .addGap(18, 18, 18)
                                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(passwordAkun)
                                    .addComponent(jScrollPane1))))
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(AkunLayout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(txt_filename, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(264, 264, 264))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AkunLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_unggah)
                                    .addGap(321, 321, 321)))
                            .addGroup(AkunLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(simpanAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hapusAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );
        AkunLayout.setVerticalGroup(
            AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AkunLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AkunLayout.createSequentialGroup()
                            .addGap(194, 194, 194)
                            .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(noHpAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel39)))
                        .addGroup(AkunLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)
                            .addComponent(txt_filename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_unggah, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(passwordAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addGap(20, 20, 20)
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AkunLayout.createSequentialGroup()
                                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(namaAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40))
                                .addGap(20, 20, 20)
                                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pilihanAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(simpanAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapusAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        KONTEN.add(Akun, "card7");

        jLabel30.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/laporankonten.png"))); // NOI18N
        jLabel30.setText(" DATA RETURN");
        jLabel30.setToolTipText("");

        jLabel31.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel31.setText("Pilih");

        pilihReturn.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        pilihReturn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH", "BULAN", "TAHUN" }));
        pilihReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihReturnActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel32.setText("Cari : ");

        CariReturn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariReturn6ActionPerformed(evt);
            }
        });
        CariReturn6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CariReturn6KeyReleased(evt);
            }
        });

        tabelReturnBarang.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        tabelReturnBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tabelReturnBarang);

        Simpan7.setBackground(new java.awt.Color(240, 225, 89));
        Simpan7.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        Simpan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/unduh.png"))); // NOI18N
        Simpan7.setText("UNDUH");
        Simpan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Simpan7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DataReturnLayout = new javax.swing.GroupLayout(DataReturn);
        DataReturn.setLayout(DataReturnLayout);
        DataReturnLayout.setHorizontalGroup(
            DataReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataReturnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DataReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataReturnLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(DataReturnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(DataReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DataReturnLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(DataReturnLayout.createSequentialGroup()
                                .addComponent(pilihReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CariReturn6, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98))))))
            .addGroup(DataReturnLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(DataReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Simpan7))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        DataReturnLayout.setVerticalGroup(
            DataReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataReturnLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel30)
                .addGap(29, 29, 29)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DataReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pilihReturn))
                    .addComponent(CariReturn6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Simpan7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        KONTEN.add(DataReturn, "card8");

        jLabel44.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/transaksieksen.png"))); // NOI18N
        jLabel44.setText("DATA PELANGGAN");
        jLabel44.setToolTipText("");

        jLabel45.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel45.setText("Cari : ");

        cariDataPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariDataPelangganActionPerformed(evt);
            }
        });
        cariDataPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariDataPelangganKeyReleased(evt);
            }
        });

        tabelDataPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tabelDataPelanggan);

        javax.swing.GroupLayout DataPelangganLayout = new javax.swing.GroupLayout(DataPelanggan);
        DataPelanggan.setLayout(DataPelangganLayout);
        DataPelangganLayout.setHorizontalGroup(
            DataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataPelangganLayout.createSequentialGroup()
                .addGroup(DataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataPelangganLayout.createSequentialGroup()
                        .addGap(524, 524, 524)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariDataPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DataPelangganLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel44))
                    .addGroup(DataPelangganLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        DataPelangganLayout.setVerticalGroup(
            DataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addGap(39, 39, 39)
                .addGroup(DataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariDataPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );

        KONTEN.add(DataPelanggan, "card5");

        OWNER.add(KONTEN, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 80, 980, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OWNER, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OWNER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void unduhTransaksiPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unduhTransaksiPenjualanActionPerformed
        // TODO add your handling code here:
        MessageFormat judul = new MessageFormat(" DATA TRANSAKSI PENJUALAN") ;
        MessageFormat footer = new MessageFormat(" page(0,number,integer)") ;
        
        try {
            tabelTransaksiPenjualan.print(JTable.PrintMode.FIT_WIDTH,judul,footer);
            
        } catch (  PrinterException e) {
            System.err.print("Error Printing");
        }
        
    }//GEN-LAST:event_unduhTransaksiPenjualanActionPerformed

    private void cariTransaksiPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariTransaksiPembelianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariTransaksiPembelianActionPerformed

    private void caridatabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caridatabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caridatabarangActionPerformed

    private void editdatabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editdatabarangActionPerformed
        // TODO add your handling code here:
        if (memilihsatuan.getSelectedItem() == "ECERAN") {
            try {
                String sql = "UPDATE barang SET harga_eceran = '" + HargaDataBarang.getText() + "' where Id_barang = '" + IdBarang.getText() + "';";
                java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
                pst.executeUpdate();
                Tabel_DataBarang();
                IdBarang.setText("");
                NamaDataBarang.setText("");
                memilihsatuan.setSelectedItem("Pilih Satuan");
                HargaDataBarang.setText("");
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah  ");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Diubah");
            }
        } else if (memilihsatuan.getSelectedItem() == "GROSIR") {
            try {
                String sql = "UPDATE barang SET harga_grosir = '" + HargaDataBarang.getText() + "' where Id_barang = '" + IdBarang.getText() + "';";
                java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
                java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
                pst.executeUpdate();
                Tabel_DataBarang();
                IdBarang.setText("");
                NamaDataBarang.setText("");
                memilihsatuan.setSelectedItem("Pilih Satuan");
                HargaDataBarang.setText("");
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Disimpan");
            }
        }
    }//GEN-LAST:event_editdatabarangActionPerformed

    private void hapusdatabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusdatabarangActionPerformed
        // TODO add your handling code here:
                try {

            String sql = "DELETE  FROM barang WHERE id_barang='" + IdBarang.getText() + "';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            Tabel_DataBarang();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Dihapus");}

    }//GEN-LAST:event_hapusdatabarangActionPerformed

    private void pilihDataLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihDataLaporanActionPerformed
        // TODO add your handling code here:
       if(PilihLaporan.getSelectedItem() == "PENJUALAN"){
            
           if(pilihDataLaporan.getSelectedItem() == "PILIH"){
           tblelaporanpenjualanharian();
            
            
           }else if (pilihDataLaporan.getSelectedItem() == "BULAN"){
                tblelaporanpenjualanbulanan();
            } else if (pilihDataLaporan.getSelectedItem() == "TAHUN"){
                tblelaporanpenjualantahunan();
            }
            
            
        } else if (PilihLaporan.getSelectedItem() == "PEMBELIAN"){
            tblelaporanpembelianharian();
            
            
            if(pilihDataLaporan.getSelectedItem() == "PILIH"){
          tblelaporanpembelianharian();
            
            
           }else if (pilihDataLaporan.getSelectedItem() == "BULAN"){
                tblelaporanpembelianbulanan();
            } else if (pilihDataLaporan.getSelectedItem() == "TAHUN"){
                tblelaporanpembeliantahunan();
            }
            
            
        } else if(PilihLaporan.getSelectedItem() == "RETURN"){
            
            
            
            if(pilihDataLaporan.getSelectedItem() == "PILIH"){
           tblelaporanreturnharian();
            
            
           }else if (pilihDataLaporan.getSelectedItem() == "BULAN"){
                tblelaporanreturnbulanan();
            } else if (pilihDataLaporan.getSelectedItem() == "TAHUN"){
                tblelaporanreturntahunan();
            }
            
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "salah");
        }
       
        
    }//GEN-LAST:event_pilihDataLaporanActionPerformed

    private void txt_usernameProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameProfilActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_usernameProfilActionPerformed

    private void txt_levelProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_levelProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_levelProfilActionPerformed

    private void txt_namaProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaProfilActionPerformed

    private void txt_nohpProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nohpProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nohpProfilActionPerformed

    private void cariAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariAkunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariAkunActionPerformed

    private void namaAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaAkunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaAkunActionPerformed

    private void usernameAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameAkunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameAkunActionPerformed

    private void simpanAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanAkunActionPerformed
        // TODO add your handling code here:
        if (gambar.getIcon() == null) {
           try {
            FileInputStream fis=null;
            String sql = "INSERT INTO petugas (username, nama,alamat, level,no_telepon, password) Values ('" + usernameAkun.getText() + "', '" + namaAkun.getText() + "', '"
                    + alamatAkun.getText() + "', '" + pilihanAkun .getSelectedItem() + "', '" + noHpAkun.getText() + "','" + passwordAkun.getText() +"');";
            java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");

            Tabel_Akun();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Tidak berhasil Disimpan");
        }
        } else {       try {
            FileInputStream fis=null;
            String sql = "INSERT INTO petugas (username, nama,alamat, level,no_telepon, password,gambar) Values ('" + usernameAkun.getText() + "', '" + namaAkun.getText() + "', '"
                    + alamatAkun.getText() + "', '" + pilihanAkun .getSelectedItem() + "', '" + noHpAkun.getText() + "','" + passwordAkun.getText() +"','"+fis+"' );";
            java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");

            Tabel_Akun();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Tidak berhasil Disimpan");
        }
        }
  
    }//GEN-LAST:event_simpanAkunActionPerformed

    private void editAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAkunActionPerformed
        // TODO add your handling code here:
        try {
            FileInputStream fis=null;
            String sql = "UPDATE petugas SET `username` ='" + usernameAkun.getText() + "',`nama` ='" + namaAkun.getText() + "',`no_telepon` ='" + noHpAkun.getText()
                    + "',`password`='" + passwordAkun.getText() + "',`level`='"
                    + pilihanAkun.getSelectedItem() +"', alamat= '"+alamatAkun.getText()+"',`gambar`='" + fis+"' WHERE `petugas`.`username`='"
                    + usernameAkun.getText() + "';";
            java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil diubah ");

            Tabel_Akun();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Tidak berhasil diubah");
        }
    }//GEN-LAST:event_editAkunActionPerformed

    private void hapusAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusAkunActionPerformed
        // TODO add your handling code here:
        try {

            String sql = "DELETE  FROM petugas WHERE username = '" + usernameAkun.getText() + "';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            Tabel_Akun();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Dihapus");
        }
    }//GEN-LAST:event_hapusAkunActionPerformed

    private void noHpAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noHpAkunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noHpAkunActionPerformed

    private void CariSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CariSupplierActionPerformed

    private void txt_idSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idSupplierActionPerformed

    private void btn_simpansupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpansupplierActionPerformed
        // TODO add your handling code here:
              try {
            String sql = "INSERT INTO supplier (id_supplier, nama_supplier,no_telepon,alamat) Values ('" + txt_idSupplier.getText() + "', '" + txt_namaSupplier.getText() + "', '"
                    + txt_nohpSupplier.getText() + "', '" + alamatSupplier.getText()+ "');";
            java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");

            Tabel_Supplier();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Tidak berhasil Disimpan");
        }
      
    }//GEN-LAST:event_btn_simpansupplierActionPerformed

    private void btn_edit_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_supplierActionPerformed
        // TODO add your handling code here:
            try {
            String sql = "UPDATE supplier SET `id_supplier` ='" + txt_idSupplier.getText() + "',`nama_supplier`='" + txt_namaSupplier.getText() + "',`alamat`='" + alamatSupplier.getText()
                    + "',`no_telepon`='" + txt_nohpSupplier.getText()+ "' WHERE `supplier`.`nama_supplier`='"
                    + txt_namaSupplier.getText() + "';";
            java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil diubah ");

            Tabel_Supplier();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Tidak berhasil diubah");
        }
    }//GEN-LAST:event_btn_edit_supplierActionPerformed

    private void btn_hapusSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusSupplierActionPerformed
        // TODO add your handling code here:
              try {

            String sql = "DELETE  FROM supplier WHERE nama_supplier='" + txt_idSupplier.getText() + "';";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            Tabel_Supplier();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Dihapus");
        }
    }//GEN-LAST:event_btn_hapusSupplierActionPerformed

    private void cariAkunKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariAkunKeyReleased
        // TODO add your handling code here:
        Tabel_Akun();
    }//GEN-LAST:event_cariAkunKeyReleased

    private void passwordAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordAkunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordAkunActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(TransaksiPenjualan1);
        KONTEN.repaint();
        KONTEN.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(TransaksiPembelian1);
        KONTEN.repaint();
        KONTEN.revalidate();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_unggahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_unggahActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        
        FileNameExtensionFilter filter=new FileNameExtensionFilter("*.Image","jpg","png","jpeg");
        chooser.addChoosableFileFilter(filter);
        
        int result=chooser.showSaveDialog(null);
        
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
       
        if (filename.endsWith(".jpg")||filename.endsWith(".JPG")||filename.endsWith(".PNG")||filename.endsWith(".png")||filename.endsWith(".jpeg")||filename.endsWith(".JPEG")) {
             if (result==JFileChooser.APPROVE_OPTION) {
                  String path = f.getAbsolutePath();
                  ImageIcon myImage = new ImageIcon(path);
                  
                  Image img =myImage.getImage();
                  Image newImage=img.getScaledInstance(gambar.getWidth(), gambar.getHeight() , Image.SCALE_SMOOTH);
                  
                  ImageIcon icon = new ImageIcon(filename);
                  gambar.setIcon(icon); 
                  
            }
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Masukkan jenis file jpg, png, jpeg", "PERINGATAN !", 1);
        }
            
        
        txt_filename.setText(filename);
      


    }//GEN-LAST:event_btn_unggahActionPerformed

    private void txt_filenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_filenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_filenameActionPerformed

    private void simpanProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanProfilActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "UPDATE petugas SET `username` ='" + txt_namaProfil.getText() + "',`level`='" + txt_levelProfil.getText() + "',`nama`='" + txt_namaProfil.getText()
                    + "',`no_telepon`='" + txt_nohpProfil.getText() + "' WHERE `petugas`.`username`='"
                    + txt_usernameProfil.getText() + "';";
            java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data pada Profil Berhasil diubah ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data pada Profil Tidak berhasil diubah");
        }
        
        
        
        
    }//GEN-LAST:event_simpanProfilActionPerformed

    private void IdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdBarangActionPerformed

    private void unduhTransaksiPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unduhTransaksiPembelianActionPerformed
        // TODO add your handling code here:
        MessageFormat judul = new MessageFormat("DATA TRANSAKSI PEMBELIAN") ;
        MessageFormat footer = new MessageFormat("page(0,number,integer)") ;
        
        try {
            tabelTransaksiPembelian.print(JTable.PrintMode.FIT_WIDTH,judul,footer);
            
        } catch (  PrinterException e) {
            System.err.print("Error Printing");
        }
    }//GEN-LAST:event_unduhTransaksiPembelianActionPerformed

    private void Simpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Simpan5ActionPerformed
        // TODO add your handling code here:
        MessageFormat judul = new MessageFormat(" DATA LAPORAN") ;
        MessageFormat footer = new MessageFormat(" page(0,number,integer)") ;
        
        try {
            tabelDataLaporan.print(JTable.PrintMode.FIT_WIDTH,judul,footer);
            
        } catch (  PrinterException e) {
            System.err.print("Error Printing");
        }
    }//GEN-LAST:event_Simpan5ActionPerformed

    private void txt_gambarprofilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_gambarprofilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gambarprofilActionPerformed

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(Profil);
        KONTEN.repaint();
        KONTEN.revalidate();
    }//GEN-LAST:event_jLabel29MouseClicked

    private void caridatabarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caridatabarangKeyReleased
        Tabel_DataBarang();
    }//GEN-LAST:event_caridatabarangKeyReleased

    private void data_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_data_barangMouseClicked
        int a = data_barang.rowAtPoint(evt.getPoint());
        
        String idBarang = (String) data_barang.getValueAt(a, 0);
        IdBarang.setText(idBarang);
        
        String namaBarang = (String) data_barang.getValueAt(a, 1);
        NamaDataBarang.setText(namaBarang);
        
        
        
    }//GEN-LAST:event_data_barangMouseClicked

    private void HargaDataBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HargaDataBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HargaDataBarangActionPerformed

    private void pilihReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihReturnActionPerformed
        // TODO add your handling code here:
     if (pilihReturn.getSelectedItem() == "BULAN"){
        TabelReturnBulan();
        
        } else if(pilihReturn.getSelectedItem() == "TAHUN"){
          TabelReturnTahun();
          
        }   else if(pilihReturn.getSelectedItem() == "PILIH"){
                Tabel_Returnbarang();
        }
    }//GEN-LAST:event_pilihReturnActionPerformed

    private void CariReturn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariReturn6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CariReturn6ActionPerformed

    private void Simpan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Simpan7ActionPerformed
        // TODO add your handling code here:
        MessageFormat judul = new MessageFormat(" DATA LAPORAN") ;
        MessageFormat footer = new MessageFormat(" page(0,number,integer)") ;
        
        try {
            tabelReturnBarang.print(JTable.PrintMode.FIT_WIDTH,judul,footer);
            
        } catch (  PrinterException e) {
            System.err.print("Error Printing");
        }
    }//GEN-LAST:event_Simpan7ActionPerformed

    private void cariTransaksiPembelianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTransaksiPembelianKeyReleased
        // TODO add your handling code here:
        Tabel_TransaksiPembelian();
    }//GEN-LAST:event_cariTransaksiPembelianKeyReleased

    private void cariTransaksiPenjualanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTransaksiPenjualanKeyReleased
        // TODO add your handling code here:
        Tabel_TransaksiPenjualan();
    }//GEN-LAST:event_cariTransaksiPenjualanKeyReleased

    private void CariSupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CariSupplierKeyReleased
        Tabel_Supplier();        // TODO add your handling code here:
    }//GEN-LAST:event_CariSupplierKeyReleased

    private void txt_nohpSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nohpSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nohpSupplierActionPerformed

    private void txt_namaSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaSupplierActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        
        FileNameExtensionFilter filter=new FileNameExtensionFilter("*.Image","jpg","png","jpeg");
        chooser.addChoosableFileFilter(filter);
        
        int result=chooser.showSaveDialog(null);
        
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
       
        if (filename.endsWith(".jpg")||filename.endsWith(".JPG")||filename.endsWith(".PNG")||filename.endsWith(".png")||filename.endsWith(".jpeg")||filename.endsWith(".JPEG")) {
             if (result==JFileChooser.APPROVE_OPTION) {
                  String path = f.getAbsolutePath();
                  ImageIcon myImage = new ImageIcon(path);
                  
                  Image img =myImage.getImage();
                  Image newImage=img.getScaledInstance(gambar.getWidth(), gambar.getHeight() , Image.SCALE_SMOOTH);
                  
                  ImageIcon icon = new ImageIcon(filename);
                  gambarProfil.setIcon(icon); 
                  
            }
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Masukkan jenis file jpg, png, jpeg", "PERINGATAN !", 1);
        }
            
        
        txt_gambarprofil.setText(filename);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabelAkunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelAkunMouseClicked
        // TODO add your handling code here:
       
        
        int a = tabelAkun.rowAtPoint(evt.getPoint());
    
        String username = (String) tabelAkun.getValueAt(a, 0);
        usernameAkun.setText(username);
        
        String nama = (String) tabelAkun.getValueAt(a, 1);
        namaAkun.setText(nama);
        
        String nohp  = (String) tabelAkun.getValueAt(a, 3);
        noHpAkun.setText(nohp);
        
        String level = (String) tabelAkun.getValueAt(a, 2);
        pilihanAkun.setSelectedItem(level);
        
        String password = (String) tabelAkun.getValueAt(a, 5);
        passwordAkun.setText(password);
        
        String alamat = (String) tabelAkun.getValueAt(a, 4);
        alamatAkun.setText(alamat);
        
        try {
            int row = tabelAkun.getSelectedRow();
            java.sql.Statement st =(java.sql.Statement)Koneksi.getkoneksi().createStatement();
            ResultSet rs = st.executeQuery("select * from petugas where username='"+username+"'");
            while (rs.next()) {
                Blob gambar= (com.mysql.jdbc.Blob)rs.getBlob("gambar");
                int ukuran =(int) (gambar.length());
                ImageIcon tampil= new ImageIcon(gambar.getBytes(1, ukuran));
                this.gambar.setIcon(tampil);
            }
        } catch (Exception e) {
            
        }
       
        
        //String image = (String) tabelAkun.getValueAt(a, 6);
        //this.gambar.setIcon();
      
      
        
       
        
    }//GEN-LAST:event_tabelAkunMouseClicked

    private void Tabel_suplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel_suplierMouseClicked
        // TODO add your handling code here:
        int a = Tabel_suplier.rowAtPoint(evt.getPoint());
        
        String idsupp = (String) Tabel_suplier.getValueAt(a, 0);
        txt_idSupplier.setText(idsupp);
        
        String namasupp = (String) Tabel_suplier.getValueAt(a, 1);
        txt_namaSupplier.setText(namasupp);
        
        String nohpsupp  = (String) Tabel_suplier.getValueAt(a, 3);
        txt_nohpSupplier.setText(nohpsupp);
        
        String alamatsupp = (String) Tabel_suplier.getValueAt(a, 2);
        alamatSupplier.setText(alamatsupp);
        
        
        
    }//GEN-LAST:event_Tabel_suplierMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(Dashboard);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(51,102,255));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel2MouseClicked

    private void DashboardMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardMenuMouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(Dashboard);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(51,102,255));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));

    }//GEN-LAST:event_DashboardMenuMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(Akun);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(51,102,255));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel3MouseClicked

    private void AKUNMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AKUNMENUMouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(Akun);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(51,102,255));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_AKUNMENUMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(TRANSAKSI);
        KONTEN.repaint();
        KONTEN.revalidate();
        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(51,102,255));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel4MouseClicked

    private void TRANSAKSIMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TRANSAKSIMENUMouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(TRANSAKSI);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(51,102,255));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_TRANSAKSIMENUMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataBarang);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(51,102,255));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel5MouseClicked

    private void DATABARANGMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DATABARANGMENUMouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataBarang);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(51,102,255));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_DATABARANGMENUMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(Suplier);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(51,102,255));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel6MouseClicked

    private void SUPPLIERMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SUPPLIERMENUMouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(Suplier);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(51,102,255));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_SUPPLIERMENUMouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataLaporan);
        KONTEN.repaint();
        KONTEN.revalidate();
        tblelaporanpenjualanharian();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(51,102,255));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel33MouseClicked

    private void DATALAPORANMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DATALAPORANMENUMouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataLaporan);
        KONTEN.repaint();
        KONTEN.revalidate();
        tblelaporanpenjualanharian();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(51,102,255));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_DATALAPORANMENUMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataReturn);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(51,102,255));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel7MouseClicked

    private void DATARETURNMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DATARETURNMENUMouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataReturn);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(51,102,255));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_DATARETURNMENUMouseClicked

    private void KeluarMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KeluarMenuMouseClicked
        // TODO add your handling code here
        
        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(213,0,0));
        
        int ok=JOptionPane.showConfirmDialog(null,"Apakah Anda Yakin Ingin Keluar?","Confirmation",JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            new Login().setVisible(true);
        }
        
    }//GEN-LAST:event_KeluarMenuMouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        // TODO add your handling code here:
       
        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(213,0,0));
        
        int ok=JOptionPane.showConfirmDialog(null,"Apakah Anda Yakin Ingin Keluar?","Confirmation",JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_jLabel42MouseClicked

    private void KeluarMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KeluarMenuMouseEntered
        // TODO add your handling code here:
        KeluarMenu.setBackground(new Color(213,0,0));
    }//GEN-LAST:event_KeluarMenuMouseEntered

    private void KeluarMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KeluarMenuMouseExited
        // TODO add your handling code here:
        KeluarMenu.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_KeluarMenuMouseExited

    private void jLabel42MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseEntered
        // TODO add your handling code here:
        KeluarMenu.setBackground(new Color(213,0,0));
    }//GEN-LAST:event_jLabel42MouseEntered

    private void jLabel42MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseExited
        // TODO add your handling code here:
        KeluarMenu.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel42MouseExited

    private void barangdashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barangdashboardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barangdashboardActionPerformed

    private void barangdashboardKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barangdashboardKeyReleased
        try { 
            // TODO add your handling code here:
            list = new ArrayList<>();
            String sql1 = "SELECT barang.nama_barang ,COUNT(detail_transaksi_penjualan.Id_barang) AS MA FROM barang JOIN detail_transaksi_penjualan ON detail_transaksi_penjualan.Id_barang = barang.id_barang where CONCAT(barang.nama_barang, detail_transaksi_penjualan.Id_barang) Like '%"+barangdashboard.getText()+"%' GROUP BY detail_transaksi_penjualan.Id_barang ";
            
            java.sql.Connection conn = (Connection) Koneksi.getkoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql1);
            while(rs.next()){
                String gr = rs.getString(1);
                double dbj = rs.getDouble(2);
                list.add(new ModelChart(gr, new double[]{dbj}));
            }
            
            if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                try {
                    for (int i = list.size() - 1; i >= 0; i--) {
                        barChart3.addData(list.get(i));
                    }
                    barChart3.start();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            
            if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                barChart3.start();
                barChart3.clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_barangdashboardKeyReleased

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataPelanggan);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(51,102,255));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel43MouseClicked

    private void DATAPELANGGANMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DATAPELANGGANMouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataPelanggan);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATAPELANGGAN.setBackground(new Color(51,102,255));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
        KeluarMenu.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_DATAPELANGGANMouseClicked

    private void cariDataPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariDataPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariDataPelangganActionPerformed

    private void cariDataPelangganKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariDataPelangganKeyReleased
        // TODO add your handling code here:
        Tabel_DataPelanggan();
    }//GEN-LAST:event_cariDataPelangganKeyReleased

    private void CariReturn6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CariReturn6KeyReleased
        // TODO add your handling code here:
        Tabel_Returnbarang();
    }//GEN-LAST:event_CariReturn6KeyReleased

    private void PilihLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PilihLaporanActionPerformed
        // TODO add your handling code here:
        if(PilihLaporan.getSelectedItem() == "PENJUALAN"){
            tblelaporanpenjualanharian();
        } else if (PilihLaporan.getSelectedItem() == "PEMBELIAN"){
            tblelaporanpembelianharian();
        } else if(PilihLaporan.getSelectedItem() == "RETURN"){
            tblelaporanreturnharian();
        } else {
            JOptionPane.showMessageDialog(rootPane, "salah");
        }
    }//GEN-LAST:event_PilihLaporanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AKUNMENU;
    private javax.swing.JPanel Akun;
    private javax.swing.JTextField CariReturn6;
    private javax.swing.JTextField CariSupplier;
    private javax.swing.JPanel DATABARANGMENU;
    private javax.swing.JPanel DATALAPORANMENU;
    private javax.swing.JPanel DATAPELANGGAN;
    private javax.swing.JPanel DATARETURNMENU;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel DashboardMenu;
    private javax.swing.JPanel DataBarang;
    private javax.swing.JPanel DataLaporan;
    private javax.swing.JPanel DataPelanggan;
    private javax.swing.JPanel DataReturn;
    private javax.swing.JTextField HargaDataBarang;
    private javax.swing.JTextField IdBarang;
    private javax.swing.JPanel KONTEN;
    private javax.swing.JPanel KeluarMenu;
    private javax.swing.JPanel MENUSAMPING;
    private javax.swing.JTextField NamaDataBarang;
    private javax.swing.JPanel OWNER;
    private javax.swing.JComboBox<String> PilihLaporan;
    private javax.swing.JPanel Profil;
    private javax.swing.JPanel SUPPLIERMENU;
    private javax.swing.JButton Simpan5;
    private javax.swing.JButton Simpan7;
    private javax.swing.JPanel Suplier;
    private javax.swing.JPanel TRANSAKSI;
    private javax.swing.JPanel TRANSAKSIMENU;
    private javax.swing.JTable Tabel_suplier;
    private javax.swing.JPanel TransaksiPembelian1;
    private javax.swing.JPanel TransaksiPenjualan1;
    private javax.swing.JTextArea alamatAkun;
    private javax.swing.JTextArea alamatSupplier;
    private javaswingdev.chart.BarChart barChart1;
    private javaswingdev.chart.BarChart barChart2;
    private javaswingdev.chart.BarChart barChart3;
    public static javax.swing.JTextField barangdashboard;
    private javax.swing.JButton btn_edit_supplier;
    private javax.swing.JButton btn_hapusSupplier;
    private javax.swing.JButton btn_simpansupplier;
    private javax.swing.JButton btn_unggah;
    private javax.swing.JTextField cariAkun;
    private javax.swing.JTextField cariDataPelanggan;
    private javax.swing.JTextField cariTransaksiPembelian;
    private javax.swing.JTextField cariTransaksiPenjualan;
    private javax.swing.JTextField caridatabarang;
    private javax.swing.JTable data_barang;
    private javax.swing.JButton editAkun;
    private javax.swing.JButton editdatabarang;
    private javax.swing.JLabel gambar;
    private javax.swing.JLabel gambarProfil;
    private javax.swing.JButton hapusAkun;
    private javax.swing.JButton hapusdatabarang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JComboBox<String> memilihsatuan;
    private javax.swing.JTextField namaAkun;
    private javax.swing.JPanel navbar;
    private javax.swing.JTextField noHpAkun;
    private javax.swing.JTextField passwordAkun;
    private javax.swing.JComboBox<String> pilihDataLaporan;
    private javax.swing.JComboBox<String> pilihReturn;
    private javax.swing.JComboBox<String> pilihanAkun;
    private javax.swing.JButton simpanAkun;
    private javax.swing.JButton simpanProfil;
    private javax.swing.JTable tabelAkun;
    private javax.swing.JTable tabelDataLaporan;
    private javax.swing.JTable tabelDataPelanggan;
    private javax.swing.JTable tabelReturnBarang;
    private javax.swing.JTable tabelTransaksiPembelian;
    private javax.swing.JTable tabelTransaksiPenjualan;
    private javax.swing.JTextField txt_filename;
    private javax.swing.JTextField txt_gambarprofil;
    private javax.swing.JTextField txt_idSupplier;
    public static final javax.swing.JTextField txt_levelProfil = new javax.swing.JTextField();
    public static final javax.swing.JTextField txt_namaProfil = new javax.swing.JTextField();
    private javax.swing.JTextField txt_namaSupplier;
    private javax.swing.JTextField txt_nohpProfil;
    private javax.swing.JTextField txt_nohpSupplier;
    public static final javax.swing.JTextField txt_usernameProfil = new javax.swing.JTextField();
    private javax.swing.JButton unduhTransaksiPembelian;
    private javax.swing.JButton unduhTransaksiPenjualan;
    private javax.swing.JTextField usernameAkun;
    // End of variables declaration//GEN-END:variables
class RoundedPanel extends JPanel {

        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;

        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
//            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
//             
        }
    }
}
