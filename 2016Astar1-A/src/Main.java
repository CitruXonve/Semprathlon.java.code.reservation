/**
 * May 14, 2016 4:18:02 PM
 * PrjName: 2016Astar1-A
 * @semprathlon
 */
//import java.io.*;
import java.util.*;
public class Main {
	final static int mod=9973;
	final static int maxn=10010;
	static int[] inv;//=new int[maxn];
	static int[] mul;//=new int[100010];
	static String s;
	static void cal_inv(int maxn, int mod) {
        inv[1] = 1;
        for (int i = 2; i < maxn; i++)
            inv[i] = (mod - mod / i) * inv[mod % i] % mod;
	}
	static int div_mod(int n, int m, int mod) {
        return n * inv[m] % mod;
	}
	public static void main(String[] args)/* throws IOException*/{
		//InputReader in=new InputReader(System.in);
		Scanner in=new Scanner(System.in);
		//PrintWriter out=new PrintWriter(System.out);
//		cal_inv(maxn, mod);
		while(in.hasNext()){
			int n=in.nextInt();
//			s=new String(in.next());
			s=in.next();
			int len=s.length();
//			mul[0]=1;
//			for(int i=0;i<len;i++)
//				mul[i+1]=mul[i]*(s.charAt(i)-28)%mod;
			while(n-->0){
				int a=in.nextInt();
				int b=in.nextInt();
				if (a>b){
					int t=a;a=b;b=t;
				}
//				out.println(div_mod(mul[b], mul[a-1], mod));
			}
		}
		//out.flush();
		//out.close();
	}

}