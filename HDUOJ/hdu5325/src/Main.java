/**
 * 2015年7月29日 下午3:29:29
 * PrjName:hdu5325
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int n;
	static int[] w,f;
	static Graph G;
	static Vector<Pair<Integer,Integer>> P=new Vector<Pair<Integer,Integer>>();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			n=(int)in.nval;
			w=new int[n+1];
			f=new int[n+1];
			Arrays.fill(f, 1);
			P.clear();
			for(int i=1;i<=n;i++){
				in.nextToken();
				w[i]=(int)in.nval;
				P.add(new Pair<Integer,Integer>(w[i], i));
			}
			G=new Graph((n+1)<<1);
			for(int i=1;i<n;i++){
				in.nextToken();
				int u=(int)in.nval;
				in.nextToken();
				int v=(int)in.nval;
				G.addedge(u, v);
				G.addedge(v, u);
			}
			P.sort(Pair.VECTOR_COMPARATOR);
			int ans=1;
			for(int i=0;i<P.size();i++){
				int u=P.get(i).b();
				for(int j=G.h[u];j>-1;j=G.E[j].next){
					int v=G.E[j].to;
					if (w[u]>w[v]) continue;
					f[u]+=f[v];
				}
				ans=(f[u]>ans)?f[u]:ans;
			}
			out.println(ans);
		}
		out.flush();
		out.close();
	}

}

class Edge{
	int to,next;
	Edge(){}
	Edge(int v,int w){
		to=v;next=w;
	}
}

class Graph{
	int[] h;
	Edge[] E;
	int size;
	Graph(int sz){
		size=sz;
		h=new int[size+1];
		Arrays.fill(h, -1);
		h[0]=1;
		E=new Edge[size+1];
	}
	void addedge(int u,int v){
		E[h[0]]=new Edge(v,h[u]);
		h[u]=h[0]++;
	}
}

class Pair<A extends Comparable,B extends Comparable>{
	final A f;
	final B s;
	Pair(A first,B second){
		f=first;
		s=second;
	}
	A a(){
		return f;
	}
	B b(){
		return s;
	}
	public static final Comparator<Pair<Integer,Integer>> VECTOR_COMPARATOR=new Comparator<Pair<Integer,Integer>>() {
		@Override
		public int compare(Pair<Integer,Integer> p1,Pair<Integer,Integer> p2){
			if (p1.f==p2.f){
				return p1.s.compareTo(p2.s);
			}
			return -p1.f.compareTo(p2.f);
		}
	};
}