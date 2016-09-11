/**
 * Apr 22, 2016 7:01:27 PM
 * PrjName: Bc81-01
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int m=in.nextInt();
			long n=in.nextLong();
			long up=1L;
			for(int i=1;i<=m;i++)
				up*=3L;
			n%=up;
			String s=new String();
			while(n>0L){
				switch ((int)(n%3L)) {
				case 0:
					s="R"+s;
					break;
				case 1:
					s="G"+s;
					break;
				case 2:
					s="B"+s;
					break;
				}
				n/=3L;
			}
			int len=s.length();
			for(int i=1;i<=m-len;i++)
				s="R"+s;
			out.println(s);
			
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