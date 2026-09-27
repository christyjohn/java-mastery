package ch04_generics.examples.yt_coderarmy.invariance;

import java.util.List;
import java.util.ArrayList;

public class InvarianceDemo2 {
	public static void main(String[] args) {
		List<Dog> dogs = new ArrayList<>();

		dogs.add(new Dog());
		dogs.add(new Dog());

		// The method fun(List<Animal>) in the type InvarianceDemo2 is 
		//not applicable for the arguments (List<Dog>)
		// fun(dogs);

		fun2(dogs);

		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal());
		animals.add(new Animal());

		fun(animals);
		fun2(animals);
	}

	static void fun(List<Animal> animals) {
		for(Animal animal : animals) {
			animal.eat();
		}
	}

	static void fun2(List<?> values) {
		for(Object obj : values) {
			//obj.eat();
			System.out.println(obj.getClass().getName());
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