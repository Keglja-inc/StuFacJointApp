package com.example.stufacjoint.db;

public class Interesi {
	
	
	private String idInteresa;
	private String naziv;
	
	
	
	
	public Interesi(String idInteresa, String naziv) {
		super();
		this.idInteresa = idInteresa;
		this.naziv = naziv;
	}
	public String getIdInteresa() {
		return idInteresa;
	}
	public void setIdInteresa(String idInteresa) {
		this.idInteresa = idInteresa;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	

}
