/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dd.qldc.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS_PC
 */
@XmlRootElement(name = "SpecialPerson")
@XmlAccessorType(XmlAccessType.FIELD)
public class Doituongdacbiet extends Person implements Serializable{
    private static final long serialVersionUID = 1L;
   // private int id;
  //  private String name;
  //  private int year;
  //  rivate String address;
    private Date OpeningDate;
    private String type;
    private byte[] picture;
    //private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    public Doituongdacbiet() 
    {
        
    }
    public Doituongdacbiet(int id, String name, Date birthday, String address, String OpeningDate, String type, byte[] image) throws ParseException 
    {
        super();
        SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        this.id= id;
        this.name = name;
        this.birthday = birthday;
        this.address= address;
        this.OpeningDate = fDate.parse(OpeningDate);
        this.type=type;
        this.picture=image;
    }
    
    public Date getOpeningDate() 
    {
        return this.OpeningDate;
    }

    public void setOpeningDate(Date OpeningDate) 
    {
        //SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        this.OpeningDate = OpeningDate;
    }
    
    public String getType() 
    {
        return this.type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }
    public byte[] getImage()
    {
        return picture;
    }
    
    public void setImage(byte[] image)
    {
        this.picture=image;
    }
}
