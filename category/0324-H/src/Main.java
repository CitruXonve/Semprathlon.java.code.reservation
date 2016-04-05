import java.io.*;
import java.util.*;
public class Main {
	static Map<String, Integer> mp=new HashMap<String, Integer>();
	static Set<String> st=new HashSet<String>();
//	static Vector<String> v=new Vector<String>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			mp.clear();
			st.clear();
			for(int i=1;i<=n;i++){
				String s=in.next();
				int k=in.nextInt();
				mp.put(s, k);
			}
//			v.clear();
			for(Map.Entry<String,Integer> e1:mp.entrySet()){
				for(Map.Entry<String,Integer> e2:mp.entrySet()){
					if (e1.getKey().compareTo(e2.getKey())==0) continue;
					for(Map.Entry<String,Integer> e3:mp.entrySet()){
						if (e1.getKey().compareTo(e3.getKey())==0||e2.getKey().compareTo(e3.getKey())==0) continue;
						if (e1.getValue()+e2.getValue()==e3.getValue()/*||e1.getValue()+e3.getValue()==e2.getValue()||e2.getValue()+e3.getValue()==e1.getValue()*/)
							st.add(e3.getKey());
						for(Map.Entry<String,Integer> e4:mp.entrySet()){
							if (e1.getKey().compareTo(e4.getKey())==0||e2.getKey().compareTo(e4.getKey())==0||e3.getKey().compareTo(e4.getKey())==0) continue;
							if (e1.getValue()+e2.getValue()+e3.getValue()==e4.getValue()
									/*||e1.getValue()+e3.getValue()+e4.getValue()==e2.getValue()
									||e1.getValue()+e2.getValue()+e4.getValue()==e3.getValue()
									||e2.getValue()+e3.getValue()+e4.getValue()==e1.getValue()*/)
								st.add(e4.getKey());
						}
					}
				}
			}
			out.println(st.size());
			for(Iterator<String> it=st.iterator();it.hasNext();)
				out.println(it.next());
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
