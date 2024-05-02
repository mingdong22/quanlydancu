/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dd.qldc.controller;

import com.dd.qldc.dao.Quanlydoituongdacbietdao;
import com.dd.qldc.entity.Doituongdacbiet;
//import com.dd.qldc.view.Loginview;
import com.dd.qldc.view.Quanlydoituongdacbietview;
import com.dd.qldc.view.ResidentView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ASUS_PC
 */
public class Quanlydoituongdacbietcontroller {
    
 private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    private Quanlydoituongdacbietdao managerSpecialPerson;
    private Quanlydoituongdacbietview specialPersonView;
    private ResidentView view;
    //private Loginview loginView;
    //private MainView mainView;

    public Quanlydoituongdacbietcontroller(Quanlydoituongdacbietview View) {
        this.specialPersonView = View;
        managerSpecialPerson=new Quanlydoituongdacbietdao();
        View.addAddSpecialPersonListener(new AddSpecialPersonListener());
        View.addEditSpecialPersonListener(new EditSpecialPersonListener());
        View.addDeleteSpecialPersonListener(new DeleteSpecialPersonListener());
        View.addClearSpecialPersonListener(new ClearSpecialPersonListener());
        View.addSearchSpecialPersonListener(new SearchSpecialPersonListener());
        View.addcancelSpecialPersonListener(new CancelSpecialPersonListener());
        View.addSortByNameListener(new SortByNameListener());
        View.addSortByOpeningDateListener(new SortByOpeningDateListener());
        View.addSortByYearListener(new SortByYearListener());
        //View.addStatisticListener(new statisticViewListener());
        View.addlistSpecialPersonSelectionListener(new ListSpecialPersonSelectionListener());
        View.addSearchDialogListener(new SearchDialogListener());
        //View.addSearchDialogSpecialPersonListener(new SearchDialogSpecialPersonListener);
        View.addCancelSearchDialogListener(new CancelSearchDialogListener());
        View.addImageSpecialPersonListener(new ImageSpecialPersonListener());
        
        View.addStatisticAgeListener(new StatisticAgeListener());
        View.addStaticTypeListener(new StaticTypeListener());
        View.addStaticUndoListener(new StaticUndoListener());
        //
        View.addbBackListener(new BackUndoListener());
    }
    public void showQuanlydoituongdacbietview()
    {
        List<Doituongdacbiet> listdoituongdacbiet=managerSpecialPerson.getListSpecialPersons();
       specialPersonView.setVisible(true);
       specialPersonView.showListSpecialPersons(listdoituongdacbiet);
       specialPersonView.showCountListSpecialPerson(listdoituongdacbiet);
    }
    
    class AddSpecialPersonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Doituongdacbiet specialPerson=specialPersonView.getSpecialPersonInfo();
            if(specialPerson!=null)
            {
                managerSpecialPerson.add(specialPerson);
                specialPersonView.showSpecialPerson(specialPerson);
                specialPersonView.showListSpecialPersons(managerSpecialPerson.getListSpecialPersons());
                specialPersonView.showCountListSpecialPerson(managerSpecialPerson.getListSpecialPersons());
                specialPersonView.showMessage("Thêm thành công");
            }
        }
    }
    class EditSpecialPersonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            Doituongdacbiet specialPerson=specialPersonView.getSpecialPersonInfo();
            if(specialPerson!=null)
            {
                try {
                    managerSpecialPerson.edit(specialPerson);
                } catch (ParseException ex) {
                    Logger.getLogger(Quanlydoituongdacbietcontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
                specialPersonView.showSpecialPerson(specialPerson);
                specialPersonView.showListSpecialPersons(managerSpecialPerson.getListSpecialPersons());
                specialPersonView.showCountListSpecialPerson(managerSpecialPerson.getListSpecialPersons());
                specialPersonView.showMessage("Cập nhật thành công");
            }
        }
        
    }
     class DeleteSpecialPersonListener implements ActionListener
     {

        @Override
        public void actionPerformed(ActionEvent e) {
            Doituongdacbiet specialPerson=specialPersonView.getSpecialPersonInfo();
            if(specialPerson!=null)
            {
                managerSpecialPerson.delete(specialPerson);
                specialPersonView.showSpecialPerson(specialPerson);
                specialPersonView.showListSpecialPersons(managerSpecialPerson.getListSpecialPersons());
                specialPersonView.showCountListSpecialPerson(managerSpecialPerson.getListSpecialPersons());
                specialPersonView.showMessage("Xóa thành công");
            }
        }
         
     }
     class ClearSpecialPersonListener implements ActionListener
     {

        @Override
        public void actionPerformed(ActionEvent e) {
            specialPersonView.clearSpecialPersonInfo();
        }
         
     }
     class SearchSpecialPersonListener implements ActionListener
     {

        @Override
        public void actionPerformed(ActionEvent e) {
            specialPersonView.searchNameSpecialPersonInfo();
        }
         
     }
     class CancelSpecialPersonListener implements ActionListener
     {

        @Override
        public void actionPerformed(ActionEvent e) {
            specialPersonView.showListSpecialPersons(managerSpecialPerson.getListSpecialPersons());
            specialPersonView.cancelSearchSpecialPerson();
        }
         
     }
     class ImageSpecialPersonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            specialPersonView.SpecialPersonImage();
        }
        
    }
     class ListSpecialPersonSelectionListener implements ListSelectionListener
    {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            List<Doituongdacbiet> list = managerSpecialPerson.getListSpecialPersons();
            try {
                specialPersonView.fillSpecialPersonFromSelectedRow(list);
            } catch (ParseException ex) {
                Logger.getLogger(Quanlydoituongdacbietcontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      class CancelSearchDialogListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            specialPersonView.cancelDialogSearchSpecialPersonInfo();
        }
        
    }
       class UndoListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            specialPersonView.showListSpecialPersons(managerSpecialPerson.getListSpecialPersons());
            specialPersonView.cancelSearchSpecialPerson();
        }
        
    }
        class statisticViewListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
           specialPersonView.displayStatisticView();
        }
        
    }
         class SortByNameListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            managerSpecialPerson.sortSpecialPersonByName();
            specialPersonView.showListSpecialPersons(managerSpecialPerson.getListSpecialPersons());
        }
        
    }
     class SortByOpeningDateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            managerSpecialPerson.sortSpecialPersonByOpeningDate();
            specialPersonView.showListSpecialPersons(managerSpecialPerson.getListSpecialPersons());
        }

    }

    class SortByYearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            managerSpecialPerson.sortSpecialPersonByBirthDay();
            specialPersonView.showListSpecialPersons(managerSpecialPerson.getListSpecialPersons());
        }

    }     
    class StatisticAgeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            specialPersonView.displayStatisticView();
            specialPersonView.showstatisticAgeSpecialPersons(managerSpecialPerson.getListSpecialPersons());
        }
    }  
    class StaticTypeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            specialPersonView.displayStatisticView();
            specialPersonView.showstatisticTypeSpecialPersons(managerSpecialPerson.getListSpecialPersons());
        }
    } 
    class SearchDialogListener implements ActionListener {

       public void actionPerformed(ActionEvent e){
            boolean kt = false;
            List<Doituongdacbiet> temp = new ArrayList<>();
            int check = specialPersonView.getChooserSelectSearch();
            String search = specialPersonView.validateSearch();
            if(check == 1){
                // Tìm kiếm theo tên
                temp = managerSpecialPerson.searchdoituongdacbietname(search);
            }else if(check == 2){
                // Tìm kiếm theo địa chỉ
                temp = managerSpecialPerson.searchdoituongdacbietadress(search);
            }else if(check == 3){
                // Tìm kiếm theo loại đối tượng
                temp = managerSpecialPerson.searchdoituongdacbietyear(search);
            }
            if(!temp.isEmpty())specialPersonView.showListSpecialPersons(temp);
            else specialPersonView.showMessage("Không tìm thấy kết quả!");
        }
    

    } 
    class StaticUndoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           specialPersonView.UndoViewSpecialPerson();
        }

    } 
    class  BackUndoListener implements ActionListener{
         public void actionPerformed(ActionEvent e) {
        
         view =new ResidentView();
          ResidentController residentcontroller=new ResidentController(view);
          residentcontroller.showManagerView();
          specialPersonView.setVisible(false);
         }
    }
}