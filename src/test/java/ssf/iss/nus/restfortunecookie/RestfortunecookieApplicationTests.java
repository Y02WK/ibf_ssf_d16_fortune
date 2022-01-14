package ssf.iss.nus.restfortunecookie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ssf.iss.nus.restfortunecookie.services.FortuneCookie;

@SpringBootTest
class RestfortunecookieApplicationTests {
	@Autowired
	private FortuneCookie fc;

	@Test
	void contextLoads() {
	}

	@Test
	void fortuneCookieSingle() {
		String cookie = fc.getCookie();
		assertTrue(cookie.length() > 0);
	}

	@Test
	void fortuneCookieMultiple() {
		List<String> cookieList = fc.getCookies(8);
		assertTrue(cookieList.size() == 8);
	}

	@Test
	void fortuneCookieMultipleZero() {
		boolean caught = false;
		try {
			List<String> cookieList = fc.getCookies(0);
		} catch (AssertionError e) {
			caught = true;
		}
		assertEquals(caught, true);
	}

}
