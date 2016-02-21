/**
 * Oct 31, 2015 3:22:05 PM
 * PrjName: 1031-02-2
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static String[] str;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
        while (T-- > 0) {
            int n = in.nextInt(),ans=-1;
            str=new String[n+1];
            for(int i=1;i<=n;i++){
            	str[i]=new String(in.next());
            }
            for(int i=n;i>=2;i--)
            	if (KMP.Index(ans>0?str[ans]:str[i], str[i-1])<0){
            		ans=Math.max(ans, i);
            		for(int j=n;j>ans;j--)
            			if (KMP.Index(str[j], str[i-1])<0){
            				ans=Math.max(ans, j);
            				break;
            			}
            	}
			out.println("Case #"+(++cas)+": "+ans);
		}
		out.flush();
		out.close();
	}
}
class KMP {
    static int next[];

    static void getNext(String T) {
        next = new int[T.length() + 1];
        int j = 0, k = -1;
        next[0] = -1;
        while (j < T.length())
            if (k == -1 || T.charAt(j) == T.charAt(k))
                next[++j] = ++k;
            else
                k = next[k];
    }

    static int Index(String S, String T) {
        int i = 0, j = 0;
        getNext(T);

        for (i = 0; i < S.length() && j < T.length(); i++) {
            while (j > 0 && S.charAt(i) != T.charAt(j))
                j = next[j];
            if (S.charAt(i) == T.charAt(j))
                j++;
        }
        if (j == T.length())
            return i - T.length();
        else
            return -1;
    }

    static int Count(String S, String T) {
        int res = 0, j = 0;
        if (S.length() == 1 && T.length() == 1) {
            if (S.charAt(0) == T.charAt(0))
                return 1;
            else
                return 0;
        }
        getNext(T);
        for (int i = 0; i < S.length(); i++) {
            while (j > 0 && S.charAt(i) != T.charAt(j))
                j = next[j];
            if (S.charAt(i) == T.charAt(j))
                j++;
            if (j == T.length()) {
                res++;
                j = next[j];
            }
        }
        return res;
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
