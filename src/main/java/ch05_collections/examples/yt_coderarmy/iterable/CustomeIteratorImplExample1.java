package ch05_collections.examples.yt_coderarmy.iterable;

import java.util.Iterator;

public class CustomeIteratorImplExample1 {
	public static void main(String[] args) {
		String[] names = { "Chrsity", "Cecil", "John", "Elizabeth", "Ann"};
		NameContainer nm = new NameContainer(names);

		Iterator<String> it = nm.iterator(); 

		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}

class NameContainer implements Iterable<String> {

	private String[] names;
	private int size;

	public NameContainer(String[] names) {
		this.names = names;
		this.size = this.names.length;
	}

	@Override
	public Iterator<String> iterator() {
		return new NameContainerIterator();
	}

	private class NameContainerIterator implements Iterator<String> {

		private int pos = 0;

		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public String next() {
			return names[pos++];
		}

	}
}