package zorg.game_test.composite_all;

/**
 * 新建物品信息。
 * 
 * @author zhangbo
 *
 */
public class ItemCreateInfo {

	/**
	 * 物品标识
	 */
	final private int uniq;
	/**
	 * 数量
	 */
	final private int num;

	public ItemCreateInfo(int uniq, int num) {
		super();
		this.uniq = uniq;
		this.num = num;
	}

	public int getUniq() {
		return uniq;
	}

	public int getNum() {
		return num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
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
		ItemCreateInfo other = (ItemCreateInfo) obj;
		if (num != other.num) {
			return false;
		}
		if (uniq != other.uniq) {
			return false;
		}
		return true;
	}

}
