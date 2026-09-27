package ch04_generics.examples.yt_codearmy;

public class GenericsDemo2 {
	public static void main(String[] args) {
		Pair<Integer, String> pair = new Pair<>(43, "Christy");
		System.out.println(pair.value1 + ", " + pair.value2);
	}
}

class Pair<T,U> {

	T value1;
	U value2;

	public Pair(T value1, U value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
}