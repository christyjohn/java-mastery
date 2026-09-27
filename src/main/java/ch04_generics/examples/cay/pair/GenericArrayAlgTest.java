package ch04_generics.examples.cay.pair;

import java.time.LocalDate;

public class GenericArrayAlgTest {
	public static void main(String[] args) {
		LocalDate[] birthdays = 
				{
					LocalDate.of(1906, 12, 9), // G. Hopper
					LocalDate.of(1815, 12, 10), // A. Lovelace
					LocalDate.of(1903, 12, 3), // J. von Neumann
					LocalDate.of(1910, 6, 22), // K. Zuse
				};

		Pair<LocalDate> mm = GenericArrayAlg.minmax(birthdays);

		System.out.println("min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());
	}
}