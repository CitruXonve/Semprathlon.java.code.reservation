/** Sep 5, 2015 8:31:13 PM
 * PrjName:Bc54-04
 * @author Semprathlon
 */
import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxn=1000001;
	static BigInteger[] f=new BigInteger[maxn];
	static void init(){
		f[1]=new BigInteger("1");
		f[2]=new BigInteger("2");
		f[3]=new BigInteger("4");
		for(int i=4;i<1000;i++){
			f[i]=new BigInteger("0");
			for(int j=1;j<i;j++)
				f[i].add(f[j].multiply(f[i-j]));
		}
	}
	static BigInteger solve(int n){
		return f[n];
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			out.println(solve(n));
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