/**
 * 2015年7月27日 上午11:25:38
 * PrjName:hdu5241
 * @ Semprathlon
 */
import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Main {
	static BigInteger TWO=new BigInteger("2");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int n=in.nextInt();
			out.println("Case #"+(++cas)+": "+TWO.pow(n*5));
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
