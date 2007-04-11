/**
 *
 */
package org.jaden.websterm.lib.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author jack
 *
 */
public class FileConfiguration implements Serializable {
	private List<String> directoryLocations;

	/**
	 * @return the fileLocations
	 */
	public List<String> getDirectoryLocations() {
		return directoryLocations;
	}

	/**
	 * @param fileLocations
	 *            the fileLocations to set
	 */
	public void setDirectoryLocations(List<String> fileLocations) {
		this.directoryLocations = fileLocations;
	}
}
