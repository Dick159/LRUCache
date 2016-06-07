package LRUCache;

import java.util.HashMap;

/*
 * Author: Hdw-dick159
 * 
 * use doubly linkedList and HashMap implement
 */
public class LRUCache<INDEX, K, V> {

	int capacity = 100;

	Node<K, V> head = null;

	Node<K, V> end = null;

	private HashMap<INDEX, Node<K, V>> hashMap;

	public LRUCache( int capacity ) {
		this.capacity = capacity;
		hashMap = new HashMap<INDEX, Node<K, V>>( capacity );
	}

	public LRUCache() {
		hashMap = new HashMap<INDEX, Node<K, V>>( this.capacity );
	}

	public Node<K,V> get( INDEX index ) {
		if( hashMap.get( index ) != null ) {
			Node<K, V> node = hashMap.get( index );
			remove( node );
			return node;
		}
		return null;
	}

	private void remove( Node<K, V> node ) {
		// maintain preNode
		if( node.pre != null ) {
			node.pre.next = node.next;
		} else {
			// if this node is head of the list
			head = node.next;
		}
		// maintain nextNode;
		if( node.next != null ) {
			node.next.pre = node.pre;
		} else {
			end = node.pre;
		}
	}

	private void setHead( Node<K, V> node ) {
		node.next = head;
		node.pre = null;
		if( head != null ) {
			head.pre = node;
		}
		head = node;
		if( end == null ) {
			end = head;
		}
	}

	public void put( INDEX index, Node<K, V> node ) {
		if( hashMap.containsKey( index ) ) {
			Node<K, V> n = hashMap.get( index );
			remove( n );
			setHead( n );
		} else {
			// remove the last element when container is fulled
			if( hashMap.size() >= capacity ) {
				hashMap.remove( end );
				remove( end );
				setHead( node );
			} else {
				setHead( node );
			}

			hashMap.put( index, node );
		}
	}
}

class Node<KEY, VALUE> {

	public Node<KEY, VALUE> pre;

	public Node<KEY, VALUE> next;

	public KEY key;

	public VALUE value;

	public Node( KEY key, VALUE value ) {
		this.key = key;
		this.value = value;
	}
}
