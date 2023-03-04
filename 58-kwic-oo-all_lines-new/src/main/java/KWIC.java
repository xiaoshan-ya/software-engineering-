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
 *  Purpose: The Master Control class
 *
 *  Created: 20 Sep 2002
 *
 *  $Id$
 *
 *  Description:
 *    The Master Control class
 * </file>
 */

/*
 * $Log$
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * An instance of the KWIC class controls all other objects in the KWIC system
 * to achieve the desired functionality. Thus, KWIC instance creates the following
 * instances of other classes:
 * <ul>
 * <li>An instance of the LineStorage class that holds the parsed data
 * <li>An instance of the Input class which parses the input file
 * <li>An instance of the CircularShift class that makes all circular
 * shifts of lines
 * <li>An instance of the Alphabetizer class that sorts circular
 * shifts alphabetically
 * <li>An instance of the Output class that prints the lines in a nice format
 * </ul>
 * The KWIC class provides also the main method which checks the command line
 * arguments.
 *
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
	 * Parses the data, makes shifts and sorts them. At the end prints the
	 * sorted shifts.
	 * 解析数据，进行转换并对其进行排序。最后打印排序的移位。
	 *
	 * @param file name of the input file
	 */

	public LineStorage line_storage;
//	public Input input = new Input();
//	public CircularShifter CircularShifter = new CircularShifter();
//	public Alphabetizer alphabetizer = new Alphabetizer();
//	public Output Output = new Output();
	public char[] chars_ = new char[10000];

	/** 表示一共有多少个字符 */
	public int charNum = 0;

	/** 每行的字符总数 */
	public int[] line_char_num = new int[10000];

	/** 每行的单词数 */
	public int[] line_words_num = new int[10000];

	/**
	 * Array that keeps line indices (line index is the index of the first character of a line)
	 */

	public int[] line_index_ = new int[10000];

	/** 表示一共有多少行 */
	public int line = 0;

	/**
	 * 2D array that keeps circular shift indices (each circular shift index is a column
	 * in this 2D array, the first index is the index of the original line from line_index_
	 * array, the second index is the index of the starting word from chars_ array of
	 * that circular shift)
	 */

	public int[][] circular_shifts_ = new int[10000][];

	/** 表示一共有多少种情况,即圆形位移后一共有多少行 */
	public int num = 0;

	/**
	 * 2D array that keeps circular shift indices, sorted alphabetically
	 */

	public int[][] alphabetized_ = new int[10000][];

	public void execute(String file) {
		//TODO
//		input.parse(file, line_storage);
//		CircularShifter.setup(line_storage);
//		alphabetizer.alpha(CircularShifter);
//		Output.print(alphabetizer);

//		input.input(file);
//		CircularShifter.circularShift();
//		alphabetizer.alphabetizing();
//		Output.output();

		input(file);
		circularShift();
		alphabetizing();
		output();

	}
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
	 * Main function checks the command line arguments. The program expects
	 * exactly one command line argument specifying the name of the file
	 * that contains the data. If the program has not been started with
	 * proper command line arguments, main function exits
	 * with an error message. Otherwise, a KWIC instance is created and program
	 * control is passed to it.
	 *
	 * @param args command line arguments
	 */

	public static void main(String[] args) {

		KWIC kwic = new KWIC();
		kwic.execute("D:\\软工II作业\\8cb15ccdd835479d84207815b6d8b35c\\58-kwic-oo-all_lines-new\\Test_Case2.txt");
	}

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
