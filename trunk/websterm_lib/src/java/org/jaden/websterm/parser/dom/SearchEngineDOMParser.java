/**
 * Filename: SearchEngineDOMParser.java
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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.cyberneko.html.parsers.DOMParser;
import org.jaden.websterm.model.SearchResult;
import org.jaden.websterm.parser.SearchEngineParser;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Jack Shen
 */
public abstract class SearchEngineDOMParser implements SearchEngineParser {
	protected Element root;

	/**
	 * @see org.jaden.websterm.parser.SearchEngineParser#parseDocument(java.io.InputStream)
	 */
	public List<SearchResult> parseDocument(InputStream stream) {
		InputSource source = new InputSource(stream);
		DOMParser parser = new DOMParser();

		try {
			parser.parse(source);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		root = parser.getDocument().getDocumentElement();

		return parseDocument();
	}

	public abstract List<SearchResult> parseDocument();
}
