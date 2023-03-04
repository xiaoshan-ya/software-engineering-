// -*- Java -*-
/*
 * <copyright>
 *
 *  Copyright (c) 2002
 *  Institute for Information Processing and Computer Supported New Media (IICM),
 *  Graz University of Technology, Austria.
 *
 * </copyright>
 *
 * <file>
 *
 *  Name:    CircularShifter.java
 *
 *  Purpose: Holds circular shifts of input lines
 *
 *  Created: 23 Sep 2002
 *
 *  $Id$
 *
 *  Description:
 *    Holds circular shifts of input lines
 * </file>
 */



/*
 * $Log$
 */

/**
 * An object of the CircularShifter class produces and holds all circular shifts of
 * a set of lines. In principle, the CircularShifter class provides a
 * similar interface as the LineStorage class, thus allowing to manipulate
 * the lines that it holds. However, in the case of the CircularShifter
 * class the lines are actually circular shifts of a particular set of original
 * lines. Also, the CircularShifter class does not provide interface for
 * updating of characters, words, and lines that it holds, but just an
 * interface for reading characters, words, and lines.
 * <p>
 * CircularShifter类的一个对象产生并保存一组直线的所有圆移位。原则上，CircularShifter类提供了与LineStorage类类似的接口，从而允许操作它所持有的行
 * 然而，在CircularShifter类的情况下，这些线实际上是对一组特定的原始线的圆移动
 * 此外，CircularShifter类不提供用于更新它所保存的字符、单词和行的接口，而只提供用于读取字符、单词和行的接口。
 *
 * @author dhelic
 * @version $Id$
 */

public class CircularShifter {


//----------------------------------------------------------------------

	/**
	 * Fields
	 */
//----------------------------------------------------------------------

	/**
	 * LineStorage for circular shifts
	 * 用于循环移位的LineStorage
	 */

	private LineStorage shifts_;

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------

	/**
	 * Produces all circular shifts of lines in a given set. Circular shifts
	 * are stored internally and can be queried by means of other methods. Note,
	 * that for each line the first circular shift is same as the original line.
	 * 产生给定集合中所有直线的圆移动。循环移位存储在内部，可以通过其他方法查询。注意，对于每一行，第一次圆周移动与原始行相同。
	 *
	 * @param lines A set of lines
	 * @see #getChar
	 * @see #getCharCount
	 * @see #getWord
	 * @see #getWordCount
	 * @see #getLine
	 * @see #getLineCount
	 */

	public void setup(LineStorage lines) {
		shifts_ = new LineStorage();


	}

//----------------------------------------------------------------------

	/**
	 * Gets the character from the specified position in the specified word
	 * in a particular line.
	 * 从特定行中指定单词中的指定位置获取字符。
	 *
	 * @param position character index in the word
	 * @param word     word index in the line
	 * @param line     line index
	 * @return char
	 */

	public char getChar(int position, int word, int line) {
		return shifts_.getChar(position, word, line);
	}

	/**
	 * Gets the number of characters in this particular word.
	 * 获取此特定单词中的字符数。
	 *
	 * @param word word index in the line
	 * @param line line index
	 * @return int
	 */

	public int getCharCount(int word, int line) {
		return shifts_.getCharCount(word, line);
	}

//----------------------------------------------------------------------

	/**
	 * Gets the word from the specified position in a particular line
	 * String representing the word is returned.
	 * 从特定行中的指定位置获取单词。返回表示单词的字符串。
	 *
	 * @param word word index in the line
	 * @param line line index
	 * @return String
	 */

	public String getWord(int word, int line) {
		return shifts_.getWord(word, line);
	}

//----------------------------------------------------------------------

	/**
	 * Gets the number of words in this particular line.
	 * 获取此特定行的字数。
	 *
	 * @param line line index
	 * @return int
	 */

	public int getWordCount(int line) {
		return shifts_.getWordCount(line);
	}

//----------------------------------------------------------------------

	/**
	 * Gets the line from the specified position.
	 * String array representing the line is returned.
	 * 从指定位置获取行。返回表示行的字符串数组。
	 *
	 * @param line line index
	 * @return String[]
	 * @see #getLineAsString
	 */

	public String[] getLine(int line) {
		return shifts_.getLine(line);
	}


//----------------------------------------------------------------------

	/**
	 * Gets the line from the specified position.
	 * String representing the line is returned.
	 * 从指定位置获取行。返回表示行的字符串。
	 *
	 * @param line line index
	 * @return String
	 * @see #getLine
	 */

	public String getLineAsString(int line) {
		return shifts_.getLineAsString(line);
	}

//----------------------------------------------------------------------

	/**
	 * Gets the number of lines.
	 *
	 * @return int
	 */

	public int getLineCount() {
		return shifts_.getLineCount();
	}

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
