/**
 * Oct 31, 2015 12:49:12 PM
 * PrjName: 1031-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static String[] str;
	static AC ac=new AC();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
        while (T-- > 0) {
            int n = in.nextInt(),ans=-1;
            str=new String[n+1];
//            ac.clear();
            for(int i=1;i<=n;i++){
            	str[i]=new String(in.next());
            	/*if (i>1){
            		ac.build();
            		if (ac.query(str[i])<i-1)
            			ans=Math.max(ans, i);
            	}
            	ac.insert(str[i]);*/
            }
            
            for (int i = 2; i <= n; i++){
            	ac.clear();
            	for(int j=1;j<i;j++)
            		ac.insert(str[j]);
            	ac.build();
            	int tmp=ac.query(str[i]);
//            	out.println(i+":"+tmp);
            	if (tmp<i-1)
            		ans=Math.max(ans, i);
            }
//            ac.build();
			out.println("Case #"+(++cas)+": "+ans);
		}
		out.flush();
		out.close();
	}
}
class AC {
    final int maxl = 2010, maxc = 26;
    final char fstc = 'a';
    int root, L;
    int[][] next;
    int[] fail, end;
    Queue<Integer> que = new LinkedList<Integer>();

    AC() {
        next = new int[maxl][maxc];
        fail = new int[maxl];
        end = new int[maxl];
        L = 0;
        root = newnode();
    }

    void clear() {
        Arrays.fill(fail, 0);
        Arrays.fill(end, 0);
        L = 0;
        root = newnode();
    }

    int newnode() {
        Arrays.fill(next[L], -1);
        end[L++] = 0;
        return L - 1;
    }

    void insert(String str) {
        int now = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (next[now][ch - fstc] == -1)
                next[now][ch - fstc] = newnode();
            now = next[now][ch - fstc];
        }
        end[now]++;
    }

    void build() {
        que.clear();
        fail[root] = root;
        for (int i = 0; i < maxc; i++)
            if (next[root][i] == -1)
                next[root][i] = root;
            else {
                fail[next[root][i]] = root;
                que.add(next[root][i]);
            }
        while (!que.isEmpty()) {
            int now = que.poll();
            for (int i = 0; i < maxc; i++)
                if (next[now][i] == -1)
                    next[now][i] = next[fail[now]][i];
                else {
                    fail[next[now][i]] = next[fail[now]][i];
                    que.add(next[now][i]);
                }
        }
    }

    int query(String str) {
        int now = root, res = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            now = next[now][ch - fstc];
            int tmp = now;
            while (tmp != root) {
                res += end[tmp];
                 end[tmp] = 0;
                tmp = fail[tmp];
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
