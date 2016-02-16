package zorg.game_test.valuetree;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;

public class ValueNodeTest {

	private long start, end;

	private Logger log = Logger.getGlobal();

	@Test
	public void correctnessTest1() {
		start = System.currentTimeMillis();
		IValueNode base1 = new FixedValueNode("base1", 12);
		IValueNode base2 = new FixedValueNode("base2", 35);
		IValueNode percent_1_2 = new PercentValueNode("percent_1_2", 33.33f);
		percent_1_2.appendChild(base1);
		percent_1_2.appendChild(base2);
		assertEquals((12 + 35) * 1.3333, percent_1_2.getValue(), 0.000001);
		IValueNode fix3 = new FixedValueNode("fix3", 50);
		fix3.appendChild(percent_1_2);
		assertEquals((12 + 35) * 1.3333 + 50, fix3.getValue(), 0.000001);
		IValueNode total = new PercentValueNode("total", 12f);
		assertEquals(0, total.getValue(), 0.000001);
		fix3.appendParent(total);
		assertEquals(((12 + 35) * 1.3333 + 50) * 1.12, total.getValue(),
				0.000001);
		base2.addValue(-12);
		assertEquals(((12 + 35 - 12) * 1.3333 + 50) * 1.12, total.getValue(),
				0.000001);
		percent_1_2.addValue(-20);
		assertEquals((12 + 35 - 12) * (1.3333 - 0.2), percent_1_2.getValue(),
				0.000001);
		percent_1_2.removeChild(base2);
		assertEquals((12 * (1.3333 - 0.2) + 50) * 1.12, total.getValue(),
				0.000001);

		end = System.currentTimeMillis();
		long d = end - start;
		log.info("本次测试用时" + d + "(millis), 注：1s=1000millis.");
	}

	@Test(timeout = 1100)
	public void loadTest1() {
		start = System.currentTimeMillis();
		for (int i = 8000000; i > 0; --i) {
			IValueNode base1 = new FixedValueNode("base1", 12);
			IValueNode base2 = new FixedValueNode("base2", 35);
			IValueNode percent_1_2 = new PercentValueNode("percent_1_2", 33.33f);
			percent_1_2.appendChild(base1);
			percent_1_2.appendChild(base2);
			IValueNode fix3 = new FixedValueNode("fix3", 50);
			fix3.appendChild(percent_1_2);
			IValueNode total = new PercentValueNode("total", 12f);
			fix3.appendParent(total);
			base2.addValue(-12);
			percent_1_2.removeChild(base2);
		}

		end = System.currentTimeMillis();
		long d = end - start;
		log.info("本次测试用时" + d + "(millis), 注：1s=1000millis.");
	}

}
