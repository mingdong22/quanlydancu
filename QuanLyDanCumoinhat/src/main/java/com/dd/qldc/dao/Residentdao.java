/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dd.qldc.dao;

import com.dd.qldc.entity.ResidentXML;
import com.dd.qldc.view.ResidentView;
import com.dd.qldc.entity.Residents;
import com.dd.qldc.utils.FileUtils;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
public class Residentdao {

    private static final String RESIDENT_FILE_NAME = "Residents.xml";
    private List<Residents> listResidents;
    private ResidentView residentView;
     //
    public Residentdao(){
        this.listResidents = readListResidents();
         if (listResidents == null) {
            listResidents = new ArrayList<>();
        }
    }
     public void writeListResidents(List<Residents> residents) 
    {
        ResidentXML residentXML = new ResidentXML();
        residentXML.setResidents(residents);
        FileUtils.writeXMLtoFile(RESIDENT_FILE_NAME, residentXML);
    }
    public List<Residents> readListResidents() 
    {
        List<Residents> list = new ArrayList<Residents>();
        ResidentXML residentXML = (ResidentXML) FileUtils.readXMLFile(
                RESIDENT_FILE_NAME, ResidentXML.class);
        if (residentXML != null) 
        {
            list = residentXML.getResidents();
        }
        return list;
    }
    
    //them resident
    public void add(Residents resident){
        int max=0;
        for(int i=0;i<listResidents.size();i++){
            if(listResidents.get(i).getId()>max)max=listResidents.get(i).getId();
        }
        resident.setId(max+1);
        listResidents.add(resident);
    }
    //hien thi danh sach theo ten
    public List <Residents>searchResidentName(String search){
        List <Residents>temp=new ArrayList<>();
        for(Residents person :listResidents){
            if(person.getName().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
    //hien thi danh sach theo nơi ở
    public List <Residents>searchResidentAddress(String search){
        List <Residents>temp=new ArrayList<>();
        for(Residents person:listResidents){
            if(person.getAddress().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
    //hiển thị theo IDFamily
    public List<Residents> searchResidentIDFamily(String search){
        List<Residents>temp = new ArrayList<>();
        for(Residents person : listResidents){
            if(person.getIDFamily().contains(search)){
                temp.add(person);
            }
        }
        return temp;
    }
    // hiển thị listSpecialPersons theo năm sinh
    public List <Residents>searchResidentyear(String year){
        List<Residents>temp=new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        for(Residents person :listResidents ){
            //chuyển đổi ngày sinh thành chuỗi năm
            String personYearStr =yearFormat.format(person.getBirthday());
            
            //so sánh chưỡi năm với năm tìm kiếm 
            if(personYearStr.equals(year)){
                temp.add(person);
            }
        }
        return temp;
    }    
    
   //hàm kiểm tra số CMT không trùng 
    public boolean isCMTUnique(Residents resident){
        String cmt =resident.getCMT();
        for(Residents existingResident :listResidents){
            if(existingResident.getCMT().equals(cmt)){
                return false;
            }
        }
        return true;
    }
    
    
    //Hàm kiểm tra số hộ khẩu không trùng cho vai trò "Chủ hộ"
      public boolean isHouseholdUnique(Residents resident) {
        String IDFamily=resident.getIDFamily();
        String role = resident.getRole();
        for (Residents existingResident : listResidents) {
            if ("Chủ hộ".equals(role) && existingResident.getIDFamily().equals(IDFamily) && existingResident.getRole().equals(role)) {
                return false; // Trùng số hộ khẩu cho vai trò "Chủ hộ"
            }
        }
        return true; // Số hộ khẩu không trùng cho vai trò "Chủ hộ"
    }
      
      //cái này chưa rõ 
       public void showMessage(String message) {
        JOptionPane.showMessageDialog(residentView, message);
    }
       
    //hàm chỉnh sửa 
       public void edit (Residents resident)   throws ParseException{ 
           SimpleDateFormat fDate =new SimpleDateFormat("dd/MM/yyyy");
           int size =listResidents.size();
           for(int i=0;i<size;i++){
               if(listResidents.get(i).getId()==resident.getId()){
                    listResidents.get(i).setIDFamily(resident.getIDFamily());
                listResidents.get(i).setName(resident.getName());
                listResidents.get(i).setRole(resident.getRole());
                listResidents.get(i).setBirthday(resident.getBirthday());
                listResidents.get(i).setAddress(resident.getAddress());
                listResidents.get(i).setSex(resident.getSex());
                listResidents.get(i).setTypeCMT(resident.getTypeCMT());
                listResidents.get(i).setCMT(resident.getCMT());
                listResidents.get(i).setBirthPlace(resident.getBirthPlace());
                listResidents.get(i).setPhoneNumber(resident.getPhoneNumber());

               // writeListResidents(listResidents);
                break;
               }
           }
       }
       
       //hàm xóa 
         public boolean delete(Residents resident) {
        boolean isFound = false;
        int size = listResidents.size();
        for (int i = 0; i < size; i++) {
            if (listResidents.get(i).getId() == resident.getId()) {
                listResidents.remove(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            // Cập nhật lại ID của các đối tượng sau
            for (int i = 0; i < listResidents.size(); i++) {
                if (listResidents.get(i).getId() > resident.getId()) {
                    listResidents.get(i).setId(listResidents.get(i).getId() - 1);
                }
            }
           // writeListResidents(listResidents);
            return true;
        }
        return false;
    }
   
   //sắp xếp resident   theo tên
         public void sortResidentsByName(){
             Collections.sort(listResidents,new Comparator <Residents>(){
                 public int compare (Residents p1,Residents p2){
                     Collator collator =Collator.getInstance(new Locale ("vi","VN"));
                     //so sánh tên
                     int result =collator.compare(p1.getLastName(), p2.getLastName());
                     if(result ==0){
                         //nếu tên bằng nhau thì so sáng họ lót
                         result =collator.compare(p1.getFirstName(), p2.getFirstName());
                     }
                     return result;
                 }
             });
         }
         
      // sắp xếp theo IDFamily
          public void sortResidentsByIDFamily(){
             Collections.sort(listResidents,new Comparator <Residents>(){
                 public int compare (Residents person1,Residents person2){
                     return person1.getIDFamily().compareTo(person2.getIDFamily());
                 }
             });
         }
         
         
        
      // sắp xếp theo ID
         public void sortResidentsByID(){
             Collections.sort(listResidents,new Comparator<Residents >(){
                 public int compare (Residents person1,Residents person2){
                     if(person1.getId()>person2.getId()){
                         return 1;
                     }
                        return -1;
                 }
               
             });
         }
         //
    public List<Residents>getListResidents(){
        return this.listResidents;
    }     
         
     
  
      
}