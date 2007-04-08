/**
 *
 */
package org.jaden.websterm.lib.rule;

import java.util.List;

import org.jaden.websterm.lib.exception.FunctionNotValidException;
import org.jaden.websterm.lib.model.InputData;
import org.jaden.websterm.lib.model.Result;

/**
 * @author jack
 *
 */
public interface FunctionImpl {
	public void setInputData(InputData data);

	public List<Result> execute(List<String> parameters)
			throws FunctionNotValidException;

	public String explain();

	public String getName();
}
