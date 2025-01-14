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
 *  Name:    KWIC.java
 *
 *  Purpose: Demo system for practice in Software Architecture
 *
 *  Created: 11 Sep 2002
 *
 *  $Id$
 *
 *  Description:
 *    The basic KWIC system is defined as follows. The KWIC system accepts an ordered
 *  set of lines, each line is an ordered set of words, and each word is an ordered set
 *  of characters. Any line may be "circularly shifted" by repeadetly removing the first
 *  word and appending it at the end of the line. The KWIC index system outputs a
 *  listing of all circular shifts of all lines in alphabetical order.
 * </file>
 */

//package kwic.ms;

/*
 * $Log$
 */

import java.io.*;

/**
 * This class is an implementation of the main/subroutine architectural solution
 * for the KWIC system. This solution is based on functional
 * decomposition of the system. Thus, the system is decomposed into a number of
 * modules, each module being a function. In this solution all functions share access
 * to data, which is stored in the "core storage". The system is decomposed into the
 * following modules (functions):
 * <ul>
 * <li>Master Control (main). This function controls the sequencing among the
 * other four functions.
 * <li>Input. This function reads the data lines from the input medium (file) and
 * stores them in the core for processing by the remaining modules. The characters are
 * stored in a character array (char[]). The blank space is used to separate words in
 * a particular line. Another integer array (int[]) keeps the starting indices of
 * each line in the character array.
 * <li>Circular Shift. This function is called after the input function has
 * completed its work. It prepares a two-dimensional integer array (int[][]) to keep
 * track of all circular shifts. The array is organized as follows: for each circular
 * shift, both index of its original line, together with the index of the starting word of
 * that circular shift are stored as one column of the array.
 * <li>Alphabetizing. This function takes as input the arrays produced by the input
 * and circular shift functions. It produces an array in the same format (int[][])
 * as that produced by circular shift function. In this case, however, the circular
 * shifts are listed in another order (they are sorted alphabetically).
 * <li>Output. This function uses the arrays produced by input and alphabetizing
 * function. It produces a nicely formated output listing of all circular shifts.
 * </ul>
 * @author dhelic
 * @version $Id$
 */

public class KWIC {

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

	/*
	 * Core storage for shared data
	 *
	 */

	/**
	 * Input characters
	 */

	private char[] chars_ = new char[10000];

	/** 表示一共有多少个字符 */
	int charNum = 0;

	/** 每行的字符总数 */
	private int[] line_char_num = new int[10000];

	/** 每行的单词数 */
	private int[] line_words_num = new int[10000];

	/**
	 * Array that keeps line indices (line index is the index of the first character of a line)
	 */

	private int[] line_index_ = new int[10000];

	/** 表示一共有多少行 */
	int line = 0;

	/**
	 * 2D array that keeps circular shift indices (each circular shift index is a column
	 * in this 2D array, the first index is the index of the original line from line_index_
	 * array, the second index is the index of the starting word from chars_ array of
	 * that circular shift)
	 */

	private int[][] circular_shifts_ = new int[10000][];

	/** 表示一共有多少种情况,即圆形位移后一共有多少行 */
	int num = 0;

	/**
	 * 2D array that keeps circular shift indices, sorted alphabetically
	 */

	private int[][] alphabetized_ = new int[10000][];

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
	 * Input function reads the raw data from the specified file and stores it in the core storage.
	 * If some system I/O error occurs the program exits with an error message.
	 * The format of raw data is as follows. Lines are separated by the line separator
	 * character(s) (on Unix '\n', on Windows '\r\n'). Each line consists of a number of
	 * words. Words are delimited by any number and combination of the space chracter (' ')
	 * and the horizontal tabulation chracter ('\t'). The entered data is parsed in the
	 * following way. All line separators are removed from the data, all horizontal tabulation
	 * word delimiters are replaced by a single space character, and all multiple word
	 * delimiters are replaced by a single space character. Then the parsed data is represented
	 * in the core as two arrays: chars_ array and line_index_ array.
	 *
	 * @param file Name of input file
	 */

	public void input(String file) {
		// 按行读取文件
		File text = new File(file);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(text));
			String tempString = "";
			while ((tempString = reader.readLine()) != null) {

				int cnt = 0;
				line_char_num[line] = charNum;
				int word = 1;

				for (int i = 0; i < tempString.length(); i++) {

					if (charNum != 0 && this.chars_[charNum - 1] == ' ' && tempString.charAt(cnt) == ' ') {
						cnt++;
					}
					if (i == tempString.length() - 1 && tempString.charAt(cnt) == ' ') {
						cnt++;
					}
					else if (tempString.charAt(cnt) == '\t') {
						chars_[charNum] = ' ';
						cnt++;
						charNum++;
						word++;
					}
					else {
						chars_[charNum] = tempString.charAt(cnt);
						if (chars_[charNum] == ' ') word++;
						cnt++;
						charNum++;
					}

				}

				line_char_num[line] = charNum - line_char_num[line];
				line_index_[line] = line_char_num[line];
				line_words_num[line] = word;

				line++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

//----------------------------------------------------------------------

	/**
	 * This function processes arrays prepared by the input
	 * function and produces circular shifts of the stored lines. A circular
	 * shift is a line where the first word is removed from the begin of a line
	 * and appended at the end of the line. To obtain all circular shifts of a line
	 * we repeat this process until we can't obtain any new lines. Circular shifts
	 * are represented as a 2D array that keeps circular shift indices (each circular
	 * shift index is a column in this 2D array, the first index is the index of
	 * the original line from line_index_ array, the second index is the index of
	 * the starting word from chars_ array of that circular shift)
	 */

	public void circularShift() {
		int cnt = 0;
		for (int i = 0; i < line; i++) {

			circular_shifts_[num] = new int[10000];

			circular_shifts_[num][0] = line_index_[i];
			for (int j = 0; j < line_char_num[i]; j++) {
				circular_shifts_[num][j + 1] = cnt;
				cnt++;
			}
			num++;
			cnt -=  line_char_num[i];

			for (int word = 0; word < line_words_num[i] - 1; word++) {
				circular_shifts_[num] = new int[10000];
				circular_shifts_[num][0] = line_index_[i];

				int[] words = new int[line_char_num[i]];
				int judge = 0;
				int flag = cnt;
				boolean prime = true;
				for (int j = 0; j < line_char_num[i]; j++) {
					words[j] = cnt;
					if (judge == word && chars_[cnt] == ' ' && prime) {
						flag = j;
						prime = false;
					}
					else if (chars_[cnt] == ' ') {
						judge++;
					}
					cnt++;
				}
				cnt -=  line_char_num[i];

				int k = 1;
				for (int j = flag + 1; j < line_char_num[i]; j++, k++) {
					circular_shifts_[num][k] = words[j];
				}
				circular_shifts_[num][k] = words[flag];
				k++;
				for (int j = 0; j < flag; j++, k++) {
					circular_shifts_[num][k] = words[j];
				}

				num++;
			}

			cnt += line_char_num[i];
		}
	}

//----------------------------------------------------------------------

	/**
	 * This function sorts circular shifts lines alphabetically. The sorted shifts
	 * are represented in the same way as the unsorted shifts with the only difference
	 * that now they are ordered alphabetically. This function implements binary search
	 * to sort the shifts.
	 */

	public void alphabetizing() {

		// 对circular_shifts_进行处理, 按照字母顺序排序
		for (int i = 0; i < num; i++) {

			for (int j = 0; j < num - i - 1; j++) {
				String str1 = "";
				String str2 = "";
				for (int x = 0; x < circular_shifts_[j][0]; x++) str1 = str1 + chars_[circular_shifts_[j][x + 1]];

				for (int x = 0; x < circular_shifts_[j + 1][0]; x++) str2 += chars_[circular_shifts_[j + 1][x + 1]];

				if (compareString(str1, str2)) { // str1大，应该排在后面
					for (int k = 0; k < 10000; k++) {
						int temp = circular_shifts_[j][k];
						circular_shifts_[j][k] = circular_shifts_[j+ 1][k];
						circular_shifts_[j+ 1][k] = temp;
					}
				}
			}
		}
	}

	// true表示str1更大
	public boolean compareString (String str1, String str2) {
		boolean result = true;
		int cnt = 0;
		int length = str1.length() > str2.length() ? str2.length() : str1.length();
		for (int i = 0; i < length; i++) {
			if (getASCII(str1.charAt(i)) > getASCII(str2.charAt(i))) {
				cnt = 1;
				result = true;
				break;
			}
			if (getASCII(str1.charAt(i)) < getASCII(str2.charAt(i))){
				cnt = 1;
				result = false;
				break;
			}
		}
		if (cnt == 0 && str1.length() != str2.length()) {
			if (str1.length() > str2.length()) result = true;
			else result = false;
		}
		return result;
	}
	public int getASCII (char ch) {
		return (int) ch;
	}

//----------------------------------------------------------------------

	/**
	 * This function prints the sorted shifts at the standard output.
	 */

	public void output() {
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < circular_shifts_[i][0]; j++) {
				System.out.print(chars_[circular_shifts_[i][j + 1]]);
			}
			System.out.println();
		}
	}

//----------------------------------------------------------------------

	/**
	 * This function controls all other functions in the system. It implements
	 * the sequence of calls to other functions to obtain the desired functionality
	 * of the system. Before any other function is called, main function checks the
	 * command line arguments. The program expects exactly one command line argument
	 * specifying the name of the file that contains the data. If the program have
	 * not been started with proper command line arguments, main function exits
	 * with an error message. Otherwise, the input function is called first to read the
	 * data from the file. After that the circularShift and alphabetizing
	 * functions are called to produce and sort the shifts respectivelly. Finally, the output
	 * function prints the sorted shifts at the standard output.
	 *
	 * @param args command line argumnets
	 */

	public static void main(String[] args) {
		KWIC kwic = new KWIC();
		kwic.input("Test_Case2.txt");
		kwic.circularShift();
		kwic.alphabetizing();
		kwic.output();
	}

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
