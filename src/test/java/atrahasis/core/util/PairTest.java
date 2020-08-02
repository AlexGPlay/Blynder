package atrahasis.core.util;

import static org.junit.Assert.*;

import org.blynder.core.util.Pair;
import org.junit.Test;

public class PairTest {

	@Test
	public void pairTest() {
		Pair<Object,Object> p = new Pair<Object,Object>("a",1);
		assertEquals(p.object1,"a");
		assertEquals(p.object2,1);
	}

}
