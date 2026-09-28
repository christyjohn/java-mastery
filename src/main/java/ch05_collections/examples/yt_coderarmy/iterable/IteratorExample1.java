package ch05_collections.examples.yt_coderarmy.iterable;

import java.util.List;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class IteratorExample1 {
	public static void main(String[] args) {
		//List<Integer> list = new ArrayList<>();
		List<Integer> list = new LinkedList<>();

		//Collection<Integer> list = new HashSet<>();

		list.add(10);
		list.add(20);
		list.add(50);
		list.add(70);
		list.add(30);
		list.add(5);

		Iterator it = list.iterator();

		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}