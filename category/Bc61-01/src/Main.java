/**
 * Oct 31, 2015 7:01:33 PM
 * PrjName: Bc61-01
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int maxl=1001;
	static int[] f=new int[maxl];
	static Vector<Integer> v=new Vector<Integer>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			int n=in.nextInt();
//			if (n<1) continue;
			boolean has1=false,has2=false,has=false;;
			Arrays.fill(f, 0);
			for(int i=1;i<=n;i++){
				int k=in.nextInt();
				f[k]++;
				if (f[k]>1&&k>0) has1=true;
				if (f[k]>2) has2=true;
			}
			v.clear();
			for(int i=0;i<maxl;i++)
				if (f[i]>0)
					v.add(i);
//			for(Integer e:v.toArray(new Integer[0]))
//				out.print(e+" ");
			if (v.get(0)==0&&has1||has2){
				out.println("YES");continue;
			}
			Integer[] a=v.toArray(new Integer[0]);
			for(int i=0;i<a.length-1;i++)
				if (!has)
					for(int j=a.length-1;j>i;j--){
						int k=Arrays.binarySearch(a, a[j]-a[i]);
						if (k>i&&k<j){
							has=true;break;
						}
							
					}
			out.println(has?"YES":"NO");
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
