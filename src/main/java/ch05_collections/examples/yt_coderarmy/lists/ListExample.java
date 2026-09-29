package ch05_collections.examples.yt_coderarmy.lists;

import java.util.*;

public class ListExample {
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();

		list.add(1);
        list.add(2);
        list.add(3);

        //System.out.println(list.get(1));

        //list.set(1, 5);
        /*list.addAll(0, List.of(9,8,7));
        System.out.println(list);
        list.remove(0);
        System.out.println(list);

        System.out.println(list.indexOf(2));
        System.out.println(list.lastIndexOf(5));*/

        Iterator<Integer> it = list.listIterator();

       while(it.hasNext()) {
        	System.out.println(it.next());
        }
        System.out.println("-----");

       ListIterator<Integer> it2 =list.listIterator(2);

       while(it2.hasPrevious()) {
       		System.out.println(it2.previous());
       }

       System.out.println("-----");

       ListIterator<Integer> it3 =list.listIterator(3);

       while(it3.hasPrevious()) {
       		System.out.println(it3.previous());
       }

		
	   System.out.println("-----");
       it3 =list.listIterator(3);

       while(it3.hasPrevious()) {
       		System.out.println(it3.previousIndex());
       		it3.previous();
       }

		System.out.println("-----");
        List<Integer> l = List.of(1,2,3,4,5,6,7,8);
        // l.add(9); //java.lang.UnsupportedOperationException

        List<Integer> l2 = List.copyOf(l);
        // l2.add(7); // java.lang.UnsupportedOperationException

        System.out.println(l2); // [1, 2, 3, 4, 5, 6, 7, 8]
}