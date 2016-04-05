/**
 * Mar 9, 2016 2:42:48 PM
 * PrjName: hdu5636
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a=new int[7];
	static int[][] f=new int[7][7];
	final static int inf=0x3fffffff;
	final static int mod=1000000007;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			for(int i=0;i<7;i++)
				Arrays.fill(f[i], inf);
			for(int i=1;i<=6;i++)
				a[i]=in.nextInt();
			f[1][2]=f[2][1]=f[3][4]=f[4][3]=f[5][6]=f[6][5]=1;
			for(int i=1;i<=6;i++)
				for(int j=1;j<=6;j++)
					f[i][j]=Math.min(f[i][j], Math.abs(a[i]-a[j]));
			for(int i=1;i<=6;i++)
				for(int j=1;j<=6;j++)
					for(int k=1;k<=6;k++)
						if (k!=j&&k!=i)
								f[i][j]=f[j][i]=Math.min(f[i][j],f[i][k]+f[k][j]);
			int ans=0;
			for(int k=1;k<=m;k++){
				int s=in.nextInt();
				int t=in.nextInt();
				int tmp=Math.abs(s-t);
				for(int i=1;i<=6;i++)
					for(int j=1;j<=6;j++)
						tmp=Math.min(tmp, Math.abs(s-a[i])+f[i][j]+Math.abs(a[j]-t));
				ans+=tmp%mod*k%mod;
				ans%=mod;
//				out.println(ans);
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
