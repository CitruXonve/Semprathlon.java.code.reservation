/**
 * Jan 13, 2016 8:19:51 PM
 * PrjName: 0113-K-2
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int maxw=110,maxn=110;
	final static int maxk=6;
	//static int[] w1,w2,v;
	static Obj[] ob;
	static int[][] f;
	static int n;
	static void init(){
		ob=new Obj[n]; 
		f=new int[maxw][maxw];
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			n=in.nextInt();
			int v1=in.nextInt();
			int v2=in.nextInt();
			int k=in.nextInt();
			init();
			for(int i=0;i<n;i++){
				ob[i]=new Obj(in.nextInt(), in.nextInt(), in.nextInt());
			}
			Arrays.sort(ob, new Obj.Comp());
			int ans=0;
			for(int i=Math.min(k, n);i<n;i++)
				for(int j1=v1;j1>=0;j1--)
					for(int j2=v2;j2>=0;j2--){
							int tmp=0;
							if (j1-ob[i].w1>=0)
								tmp=Math.max(tmp, f[j1-ob[i].w1][j2]+ob[i].v);
							if (j2-ob[i].w2>=0)
								tmp=Math.max(tmp, f[j1][j2-ob[i].w2]+ob[i].v);
							f[j1][j2]=Math.max(f[j1][j2], tmp);
						}
			for(int i=0;i<Math.min(k, n);i++)
				ans+=ob[i].v;
			out.println(f[v1][v2]+ans);
		}
		out.flush();
		out.close();
	}

}
class Obj{
	int w1,w2,v;
	Obj(){}
	Obj(int _w1,int _w2,int _v){
		w1=_w1;w2=_w2;v=_v;
	}
	static class Comp implements Comparator<Obj>{
		public int compare(Obj o1,Obj o2){
			if (o1.v==o2.v&&o1.w2==o2.w2)
				return -Integer.compare(o1.w1, o2.w1);
			if (o1.v==o2.v)
				return -Integer.compare(o1.w2,o2.w2);
			else 
				return -Integer.compare(o1.v, o2.v);
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
