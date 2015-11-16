/**
 * Nov 15, 2015 3:35:37 PM
 * PrjName: isPrime
 * @semprathlon
 */
package isPrime;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		while(sc.hasNextInt()){
			int n=sc.nextInt();
			for(int i=2;i*i<n;i++)
				if (n%i==0)
					System.out.print(i+",");
			System.out.println();
		}
	}

}
