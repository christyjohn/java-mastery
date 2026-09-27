package ch04_generics.examples.yt_codearmy;

public class UpperBoundsDemo {
	public static void main(String[] args) {
		Box<Double> b1 = new Box<>(3.41);
		b1.printDouble();
	}
}

class Box<T extends Number> {
	T value;

	public Box(T value) {
		this.value = value;
	}

	public void printDouble() {
		System.out.println(value.doubleValue());
	}
}