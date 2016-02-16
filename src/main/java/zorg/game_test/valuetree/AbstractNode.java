package zorg.game_test.valuetree;

import java.util.LinkedList;
import java.util.List;

/**
 * {@link IValueNode}的基本实现。
 * 
 * @author zhangbo
 *
 */
public abstract class AbstractNode implements IValueNode {
	/**
	 * Id标记
	 */
	private Object nodeId;
	/**
	 * 外层节点
	 */
	private IValueNode parent;
	/**
	 * 子节点
	 */
	private List<IValueNode> children;
	/**
	 * 取值时是否需要重算缓存值
	 */
	private boolean needUpdate;
	/**
	 * 此节点本身的属性值。
	 */
	protected double selfValue;
	/**
	 * 本身值和子节点值合成后的总值缓存
	 */
	protected double v;

	public AbstractNode(Object nodeId) {
		this.nodeId = nodeId;
		needUpdate = true;
	}

	@Override
	public Object getId() {
		return nodeId;
	}

	@Override
	public boolean hasChildren() {
		return children != null && !children.isEmpty();
	}

	@Override
	public boolean hasParent() {
		return parent != null;
	}

	@Override
	public List<IValueNode> getChildren() {
		if (children == null)
			children = new LinkedList<>();
		return this.children;
	}

	@Override
	public IValueNode getParent() {
		return this.parent;
	}

	@Override
	public void appendParent(IValueNode parent) {
		if (hasParent()) {
			throw new IllegalArgumentException();
		}
		this.parent = parent;
		parent.getChildren().add(this);
		parent.notifyChange();
	}

	@Override
	public void appendChild(IValueNode child) {
		child.appendParent(this);
	}

	@Override
	public void removeParent() {
		if (hasParent()) {
			IValueNode p = getParent();
			this.parent = null;
			p.getChildren().remove(this);
			p.notifyChange();
		}
	}

	@Override
	public void removeChild(IValueNode child) {
		if (child.getParent() == this) {
			child.removeParent();
		}
	}

	@Override
	public double getValue() {
		refreshValue();
		return v;
	}

	/**
	 * 更新节点缓存值。
	 */
	private void refreshValue() {
		if (needUpdate) {
			v = 0;
			if (hasChildren()) {
				getChildren().forEach(c -> this.v += c.getValue());
			}
			processRefreshValue();
			this.needUpdate = false;
		}
	}

	/**
	 * 如何处理给此节点加值。
	 */
	abstract protected void processRefreshValue();

	@Override
	public void addValue(double value) {
		processAddValue(value);
		notifyChange();
	}

	/**
	 * 如何处理加值。
	 */
	abstract protected void processAddValue(double value);

	@Override
	public void notifyChange() {
		this.needUpdate = true;
		if (hasParent()) {
			getParent().notifyChange();
		}
	}

}
