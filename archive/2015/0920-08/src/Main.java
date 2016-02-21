/** Sep 20, 2015 12:24:28 PM
 * PrjName:0920-08
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static double eps = 0;

	static int dcmp(double d) {
		if (Math.abs(d) <= eps)
			return 0;
		return d > 0 ? 1 : -1;
	}
	static double pow_mod(double n, int m) {
		double res = 1.0;
		while (m > 0) {
			if ((m & 1) > 0)
				res = res * n;
			n = n * n;
			m >>= 1;
		}
		return res;
	}
	static Vector<Double> v=new Vector<Double>();
	static void init(){
		double tmp=0;
		for(int i=1;i<1000;i++){
			if (dcmp(0.5-tmp)==0) break;
			tmp=0.5-pow_mod(0.5, i);
			v.add(tmp);
		}
		v.add(0.5);
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		init();
		while(T-->0){
			double k=in.nextDouble();
			if (dcmp(k-0.5)==0){
				out.println(1000);continue;
			}
			int j;
			for(j=0;j<v.size();j++)
				if (dcmp(k-v.get(j))==0)
					break;
			
			if (j<v.size()){
				out.println(-1);continue;
			}
			int i;
			for(i=0;i<v.size()-1;i++)
				if (dcmp(k-v.get(i))*dcmp(k-v.get(i+1))<0)
					break;
			out.println(4*i+4);
		}
		//for(Double e:v.toArray(new Double[0]))out.print(e+" ");
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
