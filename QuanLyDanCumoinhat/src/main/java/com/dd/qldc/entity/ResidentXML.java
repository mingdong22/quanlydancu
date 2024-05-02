
package com.dd.qldc.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@XmlRootElement(name = "Residents")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResidentXML {
    private List<Residents> residents;

    public List<Residents> getResidents() {
        return residents;
    }

    public void setResidents(List<Residents> residents) {
        this.residents = residents;
    } 
}
