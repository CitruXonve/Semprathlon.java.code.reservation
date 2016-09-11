/**
 * Apr 16, 2016 1:04:45 PM
 * PrjName: 0416-A
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Map<Character, Integer> mp=new HashMap<Character,Integer>();
	static void init(){
		mp.clear();
		mp.put('A', 3);
		mp.put('B', 2);
		mp.put('C', 1);
		mp.put('D', 2);
		mp.put('E', 3);
		mp.put('F', 3);
		mp.put('G', 1);
		mp.put('a', 1);
		mp.put('b', 1);
		mp.put('c', 1);
		mp.put('d', 2);
		mp.put('e', 1);
		mp.put('f', 2);
		mp.put('g', 1);
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		int T=in.nextInt();
		while(T-->0){
			String s=in.nextLine();
			int ans=0;
			for(int i=0;i<s.length();i++)
				ans+=mp.get(s.charAt(i));
			out.println(ans);
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