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
public class AdminAction extends BaseAction {
	@DefaultHandler
	public Resolution index() throws Exception {
		return new ForwardResolution("/WEB-INF/pages/admin/index.jsp");
	}
}
