/**
 * May 1, 2016 2:19:30 PM
 * PrjName: 0501-F
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	/*static void init(PrintWriter out){
		long res=1;
		for(int i=2;i<=100;i++){
			res*=i;
			if (res>=100000000000L) break;
			out.println(i+","+res);
		}
	}*/
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int cas=0;
		while(in.hasNext()){
			int n=in.nextInt();
			if (n==1){
				out.println("Case "+(++cas)+": Impossible");
				continue;
			}
			int sqrt=(int)Math.sqrt(n),first=1;
			for(int i=sqrt+1;i>=1;i--){
				if (n%i!=0){
					break;
				}
				else
					first=i;
			}
			int ans=1;
			for(int i=first+1;i<=n&&n>1;i++){
				if (n%i!=0&&n>1){
					ans=-1;break;
				}
				n/=i;
				ans=i;
			}
			out.println(ans+","+first+","+n);
			if (ans>=1)
				out.println("Case "+(++cas)+": "+ans+" "+(first>1?first-1:1));
			else
				out.println("Case "+(++cas)+": Impossible");
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