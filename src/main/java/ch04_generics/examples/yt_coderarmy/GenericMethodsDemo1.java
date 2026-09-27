package ch04_generics.examples.yt_codearmy;

public class GenericMethodsDemo1 {
	public static void main(String[] args) {
		int y = getResult(5);
		System.out.println(y);

		String s = getGenericResult("Hello");
		System.out.println(s);

		printPair(40, "Cecil");
	}

	public static int getResult(int x) {
		return x + 5;
	}

	public static <T> T getGenericResult(T x) {
		return x;
	}

	public static <T,U> void printPair(T first, U second) {
		System.out.println(first + ", " + second);
	}
}