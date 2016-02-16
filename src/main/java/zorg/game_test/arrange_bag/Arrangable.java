package zorg.game_test.arrange_bag;

import java.util.List;

/**
 * 可整理的。
 * 
 * @author 波
 *
 */
public interface Arrangable {

	/**
	 * 整理。
	 * 
	 * @return 操作步骤。
	 */
	public List<Change> arrange();
}
