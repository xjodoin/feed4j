package feed4j;

import it.sauronsoftware.feed4j.DateUtils;

import org.junit.Test;

public class TestDateParser {

	@Test
	public void test() {
		String toParse = "Tue, 04 Sep 2012 22:09:22 +0000";
		DateUtils.parse(toParse);
	}
	
	@Test
	public void test2() {
		String toTest = "Tue, 23 Apr 2013 17:34:00 GMT";
		DateUtils.parse(toTest);
	}
	
	@Test
	public void test3() {
		String toTest = "Wed, 17 Apr 2013 10:30:00 PDT";
		DateUtils.parse(toTest);
	}
	
	@Test
	public void test4() {
		String toTest = "16 Apr 2013 00:00:00 GMT";
		DateUtils.parse(toTest);
	}
	
	@Test
	public void test5() {
		String toTest = "Apr 26, 2013 11:30:00 AM EST";
		DateUtils.parse(toTest);
	}
	
	@Test
	public void test6() {
		String toTest = "23 Apr 2013 04:00:00 +0000";
		DateUtils.parse(toTest);
	}
	
	
	@Test
	public void test7() {
		String toTest = "2013-04-02 00:00:01 -0400";
		DateUtils.parse(toTest);
	}
	
	
	@Test
	public void test8() {
		String toTest = "2013-05-15T21:45:00-07:00";
		DateUtils.parse(toTest);
	}
}
