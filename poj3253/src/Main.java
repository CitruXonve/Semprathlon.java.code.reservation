/**
 * May 6, 2016 8:51:24 PM
 * PrjName: poj3253
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a;
	static Queue<Integer> que=new PriorityQueue<Integer>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		a=new int[n];
		que.clear();
		for(int i=0;i<n;i++){
			a[i]=in.nextInt();
			que.add(a[i]);
		}
		long ans=0;
		while(que.size()>1){
			int x=que.poll();
			int y=que.poll();
			ans+=x+y;
			que.add(x+y);
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