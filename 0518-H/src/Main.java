
/**
 * May 18, 2016 7:35:38 PM
 * PrjName: 0518-H
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	final static int maxn = 40;
	static long[][][][] f=new long[maxn][maxn][maxn][maxn];
	static long[] a = new long[maxn];
	static long[] b = new long[maxn];
	static long[] sa = new long[maxn];
	static long[] sb = new long[maxn];

	static long dp(int x, int y, int i, int j)  
	{  
	    if(f[x][y][i][j]!=0)  
	    {  
	        return f[x][y][i][j];  
	    }  
	    if(x > y && i > j)  
	    {  
	        return 0;  
	    }  
	    long maxa = 0,  maxb = 0;  
	    if(x <= y)  
	    {  
	        maxa = Math.max(a[x]+dp(x+1,y,i,j),a[y]+dp(x,y-1,i,j));  
	    }  
	    if(i <= j)  
	    {  
	        maxb = Math.max(b[i]+dp(x,y,i+1,j),b[j]+dp(x,y,i,j-1));  
	    }  
	    f[x][y][i][j] = sa[y]-sa[x-1]+sb[j]-sb[i-1]-Math.max(maxa,maxb);  

	    return f[x][y][i][j];  
	} 

	public static void main(String[] args) throws IOException {
//		InputReader in = new InputReader(System.in);
		Scanner in=new Scanner(System.in);
//		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++)
					for(int k=1;k<=n;k++)
						for(int l=1;l<=n;l++)
							f[i][j][k][l]=0;
			
			Arrays.fill(sa, 0);
			Arrays.fill(sb, 0);
			for(int i = 1; i <= n; i++)  
	        {  
	            a[i]=in.nextInt(); 
	            sa[i] = sa[i-1]+a[i];  
	        }  
	        for(int i = 1; i <= n; i++)  
	        {  
	            b[i]=in.nextInt();
	            sb[i] = sb[i-1]+b[i];  
	        }  

		
			System.out.print(sa[n]+sb[n]-dp(1,n,1,n));
		}
//		out.flush();
//		out.close();
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