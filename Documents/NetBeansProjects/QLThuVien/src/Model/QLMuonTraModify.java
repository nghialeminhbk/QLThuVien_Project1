/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Controler.SQLConnection;
import com.sun.tools.javac.Main;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.Vector;
import Model.QLMuonTra;
/**
 *
 * @author Vostro 3580
 */
public class QLMuonTraModify {
    public QLMuonTraModify(){
        
    }
    
    public int Add(String dtMT, String dtDG, String dtTT, String dtNM, String dtNT, String dtTC){
        Connection conn=null;
        PreparedStatement ps=null;
        int ret=-1;
        try{
            conn=new SQLConnection().Connect();
            String sql="insert into Muontra_LeMinhNghia values (?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, dtMT);
            ps.setString(2, dtDG);
            ps.setString(3, dtTT);
            ps.setDate(4, Date.valueOf(dtNM));
            ps.setDate(5, Date.valueOf(dtNT));
            ps.setFloat(6, Float.valueOf(dtTC));
            ret=ps.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(conn!=null){
                    conn.close();
                }
                if(ps!=null){
                    ps.close();   
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return ret;
        }
    }
    
    public Vector comboxDG(){
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        Vector data= new Vector();
        try{
            conn=new SQLConnection().Connect();
            String sql="select Ma_DG_20183960 from DocGia_LeMinhNghia";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                data.add(rs.getString("Ma_DG_20183960"));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(conn!=null){
                    conn.close();
                }
                if(st!=null){
                    st.close();
                }
                if(rs!=null){
                    rs.close();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return data;
        }
               
    }
    
    public Vector comboxTT(){
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        Vector data= new Vector();
        try{
            conn=new SQLConnection().Connect();
            String sql="select Ma_TT_20183960 from ThuThu_LeMinhNghia";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                data.add(rs.getString("Ma_TT_20183960"));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(conn!=null){
                    conn.close();
                }
                if(st!=null){
                    st.close();
                }
                if(rs!=null){
                    rs.close();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return data;
        }
               
    }
    
    public int Delete(String data){
        Connection conn=null;
        Statement st=null;
        int ret=-1;
        try{
            conn= new SQLConnection().Connect(); 
            String sql="Delete from Muontra_LeMinhNghia where MaMT_20183960='"+data+"'";
            st=conn.createStatement();
            ret=st.executeUpdate(sql);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(conn!=null){
                    conn.close();
                }
                if(st!=null){
                    st.close();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return ret;
        }
    }
    
    public int Edit(QLMuonTra mt){
        Connection conn=null;
        PreparedStatement ps=null;
        int ret=-1;
        try{
            conn=new SQLConnection().Connect();
            String sql="update Muontra_LeMinhNghia set Ma_DG_20183960=?, Ma_TT_20183960=?, Ngay_muon_20183960=?, Ngay_hentra_20183960=?, Tiencoc_20183960=? where MaMT_20183960=?";
            ps=conn.prepareStatement(sql);
            ps.setString(6, mt.getMaMT());
            ps.setString(1, mt.getMaDG());
            ps.setString(2, mt.getMaTT());
            ps.setDate(3, Date.valueOf(mt.getDateM()));
            ps.setDate(4, Date.valueOf(mt.getDateT()));
            ps.setFloat(5, Float.valueOf(mt.getTienCoc()));
            ret=ps.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(conn!=null){
                    conn.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return ret;
        }
    }
    
}
