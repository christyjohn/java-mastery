package ch01_language.arrays.examples.udemy.headeasylabs;

public class ArrayIntroduction  {
	public static void main(String[] args) {
		int a = 12;
		int b = 13;
		int c = 14;

		int[] x = new int[1000];
		System.out.println(x.getClass().getName());
		
		//Zero as size
		int[] y = new int[0];

		//character as size
		int[] z = new int['a'];
		System.out.println(z.length);
		//allowed data types for an int array: byte,short,char and int

		//negative array size
		// int[] p = new int[-1]; //  RT: NegativeArraySizeException: -1

		//size of an int array cannot be more than this:2147483647
		int[] q = new int[2147483647];
		//214748364*4 memory required to create this array.
		//nt[] q1 = new int[2147483648]; // The literal 2147483648 of type int is out of range
	}
}