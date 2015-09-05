/** Sep 3, 2015 9:43:43 PM
 * PrjName:hdu5000
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int mod=1000000007;
	static int[] a,f;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int sum=0;
			a=new int[n+1];
			for(int i=1;i<=n;i++){
				a[i]=in.nextInt();
				sum+=a[i];
			}
			sum>>=1;
			f=new int[sum+1];
			f[0]=1;
			for(int i=1;i<=n;i++)
				for(int j=sum;j>=0;j--)
					for(int k=1;k<=j&&k<=a[i];k++){
						f[j]+=f[j-k];
						f[j]%=mod;
					}
			out.println(f[sum]);
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
