/** Sep 1, 2015 5:16:47 PM
 * PrjName:loj1205
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxl=17;
	static int[][] f;
	static int[] digit;
	static void init(){
		f=new int[maxl+1][10];
		for(int j=0;j<10;j++)
			f[2][j]=f[1][j]=1;
		for(int i=0;i<=maxl-2;i++)
			for(int j=0;j<10;j++)
				for(int k=0;k<10;k++)
					f[i+2][j]+=f[i][k];
		//for(int i=2;i<=maxl;i++) f[i][0]=0;
	}
	static int solve(int n){
		int res=0;
		digit = new int[maxl + 1];
		while (n > 0) {
			digit[++digit[0]] = n % 10;
			n /= 10;
		}
		for(int i=digit[0];i>0;i--)
			for(int j=0;j<digit[i];j++)
				if (!(i>1&&j==0))
				res+=f[i][j];
		return res;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		for(int i=0;i<=maxl;i++){
			for(int j=0;j<10;j++) out.print(f[i][j]+" ");
			out.println();
		}
		out.flush();
		int T=in.nextInt();
		while(T-->0){
			int m=in.nextInt();
			int n=in.nextInt();
			out.println(solve(m)+" "+solve(n+1));
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
