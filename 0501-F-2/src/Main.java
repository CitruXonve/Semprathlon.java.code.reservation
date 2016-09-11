/**
 * May 1, 2016 3:30:06 PM
 * PrjName: 0501-F-2
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Map<Long, Integer> mp=new HashMap<Long,Integer>();
	static void init(){
		mp.clear();
		for(int i=1;i<100;i++){
			long tmp=i;
			if (!mp.containsKey(tmp))
				mp.put(tmp, i);
			for(int j=i-1;j>=2;j--){
				tmp*=j;
				if (tmp>1000000000L) break;
				if (!mp.containsKey(tmp))
					mp.put(tmp, i);
			}
		}
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
//		out.println(mp.size());
		/*for(Map.Entry<Long, Integer> ent:mp.entrySet()){
			out.println(ent.getKey()+","+ent.getValue());
		}*/
		int cas=0;
		while(in.hasNext()){
			long n=in.nextLong();
			if (!mp.containsKey(n)||n==1){
				out.println("Case "+(++cas)+": Impossible");
				continue;
			}
			int l=mp.get(n),r=l;
//			out.println(n+","+l);
			for(;l>1;l--)
				if (n>1){
					n/=l;
				}
				else
					break;
			out.println("Case "+(++cas)+": "+r+" "+(l<1?1:l));
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
