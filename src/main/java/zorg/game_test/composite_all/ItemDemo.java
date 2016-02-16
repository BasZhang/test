package zorg.game_test.composite_all;

/**
 * 物品简单实现。
 * 
 * @author zhangbo
 *
 */
public class ItemDemo implements Itemlike {

	/**
	 * 物品标识
	 */
	final private int uniq;

	public ItemDemo(int uniq) {
		super();
		this.uniq = uniq;
	}

	@Override
	public int getUniq() {
		return uniq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + uniq;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ItemDemo other = (ItemDemo) obj;
		if (uniq != other.uniq) {
			return false;
		}
		return true;
	}
}
