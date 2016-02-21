/**
 * 2015年7月30日 下午12:22:40
 * PrjName:0730-02
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] f;
	static long[] a;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			a=new long[n+2];
			f=new int[n+2][2];
			for(int i=1;i<=n;i++){
				a[i]=in.nextLong();
			}
			f[1][0]=f[1][1]=Math.min(1, n);
			f[2][0]=f[2][1]=Math.min(2, n);
			for(int i=3;i<=n;i++){
				if ((a[i-1]<<1)==a[i-2]+a[i]) f[i][0]=f[i-1][0]+1;
				else f[i][0]=2;
				if (a[i-1]*a[i-1]==a[i-2]*a[i]) f[i][1]=f[i-1][1]+1;
				else f[i][1]=2;
			}
			int ans=1;
			for(int i=1;i<=n;i++){
				ans=Math.max(ans, f[i][0]);
				ans=Math.max(ans, f[i][1]);
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