package List;

/**
 * List 는 선형적 순서를 가지는 데이터 집합을 의미합니다.<br>
 * 이론적인 부분은 다음 포스트를 참고해주세요.<br>
 * {@link https://velog.io/@unchapterd/List-ArrayList-LinkedList}<br>
 * <br>
 * 코드의 출처는 다음과 같습니다.<br>
 * {@link https://st-lab.tistory.com/146}
 * @author unchaptered
 * @param <E>
 */
public interface List<E> {
	
	/**
	 * List 에 E 원소 추가
	 */
	boolean add(E value);
	
	/**
	 * List 의 특정 index 에 원소추가
	 */
	void add(int index, E value);
	E remove(int index);
	boolean remove(Object value);
	E get(int index);
	void set(int index, E value);
	
}