package main.java.com.xws.a1document.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import main.java.com.xws.a1document.xml.model.ObrazacA1;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zahtevi"
})
@XmlRootElement(name = "obrazac_a_1_list")
public class ObrazacA1List {	
	@XmlElement(name = "obrazac_a_1", required = true)
	protected List<ObrazacA1> zahtevi;
	
	public ObrazacA1List() {
		this.zahtevi = new ArrayList<>();
	}
	
	public ObrazacA1List(List<ObrazacA1> zahtevi) {
		this.zahtevi = zahtevi;
	}

	public List<ObrazacA1> getZahtevi() {
		return zahtevi;
	}

	public void setZahtevi(List<ObrazacA1> zahtevi) {
		this.zahtevi = zahtevi;
	}
	
	
}
