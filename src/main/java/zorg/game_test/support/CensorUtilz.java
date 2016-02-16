package zorg.game_test.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;

/**
 * 敏感词屏蔽工具类。
 * 
 * @author zhangbo
 *
 */
public class CensorUtilz {

	private CensorUtilz() {
	}

	private static Logger log = Logger.getGlobal();
	
	private static final char REPLACE_CHAR = '*';

	private static final String DEFAULT_CHARSET = "UTF-8";

	private static final WordsTable WORDS = new WordsTable();

	/**
	 * 从文件载入敏感词，敏感词由{@code splitter}分隔，文件编码方式默认为UTF-8。
	 * 
	 * @param file
	 *            文件
	 * @param splitter
	 *            分隔符
	 */
	public static void load(File file, String splitter) {
		load(file, splitter, DEFAULT_CHARSET);
	}

	/**
	 * 重新从文件载入敏感词，敏感词由{@code splitter}分隔，文件编码方式默认为UTF-8，之前的敏感词失效。
	 * 
	 * @param file
	 *            文件
	 * @param splitter
	 *            分隔符
	 */
	public static void reload(File file, String splitter) {
		reload(file, splitter, DEFAULT_CHARSET);
	}

	/**
	 * 从文件{@code file}载入敏感词，敏感词由{@code splitter}分隔，文件编码方式为{@code charset}。
	 * 
	 * @param file
	 *            文件
	 * @param splitter
	 *            分隔符
	 * @param charset
	 *            指定文件编码
	 */
	public static void load(File file, String splitter, String charset) {
		try (Scanner scanner = new Scanner(file, charset)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] words = StringUtils.split(line, splitter);
				for (String keyWord : words) {
					addWord(keyWord.trim());
				}
			}
		} catch (FileNotFoundException e) {
			log.warning("文件" + file + "打开失败。");
		}
		// 增加敏感词的时候做到大词在前小词在后，使得用的时候判断次数尽量少。
		WORDS.sort();
	}

	/**
	 * 重新从文件载入敏感词，敏感词由{@code splitter}分隔，文件编码方式为{@code charset}，之前的敏感词失效。
	 * 
	 * @param file
	 *            文件
	 * @param splitter
	 *            分隔符
	 * @param charset
	 *            指定文件编码
	 */
	public static void reload(File file, String splitter, String charset) {
		WORDS.clear();
		load(file, splitter, charset);
	}

	/**
	 * 增加一个敏感词。
	 * 
	 * @param keyWord
	 *            敏感词。
	 */
	private static void addWord(String keyWord) {
		if (keyWord == null || keyWord.length() == 0)
			return;// 忽略空字符串

		WORDS.put(keyWord.toCharArray());
	}

	/**
	 * 敏感词替换。
	 * 
	 * @param str
	 *            源字符串
	 * @return 替换后的字符串。
	 */
	public static String censor(String str) {
		if (Strings.isNullOrEmpty(str)) {
			return str;
		}
		if (WORDS.isEmpty()) {
			return str;
		} else {
			char[] charArray = str.toCharArray();
			for (int pos = 0; pos <= charArray.length - WORDS.getMinLen();) {
				int replaceLen = findReplace(charArray, pos);
				if (replaceLen > 0) {
					pos += replaceLen;
					continue;
				} else {
					pos++;
				}
			}
			return new String(charArray);
		}
	}

	/**
	 * 在{@code charArray}中，查找{@code char a = charArray[fromIndex]}
	 * 开头的敏感词，并做替换，返回替换长度。
	 * 
	 * @param charArray
	 *            大数组
	 * @param fromIndex
	 *            匹配起始点
	 * @return 替换掉的长度，或{@code 0}。
	 */
	private static int findReplace(char[] charArray, int fromIndex) {
		char k2 = charArray[fromIndex];
		List<char[]> possibles = WORDS.get(k2);
		if (possibles != null) {
			for (char[] expected : possibles) {
				if (partialEquals(expected, charArray, fromIndex)) {
					Arrays.fill(charArray, fromIndex, fromIndex
							+ expected.length, REPLACE_CHAR);
					return expected.length;
				}
			}
		}
		return 0;
	}

	/**
	 * 判断数组{@code bigArray}从{@code fromIndex}开始，以后{@code expected}中的元素依次出现。
	 * <p>
	 * 我记得有个类里面有这个方法，忘了在哪了，想起来后换掉。
	 * 
	 * @param expected
	 *            期待的元素
	 * @param bigArray
	 *            大数组
	 * @param fromIndex
	 *            匹配起始点
	 * @return <code>true</code>表示匹配成功，<code>false</code>不然。
	 */
	private static boolean partialEquals(char[] expected, char[] bigArray,
			int fromIndex) {
		if (expected.length + fromIndex > bigArray.length) {
			return false;
		}
		for (int i = 0; i < expected.length; i++) { // 不要把expected搞空了
			if (bigArray[i + fromIndex] != expected[i]) {
				return false;
			}
		}
		return true;
	}
}

/**
 * 单词存放表。
 * 
 * @author zhangbo
 *
 */
class WordsTable {

	private int minLen = -1;
	private Map<Character, List<char[]>> wordsMap = new HashMap<>();

	/**
	 * 取以{@code k}开头的所有敏感词。
	 * <p>
	 * 注意千万不要改返回值。
	 * 
	 * @param k
	 *            敏感词起始字符
	 * @return 以{@code k}开头的所有敏感词表。
	 */
	List<char[]> get(Character k) {
		List<char[]> vlist = wordsMap.get(k);
		if (vlist == null) {
			return null;
		} else {
			return vlist;
		}
	}

	/**
	 * 加入敏感词。
	 * 
	 * @param v
	 *            敏感词字节序列
	 */
	void put(char[] v) {
		Character k = v[0]; // v不能为空
		List<char[]> vlist = wordsMap.get(k);
		if (vlist == null) {
			vlist = new LinkedList<>();
			wordsMap.put(k, vlist);
		}
		vlist.add(v);
		if (isEmpty() || minLen > v.length) {
			minLen = v.length;
		}
	}

	/**
	 * 判断是否加入了敏感词。
	 * 
	 * @return <code>true</code>是，<code>false</code>否。
	 */
	boolean isEmpty() {
		return minLen == -1;
	}

	/**
	 * 敏感词排序为由长到短。
	 */
	void sort() {
		for (List<char[]> vlist : wordsMap.values()) {
			Collections.sort(vlist, new Comparator<char[]>() {
				@Override
				public int compare(char[] o1, char[] o2) {
					return o2.length - o1.length;
				}
			});
		}
	}

	/**
	 * 取最短的敏感词长度。
	 * 
	 * @return 最短的敏感词长度。
	 */
	int getMinLen() {
		return minLen;
	}

	/**
	 * 清除敏感词。
	 */
	void clear() {
		minLen = -1;
		wordsMap.clear();
	}
}