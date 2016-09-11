/**
 * May 5, 2016 8:00:26 PM
 * PrjName: 0505-J
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	static long x3,y3;
	static boolean check(long k){
		return x3+k*k*k<=y3;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int cas=0;
		while(in.hasNext()){
			long x=in.nextLong();
			long y=in.nextLong();
			long y3=y*10+3;
			long ans=0;
			double up=Math.pow(y3, 1.0/3);
			for(long i=x;i<=up;i++){
				long a=i*i*i;
				if (a>y3)
					break;
				for(long j=x;j<=up;j++){
					long b=a+j*j*j;
					if (b>y3)
						break;
					if (b%10==3) ans++;
				}
			}
			out.println("Case "+(++cas)+": "+ans);
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