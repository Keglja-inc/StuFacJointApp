package com.example.stufacjoint.db;

/**
 * 
 * @author Deni
 * @version 1.4
 */

public class Profil {
	
	
	
	int id;
	String ime;
	String prezime;
	String mail;
	String lozinka;
	/**
	 * metoda za punjenje profilnih podataka
	 * @param id - id korisnika
	 * @param ime - ime korinsika
	 * @param prezime - prezime korisnika
	 * @param mail - email adresa korisnika
	 * @param lozinka - lozinka korisnika
	 */
	public Profil(int id, String ime, String prezime, String mail,
			String lozinka) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.mail = mail;
		this.lozinka = lozinka;
	}
	/**
	 * metoda koja će se pozivati bez ulazih parametara
	 * već će oni biti definirani u metodi profil  s parametrima, koja se nalazi unutar metode profil
	 */
public Profil() {
		
	}

	/**
	 * metoda za punjenje profilnih podataka
	 * @param ime - ime korinsika
	 * @param prezime - prezime korisnika
	 * @param mail - email adresa korisnika
	 * @param lozinka - lozinka korisnika
	 */
	public Profil(String ime, String prezime, String mail, String lozinka) {
	super();
	this.ime = ime;
	this.prezime = prezime;
	this.mail = mail;
	this.lozinka = lozinka;
}
	/**
	 * Dohvaća ID
	 * @return ID
	 */
	public int getId() {
		return id;
	}
	/**
	 * postavlja ID
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Dohvaća ime
	 * @return ime
	 */
	public String getIme() {
		return ime;
	}
	/**
	 * Postavlja ime
	 * @param ime
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}
	/**
	 * Dohvaća prezime
	 * @return prezime
	 */
	public String getPrezime() {
		return prezime;
	}
	/**
	 * postavlja prezime
	 * @param prezime
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	/**
	 * Dohvaća email
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * Postavlja email
	 * @param mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * Dohvaća lozinku
	 * @return lozinka
	 */
	public String getLozinka() {
		return lozinka;
	}
	/**
	 * Postavlja lozinku
	 * @param lozinka
	 */
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
}
