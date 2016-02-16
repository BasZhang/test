package zorg.game_test.arrange_bag;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemDemoTest {

	@Test
	public void testCompareTo() {
		ItemDemo i1 = new ItemDemo(1, 3, 4, 0);
		ItemDemo i2 = new ItemDemo(2, 3, 4, 0);
		assertEquals(1, i2.compareTo(i1));
		assertEquals(-1, i1.compareTo(i2));
		ItemDemo i3 = new ItemDemo(2, 3, 5, 0);
		ItemDemo i4 = new ItemDemo(2, 3, 4, 0);
		assertEquals(1, i3.compareTo(i4));
		assertEquals(-1, i4.compareTo(i3));
		ItemDemo i5 = new ItemDemo(2, 3, 5, 0);
		ItemDemo i6 = new ItemDemo(2, 3, 5, 0);
		assertEquals(0, i5.compareTo(i6));
		assertEquals(0, i6.compareTo(i5));
	}

}
