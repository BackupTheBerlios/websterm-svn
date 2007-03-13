/**
 * Filename: SearchContext.java
 * Date: 2007-3-6
 *
 * Copyright (C) 2007-03-05 Jack Shen This program is free software; you can
 * redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received
 * a copy of the GNU General Public License along with this program; if not,
 * write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301 USA.
 */
package org.jaden.websterm.config;

import java.io.Serializable;


/**
 * @author Jack Shen
 */
public class SearchContext implements Serializable {
	public static final String GOOGLE_URL_TEMPLATE = "http://www.google.cn/search?q=${keywords}&complete=1&hl=zh-CN&newwindow=1&start=${paging}&sa=N";

	public static final String YAHOO_URL_TEMPLATE = "http://search.yahoo.com/search?p=${keywords}&toggle=1&cop=mss&ei=UTF-8&fp_ip=CN&fr=yfp-t-501&b=${paging}";

	public static final String BAIDU_URL_TEMPLATE = "http://www.baidu.com/s?lm=0&si=&rn=10&ie=gb2312&ct=0&wd=${keywords}&pn=${paging}&cl=3";

	private String urlTemplate;

	private UrlConfiguration configuration;

	private String keywords;

	private String parserClassName;

	/**
	 * Default Constructor that uses Google URL template.
	 */
	public SearchContext() {
		this(GOOGLE_URL_TEMPLATE);
	}

	/**
	 * Constructor that receives the url template as its argument
	 * 
	 * @param urlTemplate
	 *            The search engine url template.
	 */
	public SearchContext(String urlTemplate) {
		this.urlTemplate = urlTemplate;
	}

	/**
	 * @return the urlTemplate
	 */
	public String getUrlTemplate() {
		return urlTemplate;
	}

	/**
	 * @param urlTemplate
	 *            the urlTemplate to set
	 */
	public void setUrlTemplate(String urlTemplate) {
		this.urlTemplate = urlTemplate;
	}

	/**
	 * @return the configuration
	 */
	public UrlConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration
	 *            the configuration to set
	 */
	public void setConfiguration(UrlConfiguration configuration) {
		this.configuration = configuration;
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
	 * @return the engine
	 */
	public String getParserClassName() {
		return parserClassName;
	}

	/**
	 * @param engine
	 *            the engine to set
	 */
	public void setParserClassName(String engine) {
		this.parserClassName = engine;
	}
}
