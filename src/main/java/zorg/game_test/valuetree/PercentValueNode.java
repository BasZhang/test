package zorg.game_test.valuetree;

/**
 * 百分比节点。
 * 
 * @author zhangbo
 *
 */
public class PercentValueNode extends AbstractNode {

	/**
	 * 百分比放大至百万分比，节点值以万分比存储，整形存储，保留精度至xx.xxxx%。
	 */
	public static final long PERCENT_SCALE = 10000;

	public PercentValueNode(Object nodeId, double percent) {
		super(nodeId);
		this.selfValue = convertInputPercent(percent);
	}

	private double convertInputPercent(double percent) {
		return percent * 0.01 * PERCENT_SCALE;
	}

	@Override
	protected void processRefreshValue() {
		v = (PERCENT_SCALE + selfValue) * v / PERCENT_SCALE;
	}

	/**
	 * 增加百分比
	 * 
	 * @param percent
	 *            百分比
	 */
	@Override
	public void processAddValue(double percent) {
		selfValue += convertInputPercent(percent);
	}

}
