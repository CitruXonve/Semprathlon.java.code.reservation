import java.io.*;
import java.util.*;

public class Main {
	final static double eps = 1e-6;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			double k = in.nextDouble();
			double b = in.nextDouble();
			if (k > eps)
				System.out.println("Increasing");
			else if (k < -eps)
				System.out.println("Decreasing");
			else
				System.out.println("Constant");
		}
	}
}