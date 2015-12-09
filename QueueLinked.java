package supermercado;

public class QueueLinked<E> implements QueueTAD<E> {
	//@ public invariant count >= 0;
	
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

	//@ public initially head == null;
	//@ public initially tail == null;
	//@ public initially count == 0;

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
	
	/*@
	  @ assignable \nothing;
	  @ ensures \result == head;
	  @
	  @*/
	public /*@ pure @*/ Node<E> getHead(){
		return head;
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

	/*@ also
	  @ assignable \nothing;
	  @ ensures \result == head.element;
	  @*/
	public /*@ pure @*/ E element() {
		E element = null;
		try{
			element = head.element;
		} catch (EmptyQueueException e) {
			
		}
		return element; 
	}

	/*@ also
	  @
	  @ requires element != null && head == null;
	  @ assignable head, tail, count;
	  @ ensures tail == head && count == \old (count + 1);
	  @*/
	/* @ also
	  @
	  @ requires element != null;
	  @ 
	  @ assignable tail, count;
	  @
	  @ ensures tail != null && count == \old (count + 1);
	  @
	  @ */
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
