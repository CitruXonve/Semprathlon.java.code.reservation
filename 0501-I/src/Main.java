/**
 * May 1, 2016 5:35:42 PM
 * PrjName: 0501-I
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	static int n,m,a,b;
	static long[] f,g;
	static long swt(long v){
		return ~v&((1L<<n)-1L);
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.hasNext()){
			n=in.nextInt();
			m=in.nextInt();
			a=in.nextInt();
			b=in.nextInt();
			f=new long[m];
			g=new long[m];
			for(int i=0;i<m;i++){
				String s=in.next();
				for(int j=0;j<s.length();j++,f[i]<<=1)
					if (s.charAt(j)=='1')
						f[i]++;
				f[i]>>=1;
			}
			for(int i=0;i<m;i++)
				out.print(f[i]+","+swt(f[i])+" ");
			out.println();
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