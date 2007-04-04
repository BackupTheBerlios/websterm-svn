/**
 *
 */
package org.jaden.websterm.client.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * @author jack
 *
 */
public class IndexAction extends BaseAction {
	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution("/WEB-INF/pages/index.jsp");
	}
}
