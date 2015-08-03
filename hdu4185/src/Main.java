/**
 * 2015年7月16日 下午4:53:11
 * PrjName:hdu4185
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;

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

public class Main {
	static int n;
	static boolean[][] mp,match,vis;
	static int[][] dir={{-1,0},{1,0},{0,1},{0,-1}};
	static boolean can(int x,int y){
		if (x<0||x>=n||y<0||y>=n)
			return false;
		return mp[x][y];
	}
	static boolean dfs(int x,int y){
		for(int k=0;k<4;k++){
			int dx=x+dir[k][0];
			int dy=y+dir[k][1];
			if (/*((dx+dy)&1)==1&&*/can(dx,dy)&&!vis[dx][dy]){
				vis[dx][dy]=true;
				if (!match[dx][dy]||dfs(dx,dy)){
					match[dx][dy]=true;
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int T=in.nextInt(),cas=0;
		while (T-->0) {
			n=in.nextInt();
			mp=new boolean[n][n];
			match=new boolean[n][n];
			for(int i=0;i<n;i++){
				String str = in.next();
				for(int j=0;j<n;j++)
					if (str.charAt(j)=='#')
						mp[i][j]=true;
			}
			/*for(int i=0;i<n*n;i++){
				out.print(mp.get(i)+" ");
				if ((i+1)%n==0)
					out.println();
			}*/
			vis=new boolean[n][n];
			int ans=0;
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					if(mp[i][j]){
						vis[i][j]=true;
						if (dfs(i,j)) ans++;
					}
					
			out.println("Case "+(++cas)+": "+ans);
		}
		out.flush();
		out.close();
	}

}
