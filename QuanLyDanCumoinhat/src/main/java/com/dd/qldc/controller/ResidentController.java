/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dd.qldc.controller;

import com.dd.qldc.controller.MainController;
import com.dd.qldc.dao.Residentdao;
import com.dd.qldc.view.ResidentView;
import com.dd.qldc.entity.Residents;
import com.dd.qldc.view.Loginview;

import com.dd.qldc.view.Quanlydoituongdacbietview;
import com.dd.qldc.view.Loginview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ResidentController {
    private ResidentView residentView;
    private Residentdao residentdao;
   
    private Quanlydoituongdacbietview qldtdbview;
 
    
   
    //
    public ResidentController (ResidentView view){
        this.residentView=view;
        this.residentdao = new Residentdao();
        //view.addUndoListener(new UndoListener());
        view.addAddResidentListener(new AddResidentListener());
        view.addListResidentSelectionListener(new ListResidentsSelectionListener());
        view.addEditResidentListener(new EditResidentListener());
        view.addClearListener(new ClearResidentListener());
        view.addDeleteSpecialPersonListener(new DeleteSpecialPersonListener());
       // 
        view.addSearchListener(new SearchResidentViewListener());
        //
        view.addSearchDialogListener(new SearchResidentListener());
        view.addCancelSearchResidentListener(new CancelSearchResidentListener());
        view.addCancelDialogListener(new CancelDialogSearchResidentListener());
        //
        view.addSortSpecialPersonListener(new  SortviewResidentListener());
        
         view.addoksort(new oksort() );
        view.addCancelSort(new Cancelsort());
        //
        view.addToolListener(new ToolResidentListener());
        view.addHelpListener(new HelpResidentListener());
        view.addCustomListener(new CustomResidentListener());
        //
        // view.addUndoListener(new UndoListener());
         //
         view.addqldtdb(new qldtdbListener());
         view.addOut(  new OutResidentListener());
          //
        
         
    }
    //
       public void showManagerView() 
    {
        List<Residents> residentsList = residentdao.getListResidents();
        residentView.setVisible(true);
        residentView.showListResidents(residentsList);
        residentView.showCountListResidents(residentsList);
        residentView.showStatisticTypeCMT(residentsList);
        residentView.showStatisticIDFamily(residentsList);
    }
    //
//    class UndoListener implements ActionListener 
//    {
//        public void actionPerformed(ActionEvent e) 
//        {
//            mainView = new MainView();
//           MainController mainController =new MainController(mainView);
//            mainController.showMainView();
//            residentView.setVisible(false);
//        }
//    }
   //
   class AddResidentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Residents residents = residentView.getResidentInfo();
            if (residents != null) {
                try {
                    if (!residentdao.isCMTUnique(residents)) {
                        // Lấy thông báo từ ngoại lệ và hiển thị
                        throw new IllegalArgumentException("Lỗi: Số chứng minh thư đã tồn tại!");
                    }
                    
                    if (!residentdao.isHouseholdUnique(residents)) {
                        // Lấy thông báo từ ngoại lệ và hiển thị
                        throw new IllegalArgumentException("Lỗi: Đã có chủ hộ đối với số hộ khẩu này");
                    }
                    
                    residentdao.add(residents);
                    residentView.showResidents(residents);
                    residentView.showListResidents(residentdao.getListResidents());
                    residentView.showCountListResidents(residentdao.getListResidents());
                    residentView.showStatisticTypeCMT(residentdao.getListResidents());
                    residentView.showStatisticIDFamily(residentdao.getListResidents());
                    residentView.showMessage("Thêm thành công!");
                } catch (IllegalArgumentException ex) {
                    residentView.showMessage(ex.getMessage());
                }
            }
        }
    }
    
    //
    class EditResidentListener implements ActionListener{
        public void actionPerformed (ActionEvent e){
            Residents resident =residentView.getResidentInfo();
            if(resident!=null){
                try {
                    residentdao.edit(resident);
                } catch (ParseException ex) {
                    Logger.getLogger(ResidentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                residentView.showResidents(resident);
                residentView.showListResidents(residentdao.getListResidents());
                residentView.showCountListResidents(residentdao.getListResidents());
                residentView.showStatisticTypeCMT(residentdao.getListResidents());
                residentView.showStatisticIDFamily(residentdao.getListResidents());
                residentView.showMessage("Cập nhật thành công!");   
            }
        }     
    }
    
    class DeleteSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            Residents resident = residentView.getResidentInfo();
            if (resident != null) 
            {
                residentdao.delete(resident);
                residentView.clearResidentInfo();
                residentView.showListResidents(residentdao.getListResidents());
                residentView.showCountListResidents(residentdao.getListResidents());
                residentView.showStatisticTypeCMT(residentdao.getListResidents());
                residentView.showStatisticIDFamily(residentdao.getListResidents());
                residentView.showMessage("Xóa thành công!");
            }
        }
    }
    class ListResidentsSelectionListener implements ListSelectionListener 
    {
        public void valueChanged(ListSelectionEvent e) 
        {
            List<Residents> residentsList = residentdao.getListResidents();
            try {
                residentView.fillResidentFromSelectedRow(residentsList);
                //residentView.fillResidentFromSelectedRow();
                
            } catch (ParseException ex) {
                Logger.getLogger(ResidentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //
    class ClearResidentListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            residentView.clearResidentInfo();
        }     
    }
  
    //
    class CancelSearchResidentListener implements ActionListener{
        public void actionPerformed (ActionEvent e){
            residentView.showListResidents(residentdao.getListResidents());
            residentView.cancelSearchResident();
        }
    }
    //
    class CancelDialogSearchResidentListener implements ActionListener {
        public void actionPerformed (ActionEvent e){
            residentView.cancelDialogSearchResidentInfo();
        }
    }
    //
      //tìm kiếm theo yêu cầu
    class SearchResidentViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            residentView.SearchResidentInfo();
        }
    }
    //
   class SearchResidentListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean kt = false;
            List<Residents> temp = new ArrayList<>();
            int check = residentView.getChooseSelectSearch();
            String search = residentView.validateSearch();
            if(check == 1){
                // Tìm kiếm theo tên
                temp = residentdao.searchResidentIDFamily(search);
            }else if(check == 2){
                // Tìm kiếm theo địa chỉ
                temp = residentdao.searchResidentName(search);
            }else if(check == 3){
                // Tìm kiếm theo loại đối tượng
                temp = residentdao.searchResidentyear(search);
            }else if(check == 4){
                // Tìm kiếm theo loại đối tượng
                temp = residentdao.searchResidentAddress(search);
            }
            if(!temp.isEmpty())residentView.showListResidents(temp);
            else residentView.showMessage("Không tìm thấy kết quả!");
        }
    }
    //

    //
     class oksort implements ActionListener{
          public void actionPerformed(ActionEvent e ){
              boolean kt = false;
            List<Residents> temp = new ArrayList<>();
            int check = residentView.getChooseSelectSort();
            if(check == 1){
                residentdao.sortResidentsByID();
                residentView.showListResidents(residentdao.getListResidents());
            }else if(check == 2){
                residentdao.sortResidentsByName();
                residentView.showListResidents(residentdao.getListResidents());
            }else if(check == 3){
                residentdao.sortResidentsByIDFamily();
                residentView.showListResidents(residentdao.getListResidents());
            } else
                residentView.showMessage("Bạn chưa chọn tiêu chí sắp xếp");
              residentView.cancelshowsortsort();
          }
       }
     //
      class SortviewResidentListener implements ActionListener{
        public void actionPerformed(ActionEvent e ){
        residentView.sortResidentInfo();
        }
    }
     
     //
       class Cancelsort  implements ActionListener{
            public void actionPerformed(ActionEvent e ){
                residentView.showListResidents(residentdao.getListResidents());
                residentView.cancelshowsortsort();
            }
       }
      /*
     class CancelsortResidentListener implements ActionListener{
         public void actionPerformed(ActionEvent e ){
             //residentView.showListResidents(residentdao.getListResidents());
             residentView.cancelDialogSortResidentInfo();
         }
     }
    */
   
    //
    class ToolResidentListener implements ActionListener{
          public void actionPerformed(ActionEvent e ){
        residentView.ToolresidentInfo();
        }
    }
    //
     class HelpResidentListener implements ActionListener{
          public void actionPerformed(ActionEvent e ){
        residentView.HelpresidentInfo();
        }
    }
     //

      class CustomResidentListener implements ActionListener{
          public void actionPerformed(ActionEvent e ){
        residentView.CustomresidentInfo();
        }
    }
      //
      
      class qldtdbListener implements ActionListener{
          public void actionPerformed(ActionEvent e ){
       qldtdbview =new Quanlydoituongdacbietview();
       Quanlydoituongdacbietcontroller qldtdbcontroller =new  Quanlydoituongdacbietcontroller(qldtdbview);
       qldtdbcontroller.showQuanlydoituongdacbietview();
       residentView.setVisible(false);
        }
    }
      //
       class OutResidentListener implements ActionListener{
          public void actionPerformed(ActionEvent e ){
       residentView.setVisible(false);
       Loginview view=new Loginview();
       Logincontroller logincontroller =new Logincontroller (view);
       logincontroller.showLoginview();
       
        }
    }
    //
    
       
       
       //
      
      
}
