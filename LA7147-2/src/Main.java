/**
 * Dec 2, 2015 3:43:40 PM
 * PrjName: LA7147-2
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Obj[] c,e;
	static boolean[] vis;
	static int[] match;
	static int n,m;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			c=new Obj[n];
			e=new Obj[m];
			vis=new boolean[m];
			match=new int[m];
			for(int i=0;i<n;i++)
				c[i]=new Obj(in.nextInt(), in.nextInt());
			for(int i=0;i<m;i++)
				e[i]=new Obj(in.nextInt(), in.nextInt());
			Arrays.sort(c, new Obj.comp2());
			Arrays.sort(e, new Obj.comp1());
//			out.println("Case "+(++cas)+": "+maxmatch());
			out.print('[');for(int e:match)out.print(e+",");out.print(']');
			
		}
		out.flush();
		out.close();
	}
	static int can(Obj o1,Obj o2){
		if (o1.a>=o2.d){
			if (o1.d>=o2.a) return 2;
			else return 1;
		}
		if (o1.d<o2.a) return -1;
		return 0;
	}
}
class Set/*<T extends Comparable<T>&Cloneable>*/{
	int sz,top;
	Obj[] data;
	Set(){}
	Set(int _sz){
		sz=_sz;top=0;
		data=new Obj[_sz];
		for(int i=0;i<sz;i++)
			data[i]=new Obj();
	}
	void add(Obj a){
		int p=0;
		if (top>0)
			p=Arrays.binary
	}
}
class Obj implements Comparator<Obj>{
	int a,d;
	Obj(){}
	Obj(int _a,int _d){
		a=_a;d=_d;
	}
	static class comp1 implements Comparator<Obj>{
		public int compare(Obj o1,Obj o2){
			if (o1.a==o2.a)
				return -Integer.compare(o1.d, o2.d);
			return -Integer.compare(o1.a, o2.a);
		}
	}
	static class comp2 implements Comparator<Obj>{
		public int compare(Obj o1,Obj o2){
			if (o1.d==o2.d)
				return -Integer.compare(o1.a, o2.a);
			return -Integer.compare(o1.d, o2.d);
		}
	}
	public int compare(Obj o1,Obj o2){
		return new Obj.comp2().compare(o1, o2);
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
