package zorg.game_test.cycle;

/**
 * 周期A、B、C、C、C……方式的循环。
 * 
 * @author 波
 *
 */
public class ABCCC {

	/**
	 * 根据周期和已进行的次数，算出之后若干次的关键点，返回结果。
	 * <p>
	 * 注意：周期数组只能填正数。
	 * 
	 * @param cycleShift
	 *            周期（先从左到右依次取值，之后一直取最后一个值）
	 * @param baseCount
	 *            起始次数
	 * @param n
	 *            计算次数
	 * @return 按次数排序，<code>false</code>为非关键点，<code>true</code>为关键点。
	 */
	public static boolean[] cycleAlgorithm(final int[] cycleShift,
			final int baseCount, final int n) {
		boolean[] dest = new boolean[n];
		int section = 0;
		int nextPos = cycleShift[section];
		int steps = cycleShift[section];
		if (baseCount > 0) {
			int tempCount = baseCount;
			while (true) {
				if (baseCount > nextPos) {
					if (cycleShift.length - section > 1) {
						tempCount -= steps;
						section++;
						steps = cycleShift[section];
						nextPos += steps;
					} else {
						nextPos += tempCount / steps * steps;
					}
					continue;
				}
				if (baseCount == nextPos) {
					if (cycleShift.length - section > 1) {
						section++;
						steps = cycleShift[section];
					}
					nextPos += steps;
					break;
				}
				break;
			}
		}
		for (int i = 0; i < dest.length; i++) {
			int next = i + baseCount + 1;
			if (next == nextPos) {
				dest[i] = true;
				if (cycleShift.length - section > 1) {
					section++;
				}
				steps = cycleShift[section];
				nextPos += steps;
			} else {
				dest[i] = false;
			}
		}
		return dest;
	}

}
