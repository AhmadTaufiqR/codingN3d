/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n3dshop;

/**
 *
 * @author HP
 */
public class MenuOwner extends javax.swing.JFrame {

    /**
     * Creates new form MenuOwner
     */
    public MenuOwner() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        OWNER = new javax.swing.JPanel();
        MENUSAMPING = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        navbar = new javax.swing.JPanel();
        KONTEN = new javax.swing.JPanel();
        TRANSAKSI = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        dashborad = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OWNER.setBackground(new java.awt.Color(196, 196, 196));
        OWNER.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MENUSAMPING.setBackground(new java.awt.Color(0, 9, 87));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/Angkringan__1_-removebg-preview 1.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/icondashbord.png"))); // NOI18N
        jLabel2.setText("  DASHBOARD");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/iconprofil.png"))); // NOI18N
        jLabel3.setText("   AKUN");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/icontransaksi.png"))); // NOI18N
        jLabel4.setText("  TRANSAKSI");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/icondatabarang.png"))); // NOI18N
        jLabel5.setText("  DATA BARANG");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/iconsuplier.png"))); // NOI18N
        jLabel6.setText(" SUPLIER");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/icondatalaporan.png"))); // NOI18N
        jLabel7.setText("  DATA LAPORAN");

        javax.swing.GroupLayout MENUSAMPINGLayout = new javax.swing.GroupLayout(MENUSAMPING);
        MENUSAMPING.setLayout(MENUSAMPINGLayout);
        MENUSAMPINGLayout.setHorizontalGroup(
            MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MENUSAMPINGLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MENUSAMPINGLayout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );
        MENUSAMPINGLayout.setVerticalGroup(
            MENUSAMPINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MENUSAMPINGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addGap(42, 42, 42)
                .addComponent(jLabel6)
                .addGap(37, 37, 37)
                .addComponent(jLabel7)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        OWNER.add(MENUSAMPING, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        navbar.setBackground(new java.awt.Color(255, 232, 22));

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        OWNER.add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, -1));

        KONTEN.setBackground(new java.awt.Color(196, 196, 196));
        KONTEN.setLayout(new java.awt.CardLayout());

        TRANSAKSI.setBackground(new java.awt.Color(196, 196, 196));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/transaksipembelian.png"))); // NOI18N
        jButton3.setText("TRANSAKSI PEMBELIAN");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setIconTextGap(23);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout TRANSAKSILayout = new javax.swing.GroupLayout(TRANSAKSI);
        TRANSAKSI.setLayout(TRANSAKSILayout);
        TRANSAKSILayout.setHorizontalGroup(
            TRANSAKSILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TRANSAKSILayout.createSequentialGroup()
                .addGroup(TRANSAKSILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TRANSAKSILayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9))
                    .addGroup(TRANSAKSILayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        TRANSAKSILayout.setVerticalGroup(
            TRANSAKSILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TRANSAKSILayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel9)
                .addGap(79, 79, 79)
                .addGroup(TRANSAKSILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        KONTEN.add(TRANSAKSI, "card3");

        dashborad.setBackground(new java.awt.Color(196, 196, 196));
        dashborad.setPreferredSize(new java.awt.Dimension(650, 350));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FIleGambar/ICONDASHBOARDOWNER.png"))); // NOI18N
        jLabel8.setText(" DASHBOARD");

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

        javax.swing.GroupLayout dashboradLayout = new javax.swing.GroupLayout(dashborad);
        dashborad.setLayout(dashboradLayout);
        dashboradLayout.setHorizontalGroup(
            dashboradLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboradLayout.createSequentialGroup()
                .addGroup(dashboradLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboradLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel8))
                    .addGroup(dashboradLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(108, 108, 108))
        );
        dashboradLayout.setVerticalGroup(
            dashboradLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboradLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(dashboradLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(299, Short.MAX_VALUE))
        );

        KONTEN.add(dashborad, "card2");

        OWNER.add(KONTEN, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 0, 930, 720));

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel KONTEN;
    private javax.swing.JPanel MENUSAMPING;
    private javax.swing.JPanel OWNER;
    private javax.swing.JPanel TRANSAKSI;
    private javax.swing.JPanel dashborad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel navbar;
    // End of variables declaration//GEN-END:variables
}
