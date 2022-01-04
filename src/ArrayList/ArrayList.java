package ArrayList;

import java.util.Arrays;

import List.List;

/**
 * ArrayList 는 List 인터페이스의 기본 구현체입니다.<br>
 * 따라서 ArrayList 도 선형적 순서를 가지는 데이터 집합을 의미합니다.<br> 
 * 이론적인 부분은 다음 포스트를 참고해주세요.<br>
 * {@link https://velog.io/@unchapterd/List-ArrayList-LinkedList}<br>
 * <br>
 * 코드의 출처는 다음과 같습니다.<br>
 * {@link https://st-lab.tistory.com/161}
 * @author unchpatered
 * @param <E>
 */
public class ArrayList<E> implements List<E>, Cloneable {
	private static final int DEFAULT_CAPACITY=10;
	private static final Object[] EMPTY_ARRAY= {};
	
	// size 는 null 이 아닌 값, 즉 요소의 갯수이다.
	private int size;
	
	Object[] array;
	
	public ArrayList() {
		this.array=EMPTY_ARRAY;
		this.size=0;
	}
	
	public ArrayList(int capacity) {
		this.array=new Object[capacity];
		this.size=0;
	}

	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	@Override
	public void add(int index, E value) {
		if(size == array.length) {
			resize();
		}
		
		array[size]=value;
		size++;
	}
	
	public void addLast(E value) {
		if (size==array.length) {
			resize();
		}
		
		array[size]=value;
		size++;
	}

	/**
	 * Effective Java 아이템 27 비검사 경고문을 제거하라 참고<br>
	 * {@link https://velog.io/@unchapterd/Effective-Java-4}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		if(index >=size || index<0) {
			throw new IndexOutOfBoundsException();
		}
		
		E element = (E) array[index];
		array[index]=null;
		
		for (int i=index; i<size; i++) {
			array[i]=array[i+1];
			array[i+1]=null;
		}
		
		size--;
		resize();
		
		return element;
	}

	@Override
	public boolean remove(Object value) {
		int index=indexOf(value);
		
		if (index==-1)
			return false;
		
		remove(index);
		return true;
	}

	/**
	 * Effective Java 아이템 27 비검사 경고문을 제거하라 참고<br>
	 * {@link https://velog.io/@unchapterd/Effective-Java-4}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if(index>=size || index<0 ) {
			throw new IndexOutOfBoundsException();
		}
		
		return (E) array[index];
	}

	@Override
	public void set(int index, E value) {
		if (index>=size || index<0) {
			throw new IndexOutOfBoundsException();
		} else {
			array[index]=value;
		}
	}

	@Override
	public boolean contains(Object value) {
		return indexOf(value)>=0;
	}

	@Override
	public int indexOf(Object value) {
		for (int i=0; i<size; i++) {
			if(array[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}
	
	public int lastIndexOf(Object value) {
		for (int i=size-1; i>=0; i--) {
			if(array[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

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
		for (int i=0; i<size; i++) {
			array[i]=null;
		}
		size=0;
		resize();
	}
	
	private void resize() {
		int array_capacity=array.length;
		
		if (Arrays.equals(array, EMPTY_ARRAY)) {
			array=new Object[DEFAULT_CAPACITY];
			return;
		}
		
		if (size == array_capacity) {
			int new_capacity=array_capacity*2;
			
			array=Arrays.copyOf(array, new_capacity);
			return;
		}
		
		if (size < (array_capacity/2)) {
			int new_capacity=array_capacity/2;
			
			array=Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
			return;
		}
	}
	
	// 부록메서드
	@Override
	protected Object clone() throws CloneNotSupportedException {
		ArrayList<?> cloneList=(ArrayList<?>) super.clone();
		
		cloneList.array=new Object[size];
		
		// 원본배열, 원본배열 시작위치, 복사할 배열, 복사할 배열 시작위치, 복사할 요소 수)
		System.arraycopy(array, 0, cloneList.array, 0, size);
		
		return cloneList;
	}
	
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			return (T[]) Arrays.copyOf(array, size, a.getClass());
		}
		
		// 원본배열, 원본배열 시작위치, 복사할 배열, 복사할배열 시작위치, 복사할 요소 수
		System.arraycopy(array, 0, a, 0, size);
		
		return a;
	}
	
}
