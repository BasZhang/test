package zorg.game_test.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.list.GrowthList;

/**
 * 位置移动、交换相关的工具类。
 * 
 * @author 波
 *
 */
public class PosMoveUtilz {

	/**
	 * 检测两个数组长度相同。
	 * 
	 * @param arrayA
	 *            一个数组
	 * @param arrayB
	 *            另一个数组
	 * @throws IllegalArgumentException
	 *             当两个数组长度不同。
	 */
	private static void checkSameSize(int[] arrayA, int[] arrayB)
			throws IllegalArgumentException {
		if (arrayA.length != arrayB.length) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 检测数组全是非负数。
	 * 
	 * @param array
	 *            数组
	 * @throws IllegalArgumentException
	 *             当有负数元素。
	 */
	private static void checkNonNegative(int[] array)
			throws IllegalArgumentException {
		for (int i : array) {
			if (i < 0) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * 检测数组无重复元素。
	 * 
	 * @param array
	 *            数组
	 * @throws IllegalArgumentException
	 *             当有重复元素。
	 */
	private static void checkDistinct(int[] array)
			throws IllegalArgumentException {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] == array[j]) {
					throw new IllegalArgumentException();
				}
			}
		}
	}

	/**
	 * 计算从一组元素，从初始位置状态{@code before}到达最终位置状态{@code destiny}的交换步骤。
	 * 
	 * @param before
	 *            元素组的初始位置
	 * @param destiny
	 *            元素组的终点位置
	 * @return 交换步骤。
	 * @throws IllegalArgumentException
	 *             当两个数组长度不同、出现负数位置下表、同一数组中出现重复的位置。
	 */
	public static List<Moving> getSwaps(int[] before, int[] destiny) {
		checkSameSize(before, destiny);
		checkNonNegative(before);
		checkDistinct(before);
		checkDistinct(destiny);

		List<Moving> elems = new GrowthList<>();
		for (int i = 0, sz = destiny.length; i < sz; i++) {
			elems.set(before[i], new Moving(before[i], destiny[i]));
		}

		List<Moving> result = new ArrayList<>();

		for (int i = 0, sz = elems.size(); i < sz; i++) {
			Moving crt = elems.get(i);
			while (crt != null && crt.crtPos != crt.finalPos) {
				result.add(crt.clone());
				Moving next = elems.get(crt.finalPos);
				Collections.swap(elems, crt.crtPos, crt.finalPos);
				int tmpPos = crt.crtPos;
				crt.crtPos = crt.finalPos;
				if (next != null) {
					next.crtPos = tmpPos;
				}
				crt = next;
			}
		}
		return result;
	}

	/**
	 * 使用{@code stp}交换{@code dest}中的元素。
	 * 
	 * @param dest
	 *            修改的数组
	 * @param stp
	 *            位置信息
	 */
	public static void swap(Object[] dest, Moving stp) {
		Object t = dest[stp.crtPos];
		dest[stp.crtPos] = dest[stp.finalPos];
		dest[stp.finalPos] = t;
	}

	/**
	 * 使用{@code stp}交换{@code dest}中的元素。
	 * 
	 * @param dest
	 *            修改的列表
	 * @param stp
	 *            位置信息
	 */
	public static void swap(List<?> dest, Moving stp) {
		Collections.swap(dest, stp.crtPos, stp.finalPos);
	}

	/**
	 * 使用{@code stp}交换{@code dest}中的元素。
	 * 
	 * @param dest
	 *            修改的数组
	 * @param stp
	 *            位置信息
	 */
	public static void swap(Object[] dest, List<Moving> stp) {
		for (Moving m : stp) {
			swap(dest, m);
		}
	}

	/**
	 * 使用{@code stp}交换{@code dest}中的元素。
	 * 
	 * @param dest
	 *            修改的列表
	 * @param stp
	 *            位置信息
	 */
	public static void swap(List<?> dest, List<Moving> stp) {
		for (Moving m : stp) {
			swap(dest, m);
		}
	}
	

	/**
	 * 一个简单的结构存放当前位置和终点位置。
	 */
	public static class Moving implements Cloneable {
		/**
		 * 当前位置，可改
		 */
		public int crtPos;
		/**
		 * 终点位置，不可改
		 */
		public final int finalPos;

		public Moving(int crtPos, int finalPos) {
			super();
			this.crtPos = crtPos;
			this.finalPos = finalPos;
		}

		@Override
		public String toString() {
			return "[" + crtPos + " -> " + finalPos + "]";
		}

		@Override
		public Moving clone() {
			return new Moving(crtPos, finalPos);
		}
	}

}
