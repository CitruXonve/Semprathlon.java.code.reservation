/**
 * Apr 7, 2016 7:52:45 PM
 * PrjName: xtu1236
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static double eps = 1e-5;
	static int dcmp(double d) {
	        if (Math.abs(d) < eps)
	            return 0;
	        return d > 0 ? 1 : -1;
	}
	static int bisearch(int l,int r,double v,double m){
		while(l<r){
			int mid=(l+r)>>1;
			if (dcmp(mid/m-v)==0) return mid;
			else if (dcmp(mid/m-v)>0) r=mid-1;
			else l=mid+1;
		}
		return l;
	}
	static int kgcd(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        if ((a & 1) == 0 && (b & 1) == 0)
            return kgcd(a >> 1, b >> 1) << 1;
        else if ((b & 1) == 0)
            return kgcd(a, b >> 1);
        else if ((a & 1) == 0)
            return kgcd(a >> 1, b);
        else
            return kgcd(Math.abs(a - b), Math.min(a, b));
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			double x=in.nextDouble();
			int d1=bisearch(1, 1000, x,1000);
			int m1=1000;
			int d2=bisearch(1, 1000, x, 999);
			int m2=999;
			int tmp1=kgcd(d1, m1);
			int tmp2=kgcd(d2,m2);
			if (dcmp(Math.abs(d1/(double)m1-x)-Math.abs(d2/(double)m2-x))<0)
				out.println(d1/tmp1+"/"+m1/tmp1);
			else
				out.println(d2/tmp2+"/"+m2/tmp2);
		}
		out.flush();
		out.close();
	}

}
class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
	}

	public String nextLine() {
		String tmp = null;
		try {
			tmp = reader.readLine();
			tokenizer = new StringTokenizer(tmp);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
			return null;
		}
		return tmp;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}
