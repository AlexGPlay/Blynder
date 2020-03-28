package atrahasis.core.network;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResponseTest {

	@Test
	public void testBaseResponse() {
		Response response = new Response();
		assertEquals(200, response.getStatusCode());
		assertFalse(response.isAbleToContinue());
		assertNull(response.getResponse());
		assertNull(response.getRedirect());
		assertNull(response.getResponseType());
		try {
			response.isRenderizable();
			fail("Expected NullPointer");
		}
		catch(Exception e) {}
	}
	
	@Test
	public void testMadeResponse() {
		boolean canContinue = true;
		String responseS = "test";
		String redirect = "redirect";
		String responseType = "application/api";
		
		Response response = new Response()
				.canContinue(canContinue)
				.response(responseS)
				.redirect(redirect)
				.responseType(responseType);
		
		assertEquals(200, response.getStatusCode());
		assertTrue(response.isAbleToContinue());
		assertEquals(responseS, response.getResponse());
		assertEquals(redirect, response.getRedirect());
		assertEquals(responseType, response.getResponseType());
		assertEquals(false, response.isRenderizable());
	}

}
