package shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import simulation.State;
import util.Factory;

public abstract class Lattice<T> implements State{
	
	private final int w;
	private final int h;
	private T[][] data;
	
	@SuppressWarnings("unchecked")
	public Lattice(final int width, final int height, Factory<T> factory){
		w=Math.abs(width);
		h=Math.abs(height);
		data=(T[][]) new Object[w][h];
		for (int i=0;i<w;i++){
			for (int j=0;j<h;j++){
				data[i][j]=factory.create();
			}
		}
	}
	
	public T get(int x, int y){
		return data[Math.abs(x)%w][Math.abs(y)%h];
	}
	
	public Iterable<T> all(){
		return new Iterable<T>(){
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>(){
					int n=-1;
					int m=w*h;
					@Override
					public boolean hasNext() {
						return n<m-1;
					}
					@Override
					public T next() {
						n++;
						return data[n%w][n/w];
					}
					@Override
					public void remove() {
					}
				};
			}
		};
	}
	
	public Iterable<T> adjacents(int x, int y){
		List<T> a = new ArrayList<T>(4);
		a.add(get((x+1)%w, y%h));
		a.add(get(x%w, (y+1)%h));
		a.add(get((x-1)%w, y%h));
		a.add(get(x%w, (y-1)%h));
		return a;
	}
	
	public Iterable<Pair<T, T>> adjacentPairs(){
		return new Iterable<Pair<T, T>>(){
			@Override
			public Iterator<Pair<T, T>> iterator() {
				return new Iterator<Pair<T, T>>(){
					int n=-1;
					int m=w*h*2;
					@Override
					public boolean hasNext() {
						return n<m-1;
					}
					@Override
					public Pair<T, T> next() {
						n++;
						int x=(n/2)%w;
						int y=(n/2)/w;
						if (n%2==0){
							return new Pair<T, T>(get(x, y), get(x, y+1));
						}else{
							return new Pair<T, T>(get(x, y), get(x+1, y));
						}
						//return new Pair<T, T>(get(x, y), get(x+n&1, y+~(n&1)));
					}
					@Override
					public void remove() {
					}
				};
			}
		};
	}
	
	public int getSize(){
		return w*h;
	}
	
	public int getWidth(){
		return w;
	}
	
	public int getHeight(){
		return h;
	}
}
