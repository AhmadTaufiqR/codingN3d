/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n3dshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import sun.security.util.Password;

/**
 *
 * @author HP
 */
public class MenuOwner extends javax.swing.JFrame {

    public String SATUAN;

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
        DashboardMenu.setBackground(new Color(51,102,255));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(0,9,87));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
    }

    private void Tabel_TransaksiPembelian() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID PEMBELIAN");
        model.addColumn("ID SUPPLIER");
        model.addColumn("NAMA KASIR");
        model.addColumn("TANGGAL");

        String cari = cariTransaksiPembelian.getText();

        try {
            String sql = "SELECT * FROM transaksi_pembelian WHERE username LIKE'%" + cari + "%'";
            java.sql.Connection connt = (Connection) Koneksi.getkoneksi();
            java.sql.Statement stm = connt.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_pembelian"), res.getString("id_supplier"), res.getString("username"),
                    res.getString("tanggal")});
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

    private void Tabel_TransaksiPenjualan() {
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
            String sql = "SELECT username,nama,alamat,level,no_telepon,password FROM petugas WHERE username LIKE'%" + cari + "%'";
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
                model.addRow(new Object[]{res.getString("id_barang"), res.getString("nama_barang"), res.getString("stok_ecer"),
                    res.getString("stok_grosir"), res.getString("harga_eceran"), res.getString("harga_grosir")});
            }
            data_barang.setModel(model);
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
        DATARETURNMENU = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        navbar = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        KONTEN = new javax.swing.JPanel();
        Dashboard = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
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
        Simpan1 = new javax.swing.JButton();
        TransaksiPembelian1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cariTransaksiPembelian = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelTransaksiPembelian = new javax.swing.JTable();
        Simpan4 = new javax.swing.JButton();
        DataLaporan = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        Cari5 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        Simpan5 = new javax.swing.JButton();
        Profil = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
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
        txt_alamatSupplier = new javax.swing.JTextField();
        txt_namaSupplier = new javax.swing.JTextField();
        txt_nohpSupplier = new javax.swing.JTextField();
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
        jButton2 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        alamatAkun = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        PasswordAkun = new javax.swing.JTextField();
        btn_unggah = new javax.swing.JButton();
        txt_filename = new javax.swing.JTextField();
        DataReturn = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        CariReturn6 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        Simpan7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OWNER.setBackground(new java.awt.Color(196, 196, 196));
        OWNER.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MENUSAMPING.setBackground(new java.awt.Color(0, 9, 87));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Angkringan__1_-removebg-preview 1.png"))); // NOI18N

        DashboardMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DashboardMenu.setEnabled(false);
        DashboardMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardMenuMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/icondashbord.png"))); // NOI18N
        jLabel2.setText("  DASHBOARD");
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
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DashboardMenuLayout.setVerticalGroup(
            DashboardMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AKUNMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AKUNMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AKUNMENUMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/iconprofil.png"))); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AKUNMENULayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(165, 165, 165))
        );
        AKUNMENULayout.setVerticalGroup(
            AKUNMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AKUNMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TRANSAKSIMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TRANSAKSIMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TRANSAKSIMENUMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/icontransaksi.png"))); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TRANSAKSIMENULayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(117, 117, 117))
        );
        TRANSAKSIMENULayout.setVerticalGroup(
            TRANSAKSIMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TRANSAKSIMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DATABARANGMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DATABARANGMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DATABARANGMENUMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/icondatabarang.png"))); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATABARANGMENULayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(92, 92, 92))
        );
        DATABARANGMENULayout.setVerticalGroup(
            DATABARANGMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATABARANGMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SUPPLIERMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SUPPLIERMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SUPPLIERMENUMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/iconsuplier.png"))); // NOI18N
        jLabel6.setText(" SUPLIER");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SUPPLIERMENULayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(140, 140, 140))
        );
        SUPPLIERMENULayout.setVerticalGroup(
            SUPPLIERMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SUPPLIERMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DATALAPORANMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DATALAPORANMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DATALAPORANMENUMouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/icondatalaporan.png"))); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATALAPORANMENULayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(86, 86, 86))
        );
        DATALAPORANMENULayout.setVerticalGroup(
            DATALAPORANMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATALAPORANMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DATARETURNMENU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DATARETURNMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DATARETURNMENUMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/return-box 1.png"))); // NOI18N
        jLabel7.setText("DATA RETURN");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DATARETURNMENULayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(93, 93, 93))
        );
        DATARETURNMENULayout.setVerticalGroup(
            DATARETURNMENULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DATARETURNMENULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MENUSAMPINGLayout = new javax.swing.GroupLayout(MENUSAMPING);
        MENUSAMPING.setLayout(MENUSAMPINGLayout);
        MENUSAMPINGLayout.setHorizontalGroup(
            MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MENUSAMPINGLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(MENUSAMPINGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DashboardMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AKUNMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TRANSAKSIMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DATABARANGMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SUPPLIERMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DATALAPORANMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DATARETURNMENU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MENUSAMPINGLayout.setVerticalGroup(
            MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MENUSAMPINGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap())
        );

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8))
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel14)
                                            .addGroup(DataBarangLayout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                                .addComponent(IdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, Short.MAX_VALUE))
                                    .addGroup(DataBarangLayout.createSequentialGroup()
                                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                        .addGroup(DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(NamaDataBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(memilihsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(HargaDataBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(DataBarangLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(editdatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(hapusdatabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(21, Short.MAX_VALUE))
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
                        .addContainerGap(322, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataBarangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
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
                .addContainerGap(149, Short.MAX_VALUE))
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

        Simpan1.setBackground(new java.awt.Color(240, 225, 89));
        Simpan1.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        Simpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/unduh.png"))); // NOI18N
        Simpan1.setText("UNDUH");
        Simpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Simpan1ActionPerformed(evt);
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
                        .addComponent(Simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(Simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
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

        Simpan4.setBackground(new java.awt.Color(240, 225, 89));
        Simpan4.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        Simpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/unduh.png"))); // NOI18N
        Simpan4.setText("UNDUH");
        Simpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Simpan4ActionPerformed(evt);
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
                        .addComponent(Simpan4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(Simpan4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        KONTEN.add(TransaksiPembelian1, "card5");

        jLabel21.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/laporankonten.png"))); // NOI18N
        jLabel21.setText(" DATA LAPORAN");
        jLabel21.setToolTipText("");

        jLabel22.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel22.setText("Pilih");

        jComboBox1.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel23.setText("Cari : ");

        Cari5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cari5ActionPerformed(evt);
            }
        });

        jTable5.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable5);

        Simpan5.setBackground(new java.awt.Color(240, 225, 89));
        Simpan5.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        Simpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/unduh.png"))); // NOI18N
        Simpan5.setText("UNDUH");
        Simpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Simpan5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DataLaporanLayout = new javax.swing.GroupLayout(DataLaporan);
        DataLaporan.setLayout(DataLaporanLayout);
        DataLaporanLayout.setHorizontalGroup(
            DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataLaporanLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(DataLaporanLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DataLaporanLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(DataLaporanLayout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cari5, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98))))))
            .addGroup(DataLaporanLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        DataLaporanLayout.setVerticalGroup(
            DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataLaporanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel21)
                .addGap(29, 29, 29)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DataLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1))
                    .addComponent(Cari5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        KONTEN.add(DataLaporan, "card8");

        Profil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Roboto Slab", 0, 24)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Vector (4).png"))); // NOI18N
        jLabel24.setText("Profil");
        jLabel24.setToolTipText("");
        Profil.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 14, 168, 43));

        jLabel25.setFont(new java.awt.Font("Roboto Slab", 0, 16)); // NOI18N
        jLabel25.setText("Username");
        Profil.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 112, -1, 29));

        jTextField1.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        Profil.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 112, 245, 29));

        jLabel26.setFont(new java.awt.Font("Roboto Slab", 0, 16)); // NOI18N
        jLabel26.setText("Level");
        Profil.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 159, -1, 27));

        jLabel27.setFont(new java.awt.Font("Roboto Slab", 0, 16)); // NOI18N
        jLabel27.setText("Nama");
        Profil.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 212, -1, 27));

        jLabel28.setFont(new java.awt.Font("Roboto Slab", 0, 16)); // NOI18N
        jLabel28.setText("No.handphone");
        Profil.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 259, -1, 33));

        jTextField2.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        Profil.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 159, 245, 29));

        jTextField3.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        Profil.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 245, 29));

        jTextField4.setFont(new java.awt.Font("Roboto Slab", 0, 12)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        Profil.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 259, 245, 29));

        jButton5.setBackground(new java.awt.Color(240, 225, 89));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/edit.png"))); // NOI18N
        jButton5.setText(" EDIT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Profil.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 557, 120, 43));

        jButton6.setBackground(new java.awt.Color(240, 225, 89));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/diskette.png"))); // NOI18N
        jButton6.setText(" SIMPAN");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        Profil.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 557, 120, 43));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("foto");
        jLabel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Profil.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 139, 144));

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        Profil.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 180, -1));

        jButton4.setText("UNGGAH");
        Profil.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, -1, -1));

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

        txt_alamatSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatSupplierActionPerformed(evt);
            }
        });

        txt_namaSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaSupplierActionPerformed(evt);
            }
        });

        txt_nohpSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nohpSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SuplierLayout = new javax.swing.GroupLayout(Suplier);
        Suplier.setLayout(SuplierLayout);
        SuplierLayout.setHorizontalGroup(
            SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SuplierLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CariSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(SuplierLayout.createSequentialGroup()
                .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuplierLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51)
                            .addComponent(jLabel47))
                        .addGap(61, 61, 61)
                        .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_namaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_alamatSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_idSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nohpSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(jLabel46)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        SuplierLayout.setVerticalGroup(
            SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuplierLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CariSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(SuplierLayout.createSequentialGroup()
                        .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SuplierLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(txt_idSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SuplierLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SuplierLayout.createSequentialGroup()
                                .addComponent(txt_namaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_alamatSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_nohpSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(SuplierLayout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_edit_supplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(SuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_simpansupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_hapusSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(19, 19, 19))))))
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

        jButton2.setText("UNGGAH");

        jLabel40.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel40.setText("ALAMAT");

        alamatAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatAkunActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel41.setText("PASSWORD");

        PasswordAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordAkunActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout AkunLayout = new javax.swing.GroupLayout(Akun);
        Akun.setLayout(AkunLayout);
        AkunLayout.setHorizontalGroup(
            AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(pilihanAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noHpAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namaAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(AkunLayout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(alamatAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AkunLayout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(18, 18, 18)
                                .addComponent(PasswordAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AkunLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(txt_filename, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(264, 264, 264))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AkunLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_unggah)
                                .addGap(317, 317, 317)))
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AkunLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(373, 373, 373))))
            .addGroup(AkunLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(simpanAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hapusAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(alamatAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40))
                        .addGap(20, 20, 20)
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namaAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PasswordAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addGap(20, 20, 20)
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pilihanAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AkunLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(noHpAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel39))
                            .addComponent(jButton2)))
                    .addGroup(AkunLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txt_filename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_unggah, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jComboBox2.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tanggal", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jLabel32.setText("Cari : ");

        CariReturn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariReturn6ActionPerformed(evt);
            }
        });

        jTable6.setFont(new java.awt.Font("Roboto Slab", 0, 14)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTable6);

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
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jComboBox2))
                    .addComponent(CariReturn6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Simpan7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        KONTEN.add(DataReturn, "card8");

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

    private void Simpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Simpan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Simpan1ActionPerformed

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
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Tidak Berhasil Disimpan");
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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void Cari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cari5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cari5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

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
        try {
            String sql = "INSERT INTO petugas (username, nama,alamat, level,no_telepon, password,image) Values ('" + usernameAkun.getText() + "', '" + namaAkun.getText() + "', '"
                    + alamatAkun.getText() + "', '" + pilihanAkun.getSelectedItem() + "', '" + noHpAkun.getText() + "','" + PasswordAkun.getText() + "','" + gambar.getText() + "');";
            java.sql.Connection conntt = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conntt.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");

            Tabel_Akun();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Tidak berhasil Disimpan");
        }
    }//GEN-LAST:event_simpanAkunActionPerformed

    private void editAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAkunActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "UPDATE petugas SET `username` ='" + usernameAkun.getText() + "',`nama`='" + namaAkun.getText() + "',`no_telepon`='" + noHpAkun.getText()
                    + "',`password`='" + PasswordAkun.getText() + "',`level`='"
                    + pilihanAkun.getSelectedItem() + "' WHERE `petugas`.`username`='"
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

            String sql = "DELETE * FROM petugas WHERE username='" + usernameAkun.getText() + "';";
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
            String sql = "INSERT INTO supplier (id_supplier, nama_supplier,alamat,no_telepon) Values ('" + txt_idSupplier.getText() + "', '" + txt_namaSupplier.getText() + "', '"
                    + txt_alamatSupplier.getText() + "', '" + txt_nohpSupplier.getText()+ "');";
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
            String sql = "UPDATE supplier SET `id_supplier` ='" + txt_idSupplier.getText() + "',`nama_supplier`='" + txt_namaSupplier.getText() + "',`alamat`='" + txt_alamatSupplier.getText()
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

    private void alamatAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatAkunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatAkunActionPerformed

    private void PasswordAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordAkunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordAkunActionPerformed

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
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        txt_filename.setText(filename);
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage().getScaledInstance(gambar.getWidth(), gambar.getHeight(), Image.SCALE_SMOOTH);
        gambar.setIcon(icon);


    }//GEN-LAST:event_btn_unggahActionPerformed

    private void txt_filenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_filenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_filenameActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void IdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdBarangActionPerformed

    private void Simpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Simpan4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Simpan4ActionPerformed

    private void Simpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Simpan5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Simpan5ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

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

    }//GEN-LAST:event_data_barangMouseClicked

    private void HargaDataBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HargaDataBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HargaDataBarangActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void CariReturn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariReturn6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CariReturn6ActionPerformed

    private void Simpan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Simpan7ActionPerformed
        // TODO add your handling code here:
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

    private void txt_alamatSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatSupplierActionPerformed

    private void txt_namaSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaSupplierActionPerformed

    private void txt_nohpSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nohpSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nohpSupplierActionPerformed

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
    }//GEN-LAST:event_SUPPLIERMENUMouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataLaporan);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(51,102,255));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
    }//GEN-LAST:event_jLabel33MouseClicked

    private void DATALAPORANMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DATALAPORANMENUMouseClicked
        // TODO add your handling code here:
        KONTEN.removeAll();
        KONTEN.repaint();
        KONTEN.revalidate();

        KONTEN.add(DataLaporan);
        KONTEN.repaint();
        KONTEN.revalidate();

        DashboardMenu.setBackground(new Color(0,9,87));
        AKUNMENU.setBackground(new Color(0,9,87));
        TRANSAKSIMENU.setBackground(new Color(0,9,87));
        DATABARANGMENU.setBackground(new Color(0,9,87));
        DATALAPORANMENU.setBackground(new Color(51,102,255));
        DATARETURNMENU.setBackground(new Color(0,9,87));
        SUPPLIERMENU.setBackground(new Color(0,9,87));
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
    }//GEN-LAST:event_DATARETURNMENUMouseClicked

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
    private javax.swing.JTextField Cari5;
    private javax.swing.JTextField CariReturn6;
    private javax.swing.JTextField CariSupplier;
    private javax.swing.JPanel DATABARANGMENU;
    private javax.swing.JPanel DATALAPORANMENU;
    private javax.swing.JPanel DATARETURNMENU;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel DashboardMenu;
    private javax.swing.JPanel DataBarang;
    private javax.swing.JPanel DataLaporan;
    private javax.swing.JPanel DataReturn;
    private javax.swing.JTextField HargaDataBarang;
    private javax.swing.JTextField IdBarang;
    private javax.swing.JPanel KONTEN;
    private javax.swing.JPanel MENUSAMPING;
    private javax.swing.JTextField NamaDataBarang;
    private javax.swing.JPanel OWNER;
    private javax.swing.JTextField PasswordAkun;
    private javax.swing.JPanel Profil;
    private javax.swing.JPanel SUPPLIERMENU;
    private javax.swing.JButton Simpan1;
    private javax.swing.JButton Simpan4;
    private javax.swing.JButton Simpan5;
    private javax.swing.JButton Simpan7;
    private javax.swing.JPanel Suplier;
    private javax.swing.JPanel TRANSAKSI;
    private javax.swing.JPanel TRANSAKSIMENU;
    private javax.swing.JTable Tabel_suplier;
    private javax.swing.JPanel TransaksiPembelian1;
    private javax.swing.JPanel TransaksiPenjualan1;
    private javax.swing.JTextField alamatAkun;
    private javax.swing.JButton btn_edit_supplier;
    private javax.swing.JButton btn_hapusSupplier;
    private javax.swing.JButton btn_simpansupplier;
    private javax.swing.JButton btn_unggah;
    private javax.swing.JTextField cariAkun;
    private javax.swing.JTextField cariTransaksiPembelian;
    private javax.swing.JTextField cariTransaksiPenjualan;
    private javax.swing.JTextField caridatabarang;
    private javax.swing.JTable data_barang;
    private javax.swing.JButton editAkun;
    private javax.swing.JButton editdatabarang;
    private javax.swing.JLabel gambar;
    private javax.swing.JButton hapusAkun;
    private javax.swing.JButton hapusdatabarang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JComboBox<String> memilihsatuan;
    private javax.swing.JTextField namaAkun;
    private javax.swing.JPanel navbar;
    private javax.swing.JTextField noHpAkun;
    private javax.swing.JComboBox<String> pilihanAkun;
    private javax.swing.JButton simpanAkun;
    private javax.swing.JTable tabelAkun;
    private javax.swing.JTable tabelTransaksiPembelian;
    private javax.swing.JTable tabelTransaksiPenjualan;
    private javax.swing.JTextField txt_alamatSupplier;
    private javax.swing.JTextField txt_filename;
    private javax.swing.JTextField txt_idSupplier;
    private javax.swing.JTextField txt_namaSupplier;
    private javax.swing.JTextField txt_nohpSupplier;
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
