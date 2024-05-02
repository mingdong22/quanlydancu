package com.dd.qldc.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SpecialPersons")
@XmlAccessorType(XmlAccessType.FIELD)
public class DoituongdacbietXML {
    
    private List<Doituongdacbiet> specialPerson;

    public List<Doituongdacbiet> getSpecialPerson() {
        return specialPerson;
    }

    public void setSpecialPerson(List<Doituongdacbiet> specialPerson) {
        this.specialPerson = specialPerson;
    }
}
