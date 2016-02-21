/**
 * 2015年7月30日 下午1:37:22
 * PrjName:0730-09
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int[][] dir=new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
	final static int maxn=1010,maxm=1010;
	static int n,m;
	static boolean[][] g,vis;
	static String[][] h;
	static boolean can(int x,int y){
		if (x<1||x>n||y<1||y>m) return false;
		return true;
	}
	static int mdis(int x,int y){
		return Math.abs(x-n)+Math.abs(y-m);
	}
	static int[] find0(int x,int y){
		int u=x,v=y;
		if (vis[x][y]||!can(x,y)||g[x][y]) return new int[]{1,1};
		vis[x][y]=true;
		for(int i=0;i<4;i++){
			int[]  tmp=find0(x+dir[i][0],y+dir[i][1]);
			if (mdis(tmp[0],tmp[1])<mdis(u,v)){
				u=tmp[0];v=tmp[1];
			}
		}
		return new int[]{u,v};
	}
	static void spfa(int x0,int y0){
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			g=new boolean[n+1][m+1];
			h=new String[n+1][m+1];
			for(int i=1;i<=n;i++){
				String s=in.next();
				for(int j=0;j<s.length();j++)
					if (s.charAt(j)=='1')
						g[i][j+1]=true;
					else g[i][j+1]=false;
			}
			vis=new boolean[n+1][m+1];
			int[] tmp=find0(1,1);
			int x0=tmp[0],y0=tmp[1];
			//out.println(x0+" "+y0);
			//g[1][1]=-1;
		}
		out.flush();
		out.close();
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