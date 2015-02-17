package com.example.stufacjoint.rsss;

/**
 * 
 * @author Deni
 * @version 1.3
 *
 */
public class RssItem {
	
	private String title;
	private String link;
	
	/**
	 * Dohvaća naslov linka(teme)
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Postavlja naslov teme
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Dohvaća link
	 * @return link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * Postavlja link
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return title;
	}

}
