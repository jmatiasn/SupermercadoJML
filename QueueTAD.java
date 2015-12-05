package supermercado;

public interface QueueTAD<E> {
	/*@ pure @*/ void add(E element);

	E remove();

	int size();

	boolean isEmpty();

	/*@ pure @*/ void clear();

	E element();
}
