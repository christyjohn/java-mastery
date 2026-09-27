package ch04_generics.examples.yt_codearmy;

public class GenericsDemo1 {
	public static void main(String[] args) {
		Box<Integer> b1 = new Box<>(10);
		System.out.println(b1.getValue() + 5);

		Box<String> b2 = new Box<>("Hello");
		System.out.println(b2.getValue() + 5);

		Box<Boolean> b3 = new Box<>(true);
		System.out.println(b3.getValue());

		//String s = (String) b1.getValue();
		//System.out.println(s);
	}
}

class Box<T> {
	private T value;

	public Box(T value) {
		this.value = value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}