/**
 *
 */
package org.jaden.websterm.lib.function;

import java.util.List;

import org.jaden.websterm.lib.exception.FunctionNotValidException;
import org.jaden.websterm.lib.model.InputData;

/**
 * @author jack
 * 
 */
public interface FunctionImpl {
	public void setInputData(InputData data);

	public boolean execute(List<String> parameters)
			throws FunctionNotValidException;

	public String explain();

	public String getName();
}
