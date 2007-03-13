/**
 * Filename: SearchResult.java
 * Date: 2007-03-04
 * 
 * Copyright(C) 2007-03-05 Jack Shen
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details. You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.jaden.websterm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jack Shen
 */
public class SearchResult extends Model implements Serializable {
	private static final long serialVersionUID = 100L;

	private Date date;

	private String keywords;

	private String abstracts;

	private String url;

	public SearchResult() {
		this(new Date(), "", "", "");
	}

	public SearchResult(Date date, String keywords, String abstracts, String url) {
		super();
		this.date = date;
		this.keywords = keywords;
		this.abstracts = abstracts;
		this.url = url;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the abstracts
	 */
	public String getAbstracts() {
		return abstracts;
	}

	/**
	 * @param abstracts
	 *            the abstracts to set
	 */
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
