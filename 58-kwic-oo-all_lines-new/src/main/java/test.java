import java.util.ArrayList;

public class test {
	public static ArrayList<String> line = new ArrayList<String>();
	public static void main (String args[]) {
		x();
//		y();
//		System.out.println(line.get(0));
//		String  str = "123";
		line.add(1, "123");
		System.out.println(line.get(0));
		System.out.println(line.get(1));
		System.out.println(line.get(2));
	}
	public static void x () {
		line.add("abc");
		line.add("def");
		String str = line.get(0);
		char ch = str.charAt(1);
		ch = '1';
		str = "123";
//		return str.charAt(1);
	}
	public static void y () {
//		char ch = x();
//		ch = '1';
	}
}
