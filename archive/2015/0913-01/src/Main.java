/** Sep 13, 2015 12:39:06 PM
 * PrjName:0913-01
 * @author Semprathlon
 */
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static Data[] per;
	static Pair[] cm;
	static ArrayList<Data> res=new ArrayList<Data>();
	static ArrayList<Pair> com=new ArrayList<Pair>();
	static PriorityQueue<Data> que=new PriorityQueue<Data>(new Data.Comp());
	static String[] ans;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			int q=in.nextInt();
			per=new Data[n];
			for(int i=0;i<n;i++)
				per[i]=new Data(in.next(), in.nextInt(), i+1);
			com.clear();
			while(m-->0)
				com.add(new Pair(in.nextInt(), in.nextInt()));
			cm=com.toArray(new Pair[0]);
			Arrays.sort(cm, new Pair.Comp());
			com.clear();
			for(int i=0;i<cm.length;i++)
				if (com.size()>0&&cm[i].l==com.get(com.size()-1).l){
					int tmp=com.get(com.size()-1).r;
					com.remove(com.size()-1);
					com.add(new Pair(cm[i].l, tmp+cm[i].r));
				}
				else 
					com.add(cm[i]);
			que.clear();
			res.clear();
			int last=0;
			for(Pair pr:com.toArray(new Pair[0])){
				for(int i=last;i<pr.l-1;i++)
					que.add(per[i]);
				res.add(per[pr.l-1]);
				for(int i=0;que.size()>0&&i<pr.r;i++)
					res.add(que.remove());
				last=pr.l;
			}
			for(int i=last;i<n;i++)
				que.add(per[i]);
			while(!que.isEmpty())
				res.add(que.remove());
			ans=new String[res.size()];
			for(int i=0;i<res.size();i++){
				ans[i]=res.get(i).name;
				//out.println(ans[i]);
			}
			while(q-->0){
				out.print(ans[in.nextInt()-1]+" ");
			}
//			out.println(com.size());for(int i=0;i<com.size();i++)out.println(com.get(i).l+","+com.get(i).r);
//			out.println(cm.length);for(int i=0;i<cm.length;i++)out.println(cm[i].l+","+cm[i].r);
		}
		out.flush();
		out.close();
	}
}
class Data{
	String name;
	int v,od;
	Data(){}
	Data(String _nm,int _v,int _o){
		name=_nm;v=_v;od=_o;
	}
	static class Comp implements Comparator<Data>{

		public int compare(Data o1, Data o2) {
			// TODO Auto-generated method stub
			if (o1.v==o2.v)
				return Integer.compare(o1.od, o2.od);
			return Integer.compare(o2.v, o1.v);
		}
		
	}
}
class Pair{
	int l,r;
	Pair(){}
	Pair(int a,int b){
		l=a;r=b;
	}
	static class Comp implements Comparator<Pair>{

		public int compare(Pair o1, Pair o2) {
			// TODO Auto-generated method stub
			if (o1.l==o2.l)
				return Integer.compare(o1.r, o2.r);
			return Integer.compare(o1.l, o2.l);
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