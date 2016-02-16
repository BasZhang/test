package zorg.game_test.composite_all;

/**
 * 物品配置。
 * 
 * @author zhangbo
 *
 */
public interface ConfManager {

	/**
	 * 根据ID取配置。
	 * 
	 * @param sn
	 *            ID
	 * @return 配置。
	 */
	public Conf get(int sn);
}
