/**
 * Mar 29, 2016 9:42:15 PM
 * PrjName: 0329-I-3
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
    final static int maxn=1000010;
	static long[] a=new long[maxn];
	static long[] s=new long[maxn];
	static long[] f=new long[maxn];
	static Pt p1,p2;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			for(int i=1;i<=n;i++){
				a[i]=in.nextLong();
				s[i]=s[i-1]+a[i];
				f[i]=0;
			}
			p1=new Pt();
			p2=new Pt();
			for(int i=0;i<=n;i++){
//				long tmp=;
				/*if (i>1){
					long tmp=+s[i-1]-i*a[i];
					long t=-s[i-1]+i*a[i];
					for(int j=1;j<i;j++){
						out.println(i+":"+(f[j-1]+s[j-1]-j*a[i])+" "+j+"?"+(f[j-1]+s[j-1]-j*a[i]+t));
						tmp=Math.max(tmp, f[j-1]+s[j-1]-j*a[i]);
					}
					tmp=tmp-s[i-1]+i*a[i];
					f[i]=Math.max(f[i], tmp);
//					out.println(i+","+tmp+","+f[i]);
				}*/
				
				
				if (i>1){
					f[i]=+s[i-1]-i*a[i];
//					Pt p=que.peek();
					{
						long tmp=p1.l-(p1.r+1L)*a[i];
						if (tmp>f[i])
							f[i]=tmp;
					}
					{
						long tmp=p2.l-(p2.r+1L)*a[i];
						if (tmp>f[i])
							f[i]=tmp;
					}
					f[i]=f[i]-s[i-1]+i*a[i];
					if (f[i]<0) f[i]=0;
				}
//				que.add(new Pt(f[i]+s[i], i));
				if (f[i]+s[i]>p1.l){
					p2=p1;
					p1=new Pt(f[i]+s[i],i);
				}
				else if (f[i]+s[i]>p2.l)
					p2=new Pt(f[i]+s[i],i);
			}
			out.println(f[n]);
		}
		out.flush();
		out.close();
	}
}
class Pt{
	long l;
	int r;
	Pt(){}
	Pt(long _l,int _r){
		l=_l;r=_r;
	}
	public int compare(Pt p1,Pt p2){
		if (p1.l==p2.l){
			return Integer.compare(p1.r, p2.r);
		}
		return -Long.compare(p1.l, p2.l);
	}
	static class Comp implements Comparator<Pt>{
		public int compare(Pt p1,Pt p2){
			if (p1.l==p2.l){
				return Integer.compare(p1.r, p2.r);
			}
			return -Long.compare(p1.l, p2.l);
		}
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