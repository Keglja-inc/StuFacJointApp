package com.example.stufacjoint.db;

/**
 * 
 * @author Deni
 * @version 1.3
 *
 */
public class Interesi {
	
	
	private String idInteresa;
	private String naziv;
	
	
	
	/**
	 * 
	 * @param idInteresa - Id interesa
	 * @param naziv - naziv interesa
	 */
	public Interesi(String idInteresa, String naziv) {
		super();
		this.idInteresa = idInteresa;
		this.naziv = naziv;
	}
	/**
	 * Dohvaćanje ID interesa
	 * @return idInteresa 
	 */
	public String getIdInteresa() {
		return idInteresa;
	}
	/**
	 * Postavljanje ID interesa
	 * @param idInteresa
	 */
	public void setIdInteresa(String idInteresa) {
		this.idInteresa = idInteresa;
	}
	/**
	 * Dohvaćanje Naziva interesa
	 * @return naziv -  naziv interesa kojeg smo dohvatili
	 */
	public String getNaziv() {
		return naziv;
	}
	/**
	 * Postavljanje naziva interesa
	 * @param naziv - naziv interesa koji se postavlja
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	

}
