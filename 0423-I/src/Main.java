/**
 * Apr 23, 2016 12:40:30 PM
 * PrjName: 0423-I
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static char[][] tem=new char[][]{{'\0','O','\0'},{'/','|','\\'},{'(','\0',')'}};
	static char[][] g;
	static boolean[][] v;
	static int n,m;
	static boolean can(int x,int y){
		return x>=0&&x<n&&y>=0&&y<m;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			g=new char[n][m];
			v=new boolean[n][m];
			for(int i=0;i<n;i++){
				String s=in.next();
				for(int j=0;j<m;j++)
					g[i][j]=s.charAt(j);
			}
			int ans=0;
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					if (g[i][j]!='.'&&!v[i][j]){
						boolean has=false;
						int x0=0,y0=0;
						for(int k=0;k<3&&!has;k++)
							for(int l=0;l<3&&!has;l++)
								if (tem[k][l]>'\0'&&g[i][j]==tem[k][l]){
									has=true;
									x0=k;y0=l;
									break;
								}
						for(int k=0;k<3;k++)
							for(int l=0;l<3;l++)
								if (can(i-x0+k,j-y0+l)&&tem[k][l]!='\0'&&tem[k][l]==g[i-x0+k][j-y0+l])
									v[i-x0+k][j-y0+l]=true;
//						out.println(i+","+j);
						ans++;
					}
			
			out.println(ans);
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