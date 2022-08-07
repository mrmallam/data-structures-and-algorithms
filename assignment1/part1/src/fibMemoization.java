import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammed Allam
 * @author UCID: 
 *
 * This class uses the memoization procedure to calculate Fibonacci Values. 
 * It calculates Fibonacci Values and stores them in a Map data structure to avoid 
 * recalculating previously calculated Fib values. This procedure enhances 
 * the algorithm's efficiency which speeds up the execution of the program. 
 */
public class fibMemoization {
	static long FibValue;

	public static void main(String[] args) {
		int n = 7;
		Map<Integer, Long> calculatedVals = new HashMap<Integer, Long>();
		
		System.out.println(fibMemoization(n, calculatedVals));
	}
	
	/**
	 * Calculates Fibonacci of N using memoization
	 * 
	 * @param N a nonnegative integer that you want to find the Fib value of.
	 * @param calculatedVals a Map data structure to store calculated Fib values 
	 * @return Fibonacci value of N
	 */
	public static long fibMemoization(int N, Map<Integer, Long> calculatedVals) {
		if (N <= 1) {
			return N;
		}
		
		// Check in Map for previously calculated Fib N values,
		// if found, return that value.
		if (calculatedVals.containsKey(N) == true) {
			return calculatedVals.get(N);
		}

		// If not calculated already, calculate Fib value and store it in Map 
		FibValue = fibMemoization(N - 1, calculatedVals) + fibMemoization(N - 2, calculatedVals);
		calculatedVals.put(N, FibValue);
		
		return FibValue;
	}

}
