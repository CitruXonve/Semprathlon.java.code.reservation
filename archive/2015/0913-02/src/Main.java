/** Sep 13, 2015 9:52:25 AM
 * PrjName:0913-02
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

    /**
     * @param args
     */
    final static int maxm=200010;
    static int[] a,d;
    static int sum;
    static boolean[] vis;
    static Graph g=new Graph(maxm);
    static long dfs(int u){
        long res=0;
        vis[u]=true;
        for(int i=g.h[u];i>-1;i=g.edge[i].nx){
            int v=g.edge[i].v;
            if (!vis[v]){
                long tmp=dfs(g.edge[i].v);
                if (tmp==0L) d[u]--;
                res+=tmp;
            }
        }
        if (d[u]<2){
            d[u]=0;
            return 0;
        }
        sum++;
        res+=a[u];
        return res;
    }
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt();
        while(T-->0){
            int n=in.nextInt();
            int m=in.nextInt();
            a=new int[n+1];
            d=new int[n+1];
            vis=new boolean[n+1];
            for(int i=1;i<=n;i++){
                a[i]=in.nextInt();
            }
            g.clear();
            for(int i=1;i<=m;i++){
                int u=in.nextInt();
                int v=in.nextInt();
                if (u==v) continue;
                g.add(u, v);
                g.add(v, u);
                d[u]++;d[v]++;
            }
            long ans=0;
            /*for(int i=1;i<=n;i++)
                if (!vis[i])
                	dfs(i);
            Arrays.fill(vis, false);*/
            for(int i=1;i<=n;i++)
                if (!vis[i]){
                	sum=0;
                	long tmp=dfs(i);
                	if ((sum&1)>0&&sum>0)
                		ans+=tmp;
                }
            out.println(ans);
            //for(int i=1;i<=n;i++)out.print(d[i]+" ");out.println();
        }
        out.flush();
        out.close();
    }

}
class Edge{
    int v,nx;
    Edge(int _u,int _w){
        v=_u;nx=_w;
    }
}
class Graph{
    int[] h;
    int sz;
    Edge[] edge;
    Graph(int size){
        sz=size;
        h=new int[sz+1];
        edge=new Edge[sz+1];
        Arrays.fill(h, -1);
        h[0]=0;
    }
    void clear(){
        edge=new Edge[sz+1];
        Arrays.fill(h, -1);
        h[0]=0;
    }
    void add(int u,int v){
        edge[h[0]]=new Edge(v,h[u]);
        h[u]=h[0]++;
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