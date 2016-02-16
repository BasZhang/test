package zorg.game_test.cycle;

/**
 * 周期A、B、A、B……方式的循环。
 * 
 * @author 波
 *
 */
public class ABAB {

	/**
	 * 根据周期和已进行的次数，算出之后若干次的关键点，返回结果。
	 * <p>
	 * 注意：周期数组只能填正数。
	 * 
	 * @param cycleShift
	 *            周期（先从左到右依次取值，之后再从左开始）
	 * @param baseCount
	 *            起始次数
	 * @param n
	 *            计算次数
	 * @return 按次数排序，<code>false</code>为非关键点，<code>true</code>为关键点。
	 */
	public static boolean[] cycleAlgorithm(final int[] cycleShift,
			final int baseCount, final int n) {
		boolean[] dest = new boolean[n];
		int loop = 0;
		for (int i : cycleShift) {
			loop += i;
		}
		c: for (int i = 0; i < dest.length; i++) {
			int next = i + baseCount + 1;
			int mod = next % loop;
			if (mod == 0) {
				dest[i] = true;
			} else {
				int s = 0;
				for (int j = 0; j < cycleShift.length - 1; j++) {
					s += cycleShift[j];
					if (s == mod) {
						dest[i] = true;
						continue c;
					}
				}
				dest[i] = false;
			}
		}
		return dest;
	}

}
