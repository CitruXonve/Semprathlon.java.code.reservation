/**
 * 2015年7月28日 下午1:37:02
 * PrjName:0728-04
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static byte[][] g;
	final static int maxn=55;
	static int n,m;
	static void erase(int x,int y,int c){
		if (x>=n||y>=m||x<0||y<0) return;
		if (c==1){
			if ((g[x][y]&1)==0) return;
			g[x][y]&=~1;
			erase(x+1,y+1,1);
		};
		if (c==2){
			if ((g[x][y]&2)==0) return;
			g[x][y]&=~2;
			erase(x+1,y-1,2);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		String s=new String();
		while(T-->0){
			n=in.nextInt();
			g=new byte[n][maxn];
			for(int i=0;i<n;i++){
				s=new String(in.next());
				m=s.length();
				//out.println("m="+m);
				for(int j=0;j<m;j++)
					switch (s.charAt(j)) {
					case 'R':
						g[i][j]=1;
						break;
					case 'B':
						g[i][j]=2;
						break;
					case 'G':
						g[i][j]=3;
						break;
					default:
						g[i][j]=0;
						break;
					}
			}
			int ans=0;
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++){
					if ((g[i][j]&1)>0){
						erase(i,j,1);ans++;
					};
					if ((g[i][j]&2)>0){
						erase(i,j,2);ans++;
					};
				}
					
			out.println(ans);
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