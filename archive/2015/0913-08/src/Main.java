/** Sep 13, 2015 11:21:52 AM
 * PrjName:0913-08
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static int[] a;
	static Tr tr;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			a=new int[n+1];
			for(int i=1;i<=n;i++)
				a[i]=in.nextInt();
			tr=new Tr(n+1);
			tr.build(a, 1, n, "");
			int m=in.nextInt();
			while(m-->0){
				out.println(tr.get(in.nextInt()));
			}
		}
		out.flush();
		out.close();
	}

}
class Tr{
	String[] s;
	int size;
	Tr(int sz){
		s=new String[sz+1];
		size=sz;
	}
	void build(int[] a,int l,int r,String str){
		if (l>r) return;
		int h=a[l],p=r+1;
		s[h]=new String(str);
		for(int i=l+1;i<=r;i++)
			if (a[i]>h){
				p=i;break;
			}
		build(a,l+1,p-1,str+"E");
		build(a,p,r,str+"W");
	}
	String get(int n){
		return s[n];
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