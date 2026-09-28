package ch05_collections.examples.yt_coderarmy.iterable;

import java.util.Iterator;

public class CustomeIteratorImplExample2 {
	public static void main(String[] args) {
		String[] names = { "Chrsity", "Cecil", "John", "Elizabeth", "Ann"};
		NameContainer nm = new NameContainer(names);

		Iterator<String> it = nm.iterator(); 

		while(it.hasNext()) {
			System.out.println(it.next());
		}

		// Enhanced for loop is syntax sugar for iterator code. 
		// NameContainer has to be implementing Iterable

		// Can only iterate over an array or an instance of java.lang.Iterable ->
		// if you comment the iterator() code and don't implement
		// iterable in NameContiner
		for (String s : nm) {
			System.out.println(s);
		}
	}
}

class NameContainer implements Iterable<String> {

	private String[] names;
	private int size;

	public String[] getNames() {
		return names;
	}

	public NameContainer(String[] names) {
		this.names = names;
		this.size = this.names.length;
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {

			int pos = 0;

			@Override
			public boolean hasNext() {
				return pos < size;
			}

			@Override
			public String next() {
				return names[pos++];
			}

		};
	}

}