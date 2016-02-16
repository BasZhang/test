package zorg.game_test.random;

/**
 * 骰子制造器。
 * 
 * @author zhangbo
 *
 * @param <F>
 *            骰子面对象类型
 */
public interface DiceBuilder<F> {

	/**
	 * 添加或替换一面内容。
	 * 
	 * @param face
	 *            代表一面的内容，可为{@code null}
	 * @param weight
	 *            相对权重
	 * @return {@code DiceBuilder}。
	 */
	public abstract DiceBuilder<F> addFace(F face, int weight);

	/**
	 * 去掉一面的内容。
	 * 
	 * @param face
	 *            面
	 * @return {@code DiceBuilder}。
	 */
	public abstract DiceBuilder<F> removeFace(F face);

	/**
	 * 重置此{@code DiceBuilder}。
	 * 
	 * @return {@code DiceBuilder}。
	 */
	public abstract DiceBuilder<F> reset();

	/**
	 * 生成骰子对象。
	 * 
	 * @return 骰子对象。
	 */
	public abstract Dice<F> create();

	/**
	 * 构建默认实现的{@code DiceBuilder}
	 * 
	 * @return
	 */
	public static <F> DiceBuilder<F> defaultBuilder() {
		return new DiceBuilderImpl<F>();
	}

}