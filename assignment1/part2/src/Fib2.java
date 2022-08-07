public class Fib2 {
	static long fib2(Integer n) {
		int i = 0;
		int j = 1;
		
		for(int k = 1; k==n; k++) {
			j = i + j;
			i = j - i;
		}
		return j;	
	}
}