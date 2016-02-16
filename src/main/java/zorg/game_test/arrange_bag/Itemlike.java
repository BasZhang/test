package zorg.game_test.arrange_bag;

/**
 * 可整理的背包物品。
 * 
 * @author 波
 *
 */
public interface Itemlike extends Comparable<Itemlike> {
	/**
	 * @return 存放数量
	 */
	public int getCount();

	/**
	 * @param count
	 *            设置存放数量
	 */
	public void setCount(int count);

	/**
	 * @return 位置
	 */
	public int getPosition();

	/**
	 * @param pos
	 *            设置位置
	 */
	public void setPosition(int pos);

	/**
	 * @return 物品类别标识
	 */
	public int getUniq();

	/**
	 * @return 物品堆叠上限
	 */
	public int getLimit();

}
