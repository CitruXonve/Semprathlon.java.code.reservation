/**
 * May 1, 2016 1:36:53 PM
 * PrjName: 0501-J
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Set<Integer> st=new HashSet<Integer>();
	static void init(){
		st.clear();
		for(int i=10;i*i<=9999;i++)
			st.add(i*i);
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		int T=in.nextInt(),cas=0;
		while(T-->0){
			String s=in.next();
			String t;
			int ans=0;
			for(int i=1;i<10;i++){
				if (i==s.charAt(0)-'0') continue;
				t=(char)('0'+i)+s.substring(1);
				if (st.contains(Integer.parseInt(t)))
					ans++;
//				out.println(t);
			}
			for(int k=1;k<4;k++)
				for(int i=0;i<10;i++){
					if (i==s.charAt(k)-'0') continue;
					t=s.substring(0,k)+(char)('0'+i)+s.substring(k+1);
					if (st.contains(Integer.parseInt(t)))
						ans++;
//					out.println(t);
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