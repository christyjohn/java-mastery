package ch04_generics.examples.cay.pair;

public class PairTest1 {
	public static void main(String[] args) {
		String[] words =  { "Mary", "had", "a", "little", "lamb" };

		Pair<String> mm = ArrayAlg.minmax(words);
		System.out.println("min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());

		String middle = ArrayAlg.getMiddle("John", "Q", "Public");
		System.out.println("middle = " + middle);

		//double middle = ArrayAlg.getMiddle(3.14, 1792, 0);
		double middle2 = ArrayAlg.getMiddle(3.14, 1792.0, 0.0);
		System.out.println("middle2 = " + middle2);
	}
}

class ArrayAlg {
	/**
	 * Gets the minimum and maximum of an array of strings.
	 * @param a an array of strings
	 * @return a pair with the min and the ma values, or null if a is
	 *         null or empty 
	 */
	public static Pair<String> minmax(String[] a) {
		if (a == null || a.length == 0)
			return null;

		String min = a[0];
		String max = a[0];

		for (String s : a) {
			if (min.compareTo(s) > 0)
				min = s;
			if (max.compareTo(s) < 0)
				max = s;
		}

		return new Pair<>(min, max);
	}

	public static <T> T getMiddle(T... a) {
		return a[a.length / 2];
	}
}