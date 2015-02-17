package com.example.stufacjoint.rsss;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * 
 * @author Alen, Deni
 * @version 1.3
 *
 */
public class RssParseHandler extends DefaultHandler {
	
	
private List<RssItem> rssItems;
	
	// Used to reference item while parsing
	private RssItem currentItem;
	
	// Parsing title indicator
	private boolean parsingTitle;
	// Parsing link indicator
	private boolean parsingLink;
	
	
	/**
	 * parsiranje RSS-a u listu
	 */
	public RssParseHandler() {
		rssItems = new ArrayList<RssItem>();
	}
	
	/**
	 * Dohvaća item-e iz RSS-a i vraća ih u bliku liste
	 * @return rssItems
	 */
	public List<RssItem> getItems() {
		return rssItems;
	}
	/**
	 * 
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("item".equals(qName)) {
			currentItem = new RssItem();
		} else if ("title".equals(qName)) {
			parsingTitle = true;
		} else if ("link".equals(qName)) {
			parsingLink = true;
			
		}
	}
	/**
	 * 
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("title".equals(qName)) {
			parsingTitle = false;
		} else if ("link".equals(qName)) {
			parsingLink = false;
			
		}
	}
	/**
	 * 
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (parsingTitle) {
			if (currentItem != null)
				currentItem.setTitle(new String(ch, start, length));
		} else if (parsingLink) {
			if (currentItem != null) {
				currentItem.setLink(new String(ch, start, length));
				parsingLink = false;
			}
		} 
	}
}
