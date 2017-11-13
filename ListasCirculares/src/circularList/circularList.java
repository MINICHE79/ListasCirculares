package circularList;

import java.util.Iterator;

import node.node;

public class circularList<T> implements Iterable {
	private node<T> sentinel = null;
	private node<T> actual = null;

	public circularList() {
		sentinel = new node<T>();
		actual = new node<T>();
		sentinel.setIndex(-1);
		actual.setIndex(-1);
	}

	public circularList(T value) {
		this();
		sentinel.setNext(new node<T>(value));
		actual = sentinel.getNext();
		sentinel.getNext().setNext(actual);
	}
	
	//Metodos de Agregar

	public void addFirst(T value) {
		node<T> nuevo = new node<T>(value),last = getLast();		
		if(isEmpty()){
			sentinel.setNext(nuevo);
			nuevo.setNext(nuevo);
		}else{
			nuevo.setNext(sentinel.getNext());
			sentinel.setNext(nuevo);
			last.setNext(nuevo);
		}
		Reindex();
	}
	
	public void addEnd(T value) {
		node<T> nuevo = new node<T>(value),last = getLast();		
		if(isEmpty()){
			sentinel.setNext(nuevo);
			nuevo.setNext(nuevo);
		}else{
			nuevo.setNext(sentinel.getNext());
			last.setNext(nuevo);
		}
		Reindex();
	}
	
	public boolean addbefore(T value, T newvalue){
		node<T> tmp = sentinel;
		while(tmp.getNext()!= null && !tmp.getNext().getValue().equals(value)){
			tmp = tmp.getNext();
		}
		return (tmp.getNext()!=null)?addAfter(new node<T>(newvalue),tmp):false;
	}
	
	public boolean addAfter(T value, T newvalue){
		node<T> finder = Search(value);
		if(finder != null){
			return addAfter(new node<T>(newvalue),finder);
		}
		else{
			return false;
		}
	}
	
	private boolean addAfter (node<T> nodo, node<T> finder){
		nodo.setNext(finder.getNext());
		finder.setNext(nodo);
		Reindex();
		return true;
	}
	
	//Metodos Get 
	public node<T> GetFirst() {
		if(!isEmpty())
			return sentinel.getNext();
		else
			return null;
	}

	public node<T> getLast() {
		node<T> tmp = sentinel.getNext();
		if (!isEmpty()) {
			while (!sentinel.getNext().equals(tmp.getNext()))
				tmp = tmp.getNext();
			return tmp;
		}
		return null;
	}
	
	public void indexof(T value){
		node<T> finder = Search(value);
		if(finder != null)
			System.out.println(finder.getIndex());
		else
			System.out.println("Valor no encontrado");
	}
	
	//Metodos Search
	
	public node<T> Search(T value) {
		return (!isEmpty()) ? Search(value, sentinel.getNext()) : null;
	}

	public node<T> Search(T value, node<T> list) {
		if (list.getNext().getValue().equals(value)) {
			return list.getNext();
		}
		if (list.getNext().equals(sentinel.getNext())) {
			return null;
		}
		return Search(value, list.getNext());
	}
	
	//Metodos para Eliminar
	
	public void Clear() {
		while(sentinel.getNext() != null){
			node<T> tmp = sentinel;
			node<T> last = getLast();
			last.setNext(null);
			while (tmp.getNext().getNext()!=null) {
				tmp = tmp.getNext();
			}
			tmp.setNext(null);
			sentinel.setNext(null);
		}
		System.gc();
	}
	
	
	public boolean Replace(T value, T newvalue){
		node<T> finder = Search(value);
		if(finder != null){
			finder.setValue(newvalue);
			return true;
		}
		else
			return false;
	}
	
	public void RemoveFirst() {
		if(!isEmpty()) {
			if(size() == 1) {
				sentinel.setNext(null);
			}
			else {
				node<T> tmp = getLast();
				tmp.setNext(sentinel.getNext().getNext());
				sentinel.setNext(tmp.getNext());
				Reindex();
			}
			System.gc();
		}
	}
	
	public void RemoveLast() {
		if(!isEmpty()) {
			if(size() == 1) {
				sentinel.setNext(null);
			}
			else {
				node<T> tmp = sentinel.getNext();
				while(!tmp.getNext().equals(getLast()))
					tmp=tmp.getNext();
				tmp.setNext(sentinel.getNext());
				Reindex();
			}
			System.gc();
		}
	}
	
	public boolean RemoveBefore(T value){
		node<T> tmp = sentinel;
		if(Search(value) != null){
			while (!tmp.getNext().getNext().getValue().equals(value)) {
				tmp = tmp.getNext();
			}
			tmp.setNext(tmp.getNext().getNext());
			Reindex();
			System.gc();
			return true;
		}
		return false;
	}
	
	public boolean RemoveAfter(T value){
		node<T> finder = Search(value);
		if(finder != null){
			if(size() == 1) {
				sentinel.setNext(null);
				System.gc();
				return true;
			}
			else {
				if (finder.equals(getLast()))
					sentinel.setNext(finder.getNext().getNext());
				finder.setNext(finder.getNext().getNext());
				Reindex();
				System.gc();
				return true;
			}
		}
		return false;
	}
	
	
	//Otros Metodos
	public void Reindex(){
		node<T> tmp = sentinel.getNext();
		int i = 0;
		while (tmp.getNext()!=sentinel.getNext()) {
			tmp.setIndex(i);
			tmp = tmp.getNext();
			i++;
		}
		tmp.setIndex(i);
	}
	
	public int size(){
		if(!isEmpty()) {
		node<T> tmp = sentinel.getNext();
		int i = 0;
		while (tmp.getNext()!=sentinel.getNext()) {
			tmp = tmp.getNext();
			i++;
		}
		i++;
//		System.out.println("Tamaño de la lista : " + i);
		return i;
		}
		else
			return 0;
	}
	
	public boolean isEmpty() {
		return (sentinel.getNext() == null) ? true : false;
	}
	
	
	public void pronter() {
		node<T> tmp = sentinel.getNext();
		if(!isEmpty()){
			while(!tmp.getNext().equals(sentinel.getNext())){
				System.out.println(tmp.getValue()+ "  " +tmp.getIndex());
				tmp = tmp.getNext();
			}
			System.out.println(tmp.getValue()+ "  " +tmp.getIndex());
		}else{
			System.out.println();
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			node<T> tmp = sentinel;
			int i = 0;
			@Override
			public boolean hasNext() {
				tmp = tmp.getNext();
				if(tmp == sentinel.getNext())
					i++;
				return (i != 2)?true:false;
			}
			@Override
			public T next() {
				return tmp.getValue();
			}
		};
	}
	
}
