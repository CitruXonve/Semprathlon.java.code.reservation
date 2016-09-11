/**
 * May 5, 2016 7:22:01 PM
 * PrjName: 0505-C
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static String[] s;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		s=new String[5];
		int ans=0;
		for(int i=0;i<5;i++)
			s[i]=new String(in.next());
		for(int i=0;i<n;i++){
			if (s[3].charAt(4*i+1)=='*'){
				ans=ans*10+1;
				continue;
			}
			if (s[3].charAt(4*i)=='*'){
				ans=ans*10+2;
				continue;
			}
			if (s[3].charAt(4*i+2)=='*'){
				ans=ans*10+3;
				continue;
			}
		}
		out.println(ans);
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