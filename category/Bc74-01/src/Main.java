/**
 * Mar 5, 2016 7:00:27 PM
 * PrjName: Bc74-01
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static long mod=1000000007L;
	static int[] a;
	static Vector<Integer> v=new Vector<Integer>();
	static Vector<Integer> w=new Vector<Integer>();
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
	static long fun1(int l,int r){
		int d=r-l-1;
		if (d==0)
			return 0L;
		else if (d==1)
			return 25L;
		else if (d==2)
			return 25L*25L;
		else 
			return 25L*25L*pow_mod(26, d-2, mod);
	}
	static long fun2(int l,int r){
		int d=r-l-1;
		if (d==0)
			return 1L;
		else if (d==1)
			return 24L;
		else if (d==2)
			return 13L*25L;
		else 
			return 13L*25L*pow_mod(25, d-2, mod);
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			a=new int[n+1];
			v.clear();
			w.clear();
			for(int i=1;i<n;i++)
				a[i]=in.nextInt();
			long ans=1L;
			for(int i=1;i<=n;i++){
				if (a[i]>n-i)
					ans*=0;
				else if (a[i-1]==0&&a[i]>0){
					v.add(i);w.add(i-1);
				}
				else if (a[i-1]>0&&a[i]>a[i-1])
					continue;
				else if (a[i]>0&&a[i]<a[i-1])
					continue;
				else if (a[i]==0&&a[i]<a[i-1]){
					v.add(i);w.add(i+1);
				}
				else if (a[i]>0&&a[i]==a[i-1])
					ans*=0;
				else if (a[i]==0&&a[i]==a[i-1])
					continue;
			}
			if ((v.size()&1)>0) v.add(n);
			if ((w.size()&1)>0) w.add(n);
			if (v.size()>0){
				ans*=pow_mod(25, v.get(0)-1, mod);ans%=mod;
//				out.println(ans+",");
				for(int i=0;i<v.size();i+=2){
					for(int j=v.get(i);j<=v.get(i+1);j++)
						if (a[j]>v.get(i+1)-j)
							ans*=0;
					if (i>0){
//						out.println(i+","+ans+","+v.get(i-1)+","+v.get(i)+","+fun1(v.get(i-1),v.get(i))+","+fun2(v.get(i-1),v.get(i)));
						ans=ans*fun1(v.get(i-1),v.get(i))%mod+ans*25L%mod*fun2(v.get(i-1),v.get(i))%mod;
						ans%=mod;
						continue;
					}
					ans*=26;ans%=mod;
				}
//				out.println(ans+",");
				ans*=pow_mod(25, n-v.get(v.size()-1), mod);
			}
			else{
				ans*=26;ans%=mod;
				ans*=pow_mod(25, n-1, mod);ans%=mod;
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
