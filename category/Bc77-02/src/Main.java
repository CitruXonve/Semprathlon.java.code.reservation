/**
 * Mar 26, 2016 7:22:25 PM
 * PrjName: Bc77-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
    static int[][] f,g;
    static boolean can(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
    static int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
    static boolean[][] vis;
    static boolean dfs(int x,int y,int q){
        if (x==0)
            return f[x][y]==0||f[x][y]>q;
        if (f[x][y]==0||f[x][y]>q)
            for(int k=0;k<4;k++){
                int dx=x-dir[k][0];
                int dy=y+dir[k][1];
                if (can(dx,dy)&&!vis[dx][dy]){
                    vis[dx][dy]=true;
                    if (dfs(dx,dy,q)) return true;
                }
            }
        return false;
    }
    static boolean scan(int q){
        vis=new boolean[n][m];
        for(int j=0;j<m;j++)
	        if (!vis[n-1][j]){
	            if (dfs(n-1,j,q)) return true;
	        }
        return false;
    }
    static int bisearch(int q){
        int l=0,r=q;
        while(l<r){
            int mid=(l+r)>>1;
            if (scan(mid)) l=mid+1;
            else r=mid;
        }
        return l;
    }
    public static void main(String[] args) throws IOException{
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt();
        while(T-->0){
            n=in.nextInt();
            m=in.nextInt();
            f=new int[n][m];
            for(int i=0;i<n;i++){
                String s=in.next();
                for(int j=0;j<m;j++)
                    if (s.charAt(j)=='1')
                        f[i][j]=-1;
            }
            int q=in.nextInt();
            for(int i=1;i<=q;i++){
                int x=in.nextInt();
                int y=in.nextInt();
                f[x][y]=i;
            }
            /*for(int i=0;i<=q;i++)
                out.println(i+":"+scan(i));*/
            int ans=scan(q)?-1:(bisearch(q));
            out.println(ans);
            //out.println(check(ans));
        }
        out.flush();
        out.close();
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