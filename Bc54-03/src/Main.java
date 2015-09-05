/** Sep 5, 2015 8:05:33 PM
 * PrjName:Bc54-03
 * @author Semprathlon
 */
import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxn=101;
	static BigInteger a[]=new BigInteger[maxn];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			boolean zero=false,nonzero=false;
			for(int i=1;i<=n;i++){
				a[i]=new BigInteger(in.next());
				if (a[i].equals(BigInteger.ZERO))
					zero|=true;
				else
					nonzero|=true;
				//out.println(a[i]);
			}
			boolean ans=(zero&&nonzero)?false:true;
			if (ans)
			for(int i=2;i<n;i++)
				if (!a[i].pow(2).equals(a[i-1].multiply(a[i+1]))){
					ans=false;
					break;
				}
			out.println(ans?"Yes":"No");
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