package jdbcUtils;
/**
 * 生成一个不重复的id值
 */
public interface IdGenerator<T> {
	 T getId();
}
