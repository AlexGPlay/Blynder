package atrahasis.core.util;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class QueryParamsExtractorTest {
	
	private QueryParamsExtractor getQPE(String url) {
		return new QueryParamsExtractor(url);
	}

	@Test
	public void testEmptyUrl() {
		String url = "";
		QueryParamsExtractor qpe = getQPE(url);
		assertEquals(qpe.extract().size(), 0);
	}
	
	@Test
	public void testNoParams() {
		String url = "/test";
		QueryParamsExtractor qpe = getQPE(url);
		assertEquals(qpe.extract().size(), 0);
	}
	
	@Test
	public void testOneParam() {
		String url = "/test?a=b";
		QueryParamsExtractor qpe = getQPE(url);
		
		Map<String,Object> params = qpe.extract();
		assertEquals(params.size(), 1);
		assertEquals(params.get("a"), "b");
	}
	
	@Test
	public void testTwoParam() {
		String url = "/test?a=b&c=d";
		QueryParamsExtractor qpe = getQPE(url);
		
		Map<String,Object> params = qpe.extract();
		assertEquals(params.size(), 2);
		assertEquals(params.get("a"), "b");
		assertEquals(params.get("c"), "d");
	}
	
	@Test
	public void testNoParamsWithPartial() {
		String url = "/test/test1";
		QueryParamsExtractor qpe = getQPE(url);
		
		Map<String,Object> params = qpe.extract();
		assertEquals(params.size(), 0);
	}
	
	@Test
	public void testOneParamWithPartial() {
		String url = "/test/test1?a=b";
		QueryParamsExtractor qpe = getQPE(url);
		
		Map<String,Object> params = qpe.extract();
		assertEquals(params.size(), 1);
		assertEquals(params.get("a"), "b");
	}
	
	@Test
	public void testTwoParamsWithPartial() {
		String url = "/test/test1?a=b&c=d";
		QueryParamsExtractor qpe = getQPE(url);
		
		Map<String,Object> params = qpe.extract();
		assertEquals(params.size(), 2);
		assertEquals(params.get("a"), "b");
		assertEquals(params.get("c"), "d");
	}

}
