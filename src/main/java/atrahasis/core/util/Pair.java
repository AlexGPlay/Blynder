package atrahasis.core.util;

/**
 * 
 * A generic helper class that allows the framework to save a pair of objects.
 * @param <T>
 * The type of the object1.
 * @param <K>
 * The type of the object2.
 * 
 */
public class Pair<T,K> {

	public T object1;
	public K object2;
	
	public Pair(T object1, K object2) {
		this.object1 = object1;
		this.object2 = object2;
	}
	
}
