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
 *  Name:    LineStorage.java
 *
 *  Purpose: LineStorage holds all input lines and provides a public interface to manipulate the lines.
 *
 *  Created: 19 Sep 2002
 *
 *  $Id$
 *
 *  Description:
 *
 * </file>
 */



/*
 * $Log$
 */

import java.util.ArrayList;

/**
 * LineStorage holds a number of lines and provides a number of public methods
 * to manipulate the lines. A line is defined as a set of words, and a word consists of a number of
 * characters. Methods defined by the LineStorage class allow objects of other classes to:
 * <ul>
 * <li>set, read and delete a character from a particular word in a particular line
 * <li>add a new character to a particular word in a particular line
 * <li>obtain the number of characters in a particular word in a particular line
 * <li>set, read and delete a word from a particular line
 * <li>add a new word to a particular line
 * <li>add an empty word to a particular line
 * <li>obtain words count in a particular line
 * <li>set, read and delete a particular line
 * <li>add a new line
 * <li>add an empty line
 * <li>obtain lines count
 * </ul>
 *
 * @author dhelic
 * @version $Id$
 */

public class LineStorage {

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

	/**
	 * ArrayList holding all lines. Each line itself is represeneted as an
	 * Arraylist object holding all words from that line. The ArrayList class is a
	 * standard Java Collection class, which  implements the typical buffer
	 * functionality, i.e., it keeps its objects in an array of a fix capacity.
	 * When the current capacity is exceeded, ArrayList object resizes its array
	 * automatically, and copies the elements of the old array into the new one.
	 * 数组列表保存所有行。每一行本身都表示为一个Arraylist对象，包含该行中的所有单词
	 * ArrayList类是一个标准的Java Collection类，它实现了典型的缓冲区功能，也就是说，它将对象保存在一个固定容量的数组中
	 *
	 */

	private ArrayList lines_ = new ArrayList();

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
	 * This method sets a new character on the specified index of
	 * a particular word in a particular line.
	 *此方法在特定行中特定单词的指定索引上设置一个新字符
	 * @param c        new character
	 * @param position character index in the word
	 * @param word     word index in the line
	 * @param line     line index
	 * @see #getChar
	 * @see #addChar
	 * @see #deleteChar
	 */

	public void setChar(char c, int position, int word, int line) {
		ArrayList currentLine = (ArrayList) lines_.get(line);
		String currentWord = (String) currentLine.get(word);

		String str = currentWord.substring(0, position) + c + currentWord.substring(position + 1);
		currentLine.set(word, str);
		lines_.set(line, currentLine);
	}

//----------------------------------------------------------------------

	/**
	 * Gets the character from the specified position in the specified word
	 * in a particular line.
	 * 从特定行中指定单词中的指定位置获取字符。
	 * @param position character index in the word
	 * @param word     word index in the line
	 * @param line     line index
	 * @return char
	 * @see #setChar
	 * @see #addChar
	 * @see #deleteChar
	 */

	public char getChar(int position, int word, int line) {

		ArrayList currentLine = (ArrayList) lines_.get(line);
		String currentWord = (String) currentLine.get(word);

		return currentWord.charAt(position);
	}

//----------------------------------------------------------------------

	/**
	 * Adds a character at the end of the specified word in a particular line.
	 在特定行中指定单词的末尾添加一个字符。
	 * @param c    new character
	 * @param word word index in the line
	 * @param line line index
	 * @see #setChar
	 * @see #getChar
	 * @see #deleteChar
	 */

	public void addChar(char c, int word, int line) {
		ArrayList currentLine = (ArrayList) lines_.get(line);
		String currentWord = (String) currentLine.get(word);
		currentLine.set(word, currentWord += c);

		lines_.set(line, currentLine);
	}

//----------------------------------------------------------------------

	/**
	 * Deletes the character from the specified position in the specified word
	 * in a particular line.
	 * 从特定行中指定字的指定位置删除字符。
	 * @param position character index in the word
	 * @param word     word index in the line
	 * @param line     line index
	 * @see #setChar
	 * @see #getChar
	 * @see #addChar
	 */

	public void deleteChar(int position, int word, int line) {
		ArrayList currentLine = (ArrayList) lines_.get(line);
		String currentWord = (String) currentLine.get(word);

		String str = currentWord.substring(0, position) + currentWord.substring(position + 1);
		currentLine.set(word, str);

		lines_.set(line, currentLine);
	}

//----------------------------------------------------------------------

	/**
	 * Gets the number of characters in this particular word.
	 * 获取此特定单词中的字符数。
	 * @param word word index in the line
	 * @param line line index
	 * @return int
	 */

	public int getCharCount(int word, int line) {
		ArrayList currentLine = (ArrayList) lines_.get(line);
		String currentWord = (String) currentLine.get(word);

		return currentWord.length();

	}

//----------------------------------------------------------------------

	/**
	 * This method sets a new word on the specified index of a particular line.
	 * Character array is taken as an argument for the word.
	 * 此方法在特定行的指定索引上设置一个新单词。字符数组被作为单词的参数。
	 * @param chars new word
	 * @param word  word index in the line
	 * @param line  line index
	 * @see #getWord
	 * @see #addWord
	 * @see #addEmptyWord
	 * @see #deleteWord
	 */

	public void setWord(char[] chars, int word, int line) {
		ArrayList currentLine = (ArrayList) lines_.get(line);

		String str = "";
		for (int i = 0; i < chars.length; i++) str += chars[i];

		currentLine.add(word, str);

		lines_.set(line, currentLine);
	}

//----------------------------------------------------------------------

	/**
	 * This method sets a new word on the specified index of a particular line.
	 * String is taken as an argument for the word.
	 * 此方法在特定行的指定索引上设置一个新单词。String被作为单词的参数。
	 * @param chars new word
	 * @param word  word index in the line
	 * @param line  line index
	 * @see #getWord
	 * @see #addWord
	 * @see #addEmptyWord
	 * @see #deleteWord
	 */

	public void setWord(String chars, int word, int line) {
		ArrayList currentLine = (ArrayList) lines_.get(line);
		currentLine.add(word, chars);

		lines_.set(line, currentLine);
	}

//----------------------------------------------------------------------

	/**
	 * Gets the word from the specified position in a particular line
	 * String representing the word is returned.
	 * 从特定行中的指定位置获取单词。返回表示单词的字符串。
	 * @param word word index in the line
	 * @param line line index
	 * @return String
	 * @see #setWord
	 * @see #addWord
	 * @see #addEmptyWord
	 * @see #deleteWord
	 */

	public String getWord(int word, int line) {

		ArrayList currentLine = (ArrayList) lines_.get(line);
		String currentWord = (String) currentLine.get(word);
		return currentWord;
	}

//----------------------------------------------------------------------

	/**
	 * Adds a word at the end of the specified line.
	 * The method takes a character array as an argument.
	 * 在指定行的末尾添加一个单词。该方法接受一个字符数组作为参数。
	 * @param chars new word
	 * @param line  line index
	 * @see #addEmptyWord
	 * @see #setWord
	 * @see #getWord
	 * @see #deleteWord
	 */

	public void addWord(char[] chars, int line) {
		ArrayList currentLine = (ArrayList) lines_.get(line);

		String str = "";
		for (int i = 0; i < chars.length; i++) str += chars[i];
		currentLine.add(str);
		lines_.set(line, currentLine);

	}

//----------------------------------------------------------------------

	/**
	 * Adds a word at the end of the specified line.
	 * The method takes a string as an argument.
	 * 在指定行的末尾添加一个单词。该方法接受一个字符数组作为参数。
	 * @param chars new word
	 * @param line  line index
	 * @see #addEmptyWord
	 * @see #setWord
	 * @see #getWord
	 * @see #deleteWord
	 */

	public void addWord(String chars, int line) {
		ArrayList current_line = (ArrayList) lines_.get(line);
		current_line.add(chars);
	}

//----------------------------------------------------------------------

	/**
	 * Adds an empty word at the end of the specified line.
	 *
	 * @param line line index
	 * @see #setWord
	 * @see #getWord
	 * @see #addWord
	 * @see #deleteWord
	 */

	public void addEmptyWord(int line) {
		ArrayList current_line = (ArrayList) lines_.get(line);
		current_line.add(new String());
	}

//----------------------------------------------------------------------

	/**
	 * Deletes the word from the specified position in a particular line.
	 * 从特定行的指定位置删除单词。
	 * @param word word index in the line
	 * @param line line index
	 * @see #setWord
	 * @see #getWord
	 * @see #addWord
	 * @see #addEmptyWord
	 */

	public void deleteWord(int word, int line) {
		ArrayList current_line = (ArrayList) lines_.get(line);
		current_line.remove(word);
		lines_.set(line, current_line);
	}

//----------------------------------------------------------------------

	/**
	 * Gets the number of words in this particular line.
	 *
	 * @param line line index
	 * @return int
	 */

	public int getWordCount(int line) {
		ArrayList current_line = (ArrayList) lines_.get(line);
		return current_line.size();
	}

//----------------------------------------------------------------------

	/**
	 * This method sets a new line on the specified index.
	 * This method takes two dimensional character array as an argument
	 * for the line.
	 * 此方法在指定索引上设置新行。此方法接受二维字符数组作为该行的参数。
	 * words 第一维是一个word，第二维是具体内容
	 * @param words new line
	 * @param line  line index
	 * @see #getLine
	 * @see #getLineAsString
	 * @see #addLine
	 * @see #addEmptyLine
	 * @see #deleteLine
	 */

	public void setLine(char[][] words, int line) {
		ArrayList newLine = new ArrayList();
		for (int i = 0; i < words.length; i++) {
			String str = "";
			for (int j = 0; j < words[i].length; j++) str += words[i][j];
			newLine.add(str);
		}
		lines_.add(line, newLine);
	}

//----------------------------------------------------------------------

	/**
	 * This method sets a new line on the specified index.
	 * This method takes a string array as argument
	 *
	 * @param words new line
	 * @param line  line index
	 * @see #getLine
	 * @see #getLineAsString
	 * @see #addLine
	 * @see #addEmptyLine
	 * @see #deleteLine
	 */

	public void setLine(String[] words, int line) {
		ArrayList newLine = new ArrayList();
		for (int i = 0; i < words.length; i++) {
			newLine.add(words[i]);
		}
		lines_.add(line, newLine);
	}

//----------------------------------------------------------------------

	/**
	 * Gets the line from the specified position.
	 * String array representing the line is returned.
	 * 从指定位置获取行。返回表示行的字符串数组。
	 * @param line line index
	 * @return String[]
	 * @see #setLine
	 * @see #getLineAsString
	 * @see #addLine
	 * @see #addEmptyLine
	 * @see #deleteLine
	 */

	public String[] getLine(int line) {
		ArrayList current_line = (ArrayList) lines_.get(line);
		String[] words = new String[current_line.size()];
		for (int i = 0; i < current_line.size(); i++) {
			words[i] = (String) current_line.get(i);
		}
		return words;
	}

//----------------------------------------------------------------------

	/**
	 * Gets the line from the specified position.
	 * A single String representing the line is returned.
	 * 从指定位置获取行。返回表示该行的单个String。
	 * @param line line index
	 * @return String
	 * @see #setLine
	 * @see #getLine
	 * @see #addLine
	 * @see #addEmptyLine
	 * @see #deleteLine
	 */

	public String getLineAsString(int line) {
		ArrayList current_line = (ArrayList) lines_.get(line);
		String str = "";
		for (int i = 0; i < current_line.size(); i++) {
			str += (String) current_line.get(i);
		}
		return str;
	}

//----------------------------------------------------------------------

	/**
	 * Adds a line at the end of the line array.
	 * Two dimensional array is the argument for the new line
	 * 在行数组的末尾添加一行。二维数组是新行的参数
	 * @param words new line
	 * @see #addEmptyLine
	 * @see #setLine
	 * @see #getLine
	 * @see #deleteLine
	 */

	public void addLine(char[][] words) {
		ArrayList newLine = new ArrayList();
		for (int i = 0; i < words.length; i++) {
			String str = "";
			for (int j = 0; j < words[i].length; j++) str += words[i][j];
			newLine.add(str);
		}
		lines_.add(newLine);
	}

//----------------------------------------------------------------------

	/**
	 * Adds a line at the end of the line array.
	 * String array is the argument for the new line
	 *
	 * @param words new line
	 * @see #addEmptyLine
	 * @see #setLine
	 * @see #getLine
	 * @see #deleteLine
	 */

	public void addLine(String[] words) {
		ArrayList newLine = new ArrayList();
		for (int i = 0; i < words.length; i++) {
			newLine.add(words[i]);
		}
		lines_.add(newLine);
	}

//----------------------------------------------------------------------

	/**
	 * Adds an empty line at the end of the lines array.
	 *
	 * @see #setLine
	 * @see #getLine
	 * @see #getLineAsString
	 * @see #addLine
	 * @see #deleteLine
	 */

	public void addEmptyLine() {
		ArrayList newLine = new ArrayList();
		lines_.add(newLine);
	}

//----------------------------------------------------------------------

	/**
	 * Deletes the line from the specified position.
	 * 从指定位置删除该行。
	 * @param line line index
	 * @see #setLine
	 * @see #getLine
	 * @see #getLineAsString
	 * @see #addLine
	 * @see #addEmptyLine
	 */

	public void deleteLine(int line) {
		lines_.remove(line);

	}

//----------------------------------------------------------------------

	/**
	 * Gets the number of lines.
	 *
	 * @return int
	 */

	public int getLineCount() {
		return lines_.size();
	}

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
