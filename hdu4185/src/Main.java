/** Aug 24, 2015 11:15:57 AM
 * PrjName:hdu4185
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

    /**
     * @param args
     */
    static char[][] mp;
    //static int[][] adj;
    static int[] match;
    static boolean[] vis;
    static int n,cnt,sum;
    final static int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
    final static int maxn=605;
    static Vector<Integer>[] adj=new Vector[maxn*maxn>>1];
    static HashMap<Integer, Integer> sta=new HashMap<Integer,Integer>();
    static HashMap<Integer, Integer> stb=new HashMap<Integer,Integer>();
    static int geta(Pt p){
        int hash=p.hashCode();
        if (sta.containsKey(hash))
            return sta.get(hash);
        else{
            sta.put(hash, ++cnt);
            return cnt;
        }
    }
    static int getb(Pt p){
        int hash=p.hashCode();
        if (stb.containsKey(hash))
            return stb.get(hash);
        else{
            stb.put(hash, ++sum);
            return sum;
        }
    }
    static boolean cango(int x,int y){
        if (x<0||x>=n||y<0||y>=n||mp[x][y]!='#') return false;
        return true;
    }
    static boolean dfs(int u){
        for(int v:adj[u]){
            if (vis[v]) continue;
            vis[v]=true;
            if (match[v]<0||dfs(match[v])){
                match[v]=u;
                return true;
            }
        }
        return false;
    }
    static int maxmatch(){
        int res=0;
        Arrays.fill(match, -1);
        for(int i=1;i<=cnt;i++){
            Arrays.fill(vis, false);
            if (dfs(i)) res++;
        }
        return res;
    }
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        for(int i=0;i<maxn*maxn>>1;i++) adj[i]=new Vector<Integer>();
        int T=in.nextInt(),cas=0;
        
        while(T-->0){
            n=in.nextInt();
            mp=new char[n][n];
            for(int i=0;i<n*n>>1;i++) adj[i].clear();
            sta.clear();stb.clear();cnt=sum=0;
            for(int i=0;i<n;i++){
                String s=in.next();
                for(int j=0;j<n;j++)
                    mp[i][j]=s.charAt(j);
            }
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    if (mp[i][j]=='#'){
                        Pt p=new Pt(i, j,n);
                        for(int k=0;k<4;k++){
                            int x=i+dir[k][0];
                            int y=j+dir[k][1];
                            if (((x+y)&1)>0&&cango(x,y)){
                                Pt q=new Pt(x, y,n);
                                adj[geta(p)].add(getb(q));
                                //adj[q.hashCode()].add(p);
                            }
                        }
                    }
            for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
            	Pt p=new Pt(i,j,n);
            	if (sta.containsKey(p.hashCode()))
            		out.println(i+" "+j+" "+geta(p));
            }
            out.println("-----");
            for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
            	Pt p=new Pt(i,j,n);
            	if (stb.containsKey(p.hashCode()))
            		out.println(i+" "+j+" "+getb(p));
            }
            match=new int[sum+1];
            vis=new boolean[sum+1];
            out.println("Case "+(++cas)+": "+maxmatch());
        }
        out.flush();
        out.close();
    }

}
class Pt{
    int x,y,n;
    Pt(int _x,int _y,int _n){
        x=_x;y=_y;n=_n;
    }
    Pt(int hash,int _n){
        n=_n;
        y=hash%n;
        x=hash/n;
    }
    public int hashCode(){
        return x*n+y;
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