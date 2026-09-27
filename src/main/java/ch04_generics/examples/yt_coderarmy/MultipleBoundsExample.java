package ch04_generics.examples.yt_codearmy;

public class MultipleBoundsExample {
	public static void main(String[] args) {
		Box<Fish> b1 = new Box<>();
		// Box<Dog> b2 = new Box<>(); // Bound mismatch
		// Box<Animal> b3 = new Box<>(); // Bound mismatch
	}
}

class Box<T extends Animal & Swimmable> {
	T value;
} 

class Animal {

}

interface Swimmable {
	void swim();
}

class Dog extends Animal {
	void bark() {
		System.out.println("I'm barking");
	}
}

class Fish extends Animal implements Swimmable {
	public void swim() {
		System.out.println("I'm swimming");
	}
}