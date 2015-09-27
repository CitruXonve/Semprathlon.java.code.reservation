/** Sep 26, 2015 8:30:25 PM
 * PrjName:Bc57-02
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Vector<Integer> vx=new Vector<Integer>();
	static Vector<Integer> vy=new Vector<Integer>();
	static HashMap<Integer, Integer> mx=new HashMap<>();
	static HashMap<Integer, Integer> my=new HashMap<>();
	static int[] sx,sy;
	static int idx,idy;
	static int getx(int h){
        if (mx.containsKey(h))
            return mx.get(h);
        mx.put(h, ++idx);
        return idx;
    }
    static int gety(int h){
        if (my.containsKey(h))
            return my.get(h);
        my.put(h, ++idy);
        return idy;
    }
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			mx.clear();
			my.clear();
			idx=idy=0;
			for(int i=1;i<=n;i++){
				int l=in.nextInt();
				int r=in.nextInt();
				getx(l);
				gety(r);
				vx.add(l);vy.add(r);
			}
			sx=new int[idx+1];
			sy=new int[idy+1];
			for(int i=0;i<n;i++){
				sx[getx(vx.get(i))]++;
				sy[gety(vy.get(i))]++;
			}
			for(int )
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
