package supermercado;

public class QueueLinked<E> implements QueueTAD<E> {
	
	private static final class Node<E> {
		public /*@ nullable @*/ E element;
		public /*@ nullable @*/ Node<E> next;

		public Node(E e) {
			element = e;
			next = null;
		}
	}

	private /*@ spec_public nullable @*/ Node<E> head;
	private /*@ spec_public nullable @*/ Node<E> tail;
	private /*@ spec_public @*/int count;

	/*@
	  @ 
	  @ assignable head, tail, count;
	  @ 
	  @ ensures head == null && tail == null && count == 0;
	  @ 
	  @*/
	public QueueLinked() {
		head = null;
		tail = null;
		count = 0;
	}

	/*@ also
	  @ 
	  @ assignable \nothing;
	  @ 
	  @ ensures \result == count;
	  @ 
	  @*/
	public /*@ pure @*/ int size() {
		return count;
	}

	/*@ also
	  @ 
	  @ assignable \nothing;
	  @
	  @ ensures \result == (count == 0);
	  @ 
	  @*/
	public /*@ pure @*/ boolean isEmpty() {
		return (count == 0);
	}

	/*@ also
	  @ 
	  @ assignable head, tail, count;
	  @ 
	  @ ensures head == null && tail == null && count == 0;
	  @ 
	  @*/
	public void clear() {
		head = null;
		tail = null;
		count = 0;
	}
	
<<<<<<< HEAD
	/*@ also
	  @ assignable \nothing;
	  @ ensures \result == head.element;
	  @*/
=======
	/* @ public normal_behavior
	  @ 
	  @
	  @ 
	  @ assignable \nothing;
	  @ 
	  @ ensures \result == head.element;
	  @ 
	  @ also
	  @ 
	  @ public exceptional_behavior
	  @ 
	  @ requires head.isEmpty();
	  @ 
	  @ assignable \nothing;
	  @ 
	  @ signals_only EmptyQueueException;
	  @ 
	  @ signals (EmptyQueueException e);
	  @ */
>>>>>>> 5e9b31160fa109aaf07d18859484c7167af646ea
	public /*@ pure @*/ E element() {
		if (isEmpty())
			throw new EmptyQueueException();
		return head.element;
	}

<<<<<<< HEAD
	/*@ also
	  @
	  @ requires element != null && head == null;
	  @ assignable head, tail, count;
	  @ ensures tail == head && count == \old (count + 1);
	  @*/
=======
	/* @ also
	  @
	  @ requires element != null;
	  @ 
	  @ assignable tail, count;
	  @
	  @ ensures tail != null && count == \old (count + 1);
	  @
	  @ */
>>>>>>> 5e9b31160fa109aaf07d18859484c7167af646ea
	public void add(E element) {
		Node<E> n = new Node<E>(element);
		if (head == null)
			head = n;
		else
			tail.next = n;
		tail = n;
		count++;
	}
	
	/*@ also
	  @
	  @ assignable head, count;
	  @
	  @ ensures head != null && count == \old (count - 1);
	  @
	  @*/
	public E remove() {
		if (isEmpty())
			throw new EmptyQueueException();
		Node<E> target = head;
		E item = target.element;
		head = target.next;
		target.element = null;
		target.next = null;
		if (head == null)
			tail = null;
		count--;
		return item;
	}
}
