package ch04_generics.examples.yt_coderarmy.wildcards;

import java.util.List;
import java.util.ArrayList;

public class LowerBoundedWildcardsDemo {
	public static void main(String[] args) {
		List<Dog> dogs = new ArrayList<>();

		dogs.add(new Dog());
		dogs.add(new Dog());

		// The method fun(List<? super Animal>) in the type 
		// LowerBoundedWildcardsDemo is not applicable for the arguments (List<Dog>) 
		//fun(dogs);

		List<Animal> animals = new ArrayList<>();
		//animals.add(new Object());
		animals.add(new Animal());
		animals.add(new Dog());
		animals.add(new Labrador());
		animals.add(new GSD());

		fun(animals);
	}	

	// PECS - Producer extends, Consumer supplies
	// super - can write to, but not read from
	public static void fun (List<? super Animal> values) {
		//values.add(new Object());
		values.add(new Animal());
		values.add(new Dog());
		values.add(new Labrador());
		values.add(new GSD());

		for (Object obj : values) {
			// obj.eat(); // The method eat() is undefined for the type Object
			System.out.println(obj.toString());
			Animal a = (Animal) obj;
			a.eat();
		}
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
		System.out.println("Dog barking");
	} 
}

class Labrador extends Dog {
	void eat() {
		System.out.println("Labrador eating");
	}

	void bark() {
		System.out.println("Labrador barking");
	} 
}

class GSD extends Dog {
	void eat() {
		System.out.println("German Sepheard eating");
	}

	void bark() {
		System.out.println("German Sepheard barking");
	} 
}