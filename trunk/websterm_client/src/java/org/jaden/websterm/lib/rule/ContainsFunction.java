/**
 *
 */
package org.jaden.websterm.lib.rule;

import java.util.List;

import org.jaden.websterm.lib.exception.FunctionNotValidException;
import org.jaden.websterm.lib.model.Result;

/**
 * @author jack
 *
 */
public class ContainsFunction extends AbstractFunctionImpl {
	public static final String NAME = "contains";

	public List<Result> execute(List<String> parameters)
			throws FunctionNotValidException {
		List<Result> results = super.execute(parameters);
		return null;
	}

	public String explain() {
		return null;
	}

	public String getName() {
		return NAME;
	}

	@Override
	protected boolean validate(List<String> parameters) {
		// TODO Auto-generated method stub
		return false;
	}
}
