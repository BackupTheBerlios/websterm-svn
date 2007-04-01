/**
 *
 */
package org.jaden.websterm.lib.model;

/**
 * @author jack
 *
 */
public class Result {
	private InputData originalData;

	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the originalData
	 */
	public InputData getOriginalData() {
		return originalData;
	}

	/**
	 * @param originalData
	 *            the originalData to set
	 */
	public void setOriginalData(InputData originalData) {
		this.originalData = originalData;
	}
}
