import java.io.*;
import java.util.*;
public class Main {
	static long mul_mod(long n, long m, long mod) {
        long ans = 0L;
        n %= mod;
        while (m > 0L) {
            if ((m & 1L) > 0L)
                ans = (ans + n) % mod;
            m >>= 1;
            n = (n + n) % mod;
        }
        return ans;
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
	static long div_mod(long n, long m, long mod) {
		return mul_mod(pow_mod(m, mod - 2, mod), n, mod);
        // return n * pow_mod(m, phi(mod) - 1, mod) % mod;
	}
	static long kgcd(long a, long b) {
        if (a == 0L)
            return b;
        if (b == 0L)
            return a;
        if ((a & 1L) == 0L && (b & 1L) == 0L)
            return kgcd(a >> 1, b >> 1) << 1;
        else if ((b & 1L) == 0L)
            return kgcd(a, b >> 1);
        else if ((a & 1L) == 0L)
            return kgcd(a >> 1, b);
        else
            return kgcd(Math.abs(a - b), Math.min(a, b));
	}
	static long get_segment_point(long dx,long dy) 
	{  
	    if(dx==0L && dy==0L) return 0;  
	    return kgcd(dx,dy)-1L;  
	}  
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			long q=in.nextLong();
			long p=in.nextLong();
			q%=p;
			long s=div_mod(mul_mod(q, q, p), 2, p);
//			long s=q*q/2; 
			long l=q+q+1+get_segment_point(q, q);
			while(l>=p)
				l-=p;
			long ans=s+1-div_mod(l, 2, p);
			while(ans<0)
				ans+=p;
			while(ans>=p)
				ans-=p;
//			long ans=s+1-l/2;
//			ans%=p;
			out.println(s+","+l);
			out.println(ans);
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

    public boolean hasNext() {
        return tokenizer != null && tokenizer.hasMoreTokens() || nextLine() != null;
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