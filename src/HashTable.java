
import java.util.ArrayList;
import java.util.List;

/***************************************
 * Written by: Simon Cicek             *
 * Last changed: 2012-05-04            *
 *                                     *
 * The class implementing a hashtable. *
 ***************************************/

public class HashTable<K,V> 
{
    // Inner class representing a node in the table
    private class Node<K,V>
    {
        K key;
        V value;
        Node<K,V> next;
        
        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
            next = null;
        }
    }
    
    // Variables
    private static final int DEFAULT_CAPACITY = 100;
    private static final double enlargenValue = 1.50;
    private static int threshold = (int)(DEFAULT_CAPACITY * 0.75);
    
    int size = 0;
    Node[] elements;
    
    public HashTable()
    {
        elements = new Node[DEFAULT_CAPACITY];
    }
    
    public HashTable(int capacity)
    {
        elements = new Node[capacity];
        threshold = (int)(capacity * 0.75);
    }
    
    public void add(K key, V value)
    {
        if(elements[threshold] != null)
            this.enlargen();
        
        Node n = nodeOf(key,null);
        if(n != null)
        {
            ((ArrayList) n.value).add(value);
            return;
        }
        
        int index = key.hashCode() % elements.length;
        if(index < 0 || index >= elements.length)
                return;           
        
        if(elements[index] == null)
            elements[index] = new Node(key,value);
        else
        {
            Node pre = elements[index];
            Node current = elements[index].next;
            while(current != null)
            {
                pre = current;
                current = current.next;
            }
            pre.next = new Node(key,value);
        }           
        size++;
    }
    
    public V get(K key)
    {        
        return (nodeOf(key,null) == null) ? null : (V) nodeOf(key,null).value;
    }
    
    public K getKey(V value)
    {        
        return (nodeOf(null,value) == null) ? null : (K) nodeOf(null,value).key;
    }
    
    private Node nodeOf(K key, V value)
    {
        if(key != null)
        {
            int index = key.hashCode() % elements.length;
            if(index < 0 || index >= elements.length)
                return null;
            Node current = elements[index];
            while(current != null && !current.key.equals(key))
                current = current.next;
            return current;
        }
        else
        {
            Node current = null;
            for(int i = 0; i < elements.length;i++)
            {
                current = elements[i];
                while(current != null && !current.value.equals(value))
                    current = current.next;
                if(current != null && current.value.equals(value))
                    break;
            }
            return current;
        }
    }
    
    public void remove(K key)
    {
        int index = key.hashCode() % elements.length;
        Node parent = elements[index];
        if(parent.key.equals(key))
        {
            elements[index] = null;
            size--;
            return;
        }
        if(parent.next == null)
            return;
        Node current = parent.next;
        while(current.next != null)
        {
            parent = current;
            current = current.next;
            
            if(current.key.equals(key))
                break;
        }
        
        if(current != null)
        {
            parent.next = current.next;
            size--;
        }
    }
    
    public boolean contains(K key)
    {
        return nodeOf(key,null) != null;
    }
    
    public void set(K key, V value)
    {
        Node n = nodeOf(key,null);
        if(n == null)
            return;
        n.value = value;
    }
    
    private void enlargen() 
    {
	int oldSize = elements.length;
	Node[] oldElementsArray = elements;

	int newSize = (int) (oldSize * enlargenValue + 1);
	Node[] newElementsArray = new Node[newSize];

	threshold = (int)(newSize * 0.75);
	elements = newElementsArray;
        
        for (Node current : oldElementsArray) 
        {
            if(current != null)
            {
		int index = current.key.hashCode() % newSize;
		newElementsArray[index] = current;
            }
        }
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public int size()
    {
        return size;
    }
    
    public void clear()
    {
        int esize = elements.length;
        elements = new Node[esize];
        size = 0;
    }
    
    @Override
    public String toString() 
    {
	StringBuffer buf = new StringBuffer();

        Node current;
	for (int i = 0; i < elements.length; i++) 
        {
            if(elements[i] != null)
            {
                current = elements[i];
                if(current != null)
                {
                    while(current != null)
                    {
                        buf.append("(Key: " + current.key + ", Value: " + current.value + ")");
                        if(current.next != null)
                            buf.append(", ");
                        current = current.next;
                    }
                    buf.append("\n");
                }
            }
	}

	return buf.toString();
    }
    
    public List keyView()
    {
        ArrayList keys = new ArrayList();
     
        Node current;
	for (int i = 0; i < elements.length; i++) 
        {
            if(elements[i] != null)
            {
                current = elements[i];
                if(current != null)
                {
                    while(current != null)
                    {
                        keys.add(current.key);
                        current = current.next;
                    }
                }
            }
        }
        return keys;
    }
    
    public List valueView()
    {
        ArrayList values = new ArrayList();
     
        Node current;
	for (int i = 0; i < elements.length; i++) 
        {
            if(elements[i] != null)
            {
                current = elements[i];
                if(current != null)
                {
                    while(current != null)
                    {
                        values.add(current.value);
                        current = current.next;
                    }
                }
            }
        }
        return values;
    }
}
