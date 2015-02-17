package com.example.stufacjoint.db;

import java.util.Date;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
/**
 * 
 * @author Deni
 * @version 1.4
 */
@Table (name = "Sveucilista")
public class Sveucilista extends Model {
	
	
		@Column (name = "vrijeme")
		private Date vrijeme;
		@Column (name="brojZapisa")
		private long brojZapisa;
		@Column (name = "podaci")
		private long podaci;
		@Column (name = "idVisokogUcilista")
		private long idVisokogUcilista;
		@Column (name = "naziv")
		private String naziv;
		@Column (name = "tipVisokogUcilista_idtipVisokogUcilista")
		private long tipVisokogUcilista_idtipVisokogUcilista;
		@Column (name="statusVisokogUcilista_idstatusVisokogUcilista")
		private long statusVisokogUcilista_idstatusVisokogUcilista;
		@Column (name="zadnjaIzmjena")
		private Date zadnjaIzmjena;
		
		
		
		public Sveucilista(){
			super();
			
		}
		
		/**
		 * metoda za punjenje zapisa sveucilišta
		 * 
		 * @param vrijeme 
		 * @param brojZapisa
		 * @param podaci
		 * @param idVisokogUcilista
		 * @param naziv
		 * @param tipVisokogUcilista_idtipVisokogUcilista
		 * @param statusVisokogUcilista_idstatusVisokogUcilista
		 * @param zadnjaIzmjena
		 */
		public Sveucilista(Date vrijeme, long brojZapisa, long podaci,
				long idVisokogUcilista, String naziv,
				long tipVisokogUcilista_idtipVisokogUcilista,
				long statusVisokogUcilista_idstatusVisokogUcilista,
				Date zadnjaIzmjena) {
			super();
			this.vrijeme = vrijeme;
			this.brojZapisa = brojZapisa;
			this.podaci = podaci;
			this.idVisokogUcilista = idVisokogUcilista;
			this.naziv = naziv;
			this.tipVisokogUcilista_idtipVisokogUcilista = tipVisokogUcilista_idtipVisokogUcilista;
			this.statusVisokogUcilista_idstatusVisokogUcilista = statusVisokogUcilista_idstatusVisokogUcilista;
			this.zadnjaIzmjena = zadnjaIzmjena;
		}



		public Date getVrijeme() {
			return vrijeme;
		}


		public void setVrijeme(Date vrijeme) {
			this.vrijeme = vrijeme;
		}


		public long getBrojZapisa() {
			return brojZapisa;
		}


		public void setBrojZapisa(long brojZapisa) {
			this.brojZapisa = brojZapisa;
		}


		public long getPodaci() {
			return podaci;
		}


		public void setPodaci(long podaci) {
			this.podaci = podaci;
		}


		public long getIdVisokogUcilista() {
			return idVisokogUcilista;
		}


		public void setIdVisokogUcilista(long idVisokogUcilista) {
			this.idVisokogUcilista = idVisokogUcilista;
		}


		public String getNaziv() {
			return naziv;
		}


		public void setNaziv(String naziv) {
			this.naziv = naziv;
		}


		public long getTipVisokogUcilista_idtipVisokogUcilista() {
			return tipVisokogUcilista_idtipVisokogUcilista;
		}


		public void setTipVisokogUcilista_idtipVisokogUcilista(
				long tipVisokogUcilista_idtipVisokogUcilista) {
			this.tipVisokogUcilista_idtipVisokogUcilista = tipVisokogUcilista_idtipVisokogUcilista;
		}


		public long getStatusVisokogUcilista_idstatusVisokogUcilista() {
			return statusVisokogUcilista_idstatusVisokogUcilista;
		}


		public void setStatusVisokogUcilista_idstatusVisokogUcilista(
				long statusVisokogUcilista_idstatusVisokogUcilista) {
			this.statusVisokogUcilista_idstatusVisokogUcilista = statusVisokogUcilista_idstatusVisokogUcilista;
		}


		public Date getZadnjaIzmjena() {
			return zadnjaIzmjena;
		}


		public void setZadnjaIzmjena(Date zadnjaIzmjena) {
			this.zadnjaIzmjena = zadnjaIzmjena;
		}
		
		
}
