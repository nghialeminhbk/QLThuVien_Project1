/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;
import View.Add_edit_Delete_DocGia_View;
import View.QLMuonTra;
import View.QLSach;
/**
 *
 * @author Vostro 3580
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Add_edit_Delete_DocGia_View search= new Add_edit_Delete_DocGia_View();
       QLMuonTra qlmt= new QLMuonTra();
       QLSach qls= new QLSach();
       qls.setVisible(true);
    }
    
}
