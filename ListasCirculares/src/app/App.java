package app;

import java.util.Iterator;

import circularList.circularList;

public class App {
	public static void main(String[] args) {
		circularList<String> names = new circularList<String>();

		names.addFirst("Bato");
		names.addFirst("Wey");
		names.addFirst("Juancho");
		names.addFirst("Susana");
		names.addEnd("El hermoso jorge");
		
		names.addAfter("Wey", "meme");
		names.addbefore("Wey", "Momo");
		
		names.Replace("Bato", "Loli");
		names.RemoveAfter("El hermoso jorge");
		names.RemoveBefore("Loli");
	//	names.RemoveLast();
	
		
		names.pronter();
		Iterator<String> iterator = names.iterator();
		for (Iterator<String> i = iterator; i.hasNext();) {
			System.out.println(iterator.next());
		}
				
		
	}
}
