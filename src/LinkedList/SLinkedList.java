package LinkedList;

import java.util.NoSuchElementException;

import List.List;

/**
 * 필수목록
 * 
 * 1. 클래스 및 생성자 구성
 * 2. search 메서드
 * 3. add 메서드
 * 4. remove 메서드
 * 5. get, set, indexOf, contains 메서드
 * 6. size, isEmpty, clear 메서드
 */
public class SLinkedList<E> implements List<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public SLinkedList() {
		this.head=null;
		this.tail=null;
		this.size=0;
	}
	
	// 2단계 search 메서드 구현
	private Node<E> search(int index) {
		if (index<0 || index>=size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> x=head;
		
		for (int i=0; i<index; i++) {
			x=x.next;
		}
		
		return x;
	}
	
	// 3단계 add 메서드 구현(addFirst, addLast, add, add)
	public void addFirst(E value) {
		Node<E> newNode=new Node<>(value);
		newNode.next=head;
		head=newNode;
		
		size++;
		
		/*
		 * 48 줄에서 newNode.next 에 null 이 들어갔다는 것은 그 전까지는 0개의 Node 가 있었다는 뜻이다.
		 * 따라서 newNode 는 head 임과 동시에 tail 이어야 한다.
		 */
		if (head.next==null) {
			tail=head;
		}
	}
	
	public void addLast(E value) {
		Node<E> newNode=new Node<>(value);
		
		if (size==0) {
			addFirst(value);
			return;
		}
		
		tail.next=newNode;
		tail=newNode;
		size++;
	}
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	@Override
	public void add(int index, E value) {
		if (index<0 || index>size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index==0) {
			addFirst(value);
			return;
		}
		
		if (index==size) {
			addLast(value);
			return;
		}
		
		Node<E> prevNode=search(index-1);
		
		Node<E> nextNode=prevNode.next;
		
		Node<E> newNode=new Node<E>(value);
		
		prevNode.next=null;
		prevNode.next=newNode;
		newNode.next=nextNode;
		
		size++;
	}
	
	// 4단계 remove 메서드 구현 (remove, remove, remove)
	public E remove() {
		Node<E> headNode=head;
		
		if (headNode==null) {
			throw new NoSuchElementException();
		}
		
		E removeTarget=headNode.data;
		Node<E> newNode=head.next;
		
		head.data=null;
		head.next=null;
		
		head=newNode;
		size--;
		
		if(size==0) {
			tail=null;
		}
		
		return removeTarget;
	}
	
	@Override
	public E remove(int index) {
		if (index<0 || index>size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index==0) {
			return remove();
		}
		
		Node<E> prevNode=search(index-1);
		Node<E> removeTargetNode=prevNode.next;
		Node<E> nextNode=removeTargetNode.next;
		
		E removeTarget=removeTargetNode.data;
		
		prevNode.next=nextNode;
		
		removeTargetNode.next=null;
		removeTargetNode.data=null;
		size--;
		
		return removeTarget;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object value) {
		Node<E> prevNode=head;
		
		boolean hasValue=false;
		Node<E> x=head;

		for (; x!=null; x=x.next) {
			if (((E) value).equals(x.data)) {
				hasValue=true;
				break;
			}
			prevNode=x;
		}
		
		if (x==null) {
			return hasValue;
		}
		
		if (x.equals(head)) {
			remove();
			return hasValue;
		} else {
			prevNode.next=x.next;
			
			x.data=null;
			x.next=null;
			
			size--;
			
			return hasValue;
		}
	}
	
	// 5. get, set, indexOf, contains 메서드
	@Override
	public E get(int index) {
		return search(index).data;
	}
	
	@Override
	public void set(int index, E value) {
		Node<E> replaceNode=search(index);
		replaceNode.data=null;
		replaceNode.data=value;
	}
	
	@Override
	public int indexOf(Object value) {
		int index=0;
		
		for (Node<E> x=head; x!=null; x=x.next) {
			if (value.equals(x.data)) {
				return index;
			}
			
			index++;
		}
		
		return -1;
	}
	
	@Override
	public boolean contains(Object item) {
		return indexOf(item)>=0;
	}
	
	// 6. size, isEmpty, clear 메서드
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	@Override
	public void clear() {
		for (Node<E> x=head; x!=null;) {
			Node<E> nextNode=x.next;
			x.data=null;
			x.next=null;
			x=nextNode;
		}
		head=tail=null;
		size=0;
	}
}