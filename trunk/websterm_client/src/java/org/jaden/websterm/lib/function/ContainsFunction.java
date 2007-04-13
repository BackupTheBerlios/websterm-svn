/**
 *
 */
package org.jaden.websterm.lib.function;

import java.util.List;

import org.jaden.websterm.lib.exception.FunctionNotValidException;

/**
 * @author jack
 * 
 */
public class ContainsFunction extends AbstractFunctionImpl {
	public static final String NAME = "contains";

	public boolean execute(List<String> parameters)
			throws FunctionNotValidException {
		boolean valid = super.execute(parameters);
		return valid;
	}

	public String explain() {
		return null;
	}

	public String getName() {
		return NAME;
	}

	@Override
	protected boolean validate(List<String> parameters) {
		return true;
	}
}
