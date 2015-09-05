/** Aug 31, 2015 7:13:53 PM
 * PrjName:cf100738-A
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static boolean check(int _a1,int _b1,int _a2,int _b2){
		
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		in.nextToken();
		int a1=(int)in.nval;
		in.nextToken();
		int b1=(int)in.nval;
		in.nextToken();
		int a2=(int)in.nval;
		in.nextToken();
		int b2=(int)in.nval;
		if (a1<b1){
			int t=a1;a1=b1;b1=t;
		}
		if (a2<b2){
			int t=a2;a2=b2;b2=t;
		}
	}

}
