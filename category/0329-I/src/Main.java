/**
 * Mar 29, 2016 7:14:35 PM
 * PrjName: 0329-I
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
    static TreeMap<Long, Integer> mp=new TreeMap<Long,Integer>();
	static long[] a,s;
	static long[] f;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			a=new long[n+1];
			s=new long[n+1];
			for(int i=1;i<=n;i++){
				a[i]=in.nextLong();
				s[i]=s[i-1]+a[i];
			}
			f=new long[n+1];
			mp.clear();
			for(int i=1;i<=n;i++){
//				long tmp=;
				mp.put(f[i]+s[i], i);
				if (i>1){
					for(Map.Entry<Long, Integer> ent:(mp.descendingMap()).entrySet()){
						long tmp=ent.getKey()-(ent.getValue()+1L)*a[i];
						if (tmp>f[i])
							f[i]=tmp;
						else if (tmp<f[i])
							break;
					}
					f[i]-=s[i-1];f[i]+=i*a[i];
				}
			}
			out.println(f[n]);
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
