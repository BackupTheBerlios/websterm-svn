/**
 * Filename: SearchEngineWrapper.java
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

import java.util.List;

import org.jaden.websterm.config.SearchContext;
import org.jaden.websterm.model.SearchResult;

/**
 * Wraps up any search engine by using specific search engine HTML parsers and
 * retrieve the search results for later filtering use.
 * 
 * @author Jack Shen
 */
public interface SearchEngineWrapper {
	/**
	 * Classes that implement this interface must implement this method and
	 * parse the search engine web page with custom HTML parser in order to get
	 * the search results.
	 * 
	 * @param context
	 *            The search context of this search, e.g. search url, keywords
	 *            etc.
	 * @return A list of search results.
	 */
	public List<SearchResult> getSearchResults(SearchContext context);
}
