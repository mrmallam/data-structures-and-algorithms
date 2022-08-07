public class Fib3 {
	static long fib3(Integer n) {
		int i = 1;
		int j = 0;
		int k = 0;
		int h = 1;
		int t;
		
		while(n > 0) {
			if(n%2 == 0) {
				t = j*h;
				j = i*h + j*k + t;
				i = i*k + t;
			}
			t = h*h;
			h = 2*k*h + t;
			k = k*k + t;
			n = n / 2;
		}
		return j;
	}
}