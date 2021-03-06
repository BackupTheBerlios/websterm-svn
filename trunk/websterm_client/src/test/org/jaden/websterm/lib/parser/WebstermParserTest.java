package org.jaden.websterm.lib.parser;

import java.util.List;

import org.jaden.websterm.lib.model.Function;
import org.jaden.websterm.lib.model.Rule;
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
		String def = "rule my_rule(high, my_text):"
				+ "not contains(0.5);"
				+ "rule my_rule2(medium):"
				+ "alike(\"word1\", /jack/, 0.3);"
				+ "rule my_rule3():"
				+ "not allowed(0.4, 3.4, \"word2\") and contains(3.4) or contains(3);";
		List<Rule> rules = parser.parseDefinition(def);
		Assert.assertNotNull(rules);

		Assert.assertEquals(3, rules.size());
		Rule rule = rules.get(0);
		Assert.assertEquals("my_rule", rule.getName());
		Assert.assertEquals(2, rule.getFields().size());

		List<Function> functions = rule.getFunctions();
		Assert.assertEquals(1, functions.size());

		rule = rules.get(1);
		Assert.assertEquals("my_rule2", rule.getName());

		rule = rules.get(2);
		Assert.assertEquals("my_rule3", rule.getName());
	}
}
