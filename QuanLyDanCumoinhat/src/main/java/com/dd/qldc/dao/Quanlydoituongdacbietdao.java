/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dd.qldc.dao;

/**
 *
 * @author ASUS_PC
 */
//controller của đối tượng đặc biệt
import com.dd.qldc.entity.Doituongdacbiet;
import com.dd.qldc.entity.DoituongdacbietXML;
import com.dd.qldc.utils.FileUtils;
import com.dd.qldc.view.Quanlydoituongdacbietview;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
public class Quanlydoituongdacbietdao {
    private static final String SPECIALPERSON_FILE_NAME = "SpecialPerson.xml";
    private List<Doituongdacbiet> listSpecialPerson;
    public Quanlydoituongdacbietdao()
    {
        this.listSpecialPerson = readListSpecialPersons();
        if(listSpecialPerson==null)
        {
            listSpecialPerson=new ArrayList<Doituongdacbiet>();
        }
    }
  
    public void writeListSpecialPerson(List<Doituongdacbiet> specialPersons)
    {
        DoituongdacbietXML doituongdacbietXML=new DoituongdacbietXML();
        doituongdacbietXML.setSpecialPerson(specialPersons);
        FileUtils.writeXMLtoFile(SPECIALPERSON_FILE_NAME, doituongdacbietXML);
        
        
    }
    
   
    public List<Doituongdacbiet> readListSpecialPersons()
    {
        List<Doituongdacbiet> list=new ArrayList<Doituongdacbiet>();
        DoituongdacbietXML doituongdacbietXML=(DoituongdacbietXML) FileUtils.readXMLFile(SPECIALPERSON_FILE_NAME, DoituongdacbietXML.class);
        if(doituongdacbietXML!=null)
        {
            list=doituongdacbietXML.getSpecialPerson();
        }
        return list;
    }
    
    public List<Doituongdacbiet> searchdoituongdacbietname(String search)
    {
        List<Doituongdacbiet> temp=new ArrayList<>();
        for(Doituongdacbiet nguoi:listSpecialPerson)
        {
            if(nguoi.getName().toLowerCase().contains(search.toLowerCase()))
            {
                temp.add(nguoi);
            }
        }
        return temp;
    }
    public List<Doituongdacbiet> searchdoituongdacbietadress(String search)
    {
        List<Doituongdacbiet> temp=new ArrayList<>();
        for(Doituongdacbiet A:listSpecialPerson)
        {
            if(A.getAddress().toLowerCase().contains(search.toLowerCase()))
            {
                temp.add(A);
            }
        }
        return temp;
    }
    public List<Doituongdacbiet> searchdoituongdacbietyear(String year)
    {
        List<Doituongdacbiet> temp=new ArrayList<>();
        SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
        for(Doituongdacbiet A:listSpecialPerson)
        {
            String nguoiyearstr=yearFormat.format(A.getBirthday());
            if(nguoiyearstr.equals(year))
            {
                temp.add(A);
            }
        }
        return temp;
    }
    public void add(Doituongdacbiet doituongdacbiet)
    {
        int max=0;
        for(int i=0;i<listSpecialPerson.size();i++)
        {
            if(listSpecialPerson.get(i).getId()>max) max=listSpecialPerson.get(i).getId();
        }
        doituongdacbiet.setId(max+1);
        listSpecialPerson.add(doituongdacbiet);
        writeListSpecialPerson(listSpecialPerson);
    }
    public void edit (Doituongdacbiet doituongdacbiet) throws ParseException
    {
        SimpleDateFormat fdate=new SimpleDateFormat("dd/MM/yyyy");
        for(int i=0;i<listSpecialPerson.size();i++)
        {
             if (listSpecialPerson.get(i).getId() == doituongdacbiet.getId()) 
             {
            listSpecialPerson.get(i).setName(doituongdacbiet.getName());
            listSpecialPerson.get(i).setBirthday(doituongdacbiet.getBirthday());
            listSpecialPerson.get(i).setAddress(doituongdacbiet.getAddress());
            listSpecialPerson.get(i).setOpeningDate(doituongdacbiet.getOpeningDate());
            listSpecialPerson.get(i).setType(doituongdacbiet.getType());
            listSpecialPerson.get(i).setImage(doituongdacbiet.getImage());
            writeListSpecialPerson(listSpecialPerson);
            break;
             }
        }
    }
     public void image(Doituongdacbiet specialPerson) 
    {
        
    }
      public boolean delete(Doituongdacbiet specialPerson) {
         boolean isFound = false;
        int size = listSpecialPerson.size();
        for (int i = 0; i < size; i++) 
        {
            if (listSpecialPerson.get(i).getId() == specialPerson.getId()) 
            {
                specialPerson = listSpecialPerson.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) 
        {
            listSpecialPerson.remove(specialPerson);
            writeListSpecialPerson(listSpecialPerson);
            return true;
        }
        return false;
    }
public void sortSpecialPersonByName() 
    {
        Collections.sort(listSpecialPerson, new Comparator<Doituongdacbiet>() {
            public int compare(Doituongdacbiet p1, Doituongdacbiet p2) {
                Collator collator = Collator.getInstance(new Locale("vi", "VN"));
                // So sánh tên
                int result = collator.compare(p1.getLastName(), p2.getLastName());
                if (result == 0) {
                    // Nếu tên bằng nhau, so sánh họ lót
                    result = collator.compare(p1.getFirstName(), p2.getFirstName());
                }
                return result;
            }
        });
        //Collections.sort(listSpecialPersons, (p1, p2) -> collator.compare(p1.getLastName(), p2.getLastName()));
    }
 public void sortSpecialPersonByOpeningDate() 
    {
        Collections.sort(listSpecialPerson, new Comparator<Doituongdacbiet>() 
        {
            public int compare(Doituongdacbiet SpecialPerson1, Doituongdacbiet SpecialPerson2) 
            {
                return SpecialPerson1.getOpeningDate().compareTo(SpecialPerson2.getOpeningDate());
            }
        });
    }
    
    /**
     * sắp xếp danh sách SpecialPerson theo năm sinh theo tứ tự tăng dần
     */
    public void sortSpecialPersonByBirthDay() {
        Collections.sort(listSpecialPerson, new Comparator<Doituongdacbiet>() {
            public int compare(Doituongdacbiet person1, Doituongdacbiet person2) {
                return person1.getBirthday().compareTo(person2.getBirthday());
            }
        });
    }

    public List<Doituongdacbiet> getListSpecialPersons() 
    {
        return listSpecialPerson;
    }

    public void setListSpecialPersons(List<Doituongdacbiet> listSpecialPersons) 
    {
        this.listSpecialPerson = listSpecialPersons;
    }
}
