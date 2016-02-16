package zorg.game_test.arrange_bag;

import java.util.Comparator;

/**
 * 假设这是背包物品。
 * 
 * @author 波
 *
 */
public class ItemDemo implements Itemlike {

	/**
	 * 物品区别标识
	 */
	final private int uniq;
	/**
	 * 堆叠上限
	 */
	final private int limit;
	/**
	 * 已存数量
	 */
	private int count;
	/**
	 * 位置
	 */
	private int position;

	public ItemDemo(int uniq, int limit, int count, int position) {
		super();
		this.uniq = uniq;
		this.count = count;
		this.position = position;
		this.limit = limit;
	}

	private static Comparator<Itemlike> comparator = Comparator.comparing(
			Itemlike::getUniq).thenComparing(Itemlike::getCount);

	@Override
	public int compareTo(Itemlike o) {
		return comparator.compare(this, o);
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public void setPosition(int pos) {
		this.position = pos;
	}

	@Override
	public int getUniq() {
		return uniq;
	}

	@Override
	public int getLimit() {
		return limit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + limit;
		result = prime * result + position;
		result = prime * result + uniq;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDemo other = (ItemDemo) obj;
		if (count != other.count)
			return false;
		if (limit != other.limit)
			return false;
		if (position != other.position)
			return false;
		if (uniq != other.uniq)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemDemo [uniq=" + uniq + ", limit=" + limit + ", count="
				+ count + ", position=" + position + "]";
	}

}
