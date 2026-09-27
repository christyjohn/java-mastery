package ch04_generics.examples.yt_coderarmy.invariance;

import java.util.List;
import java.util.ArrayList;

public class InvarianceDemo1 {
	public static void main(String[] args) {

		Animal animal = new Dog();
		animal.eat();
		animal.walk();
		// animal.bark();

		// Invariant in Generics
		List<Dog> dogs = new ArrayList<>();
		// List<Animal> animals = dogs; // Type mismatch: cannot convert from List<Dog> to List<Animal>

		// Java arrays are covariant
		Dog[] dogs1 = new Dog[10]; 
		Animal[] animals = dogs1; // possible but risky

		animals[0] = new Dog();
		animals[1] = new Dog();
		animals[2] = new Dog();
		//animals[3] = new Animal(); // java.lang.ArrayStoreException - RT, no CT

		for (Animal a : animals) {

			if (a == null)
				continue;

			a.eat();
			a.walk();
			// a.bark(); // The method bark() is undefined for the type Animal 
		}
	}
}

class Animal {

	void eat() {
		System.out.println("Eating");
	}

	void walk() {
		System.out.println("Walking");
	}
}

class Dog extends Animal {

	void bark() {
		System.out.println("Barking");
	} 
}