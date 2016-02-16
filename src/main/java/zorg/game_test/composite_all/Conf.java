package zorg.game_test.composite_all;

/**
 * 物品配置。
 * 
 * @author zhangbo
 *
 */
public interface Conf {

	/**
	 * @return 物品标识
	 */
	public int getUniq();

	/**
	 * @return 可否往上合成
	 */
	public boolean canComposite();

	/**
	 * @return 合成需要的此物品个数
	 */
	public int getNeeds();

	/**
	 * @return 合成的物品标识
	 */
	public int getTargetUniq();
}
