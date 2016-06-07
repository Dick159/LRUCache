package LRUCache;

public class Test {

	public static void main( String[] args ) {
		LRUCache<Integer, String, Object> lruCache = new LRUCache<Integer, String, Object>();
		Node<String, Object> node = new Node<String, Object>( "Key1", "Value1" );
		lruCache.put( 1, node );
		System.out.println( lruCache.get( 1 ).value );
	}
}
