package ule.edi.SimpleList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;



public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T ... elements) {
		
		if(elements.length!=0) {
			Node<T> nuevo = new Node(elements[0]);
			header=nuevo;
			constructorRec(elements.length-1, elements);
		}
		System.out.println(toString());

		
		// IMPLEMENTAR DE FORMA RECURSIVA 

	}
	public void constructorRec(int tamaño, T[] elementos) {
		if(tamaño==1) {
			addLast(elementos[tamaño]);
		}else {
			constructorRec(tamaño-1, elementos);
			addLast(elementos[tamaño]);
		}
	}
	
	@Override
	public void addLast(T element) {
		if(isEmpty()) {
			addFirst(element);
		}else {
			addLastRec(element,header);
		}
	}
	public void addLastRec(T element,Node<T> aux) {
		Node<T> nuevo=new Node(element);
		if(aux.next==null) {
			aux.next=nuevo;
			nuevo.next=null;
		}else {
			addLastRec(element,aux.next);
		}

	}
	private class ForwardIterator implements Iterator<T> {

		private Node<T> aux=header ;


		public boolean hasNext() {

			return(aux!=null);
			// TODO Auto-generated method stub

		}

		public T next() {
			if(hasNext()) {
				T elem=aux.content;
				aux=aux.next;
				return elem;
			}
			// TODO Auto-generated method stub
			return null;

		}


		public void remove() {
			throw new UnsupportedOperationException();
		}
	};
	@Override
	public Iterator<T> iterator() {

		return new ForwardIterator();
	}

	@Override
	public boolean isEmpty() {
		if(header==null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}else {
			int sumatorio=sizeRec(header);
			return sumatorio;
		}
	}
	public int sizeRec(Node<T> aux) {
		if(aux.next==null) {
			return 1;
		}else {
			return 1 + sizeRec(aux.next);
		}

	}

	@Override
	public void addFirst(T element) {
		Node<T> nuevo=new Node(element);
		Node<T> siguiente;
		if(isEmpty()) {
			header=nuevo;
			nuevo.next=null;
		}else {
			siguiente=header;
			header=nuevo;
			header.next=siguiente;
		}

	}



	@Override
	public void addAtPos(T element, int p) {
		if(p==1) {
			addFirst(element);
		}else if(p-1>=size()) {
			addLast(element);
		}else {
			addAtPosRec(element, p-1, header);
		}

	}
	public void addAtPosRec(T element, int p, Node<T> aux) {
		Node<T> nuevo=new Node(element);
		Node<T> siguiente;
		Node<T> anterior;

		if(p==1) {
			siguiente=aux.next;
			anterior=aux;
			System.out.println(p);


			anterior.next=nuevo;
			nuevo.next=siguiente;
		}else {
			addAtPosRec(element,p-1,aux.next);
		}
	}

	@Override
	public void addNTimes(T element, int n) {
		if(isEmpty()) {
			addFirst(element);
			addNTimesRec(element,n-1);
		}else {
			addNTimesRec(element,n);
		}
	}
	public void addNTimesRec(T element, int n) {
		if(n==1) {
			addLast(element);
		}else {
			addNTimesRec(element, n-1);
			addLast(element);
		}
	}
	//este dispara exception
	@Override
	public int indexOf(T elem) {
		int posicion=indexOfRec(elem, header);
		return posicion;
	}

	public int indexOfRec(T element, Node<T> aux) {
		if(element.equals(aux.content)) {
			return 1;
		}else {
			return 1 + indexOfRec(element, aux.next);
		}

	}

	@Override
	public T removeLast() throws EmptyCollectionException {
		removeLastRec(header);
		return null;
	}

	public  T removeLastRec(Node<T> aux) {
		Node<T> devolver;
		if(aux.next.next==null) {
			devolver=aux.next;
			aux.next=null;
		}else {
			removeLastRec(aux.next);
		}
		return null;

	}
	//la idea de usar tamaño auxiliar es que hay un punto en el que size tiene un tamaño superior hasta que elimina el elemento  que pasan a ser iguales 
	@Override
	public T removeLast(T elem) throws EmptyCollectionException {
		int tamañoAux=size()-1;
		removeLastRec(elem, header,tamañoAux);
		return null;
	}
	public T removeLastRec(T elem, Node<T> aux, int tamañoAux) throws EmptyCollectionException {
		if(aux.next==null) {
			return null;
		}else {
			removeLastRec(elem, aux.next,tamañoAux);
			if(aux.next.content.equals(elem)&&tamañoAux!=size()) {
				aux.next=aux.next.next;
				return null;
			}
		}

		return null;

	}


	@Override
	public AbstractSingleLinkedListImpl<T> reverse() {
		AbstractSingleLinkedListImpl<T> inversa = new SingleLinkedListImpl<T>();
		inversa=reverseRec(header,inversa);
		return inversa;
	}
	
	public AbstractSingleLinkedListImpl<T> reverseRec(Node<T> aux, AbstractSingleLinkedListImpl<T> inversa) {
		if(aux.next==null) {
			inversa.addLast(aux.content);
		}else {
			reverseRec(aux.next,inversa);
			inversa.addLast(aux.content);
		}
		return inversa;
	}


	@Override
	public int isSubList(AbstractSingleLinkedListImpl<T> part) {
		int pos;
		Node<T> headerPart=part.header;
		pos=isSubListRec(header,headerPart,part);
		return pos+1;
	}
	public int isSubListRec(Node<T> aux,Node<T> partAux,AbstractSingleLinkedListImpl<T> part) {
		

		
		if(aux.content.equals(partAux.content)) {
			if(partAux.next==null) {
				
				return 0;
			}
			if(aux.next.content.equals(partAux.next.content)) {
				isSubListRec(aux.next,partAux.next,part);
				return 0;
			}else{
				return 1+isSubListRec(aux.next,part.header,part);
			}
			//isSubListRec(i,j, i.next(),j.next(),part);

		}else {
			return 1+isSubListRec(aux.next,part.header,part);

		}


	}


}
