package zorg.game_test.random;

/**
 * 骰子。
 * 
 * @author zhangbo
 *
 * @param <F>
 *            代表一面的内容
 */
public interface Dice<F> {

	/**
	 * 掷一次。
	 * 
	 * @return 得到的面内容。
	 */
	public F toss();
}
