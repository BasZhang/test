package zorg.game_test.valuetree;

import java.util.List;

/**
 * 值节点接口。
 *
 * @author zhangbo
 */
public interface IValueNode {

	/**
	 * 获得节点Id。
	 * 
	 * @return 节点Id。
	 */
	public Object getId();

	/**
	 * 取此节点的值。
	 * 
	 * @return 此节点的值。
	 */
	public double getValue();

	/**
	 * 修改节点本身值。
	 * 
	 * @param delta
	 *            增量
	 */
	public void addValue(double delta);

	/**
	 * 告知这个节点及其父节点，数据已被改过，需要重算缓存值。
	 */
	public void notifyChange();

	/**
	 * 是否有子节点。
	 *
	 * @return <code>true</code>是，<code>false</code>否。
	 */
	public boolean hasChildren();

	/**
	 * 获取子节点。
	 * 
	 * @return 子节点集合。
	 */
	public List<IValueNode> getChildren();

	/**
	 * 是否有父节点。
	 *
	 * @return <code>true</code>是，<code>false</code>否。
	 */
	public boolean hasParent();

	/**
	 * 获取父节点。
	 * 
	 * @return 父节点。
	 */
	public IValueNode getParent();

	/**
	 * 将此节点挂到父节点。
	 * 
	 * @param parent
	 *            父节点
	 */
	public void appendParent(IValueNode parent);

	/**
	 * 给此节点挂子节点。
	 * 
	 * @param child
	 *            子节点
	 */
	public void appendChild(IValueNode child);

	/**
	 * 将此节点从当前父节点游离。
	 */
	public void removeParent();

	/**
	 * 将一个节点从子节点集合去除。
	 * 
	 * @param child
	 *            子节点
	 */
	public void removeChild(IValueNode child);

}
