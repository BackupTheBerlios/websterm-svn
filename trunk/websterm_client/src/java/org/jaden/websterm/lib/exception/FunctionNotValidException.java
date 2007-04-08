/**
 *
 */
package org.jaden.websterm.lib.exception;

/**
 * @author jack
 *
 */
public class FunctionNotValidException extends Exception {
	public FunctionNotValidException(String functionName) {
		super("Function " + functionName + " is not valid, please check"
				+ " it's input parameters to see if anything is wrong.");
	}
}
