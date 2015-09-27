/** Sep 26, 2015 12:47:41 PM
 * PrjName:0926-11
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static long x,y;
	static void extgcd(long a, long b) {
        if (b == 0L) {
            x = 1L;
            y = 0L;
            return;
        }
        extgcd(b, a % b);
        long t = x;
        x = y;
        y = t - a / b * y;
	}
	
	static long gcd(long a,long b){
		extgcd(a, b);
		return a*x+b*y;
	}
	
	static long pow_mod(long n, long m, long mod) {
        long res = 1L;
        n %= mod;
        while (m > 0L) {
            if ((m & 1L) > 0L)
                res = res * n % mod;
            n = n * n % mod;
            m >>= 1;
        }
        return res;
	}
	static long period(long a,long mod){
		a%=mod;
		long ref=a,res=1;
		long tmp=a*a%mod;
		while(tmp!=ref){
			tmp*=a;tmp%=mod;res++;
		}
		return res;
	}
	static long t1,t2;
	static boolean solve(long a,long b,long k1,long b1,long k2,long c){
		long aa=pow_mod(a, k1, c);
		long bb=pow_mod(b, k2, c);
		//t1=period(aa, c);
		//t2=period(bb, c);
		long ta=pow_mod(a, b1, c)*aa%c;
		long tb=b;
		long ref=(ta+tb)%c;
		if (ref!=0) return false;
		//long t=t1*t2/gcd(t1, t2);
		//long t=Math.min(t1, t2);
		long t=50000L;
		while(t-->0){
			ta*=aa;ta%=c;
			tb*=bb;tb%=c;
			long tmp=(ta+tb)%c;
			if (tmp!=0) return false;
			//if (tmp==ref) return true;
		}
		return true;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int cas=0;
		while(in.nextLine()!=null){
			int c=in.nextInt();
			int k1=in.nextInt();
			int b1=in.nextInt();
			int k2=in.nextInt();
			//long st=System.currentTimeMillis();
			out.println("Case #"+(++cas)+":");
			boolean has=false;
			for(int i=1;i<c;i++){
				//if (!has)
				//for(int j=1;j<c;j++)
				int j=c-1;
					if (solve(i, j, k1, b1, k2, c)){
						out.println(i+" "+j);
						has=true;
						//break;
						//out.println(t1+" "+t2);
					}
			}
			if (!has) out.println(-1);
			//out.println(System.currentTimeMillis()-st);
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
