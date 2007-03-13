/**
 * Filename: YahooParser.java
 * Date: 2007-3-11
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
package org.jaden.websterm.parser.dom;

import java.util.ArrayList;
import java.util.List;

import org.jaden.websterm.model.SearchResult;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author Jack Shen
 */
public class YahooParser extends SearchEngineDOMParser {

	/**
	 * @see org.jaden.websterm.parser.dom.SearchEngineDOMParser#parseDocument(Element)
	 */
	@Override
	public List<SearchResult> parseDocument(Element root) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		Element body = (Element) root.getElementsByTagName("body").item(0);

		NodeList urls = body.getElementsByTagName("a");
		for (int i = 0; i < urls.getLength(); i++) {
			Element urlElement = (Element) urls.item(i);
			if (urlElement.getAttribute("class").equalsIgnoreCase("yschttl")) {
				SearchResult result = new SearchResult();
				result.setUrl(urlElement.getAttribute("href"));
				results.add(result);
			}
		}
		return results;
	}
}