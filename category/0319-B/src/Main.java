/**
 * Mar 19, 2016 5:39:41 PM
 * PrjName: 0319-B
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Trie h;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		h=new Trie();
		int n=in.nextInt();
		int q=in.nextInt();
		for(int i=1;i<=q;i++){
			h.insert(h,in.next(), in.next());
		}
		if (h.ch[0]!=null)
			out.println(h.ch[0].search(n,out));
		else
			out.println(0);
		out.flush();
		out.close();
	}

}
class Trie{
	boolean islink;
	Trie ch[];
	Trie ln;
	Trie(){
		islink=true;
		ch=new Trie[6];
		for(int i=0;i<6;i++)
			ch[i]=null;
	}
	Trie(Trie p){
		islink=true;
		ln=p;
		ch=new Trie[6];
		for(int i=0;i<6;i++)
			ch[i]=null;
	}
	void insert(Trie h,String s1,String s2){
		if (this.ch[s1.charAt(0)-'a']==null)
			this.ch[s1.charAt(0)-'a']=new Trie(h);
		if (this.ch[s2.charAt(0)-'a']==null)
			this.ch[s2.charAt(0)-'a']=new Trie(h);
		this.ch[s1.charAt(0)-'a'].islink=false;
		if (this.ch[s1.charAt(0)-'a'].ch[s2.charAt(0)-'a']==null){
			
			this.ch[s1.charAt(0)-'a'].ch[s2.charAt(0)-'a']=new Trie(this.ch[s2.charAt(0)-'a']);
		}
	}
	int search(int step,PrintWriter out){
		if (step==1)
			return 1;
		int res=0;
		if (!this.islink)
			for(int i=0;i<6;i++)
				res+=(this.ch[i]!=null?this.ch[i].search(step-1,out):0);
		else
			res+=(this.ln!=null?this.ln.search(step-1, out):0);
		//\out.println(step+","+res);
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
