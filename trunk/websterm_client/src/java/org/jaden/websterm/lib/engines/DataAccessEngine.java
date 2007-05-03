/**
 * 
 */
package org.jaden.websterm.lib.engines;

import java.util.List;

import org.jaden.websterm.lib.model.InputData;

/**
 * @author jack
 * 
 */
public abstract class DataAccessEngine {
	private String realContextPath;

	public abstract List<InputData> getInputData();

	public String getRealContextPath() {
		return realContextPath;
	}

	public void setRealContextPath(String realContextPath) {
		this.realContextPath = realContextPath;
	}
}
