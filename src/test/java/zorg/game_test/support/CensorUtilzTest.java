package zorg.game_test.support;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

public class CensorUtilzTest {

	private Logger log = Logger.getGlobal();

	@Test
	public void test() {
		ClassLoader classLoader = CensorUtilz.class.getClassLoader();
		File file = new File(classLoader.getResource("censor_words.txt")
				.getFile());
		CensorUtilz.load(file, ",");
		String uncensored = "草你妈的Fuck shit 氹!";
		String censored = CensorUtilz.censor(uncensored);
		assertEquals("***的**** **** *!", censored);
	}

	@Test
	public void testResp() {
		long t0 = System.nanoTime();
		int N = 100000;
		for (int i = N; i > 0; i--) {
			String uncensored = "50个子你妈怎么样？北京时间凌晨3时二十分，冰泉冷涩，test1 test2，分手的方式！哈哈哈，其实我想说：\"草你妈的\"、Fuck shit 氹";
			String censored = CensorUtilz.censor(uncensored);
			assertEquals(
					"50个子**怎么样？北京时间凌晨3时二十分，冰泉冷涩，test1 test2，分手的方式！哈哈哈，其实我想说：\"***的\"、**** **** *",
					censored);
		}
		long t1 = System.nanoTime();
		log.info("敏感词替换" + N + "次，平均用时：" + 1d * (t1 - t0) / N + " nanos");
	}

	@BeforeClass
	public static void warmUp() {
		ClassLoader classLoader = CensorUtilz.class.getClassLoader();
		File file = new File(classLoader.getResource("censor_words.txt")
				.getFile());
		CensorUtilz.load(file, ",");
	}

}
