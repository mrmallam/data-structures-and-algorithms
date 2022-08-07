import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
/**
 * 
 * @author Mohammed Allam
 * @author UCID: 
 *
 */
public class Part2 {
	public static void main(String[] args){
		
		// arbitrary values for testing
		int[] testValues = {2, 5, 7, 12, 14, 17, 20, 25};
		
		try {
			analyzeAndOutput(testValues);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Measures the running time in nanoseconds for algorithms 1, 2, and 3 for different input values,
	 *  and it writes the output to a file (output.txt),
	 *  and it plots the result on a graph using javaFX. 
	 * 
	 * @param testValues is an array containing arbitrary values for input testing 
	 * 
	 */
	public static void analyzeAndOutput(int[] testValues) throws IOException {
		long start, end, timeElapsed;
		String fileContent;
		
		// initializing linechart, 
		fibonacciChart lineChart = new fibonacciChart();
		
		// initializing series 1,2,3
		XYChart.Series series1 = new XYChart.Series<Integer, Long>();
		XYChart.Series series2 = new XYChart.Series<Integer, Long>();
		XYChart.Series series3 = new XYChart.Series<Integer, Long>();
		
		// generating a file
		BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
		
		/* for each of the following for-loops:
			- start timer
			- Call the method and pass the i'th value in testValues 
			- stop timer 
			- calculate difference in time: end - start 
			- write to output.txt
			- add the data to series# for the graph
		*/
		for(int i = 0; i<testValues.length; i++) {
			start =  System.nanoTime();
			Fib1.fib1(testValues[i]);
			end = System.nanoTime();
			timeElapsed = end - start;
			fileContent = String.format("\nFib1 computes F%d in %d", testValues[i], timeElapsed);
			writer.write(fileContent);
			series1.getData().add(new XYChart.Data<Integer, Long>(testValues[i], timeElapsed));
		}
		
		writer.write("\n"); // write a blank line
		
		for(int i = 0; i<testValues.length; i++) {
			start =  System.nanoTime();
			Fib2.fib2(testValues[i]);
			end = System.nanoTime();
			timeElapsed = end - start;
			fileContent = String.format("\nFib2 computes F%d in %d", testValues[i], timeElapsed);
			writer.write(fileContent);
			series2.getData().add(new XYChart.Data<Integer, Long>(testValues[i], timeElapsed));
		}
		
		writer.write("\n"); // write a blank line
		
		for(int i = 0; i<testValues.length; i++) {
			start =  System.nanoTime();
			Fib3.fib3(testValues[i]);
			end = System.nanoTime();
			timeElapsed = end - start;
			fileContent = String.format("\nFib3 computes F%d in %d", testValues[i], timeElapsed);
			writer.write(fileContent);
			series3.getData().add(new XYChart.Data<Integer, Long>(testValues[i], timeElapsed));
		}
		
		writer.close();
		
		lineChart.setVals(series1, series2, series3);
		fibonacciChart.launch(lineChart.getClass());
		
	}
}