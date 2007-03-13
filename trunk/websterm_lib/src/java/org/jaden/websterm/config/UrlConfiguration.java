/**
 * Filename: UrlConfiguration.java
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

/**
 * @author Jack Shen
 */
public class UrlConfiguration {
	private int pagingStart;

	private int pagingIncrement;

	private String pagingParamName;

	/**
	 * @return the pagingIncrement
	 */
	public int getPagingIncrement() {
		return pagingIncrement;
	}

	/**
	 * @param pagingIncrement
	 *            the pagingIncrement to set
	 */
	public void setPagingIncrement(int pagingIncrement) {
		this.pagingIncrement = pagingIncrement;
	}

	/**
	 * @return the pagingParamName
	 */
	public String getPagingParamName() {
		return pagingParamName;
	}

	/**
	 * @param pagingParamName
	 *            the pagingParamName to set
	 */
	public void setPagingParamName(String pagingParamName) {
		this.pagingParamName = pagingParamName;
	}

	/**
	 * @return the pagingStart
	 */
	public int getPagingStart() {
		return pagingStart;
	}

	/**
	 * @param pagingStart
	 *            the pagingStart to set
	 */
	public void setPagingStart(int pagingStart) {
		this.pagingStart = pagingStart;
	}
}
