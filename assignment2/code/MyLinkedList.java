package Assignment2;

/**
 * 
 * @author Mohammed Allam
 * @author UCID: 30106564
 *
 */

public class MyLinkedList<ElementType> implements MyLinkedListInterface<ElementType> {
    
    private MyLinkedListNode<ElementType> head;
    
	/**
	 * MyLinkedList constructor
	 */
    public MyLinkedList(){
    	 head = null;
    }
    
    
	/**
	 * Precondition: this is a linked list
	 * Postcondition: The head node is returned, if the list is empty null is returned instead 
	 */
    public MyLinkedListNode<ElementType> getHeadNode() {
    	// Check if the list is empty, if so return null
    	if(head == null) {
    		return null;
    	}
    	// else, return the head node
    	return head;
    }

    
	/**
	 * Precondition: this is a linked list, and value is a variable with appropriate type for this list, which is not already present in the list
	 * Postcondition: The tail node of this list is a new node with node.value = value, the length of the list is the previous length + 1, 
	 * 	no other nodes have been changed. If a node with the given value already exists, an exception is generated.
	 * 
	 * @param value is a variable with appropriate type for this list.
	 * 
	 */
    public void appendToTail(ElementType value) throws LinkedListValueExistsException {
    	// if the list is empty, create a new node as the first node in the list
    	// and assign Value to it.
    	if(head==null) {
    		MyLinkedListNode<ElementType> newNode = new MyLinkedListNode<ElementType>();
        	newNode.setValue(value);
        	head = newNode;
    	}
    	
    	else {
    	// A temporary node for traversing through the list
    	MyLinkedListNode<ElementType> currentNode = head;
    	
    	// traverse to the end of the list while checking if value already exists,
    	// if it does throw an exception.
    	while(currentNode.getNext() != null) {
    		currentNode = currentNode.getNext();
    		
    		if(currentNode.getValue() == value) {
    			throw new LinkedListValueExistsException("Value already exist");
    		}
    	}
    	// at this point currentNode is at the last node in the list.
    	
    	// create a new node and assign Value to it. And set it as tail
    	MyLinkedListNode<ElementType> newNode = new MyLinkedListNode<ElementType>();
    	newNode.setValue(value);
    	currentNode.setNext(newNode);
    	
    	}
    }
    	

	/**
	 * Precondition: this is a linked list, a node with appropriate type for this list such 
	 *   that node.value is not already present in the list.
	 * Postcondition: The tail node of this list is node, the length of the list is the previous
     *   length + 1, no other nodes have been changed.
     *   
	 * @param node; such that it is an appropriate type for this list
	 * 
	 */
    public void appendToTail(MyLinkedListNode<ElementType> node) throws LinkedListValueExistsException {
    	// check if list is empty, if it is, then make Node as the first node in the list
    	if(head==null) {
    		head=node;
    	}
    	else {
    		// A temporary node for traversing through the list
    		MyLinkedListNode<ElementType> currentNode = head;
    		
    		// traverse through the list to reach the end of the list
    		// while checking if node.value is present in the list, if it is,
    		// throw an exception.
        	while(currentNode.getNext()!=null) {
        		
        		if(currentNode.getValue() == node.getValue()) {
        			throw new LinkedListValueExistsException("Node value already exist");
        		}
        		
        		currentNode = currentNode.getNext();
        	}
        	// append Node to tail
        	currentNode.setNext(node);		
    	}
    }
    
    
	/**
	 * Precondition: this is a linked list
	 * Postcondition: The number of nodes in this list is returned, 0 is returned if the list is empty
	 */
    public int length() {
    	
    	// check if list is empty, if it is, return 0
    	if(head==null) {
    		return 0;
    	}
    	
    	// A temporary node for traversing through the list
    	MyLinkedListNode<ElementType> current = head;
    	
    	// traverse though the list and increment counter
    	int count = 0;
    	while(current!=null) {
    		current = current.getNext();
    		count++;
    	}
    	// counter == number of nodes
    	return count;
    }
    
    
	/**
	 * Precondition: this is a linked list and a value is a variable of appropriate type for the list.
	 * Postcondition: If a node with node.value = value is present in the list it is re- moved, the order of elements in the list is otherwise unchanged. 
	 * 	If no node with node.value = value is present in the list, the list is unchanged. 
	 * 	If the list is empty an exception is generated.
	 * 
	 * @param value is a variable with appropriate type for this list.
	 * 
	 */
    public void deleteByValue(ElementType value) throws LinkedListListEmptyException {

    	// check if the list is empty, if it is, throw an exception
    	if(head==null) {
    		throw new LinkedListListEmptyException("LinkList is empty");
    	}
    	// create two nodes, current node and previous node
    	MyLinkedListNode<ElementType> currentNode = head;
    	MyLinkedListNode<ElementType> previousNode = null;
    	
    	// if the first node.value == value; then make the second node as first node 
    	if(currentNode.getValue() == value) {
    		head = head.getNext();
    	}
    	// else, traverse through the list while checking for value
    	// if no value is found, simply pass
    	else {
	    	while(currentNode != null) {
	    		
	    		// if value is found, create a new node, set currentNode to the following node
	    		// and update the previousNode
	    		if(currentNode.getValue() == value) {
	    			MyLinkedListNode<ElementType> removeNode = currentNode;
	    			
	    			previousNode.setNext(removeNode.getNext()); 
	    			currentNode = currentNode.getNext();
	    			
	    			removeNode.setValue(null);
	    			removeNode.setNext(null);
	    			
	    		}
	    		// else, update previousNode and currentNode
	    		else {
	    			previousNode = currentNode;
	    			currentNode = currentNode.getNext();
	    			}
	    		}
    		}
    	}
    	
    	
    
	/**
	 * Precondition: this is a linked list and a value is a variable of appropriate type for the list.
	 * Postcondition: If a node with node.value = value is present in the list, it is returned. If no node with node.value = value is present in the list, null is returned.
	 *  If the list is empty an exception is generated. 
	 *  
	 * @param value is a variable with appropriate type for this list.
	 * 
	 */
    public MyLinkedListNode<ElementType> searchByValue(ElementType value) throws LinkedListListEmptyException {
    
    	// if list is empty, throw an exception
    	if(head==null) {
    		throw new LinkedListListEmptyException("Your LinkedList is EMPTY!.");
    	}
    	
    	// else, traverse through the list while checking for Value,
    	else {
    		// A temporary node for traversing through the list
	    	MyLinkedListNode<ElementType> currentNode = head;
	    	
	    	while(currentNode!=null){
	    		
	        	// if Value is found, return the node that contains Value 
	    		if(currentNode.getValue() == value) {
		    		return currentNode;	
		    	}
	    		currentNode = currentNode.getNext();
	    	}	
    	}
    	// else, return null
    	return null;
    }   
    
	/**
	 * Precondition: this is a linked list.
	 * Precondition: A string representing this list is returned, the string should contain all of values present in the list in the same order that they are present in the list.
	 * 
	 */
    public String toString() {
    	
    	// check if list is empty, if it is, return ""
    	if(head==null) {
    		return "";
    	}
    	
    	// else, return the elements of the list is a specific format
    	else {
    		// A temporary node for traversing through the list
    		MyLinkedListNode<ElementType> currentNode = head;
  
    		String printStr = null;
        	StringBuffer sb = new StringBuffer();
        	
    		printStr = "value " + currentNode.getValue();
    		sb.append(printStr);
    		
    		while(currentNode.getNext() != null) {
    			currentNode = currentNode.getNext();
        		printStr =  " -> " + "value " + currentNode.getValue() ;
        		sb.append(printStr);
        	}

            return sb.toString();
    	}
    }
    
}
