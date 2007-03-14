/**
 * Filename: DefaultSearchingEngineWrapper.java
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
package org.jaden.websterm.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jaden.websterm.config.SearchContext;
import org.jaden.websterm.model.SearchResult;
import org.jaden.websterm.parser.SearchEngineParser;

/**
 * Default implementation of {@link SearchEngineWrapper} that uses ordinary HTML
 * parsers to retrieve the search results.
 * 
 * @author Jack Shen
 */
public class ParserSearchEngineWrapper extends BaseSearchEngineWrapper {
	private SearchEngineParser parser;

	/**
	 * Implements the
	 * {@link SearchEngineWrapper#getSearchResults(SearchContext)} by connecting
	 * to the search engine with the supplied url template and keywords, parses
	 * the HTML stream, and obtains the search results.
	 * 
	 * @see org.jaden.websterm.parser.SearchEngineParser
	 * @see org.jaden.websterm.http.SearchEngineWrapper#getSearchResults(org.jaden.websterm.config.SearchContext)
	 */
	public List<SearchResult> getSearchResults(SearchContext context) {
		List<SearchResult> results = super.getSearchResults(context);

		String url = context.getUrlTemplate();
		url = url.replaceAll("\\$\\{keywords\\}", context.getKeywords());
		url = url.replaceAll("\\$\\{paging\\}", Integer.toString(context
				.getConfiguration().getPagingStart()));
		try {
			String parserName = context.getParserClassName();
			if (!parserName.matches("(.+\\.)+.+")) {
				parserName = new StringBuffer(parserName).insert(0,
						"org.jaden.websterm.parser.dom.").append("Parser")
						.toString();
			}
			setParser((SearchEngineParser) Class.forName(parserName)
					.newInstance());
		} catch (InstantiationException exception) {
			exception.printStackTrace();
		} catch (IllegalAccessException exception) {
			exception.printStackTrace();
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}

		HttpClient client = new HttpClient();
		HttpMethod get = new GetMethod(url);

		try {
			int status = client.executeMethod(get);

			if (status == HttpStatus.SC_OK) {
				InputStream stream = get.getResponseBodyAsStream();
				results = parser.parseDocument(stream);
			} else {
				throw new HttpException("Method failed" + get.getStatusLine());
			}
		} catch (HttpException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			get.releaseConnection();
		}

		return results;
	}

	/**
	 * @return the parser
	 */
	public SearchEngineParser getParser() {
		return parser;
	}

	/**
	 * @param parser
	 *            the parser to set
	 */
	public void setParser(SearchEngineParser parser) {
		this.parser = parser;
	}
}
