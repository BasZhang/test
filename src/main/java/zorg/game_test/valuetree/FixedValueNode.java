package zorg.game_test.valuetree;

/**
 * 固定值节点。
 * 
 * @author zhangbo
 *
 */
public class FixedValueNode extends AbstractNode {

	public FixedValueNode(Object nodeId, double value) {
		super(nodeId);
		this.selfValue = value;
	}

	@Override
	protected void processRefreshValue() {
		v += selfValue;
	}

	@Override
	public void processAddValue(double fixed) {
		selfValue += fixed;
	}
}
