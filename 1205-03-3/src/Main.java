/**
 * Dec 6, 2015 4:42:31 PM
 * PrjName: 1205-03-3
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a,f;
	static ST tr;
	static HashSet<Integer> st=new HashSet<Integer>();
	static void print(int[] a,PrintWriter out){
		int n=a.length-1;
		for(int i=1;i<=n;i++)
			out.print(a[i]+(i<n?" ":""));
		out.println();
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			a=new int[n+1];
			f=new int[n+1];
			tr=new ST(n);
			tr.build(1, 1, n);
			for(int i=1;i<=n;i++)
				f[i]=in.nextInt();
			
			for(int i=n;i>=1;i--){
				f[i]-=f[i-1];
				int tmp=tr.query(1, 1, n, f[i]+1);
//				out.println(i+","+f[i]+","+tmp);
				a[i]=tmp;
				
			}
			print(a, out);
		}
		out.flush();
		out.close();
	}
}
class ST{
	int[] l,r,m,v;
	int sz;
	ST(){}
	ST(int _sz){
		sz=_sz<<2;
		l=new int[sz];
		r=new int[sz];
		m=new int[sz];
		v=new int[sz];
	}
	void build(int k,int x,int y){
		l[k]=x;r[k]=y;m[k]=(x+y)>>1;
		if (x<y){
			build(k<<1,x,m[k]);
			build(k<<1|1,m[k]+1,y);
		}
		v[k]=y-x+1;
	}
	int query(int k,int x,int y,int q){
		if (l[k]==r[k]){
			v[k]=0;
			return l[k];
		}
		int res=0;
		if (v[k<<1|1]>=q)
			res=query(k<<1|1, m[k]+1, y, q);
		else
			res=query(k<<1,x,m[k],q-v[k<<1|1]);
		v[k]=v[k<<1]+v[k<<1|1];
		return res;
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
