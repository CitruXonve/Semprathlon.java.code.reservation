/** Sep 27, 2015 12:52:20 PM
 * PrjName:0927-09-2
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] a,s1,s2,f;
	static int n,m;
	final static int inf=0x3fffffff;
	static int ans;
	//static PrintWriter out=new PrintWriter(System.out);
	static Vector<Integer> vec=new Vector<Integer>();
	static int sqr(int x){
		return x*x;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			a=new int[n+1][m+1];
			s1=new int[n+1][m+1];
			s2=new int[n+1][m+1];
			f=new int[n+1][m+1];
			
			for(int i=0;i<=n;i++)
				for(int j=0;j<=m;j++)
					f[i][j]=inf;
			for(int i=1;i<=n;i++)
				for(int j=1;j<=m;j++)
					a[i][j]=in.nextInt();
			s1[1][1]=sqr(a[1][1]);
			s2[1][1]=a[1][1];
			f[1][1]=(n+m-1)*s1[1][1]*s1[1][1]-sqr(s2[1][1]);
			for(int i=2;i<=n+m-1;i++)
				for(int j=i;j>=1;j--){
					
					int x=i-j+1,y=j;
					if (x<1||x>n||y<1||y>m) continue;
					int k1,k2,k;
					if (f[x-1][y]<inf){
						k1=s1[x-1][y]+sqr(a[x][y]);
						k2=s2[x-1][y]+a[x][y];
						k=(n+m-1)*k1-sqr(k2);
						if (k<f[x][y]){
							f[x][y]=k;s1[x][y]=k1;s2[x][y]=k2;
						}
					}
					
					if (f[x][y-1]<inf){
						k1=s1[x][y-1]+sqr(a[x][y]);
						k2=s2[x][y-1]+a[x][y];
						k=(n+m-1)*k1-sqr(k2);
						if (k<f[x][y]){
							f[x][y]=k;s1[x][y]=k1;s2[x][y]=k2;
						}
					}
				}
			/*for(int i=1;i<=n;i++){
				for(int j=1;j<=m;j++)
					out.print(s1[i][j]+" ");
				out.println();
			}*/
			out.println("Case #"+(++cas)+": "+f[n][m]);
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
