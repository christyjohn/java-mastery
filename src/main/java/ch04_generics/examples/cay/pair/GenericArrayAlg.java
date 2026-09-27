package ch04_generics.examples.cay.pair;

public class GenericArrayAlg {
	/**
	 * Gets the minimum and maximum of an array of strings.
	 * @param a an array of object of type T
	 * @return a pair with the min and the ma values, or null if a isb
	 *         null or empty 
	 */
	public static <T extends Comparable> Pair<T> minmax(T[] a) {
		// this will also work; if uncommented and above line commented
	//public static <T extends Comparable<? super T>> Pair<T> minmax(T[] a) {
		if (a == null || a.length == 0)
			return null;

		T min = a[0];
		T max = a[0];

		for (T s : a) {
			if (min.compareTo(s) > 0)
				min = s;
			if (max.compareTo(s) < 0)
				max = s;
		}

		return new Pair<>(min, max);
	}
}