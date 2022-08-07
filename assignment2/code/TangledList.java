package Assignment2;
import java.util.ArrayList;
import Assignment2.MyLinkedListInterface.MyLinkedListNode;

/**
 * 
 * @author Mohammed Allam
 * @author UCID: 30106564
 *
 */

class TangledList {
	
    public static void removeSharedLinkedListNodes(MyLinkedList<String> list0, MyLinkedList<String> list1) {
    	
    	// get list0 and list1 length
    	int list0_length = list0.length();
    	int list1_length = list1.length();
    	
    	// Ensuring list0 is the longer list, if it is not swap them
    	if(list1_length > list0_length) {
    		MyLinkedList<String> tempList = list0;
    		list0 = list1;
    		list1 = tempList;
    	}
    	
    	// computing the length difference
    	int length_difference = list0_length - list1_length;
    	
    	// Initialize two pointer nodes and set them to the heads of each list.
    	MyLinkedListNode<String> ptr_list0 = new MyLinkedListNode<String>();
    	MyLinkedListNode<String> ptr_list1 = new MyLinkedListNode<String>();
    	ptr_list0 = list0.getHeadNode();
    	ptr_list1 = list1.getHeadNode();
    	
    	// Advance the node from the longer list (list0) 
    	//  by a number of nodes equal to the difference 
    	//  in lengths of the two lists.
    	for(int i = 0; i<length_difference; i++) {
    		ptr_list0 = ptr_list0.getNext();
    	}
    	
    	// At this point both nodes are the same number of steps from the end of both lists.
	
    	// This variable keeps track of the number of steps it takes to reach the shared node.
    	int count_to_reach_shared_node = 0;
    	
    	// Advance both nodes along the lists checking equality.
    	while(ptr_list0.getValue() != ptr_list1.getValue() && ptr_list0.getNext()!=null) {
    		ptr_list0 = ptr_list0.getNext();
    		ptr_list1 = ptr_list1.getNext();
    		count_to_reach_shared_node++;
    	}

    	// If a shared node is found...
    	if(ptr_list0.getValue() == ptr_list1.getValue()) {

    		// reset the list1 iterating node back to the head of list1
    		ptr_list1 = list1.getHeadNode();
    		
    		// Iterate through the list for one less steps than the steps it took to reach the shared node. 
    		for(int i = 0; i<count_to_reach_shared_node-1; i++) {
    			ptr_list1 = ptr_list1.getNext();
    		}
    		// Set its next value to null to remove the subsequent nodes from this list.
    		ptr_list1.setNext(null);
    	}
    	
    	// else, if there are no shared nodes, then the nodes will both eventually be null.
    	else {
    		System.out.println("No shared nodes found.");
    	}	
    }

    public static int detectCycleAndPeriod(MyLinkedList<String> list0) {
    	
    	// Initialize the tortoise and hare to the head node
    	MyLinkedListNode<String> tortoise = new MyLinkedListNode<String>();
    	MyLinkedListNode<String> hare = new MyLinkedListNode<String>();
    	
    	// Advance the tortoise by 1 node, and the hare by 2 nodes
    	tortoise = list0.getHeadNode().getNext();
    	hare = list0.getHeadNode().getNext().getNext();
    	
    	// Keep checking if they are equal until the end of the list is reached (no loop) 
    	// or the two nodes are equal.
    	while(tortoise.getValue() != hare.getValue()) {
    		tortoise = tortoise.getNext();
    		hare = hare.getNext().getNext();
    	}
    	
    	// if they meet...
    	if(tortoise.getValue() == hare.getValue()) {
    		// Let the hare continue traversing the list (one node at a time this time) 
    		// until it returns to the tortoise’s position.
    		hare = hare.getNext();
    		
    		int count_period = 0;
    		while(hare.getValue() != tortoise.getValue()) {
    			hare = hare.getNext();
    			// Count the number of steps it this takes for both nodes to meet to count cycle period.
        		count_period++;
    		}
    		count_period++;
    		// return period
    		return count_period;
    		
    	}
    	// If there is no cycle then return –1 for the period.
    	else {
    		return -1;
    	}
    	
    }

    public static void removeCycle(MyLinkedList<String> list0, int period) {
    	
    	// Initialize the tortoise and hare to the head node
    	MyLinkedListNode<String> tortoise = new MyLinkedListNode<String>();
    	MyLinkedListNode<String> hare = new MyLinkedListNode<String>();
    	tortoise = list0.getHeadNode();
    	hare = list0.getHeadNode();
    	
    	// Advance the hare “period” nodes into the list.
    	for(int i = 0; i<period; i++) {
    		hare = hare.getNext();
    	}
    	
    	// Advance the hare and the tortoise at the same rate, checking for equality as you go.
    	while(hare.getValue() != tortoise.getValue()) {
    		hare = hare.getNext();
    		tortoise = tortoise.getNext();
    	}
    	
    	// at this point, the tortoise and hare meet at the first node in the cycle.
    	
    	// Advance the hare period-1 nodes, this is the last node in the cycle.
    	for(int i = 0; i<period-1; i++) {
    		hare = hare.getNext();
    	}
    	// Set the last node in the cycle’s next value to be null.
    	hare.setNext(null);
    }
    
    public static void removeLinkedListCycles(MyLinkedList<String> list0) {
        int period = detectCycleAndPeriod(list0);
        removeCycle(list0, period);
    }

    // Code to setup one test case for eliminating shared nodes from two linked lists
    public static void createAndTestSharedNode() {
        // Your assignment submission should have more specific error handling
    	 try{
    	        MyLinkedList<String> stage0 = new MyLinkedList<String>();
    	        stage0.appendToTail("Arkells");
    	        stage0.appendToTail("Bruno Mars");
    	        stage0.appendToTail("Coldplay");
    	        stage0.appendToTail("David Bowie");
    	        stage0.appendToTail("Earth, Wind & Fire");
    	        
    	        MyLinkedList<String> stage1 = new MyLinkedList<String>();
    	        stage1.appendToTail("Foo Fighters");
    	        stage0.appendToTail("Gorillaz");

    	        MyLinkedListNode<String> node;
    	        node = stage0.searchByValue("Coldplay");
    	        stage1.appendToTail(node);

    	        System.out.println("Shared Nodes: Stage 0 Lineup");
    	        System.out.println(stage0.toString());
    	        System.out.println("Shared Nodes: Stage 1 Lineup");
    	        System.out.println(stage1.toString());
    	        System.out.println("\n");

    	        removeSharedLinkedListNodes(stage0, stage1);
    	        
    	        System.out.println("Shared Nodes Fixed: Stage 0 Lineup");
    	        System.out.println(stage0.toString());
    	        System.out.println("Shared Nodes Fixed: Stage 1 Lineup");
    	        System.out.println(stage1.toString());
    	        System.out.println("\n");

    	        } catch (Exception e) {}
    }
    
    // Code to setup one test case for eliminating cycles from a linked list
    public static void createAndTestCycle() {
        // Your assignment submission should have more specific error handling
        try {
        MyLinkedList<String> stage0 = new MyLinkedList<String>();
        stage0.appendToTail("Arkells");
        stage0.appendToTail("Bruno Mars");
        stage0.appendToTail("Coldplay");
        stage0.appendToTail("David Bowie");
        stage0.appendToTail("Earth, Wind & Fire");
        stage0.appendToTail("Foo Fighters");
        stage0.appendToTail("Gorillaz");
        
        MyLinkedListNode<String> loopToNode;
        loopToNode = stage0.searchByValue("Coldplay");

        MyLinkedListNode<String> tail;
        tail = stage0.searchByValue("Gorillaz");
        if(tail != null)
            tail.setNext(loopToNode);


        System.out.println("Cyclic Nodes: Stage 0 Lineup");
        MyLinkedListNode<String> currentNode;
        currentNode = stage0.searchByValue("Arkells");

        // If we use the toString method it will never terminate. 
        // Most linked list operations on this list will not work, so be careful!
        for(int i = 0; i<10 & currentNode != null; i++) { 
            System.out.println("Element: " + currentNode.toString());
            currentNode = currentNode.getNext();
        }
        System.out.println("\n");

        removeLinkedListCycles(stage0);
        
        System.out.println(stage0.toString());
        
        System.out.println("\n");
        
        } catch (Exception e) {}
    }

    public static void myTestSharedNodes() {
    	
    	try {
        MyLinkedList<String> stage1 = new MyLinkedList<String>();
        MyLinkedList<String> stage0 = new MyLinkedList<String>();
        stage0.appendToTail("I");
        stage0.appendToTail("G");
        stage0.appendToTail("H");
        stage0.appendToTail("M");
        stage0.appendToTail("W");
        stage0.appendToTail("X");
        stage0.appendToTail("HELLO");
        stage0.appendToTail("J");
        stage0.appendToTail("K");
        
        stage1.appendToTail("A");
        stage1.appendToTail("B");
        stage1.appendToTail("C");
        stage1.appendToTail("HELLO");
        stage1.appendToTail("D");
        stage1.appendToTail("E");
        

        System.out.println("list0: ");
        System.out.println(stage0.toString());
        System.out.println("list1: ");
        System.out.println(stage1.toString());
       
        removeSharedLinkedListNodes(stage0, stage1);
        System.out.println();

        System.out.println("list0: ");
        System.out.println(stage0.toString());
        System.out.println("list1: ");
        System.out.println(stage1.toString());
        

    } catch (Exception e) {}

    }
    
    
    
public static void myTestCycle() {
    	
    	try {
    	MyLinkedList<String> stage0 = new MyLinkedList<String>();
        stage0.appendToTail("A");
        stage0.appendToTail("B");
        stage0.appendToTail("C");
        stage0.appendToTail("D");
        stage0.appendToTail("E");
        stage0.appendToTail("F");
        stage0.appendToTail("G");
        
        MyLinkedListNode<String> loopToNode;
        loopToNode = stage0.searchByValue("C");

        MyLinkedListNode<String> tail;
        tail = stage0.searchByValue("G");
        if(tail != null)
            tail.setNext(loopToNode);

        System.out.println("Cyclic Nodes: ");
        MyLinkedListNode<String> currentNode;
        currentNode = stage0.searchByValue("A");
        for(int i = 0; i<10 & currentNode != null; i++) { 
            System.out.println("Element: " + currentNode.toString());
            currentNode = currentNode.getNext();
        }
        
        removeLinkedListCycles(stage0);
        System.out.println();
        System.out.println(stage0.toString());

    	} catch (Exception e) {}

    }
    
    public static void main(String[] args) {
        //Feel free to set up different tests, your code should be general, and will be tested against other cases
//        createAndTestSharedNode();
//        createAndTestCycle();
//        myTestCycle();
//        myTestSharedNodes();
    }
}