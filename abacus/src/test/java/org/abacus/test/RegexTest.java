package org.abacus.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegexTest {

	
	public void testSimpleTrue() {
		String s = "jim humbapumpa";
		assertTrue(s.matches(".*(jim|joe).*"));
		s = "humbapumpa jom";
		assertFalse(s.matches(".*(jim|joe).*"));
		s = "humbaPumpa joe";
		assertTrue(s.matches(".*(jim|joe).*"));
		s = "humbapumpa joe jim";
		assertTrue(s.matches(".*(jim|joe).*"));
	}
	
	@Test
	public void testUrl(){
		String s = "/app/definition/defItem.abc?type=ITM_SR_FN&class=FIN_R";
		
//		String regex = "/app/definition/defmanager.abc\\?.*grp=VAL.*";
//		String regex = "/app/definition/defItem.abc?type=ITM_SR_FN&class=FIN_R";
		String regex = "/app/definition/defItem.abc\\?.*((type=ITM_SR_FN.*class=FIN_R)|(class=FIN_R.*type=ITM_SR_FN)).*";
		
		assertTrue(s.matches(regex));
		
		
	}

}
