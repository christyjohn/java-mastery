package ch04_generics.examples.cay.pair;

public class PairTest1 {
	public static void main(String[] args) {
		String[] words =  { "Mary", "had", "a", "little", "lamb" };

		Pair<String> mm = ArrayAlg.minmax(words);
		System.out.println("min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());

		String middle = ArrayAlg.getMiddle("John", "Q", "Public");
		System.out.println("middle = " + middle);

		//double middle = ArrayAlg.getMiddle(3.14, 1792, 0);
		double middle2 = ArrayAlg.getMiddle(3.14, 1792.0, 0.0);
		System.out.println("middle2 = " + middle2);
	}
}