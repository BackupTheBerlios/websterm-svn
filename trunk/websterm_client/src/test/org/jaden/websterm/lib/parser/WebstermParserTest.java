package org.jaden.websterm.lib.parser;


import java.util.List;

import org.jaden.websterm.lib.parser.WebstermParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WebstermParserTest {
	private WebstermParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new WebstermParser();
	}

	@Test
	public void testParseDefinition() throws Exception {
		String def = "rule my_rule(high):" + "not contains(0.5);"
				+ "rule my_rule2(medium):" + "alike(\"word1\", 0.4, 0.3);";
		List rules = parser.parseDefinition(def);
		Assert.assertNotNull(rules);
	}
}
