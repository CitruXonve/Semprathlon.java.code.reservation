/** Sep 2, 2015 8:29:38 PM
 * PrjName:hdu5001
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static int n,m,d;
	static int[] deg;
	static int[][] u;
	static double[][] f;
	static double solve(int x){
		f=new double[d+1][n+1];
		for(int i=1;i<=n;i++)
			f[0][i]=1.0/n;
		for(int i=1;i<=d;i++)
			for(int j=1;j<=n;j++){
				if (j==x) continue;
				for(int k=1;k<=u[j][0];k++)
					f[i][u[j][k]]+=f[i-1][j]/u[j][0];
			}
		double ans=0;
		for(int i=1;i<=n;i++)
			if (i!=x)
				ans+=f[d][i];
		return ans;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			d=in.nextInt();
			u=new int[n+1][m+1];
			deg=new int[n+1];
			for(int i=1;i<=m;i++){
				int x=in.nextInt();
				deg[x]++;
				int y=in.nextInt();
				deg[y]++;
				u[x][++u[x][0]]=y;
				u[y][++u[y][0]]=x;
			}
			
			for(int i=1;i<=n;i++)
				out.println(String.format("%.6f", solve(i)));
			/*
			 * 
			 *     memset(dp,0,sizeof(dp));
    for(int i=1;i<=n;i++)
        dp[0][i]=1.0/n;
    for(int i=0;i<d;i++)
    {
        for(int j=1;j<=n;j++)
        {
            if(j==x)  continue;
            for(int k=0;k<v[j].size();k++)
                dp[i+1][v[j][k]]+=dp[i][j]*1.0/v[j].size();
        }
    }
    double ans=0.0;
    for(int i=1;i<=n;i++)
    {
        if(i!=x)
            ans+=dp[d][i];
    }
    printf("%.5f\n",ans);
			 */
			
			
			/*f=new double[2][n+1];
			int cnt=0;
			Arrays.fill(f[0], 1);
			for(int j=1;j<=m;j++)
				f[cnt^1][v[j]]+=f[cnt][u[j]]/deg[u[j]];
			for(int i=1;i<=d;i++){
				cnt^=1;
				double tmp=0;
				for(int j=1;j<=n;j++)
					tmp+=f[cnt][j];
				for(int j=1;j<=n;j++)
					f[cnt^1][j]=f[cnt][j]/tmp;
				for(int j=1;j<=m;j++)
					f[cnt^1][v[j]]+=f[cnt][u[j]]/deg[u[j]];
			}
			for(int i=1;i<=n;i++)
				out.println(f[cnt^1][i]);*/
		}
		out.flush();
		out.close();
	}

}
class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;
 
    public InputReader(InputStream stream){
           reader = new BufferedReader(
                   new InputStreamReader(stream), 32768);
           tokenizer = null;
    }
 
    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(
                           reader.readLine());
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
