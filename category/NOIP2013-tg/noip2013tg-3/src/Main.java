/**
 * 2015年7月27日 上午10:34:40
 * PrjName:noip2013tg-3
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
import java.lang.Math;
public class Main {
	static Vector<Integer> U,V;
	static HashMap<Integer, Integer> mp=new HashMap<Integer, Integer>();
	static HashMap<Integer, Integer> res=new HashMap<Integer, Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		int m=in.nextInt();
		mp.clear();
		U=new Vector<Integer>();
		V=new Vector<Integer>();
		for(int i=1;i<=m;i++){
			int u=in.nextInt();
			int v=in.nextInt();
			int w=in.nextInt();
			if (mp.containsKey(u*10000+v)){
				int tmp=mp.get(u*10000+v);
				tmp=Math.max(tmp,w);
				mp.put(u*10000+v, tmp);
			}
			else{
				mp.put(u*10000+v, w);
				U.add(u);
				V.add(v);
			}
				
		}
		m=U.size();
		Dinic dinic=new Dinic(1, n);
		for(int i=0;i<m;i++){
			int u=U.get(i);
			int v=V.get(i);
			int w=mp.get(u*10000+v);
			dinic.add(u, v, w);
		}
		//out.println(dinic.maxflow());
		int q=in.nextInt();
		for(int i=1;i<=q;i++){
			int u=in.nextInt();
			int v=in.nextInt();
			dinic.modify(u, v);
			if (res.containsKey(u*10000+v)){
				out.println(res.get(u*10000+v));
				continue;
			}
			int ans=dinic.maxflow();
			if (ans==0) ans=-1;
			out.println(ans);
			res.put(u*10000+v, ans);
		}
		out.flush();
		out.close();
	}

}

class Edge{
	int to,cap,rev,next;
	Edge(){}
	Edge(int t,int w,int r,int nt){
		to=t;cap=w;rev=r;next=nt;
	}
	Edge(Edge e){
		this.to=e.to;
		this.cap=e.cap;
		this.rev=e.rev;
		this.next=e.next;
	}
}
class Queue<T>{
	T[] data;
	int size,h,r;
	/*Queue(Class<T> c,int sz){
		size=sz;
		@SuppressWarnings("unchecked")
		final T[] tmp=(T[])Array.newInstance(c, sz);
		data=tmp;
	}*/
	Queue(int sz){
		size=sz;
		data=(T[])new Object[sz];
	}
	void clear(){
		h=r=0;
	}
	boolean empty(){
		return h==r;
	}
	boolean full(){
		return (r+1)%size==h;
	}
	void push(T n){
		data[r]=n;
		r=(r+1)%size;
	}
	T pop(){
		T tmp=data[h];
		h=(h+1)%size;
		return tmp;
	}
}

class Dinic{
    public Dinic(int sourse , int meet){
                 this.sourse = sourse ; 
                 this.meet = meet ;
                 Arrays.fill(g,  0) ;
                 id = 1 ;
    }
    public void modify(int source,int meet){
    	this.sourse=source;
    	this.meet=meet;
    }

    static final  int  maxn = 10010 , maxm = 50010 ;
    static class Edge{
           int v , f ,next ;
           Edge(){}
           Edge(int _v , int _f , int _next){
                 this.v = _v ;
                 this.f = _f ;
                 this.next = _next ;
           }
    };
    int  sourse , meet ;
    int  id ;
    static Edge[] e = new Edge[maxm*2 + 10] ;
    static int[] g = new int[maxn + 10] ;

    public void  add(int u , int v , int f){
              e[++id] = new Edge(v , f ,g[u]) ;
              g[u] = id ;
              e[++id] = new Edge(u , 0 , g[v]) ;
              g[v] = id ;
    }

    Queue<Integer> que  = new Queue<Integer>(maxm);
    static boolean[] vis = new boolean[maxn + 10] ;
    static int[]  dist = new int[maxn + 10] ;

    void bfs(){
             Arrays.fill(dist, 0) ;
             while(! que.empty()) que.pop() ;
             que.push(sourse) ;
             vis[sourse] = true ;
             while(! que.empty()){
                  int u = que.pop() ;
                  for(int i = g[u] ; i > 0 ; i = e[i].next){
                       int v = e[i].v ;
                       if(e[i].f  > 0 && !vis[v]){
                             que.push(v) ;
                             dist[v] = dist[u] + 1 ;
                             vis[v] = true ;
                       }
                  }
             }
    }

    int  dfs(int u , int delta){
             if(u == meet) return delta ;
             int ans = 0 ;
             for(int i = g[u] ; i > 0  && delta > 0  ; i = e[i].next){
                   int  v = e[i].v ;
                   if(e[i].f  > 0 && dist[v] == dist[u] + 1){
                        int d = dfs(v , Math.min(delta , e[i].f)) ;
                        e[i].f -= d ;
                        e[i^1].f += d ;
                        delta -= d ;
                        ans += d ;
                   }
             }
             return ans ;
    }

    public int  maxflow(){
                 int ans = 0 ;
                 while(true){
                     Arrays.fill(vis, false) ;
                     bfs() ;
                     if(! vis[meet]) return ans  ;
                     ans += dfs(sourse , Integer.MAX_VALUE) ;
                 }
    }

}

class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream){
           reader = new BufferedReader(new InputStreamReader(stream), 32768);
           tokenizer = null;
    }

    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (IOException e) {
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

}