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
public abstract class AbstractFunctionImpl implements FunctionImpl {
	protected InputData data;

	public boolean execute(List<String> parameters)
			throws FunctionNotValidException {
		boolean valid = validate(parameters);
		if (!valid)
			throw new FunctionNotValidException(getName());
		return true;
	}

	public String explain() {
		return null;
	}

	public String getName() {
		return null;
	}

	public void setInputData(InputData data) {
		this.data = data;
	}

	protected abstract boolean validate(List<String> parameters);
}
