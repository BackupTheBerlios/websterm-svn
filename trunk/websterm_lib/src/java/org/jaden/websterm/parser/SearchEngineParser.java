/**
 * Filename: SearchEngineParser.java
 * Date: 2007-3-10
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
package org.jaden.websterm.parser;

import java.io.InputStream;
import java.util.List;

import org.jaden.websterm.model.SearchResult;

/**
 * @author Jack Shen
 *
 */
public interface SearchEngineParser {
	public List<SearchResult> parseDocument(InputStream stream);
}
