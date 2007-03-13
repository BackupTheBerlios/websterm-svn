/**
 * Filename: DefaultSearchEngineWrapperTest.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.jaden.websterm.config.SearchContext;
import org.jaden.websterm.config.UrlConfiguration;
import org.jaden.websterm.model.SearchResult;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jack Shen
 */
public class DefaultSearchEngineWrapperTest {
	private DefaultSearchEngineWrapper testWrapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testWrapper = new DefaultSearchEngineWrapper();
	}

	/**
	 * Test method for
	 * {@link org.jaden.websterm.h$ttp.DefaultSearchEngineWrapper#getSearchResults(org.jaden.websterm.config.SearchContext)}.
	 */
	@Test
	public void testGetSearchResults() {
		UrlConfiguration config = new UrlConfiguration();
		config.setPagingIncrement(10);
		config.setPagingStart(0);
		config.setPagingParamName("start");

		SearchContext context = new SearchContext();
		context.setConfiguration(config);
		context.setKeywords("easywine");
		context.setParserClassName("Google");

		List<SearchResult> results = testWrapper.getSearchResults(context);

		assertNotNull(results);
		assertEquals(10, results.size());

		config.setPagingIncrement(10);
		config.setPagingStart(1);
		config.setPagingParamName("b");

		context = new SearchContext(SearchContext.YAHOO_URL_TEMPLATE);
		context.setConfiguration(config);
		context.setKeywords("easywine");
		context.setParserClassName("org.jaden.websterm.parser.dom.YahooParser");

		results = testWrapper.getSearchResults(context);

		assertNotNull(results);
		assertEquals(10, results.size());

		config.setPagingIncrement(10);
		config.setPagingStart(0);
		config.setPagingParamName("pn");

		context = new SearchContext(SearchContext.BAIDU_URL_TEMPLATE);
		context.setConfiguration(config);
		context.setKeywords("easywine");
		context.setParserClassName("Baidu");

		results = testWrapper.getSearchResults(context);

		assertNotNull(results);
		assertEquals(10, results.size());
	}
}
