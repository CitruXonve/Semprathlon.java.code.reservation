/** Sep 19, 2015 12:41:25 PM
 * PrjName:0919-10
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static long[] s,l,f,ans;
	static String[] sh,sr;
	final static int maxn=201315;
	final static long mod=530600414L;
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
	static void init(){
		s=new long[maxn];
		l=new long[maxn];
		f=new long[maxn];
		ans=new long[maxn];
		sh=new String[maxn];
		sr=new String[maxn];
		s[1]=s[2]=0;s[3]=1;s[4]=3;
		f[1]=f[2]=0;f[3]=f[4]=1;
		l[1]=1;l[2]=2;l[3]=3;l[4]=5;
		ans[1]=ans[2]=ans[3]=ans[4]=0;
		sh[3]=new String("cf");sr[3]=new String("ff");
		sr[4]=new String("ff");sr[4]=new String("ff");
	}
	static long solve(int n/*,PrintWriter out*/){
		long res=0L;
		if (n<5) return res;
		if (ans[n]>0L) return ans[n];
		res+=solve(n-2);
		res+=solve(n-1);
		//res+=solve(n-2,out);
		//res+=solve(n-1,out);
		sh[n]=sh[n-2];sr[n]=sr[n-1];
		boolean has=false;
		if (sr[n-2].compareTo("cf")==0&&sh[n-1].charAt(0)=='f'){
			has=true;s[n]+=l[n-2]-1;
			res+=mod-s[n-2];
			res+=mul_mod((l[n-2]-1),f[n-2],mod);
		}
		if (sr[n-2].charAt(1)=='c'&&sh[n-1].compareTo("ff")==0){
			has=true;s[n]+=l[n-2];
			res+=mod-s[n-2];
			res+=mul_mod(l[n-2],f[n-2],mod);
		}
		s[n]+=(s[n-2]+s[n-1]+mul_mod(l[n-2],f[n-1],mod))%mod;s[n]%=mod;
		f[n]=has?f[n-2]+f[n-1]+1:f[n-2]+f[n-1];f[n]%=mod;
		l[n]=l[n-2]+l[n-1];l[n]%=mod;
		//out.print(n+":");out.print(res+" ");
		res+=mod-mul_mod(s[n-2],f[n-1],mod);//out.print(res+" ");
		//res+=s[n-1]*(has?f[n-2]+1:f[n-2]);out.print(res+" ");
		//res+=l[n-2]*f[n-1];out.println(res+" ");
		res+=mul_mod(s[n-1]+mul_mod(l[n-2],f[n-1],mod),f[n-2],mod);//out.println(res+" ");
		//while(res<0L) res+=mod;
		//if (has) res+=
		res%=mod;
		ans[n]=res;
		return res;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		//long tm=System.currentTimeMillis();
		init();
		for(int i=5;i<maxn;i++)
			solve(i);
		//ut.println(System.currentTimeMillis()-tm);
		while(T-->0){
			out.println("Case #"+(++cas)+": "+solve(in.nextInt()));
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
