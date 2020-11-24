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
import java.sql.PreparedStatement;
/**
 * 
 *
 * @author Vostro 3580
 */
public class QLSachModify {
    public QLSachModify(){}
    
    public ResultSet Search(String data, int id){
        Connection conn= null;
        Statement st= null;
        ResultSet rs=null;
        String sql=null;
        try{
            conn=new SQLConnection().Connect();
            st=conn.createStatement();
            if(data.length()==0){
                sql="select * from Sach_LeMinhNghia";
            }
            if(data.length()>0){
                if(id==1){
                    sql="select * from Sach_LeMinhNghia where Masach_20183960 like '" + data+"%'";
                }
                if(id==2){
                    sql="select * from Sach_LeMinhNghia where Tensach_20183960 like '" + data+"%'";
                }
                if(id==3){
                    sql="select * from Sach_LeMinhNghia where Tacgia_20183960 like '" + data+"%'";
                }
                if(id==4){
                    sql="select * from Sach_LeMinhNghia where NhaXB_20183960 like '" + data+"%'";
                }
                if(id==5){
                    sql="select * from Sach_LeMinhNghia where NamXB_20183960 like '" + data+"%'";
                }
            }
            rs=st.executeQuery(sql);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            return rs;
        }
        
    }
    
    public int Edit(String dataMa, String dataTen, String dataTG, String dataNXB, String dataNamXB, String dataDG, String dataGT){
        String sql="update Sach_LeMinhNghia set Tensach_20183960=?, Tacgia_20183960=?, NhaXB_20183960=?, NamXB_20183960=?,Dongia_20183960=?, Gioithieu_20183960=? where Masach_20183960=?";
        Connection conn=null;
        PreparedStatement ps=null;
        int ret=0;
        try{
            conn=new SQLConnection().Connect();
            ps=conn.prepareStatement(sql);
            ps.setString(1, dataTen);
            ps.setString(2, dataTG);
            ps.setString(3, dataNXB);
            ps.setString(4, dataNamXB);
            ps.setString(5, dataDG);
            ps.setString(6, dataGT);
            ps.setString(7, dataMa);
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
    
    public int Add(String dataMa, String dataTen, String dataTG, String dataNXB, int dataNamXB, float dataDG, String dataGT){
        Connection conn=null;
        PreparedStatement ps=null;
        int ret=-1;
        try{
            conn= new SQLConnection().Connect();
            String sql="insert into Sach_LeMinhNghia values (?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, dataMa);
            ps.setString(2, dataTen);
            ps.setString(3, dataTG);
            ps.setString(4, dataNXB);
            ps.setInt(5, dataNamXB);
            ps.setFloat(6, dataDG);
            ps.setString(7, dataGT);  
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
    
    public int Delete(String data){
        Connection conn=null;
        PreparedStatement ps=null;
        int ret=-1;
        try{
            conn=new SQLConnection().Connect();
            String sql="Delete from Sach_LeMinhNghia where Masach_20183960=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, data);
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
