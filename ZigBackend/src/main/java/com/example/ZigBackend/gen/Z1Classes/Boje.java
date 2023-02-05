package com.example.ZigBackend.gen.Z1Classes;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "boja"
})
@XmlRootElement(name = "bojeZnaka")
public class Boje {

    @XmlElement(type = String.class)
    protected List<String> boja;

    public List<String> getBoja() {
        return boja;
    }

    public void setBoja(List<String> boja) {
        this.boja = boja;
    }
}