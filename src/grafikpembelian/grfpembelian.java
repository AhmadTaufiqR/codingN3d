/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikpembelian;

import grafikpenjualan.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import javaswingdev.chart.ModelChart;
import n3dshop.Koneksi;

/**
 *
 * @author HP
 */
public class grfpembelian {
    private Date tglsekarang;
    private String tgl, tglpenjualan;
    
    public void Tampil_Tanggal() {
        tglsekarang = new Date();
        SimpleDateFormat smpdtfmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        tgl = smpdtfmt.format(tglsekarang);

        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        tglpenjualan = String.valueOf(fm.format(tglsekarang));

    }
    
    
    public List<ModelChart> getData() throws  SQLException{
           List<ModelChart> list = new ArrayList<>();
            String sql = "SELECT COUNT(no_faktur) AS M , SUM(bayar) AS B from transaksi_penjualan where tanggal = '"+tgl+"'";
            java.sql.Connection cn = (Connection) Koneksi.getkoneksi();
            java.sql.PreparedStatement st = cn.prepareStatement(sql);
            java.sql.ResultSet rs = st.executeQuery();
            while(rs.next()){
                String gr = rs.getString(1);
                double dbj = rs.getDouble(2);
                list.add(new ModelChart(gr, new double[]{dbj}));
            }
            rs.close();
            st.close();
            
            return list;
  }
}
