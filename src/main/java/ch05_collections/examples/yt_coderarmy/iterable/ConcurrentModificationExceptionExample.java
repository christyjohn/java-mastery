package ch05_collections.examples.yt_coderarmy.iterable;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ConcurrentModificationExceptionExample {
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();

		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		Iterator<Integer> it = list.iterator();

		while(it.hasNext()) {
			int value = it.next();

			if (value == 3) {
				// Fail-fast
				list.remove(value); // ConcurrentModificationException - on list.remove()
				//it.remove(); // No CME on it.remover()
			}

			System.out.println(value); 
		}
	}
}