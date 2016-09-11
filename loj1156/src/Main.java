/**
 * May 4, 2016 10:16:25 PM
 * PrjName: loj1156
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int inf=0x3fffffff;
	static Dinic work;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int D=in.nextInt();
			work=new Dinic(1, n);
			for(int i=1;i<=n;i++){
				String s=in.next();
				int k=Integer.parseInt(s.substring(2));
				if (s.charAt(0)=='B'){
					for(int j=1;j<=n;j++)
						if (i!=j){
							work.add(i, j, inf);
						}
				}
				else{
					for(int j=1;j<=n;j++)
						if (i!=j)
							work.add(i, j, D);
				}
			}
		}
		out.flush();
		out.close();
	}

}
class Dinic{
    public Dinic(int sourse , int meet){
                 this.sourse = sourse ; 
                 this.meet = meet ;
                 Arrays.fill(g,  0) ;
                 id = 1 ;
    }

    static final  int  maxn = 2008 , maxm = 500000 ;
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

    Queue<Integer> que  = new LinkedList<Integer>();
    static boolean[] vis = new boolean[maxn + 10] ;
    static int[]  dist = new int[maxn + 10] ;

    void bfs(){
             Arrays.fill(dist, 0) ;
             que.clear();
             que.add(sourse) ;
             vis[sourse] = true ;
             while(! que.isEmpty()){
                  int u = que.poll() ;
                  for(int i = g[u] ; i > 0 ; i = e[i].next){
                       int v = e[i].v ;
                       if(e[i].f  > 0 && !vis[v]){
                             que.add(v) ;
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
class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public boolean hasNext() {
        return tokenizer != null && tokenizer.hasMoreTokens() || nextLine() != null;
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