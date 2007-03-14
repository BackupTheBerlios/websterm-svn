/**
 * Filename: BaseSearchEngineWrapper.java
 * Date: 2007-3-14
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

import java.util.ArrayList;
import java.util.List;

import org.jaden.websterm.config.SearchContext;
import org.jaden.websterm.model.SearchResult;

/**
 * @author Jack Shen
 */
public abstract class BaseSearchEngineWrapper implements SearchEngineWrapper {
	private List<SearchResult> results;

	/**
	 * @see org.jaden.websterm.http.SearchEngineWrapper#getSearchResults(org.jaden.websterm.config.SearchContext)
	 */
	public List<SearchResult> getSearchResults(SearchContext context) {
		results = new ArrayList<SearchResult>();

		context.setKeywords(context.getKeywords().replaceAll("\\s+", "+"));

		return results;
	}

	/**
	 * @return the results
	 */
	public List<SearchResult> getResults() {
		return results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(List<SearchResult> results) {
		this.results = results;
	}
}
