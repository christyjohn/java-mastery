package ch04_generics.examples.yt_coderarmy.wildcards;

import java.util.List;
import java.util.ArrayList;

public class UpperBoundedWildcardsDemo {
	public static void main(String[] args) {
		List<Dog> dogs = new ArrayList<>();

		dogs.add(new Dog());
		dogs.add(new Dog());

		fun(dogs);

		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal());
		animals.add(new Animal());

		fun(animals);

		List<Integer> l = new ArrayList<>();
		//fun(l);
	}

	// PECS - Producer extends, Consumer supplies
	// extends - can read, but not write to
	static void fun(List<? extends Animal> animals) {
		for(Animal a : animals) {
			a.eat();
		}

		// The method add(capture#2-of ? extends Animal) in the type 
		// List<capture#2-of ? extends Animal> is not applicable for 
		// the arguments (Animal) 
		// animals.add(new Animal());
		//animals.add(new Dog());
	}
}

class Animal {

	void eat() {
		System.out.println("Animal eating");
	}

	void walk() {
		System.out.println("Animal walking");
	}
}

class Dog extends Animal {

	void eat() {
		System.out.println("Dog eating");
	}

	void bark() {
		System.out.println("Barking");
	} 
}