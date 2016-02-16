package zorg.game_test.arrange_bag;

import java.util.Arrays;

/**
 * 背包变化记录。
 * 
 * @author 波
 *
 */
public class Change {

	/**
	 * 变化类型：数量变化
	 */
	public static final int CHANGE_COUNT = 1;
	/**
	 * 变化类型：位置交换
	 */
	public static final int CHANGE_SWAP = 2;

	/**
	 * 变化类型
	 */
	private int type;
	/**
	 * 变化值<br>
	 * 当为数量变化时，为位置、数量；<br>
	 * 当为位置交换时，为两个互换的位置。
	 */
	private int[] params;

	/**
	 * 构建一个数量变化。
	 * 
	 * @param pos
	 *            位置
	 * @param count
	 *            数量
	 * @return 数量变化。
	 */
	public static Change newCountChange(int pos, int count) {
		Change bagChange = new Change();
		bagChange.type = CHANGE_COUNT;
		bagChange.params = new int[] { pos, count };
		return bagChange;
	}

	/**
	 * 构建一个位置交换。
	 * 
	 * @param pos1
	 *            位置1
	 * @param pos2
	 *            位置2
	 * @return 位置交换。
	 */
	public static Change newSwap(int pos1, int pos2) {
		Change bagChange = new Change();
		bagChange.type = CHANGE_SWAP;
		bagChange.params = new int[] { pos1, pos2 };
		return bagChange;
	}

	public int getType() {
		return type;
	}

	public int[] getParams() {
		return params;
	}

	@Override
	public String toString() {
		return "BagChange [type=" + type + ", params="
				+ Arrays.toString(params) + "]";
	}

}
