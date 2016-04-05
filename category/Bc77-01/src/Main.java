/**
 * Mar 26, 2016 7:02:50 PM
 * PrjName: Bc77-01
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	static int[] f=new int[26];
	final static long mod=1000000007;
	final static int maxn=1010;
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
        return n * pow_mod(m, mod - 2, mod) % mod;
//         return n * pow_mod(m, phi[mod] - 1, mod) % mod;
	}
	static long fac(long n,long mod){
		long res=1;
		for(int i=2;i<=n;i++)
			res=res*(long)i%mod;
		return res;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			String s=new String(in.next());
			Arrays.fill(f, 0);
			for(int i=0;i<s.length();i++){
				f[s.charAt(i)-'a']++;
			}
			int cnt=0;
			for(int i=0;i<26;i++)
				if ((f[i]&1)>0)
					cnt++;
			if (cnt>1){
				out.println(0);continue;
			}
			long ans=fac(s.length()/2, mod);
			for(int i=0;i<26;i++){
				ans=div_mod(ans, fac(f[i]/2, mod), mod);
			}
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
