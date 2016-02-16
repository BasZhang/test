package zorg.game_test.support;


public class MathUtilz {

	private MathUtilz() {
	}

	/**
	 * 求最大公约数。
	 * 
	 * @param a
	 *            正整数1
	 * @param b
	 *            正整数2
	 * @return 所有输入的最大公约数。
	 * @throws IllegalArgumentException
	 *             当参数中含有非正数。
	 */
	public static int greatestCommonDivisor(int a, int b) {
		if (a <= 0 || b <= 0) {
			throw new IllegalArgumentException(
					"All arguments must be positive ints.");
		} else {
			return MathUtilz.basicPositiveGcd(a, b);
		}
	}

	/**
	 * 求多个输入的最大公约数。
	 * 
	 * @param a
	 *            正整数1
	 * @param b
	 *            正整数2
	 * @param c
	 *            正整数3、4、5……
	 * @return 所有输入的最大公约数。
	 * @throws IllegalArgumentException
	 *             当参数中含有非正数。
	 */
	public static int greatestCommonDivisor(int a, int b, int... c) {
		if (a <= 0 || b <= 0) {
			throw new IllegalArgumentException(
					"All arguments must be positive ints.");
		}
		for (int cc : c) {
			if (cc <= 0)
				throw new IllegalArgumentException(
						"All arguments must be positive ints.");
		}
		int e = MathUtilz.basicPositiveGcd(a, b);
		for (int cc : c) {
			e = MathUtilz.basicPositiveGcd(e, cc);
		}
		return e;
	}

	/**
	 * 经典的欧几里得算法。
	 * <p>
	 * 注：<br>
	 * Apache Commons的math3里已有实现gcd，但它有很多判断，导致我这个简单的写法比那个快一倍。
	 * 
	 * @param a
	 *            正整数a
	 * @param b
	 *            正整数b
	 * @return 最大公约数。
	 * @see ArithmeticUtils#gcd(int, int)
	 */
	private static int basicPositiveGcd(int a, int b) {
		if (a < b) {
			a = a ^ b;
			b = b ^ a;
			a = a ^ b;
		}
		if (0 == b) {
			return a;
		}
		return basicPositiveGcd(b, a % b);
	}

	/**
	 * 将一组正整数约简。
	 * 
	 * @param a
	 *            被约简的数组。
	 * @throws IllegalArgumentException
	 *             当输入中有非正数。
	 */
	public static void reduct(int[] a) {
		if (a.length >= 2) {
			int g = greatestCommonDivisor(a[0], a[0], a); // a[0]占位匹配用
			for (int i = 0; i < a.length; i++) {
				a[i] = a[i] / g;
			}
		}
	}

}
