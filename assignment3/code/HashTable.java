
import java.util.LinkedList;

/**
 * 
 * @author Mohammed Allam
 * @author UCID: 
 *
 */

public class HashTable
{
	private LinkedList<Student> arr[];
	public static final int LEN = 8;
	/**
	 * Constructor method initializes array of type LinkedList with length LEN
	 * Each element of the array is initialized to a LinkedList of type Student
	 */
	public HashTable(){
		arr = new LinkedList[LEN];
		for (int i = 0; i < LEN; i++){
			arr[i] = new LinkedList<Student>();
		}
	}
	
	/**
	 * This function returns the sum of the ASCII values of s modulo LEN.
	 * 
	 * @param s is a string composed only of alphanumeric characters.
	 */
	public int hashValue(String s){

		int s_ascii_value = 0;
		char character;
		// loop through all character of the string
		for(int i =0; i<s.length(); i++) {
			character = s.charAt(i);
			s_ascii_value += character;
		}

		return s_ascii_value % LEN;
	}
	
	/**
	 * This function returns true if 'name' is in the hash table, false otherwise.
	 * 
	 * @param 'name' is a string composed of alphanumeric characters
	 */
	public boolean search(String name){
		// get the ASCII value
		int index = hashValue(name);

		for(int i = 0; i<arr[index].size(); i++) {
			if(arr[index].get(i).getName() == name) {
				return true;
			}
		}
		return false;
	}
		
	/**
	 * This function inserts a new name with age to the hash table.
	 * If name already exists, update with new age.
	 * 
	 * @param 'name' is a string composed of alphanumeric characters.
	 * @param 'age' is a positive integer.
	 */
	public void insert(String name, int age){
		// get the ASCII value
		int index = hashValue(name);

		LinkedList<Student> current_node_ptr;
		current_node_ptr = arr[index];
		
		// check if name exists
		int i = 0;
		while(i<current_node_ptr.size()) {
			if(current_node_ptr.get(i).getName() == name) {
				
				int old_age = current_node_ptr.get(i).getAge();
				
				current_node_ptr.get(i).setAge(age);
				
				System.out.println("Changing age of " + current_node_ptr.get(i).getName() + " from " + old_age  + " to "+ current_node_ptr.get(i).getAge());
				return;	
			}	
			i++;
		}
		// else, create new name with age 
		Student newStudent = new Student(name, age);
		current_node_ptr.addFirst(newStudent);
		System.out.println("Inserting student "+newStudent.getName()+", age "+newStudent.getAge());
	}
	
	/**
	 * This function deletes a name (student) from the hash table, 
	 * if (student) does not exist; print a message indicating that
	 * 
	 * @param 'name' is a string composed of alphanumeric characters.
	 */
	public void delete(String name){
		// get the ASCII value
		int index = hashValue(name);
		
		LinkedList<Student> current_node_ptr;
		
		current_node_ptr = arr[index];
		
		for(int i =0; i<current_node_ptr.size(); i++) {
			if(arr[index].get(i).getName() == name) {
				
				System.out.println("Removing student "+name+", age "+arr[index].get(i).getAge());
				arr[index].remove(i);
				
				return;
			}
		}
		System.out.println("No Student of this name was found in the hash table");
	}
	
	/**
	 * This function returns age of a Student,
	 * if not found, print a message indicating that and return -1
	 * 
	 * @param 'name' is a string composed of alphanumeric characters.
	 */
	public int getAge(String name){
		// get the ASCII value
		int index = hashValue(name);
		
		LinkedList<Student> current_node_ptr;
		
		current_node_ptr = arr[index];
		
		for(int i =0; i<arr[index].size(); i++) {
			if(arr[index].get(i).getName() == name) {
				return arr[index].get(i).getAge();
			}
		}
		System.out.println("No student of this name was found in the hash table");
		return -1;
	}
	
	/**
	 * This function increments age of name Student by 1, and printing a message indicating that.
	 * if name was not found, print a message indicating that.
	 * 
	 * @param 'name' is a string composed of alphanumeric characters.
	 */
	public void increment(String name){
		// get the ASCII value
		int index = hashValue(name);
			
		LinkedList<Student> current_node_ptr;
		
		current_node_ptr = arr[index];
		
		for(int i =0; i<arr[index].size(); i++) {
			if(arr[index].get(i).getName() == name) {

				System.out.print("Changing age of "+arr[index].get(i).getName()+" from "+arr[index].get(i).getAge());
				arr[index].get(i).setAge(arr[index].get(i).getAge()+1);
				System.out.println(" to "+arr[index].get(i).getAge());
				return;
			}
		}
		System.out.println("No student of this name was found in the hash table.");
	}
	
	/**
	 * This function returns a string that contains list of hash table, one line per entry of array
	 */
	public String toString(){
		// i'm using a string buffer to easily manipulate the string 
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i<LEN; i++) {

			sb.append(i + ": ");
			
			if(arr[i].size() == 0) {

				sb.append("[]\n");
			}
			else {

				sb.append("[");
				
				int k;
				
				// 'step' is just for avoiding adding a "," at the very end
				String step = "";
				
				for(k = 0; k<arr[i].size(); k++) {

					sb.append(step + arr[i].get(k).toString());
		
					step = ", ";
				}

				sb.append("]\n");
			}
		}
		return sb.toString();
	}
}
