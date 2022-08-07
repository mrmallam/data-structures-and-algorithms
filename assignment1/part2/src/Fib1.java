public class Fib1 {
	public static long fib1(Integer n) {
		if(n<2) {
			return n;
		}
		else return fib1(n-1) + fib1(n-2);
	}

}