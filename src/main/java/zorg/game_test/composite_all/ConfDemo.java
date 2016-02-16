package zorg.game_test.composite_all;

/**
 * 配置简单实现。
 * 
 * @author zhangbo
 *
 */
class ConfDemo implements Conf {

	final private int uniq;
	final private int target;
	final private int needs;

	public ConfDemo(int uniq, int target, int needs) {
		this.uniq = uniq;
		this.target = target;
		this.needs = needs;
	}

	@Override
	public int getUniq() {
		return uniq;
	}

	@Override
	public boolean canComposite() {
		return target != 0;
	}

	@Override
	public int getNeeds() {
		return needs;
	}

	@Override
	public int getTargetUniq() {
		return target;
	}

}