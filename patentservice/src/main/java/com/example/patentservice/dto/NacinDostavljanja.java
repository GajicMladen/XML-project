package com.example.patentservice.dto;

public class NacinDostavljanja {
	  private boolean elektronski;
	  private boolean papirno;
	  
	public boolean isElektronski() {
		return elektronski;
	}
	public void setElektronski(boolean elektronski) {
		this.elektronski = elektronski;
	}
	public boolean isPapirno() {
		return papirno;
	}
	public void setPapirno(boolean papirno) {
		this.papirno = papirno;
	}
}
