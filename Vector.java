import java.util.Arrays;
import java.util.Iterator;
//@author Harry Pachchala

public class Vector<E> extends AbstractListADT<E> implements Iterable<E> {

	protected E[] array;
	@SuppressWarnings("unchecked")
	public Vector()
	{
		array = (E[]) new Object[10];
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public Vector(int i)
	{
		array = (E[]) new Object[i];
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public Vector(Vector i)
	{
		array = (E[]) i.data();
		size = i.size();
	}
	
	public Vector(E[] i)
	{
		array = i;
		size = i.length;
	}
	@Override
	public int capacity() {
		
		return array.length;
	}

	@Override
	public E front() {
		
		return array[0];
	}

	@Override
	public E back() {
		
		if(size == 0)
			return null;
		return array[size-1];
	}

	@Override
	public E[] data() {
		
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pushback(E element) {
		
		verifyCapacity(size+1);
		array[size++] = element;
	}

	private void verifyCapacity(int i) {
		
		if(i >= array.length-1)
			resize();

	}
	@SuppressWarnings("unchecked")
	private void resize() {
		
		E[] temp = null;
		try {
			temp = (E[])new Object[array.length*2];
			temp = Arrays.copyOf(array, temp.length);
		}
		catch(Exception E) {
			System.out.println(E.getMessage());
		}
		this.array = temp;
	}
	@Override
	public E popback() {
		E temp = at(size() - 1);
		this.erase(size() - 1);
		return temp;
	}

	@Override
	public void insert(int insertPosition, E element) {
		if(size == array.length)
			verifyCapacity(size+1);
		System.arraycopy(array, insertPosition, array, insertPosition + 1, size - insertPosition);
		size++;
		array[insertPosition] = element;
	}

	@Override
	public void erase(int position) {
		
		size--;
		System.arraycopy(array, position + 1, array, position, size - position);
	}

	@Override
	public void erase(int startRangePosition, int endRangePosition) {
		
		int change = endRangePosition - startRangePosition;
		if(change > 0)
		{
			System.arraycopy(array, endRangePosition, array, startRangePosition, size - endRangePosition);
			int save = size;
			size -= change;
			Arrays.fill(array, size, save, null);
		}
		else if(change < 0)
			throw new IndexOutOfBoundsException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void swap(Vector<E> other) {
		
		E[] tempVar = array;
		this.clear();
		this.resize(other.size());
		for(E e : other)
			this.pushback(e);
		other.clear();
		for(E e : tempVar)
			other.pushback(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void shrinkToFit() {
		
		E[] temp = null;
		try {
			temp = (E[])new Object[size];
			temp = Arrays.copyOf(array, temp.length);
		}
		catch(Exception E) {
			System.out.println(E.getMessage());
		}
		this.array = temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void resize(int newSize) {
		
		E[] temp = null;
		try {
			temp = (E[])new Object[newSize];
			temp = Arrays.copyOf(array, temp.length);
		}
		catch(Exception E) {
			System.out.println(E.getMessage());
		}
		this.array = temp;
	}

	@Override
	public Iterator<E> begin() {
		
		return new VectorIteratorHelper();
	}

	@Override
	public Iterator<E> iterator() {
		
		return new VectorIteratorHelper();
	}

	@Override
	public E at(int index) {
		
		try
		{
			return array[index];
		}
		catch(Exception E)
		{
			throw new IllegalArgumentException();
		}
	}

	class VectorIteratorHelper<E> implements Iterator<E> {
		private int counter;
		@SuppressWarnings("unchecked")
		private E[] list = (E[]) array;

		@Override
		public boolean hasNext() {
			return counter < size;
		}

		@Override
		public E next() {
			if (hasNext()) {
				return list [counter++];
			}
			return null;
		}
	}

	

}


