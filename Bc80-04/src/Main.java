/**
 * Apr 16, 2016 8:13:39 PM
 * PrjName: Bc80-04
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a;
	static long[] b,m;
	static boolean[] v;
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
	static long CRT(int n, long[] a, long[] m) {
        long pro = 1L, res = 0L;
        for (int i = 0; i < n; i++)
            pro *= m[i];
        for (int i = 0; i < n; i++) {
            long w = pro / m[i];
            extgcd(m[i], w);
            res = (res + mul_mod(y, mul_mod(w, a[i], pro), pro)) % pro;
        }
        return (res + pro) % pro;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			a=new int[n];
			v=new boolean[n];
			for(int i=0;i<n;i++)
				a[i]=in.nextInt();
			b=new long[n];
			m=new long[n];
			int p=0,k=a[0];
			boolean ans=true;
			for(int i=0;i<n;i++){
				int j=0;
				while(!(a[p]==n-i)){
					p++;
					if (v[p])
						continue;
					else
						j++;
					if (p>n) p=1;
				}
				v[p]=true;
				if (j!=k){
					ans=false;break;
				}
				b[i]=j%(n-i);
				m[i]=n-i;
			}
			if (!ans){
				out.println("Creation August is a SB!");
			}
			else{
				out.println(CRT(n, b, m));
			}
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