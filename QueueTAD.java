package supermercado;

public interface QueueTAD<E> {
	void add(E element);

	E remove();

	int size();

	/*@ pure @*/ boolean isEmpty();

	void clear();

	/*@ pure @*/ E element();
}
