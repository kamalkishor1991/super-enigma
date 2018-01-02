package lib.lib.graph;

import java.util.*;

/**
 *
 * @param <E> Set of Object type E.
 */
public class DisjointSet<E> {
	private Map<E, Node> hm;
	public DisjointSet() {
		hm = new HashMap<E, Node>();
	}
	public void makeSet(E a) {
		Node<E> n = new Node<E>(a);
		n.p = n;
		hm.put(a, n);
	}

	/**
	 */
	public boolean isSameSet(E e1, E e2) {
		return find(e1).equals(find(e2));
	}

	public void union(E x, E y) {
		Node xn = hm.get(find(x));
		Node yn = hm.get(find(y));
		if(xn.rank < yn.rank) {
			xn.p = yn;   //parent of smaller height tree will larger height tree.
		} else if(xn.rank > yn.rank) {
			yn.p = xn;
		} else {
			yn.p = xn;
			xn.rank += 1;
		}
	}

	public boolean contains(E x) {
		return hm.containsKey(x);
	}

	/**
	 * O(log(n)) performance for find
	 */
	public E find(E x) {
		Node<E> nn = hm.get(x);
		if(nn.p == nn) return x;
		else {
			Node<E> r = nn.p;
			r.p = hm.get(find(r.n));  //improvement for next find
			return r.p.n;
		}
	}

	/**
	 * Complexity O(n*log(n))
	 * @return List of sets.
	 */
	public List<Set<E>> getAllSets() {
		Set<E> all = hm.keySet();
		HashMap<E, Set<E>> map = new HashMap<E, Set<E>>();
		for(E e : all) {
			E te = find(e);
			if(map.containsKey(te)) {
				map.get(te).add(e);
			} else {
				HashSet t = new HashSet<E>();
				t.add(te);
				map.put(te, t);
			}
		}
		List<Set<E>> r = new ArrayList<Set<E>>();
		Set<E> tt = map.keySet();
		for (E e : tt) {
			r.add(map.get(e));
		}
		return r;
	}

	private class Node<T> {
		T n;
		int rank = 0;
		Node<T> p;
		Node(T n) {this.n = n;}
	}
}
