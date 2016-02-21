import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		int[][] a=new int[10][10];
		int[][] f=new int[10][10];
		while(scan.hasNextInt()){
			for(int i=1;i<=8;i++)
				for(int j=1;j<=8;j++)
					a[i][j]=scan.nextInt();
			for(int i=1;i<=8;i++)
				for(int j=1;j<=8;j++)
					if (f[i-1][j]>f[i][j-1])
						f[i][j]=f[i-1][j]+a[i][j];
					else
						f[i][j]=f[i][j-1]+a[i][j];
			System.out.println(f[8][8]);
		}
	}
}
