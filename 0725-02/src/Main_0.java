/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 2015年7月25日 下午7:16:39
 * PrjName:0725-02
 * @ Semprathlon
 */
/*
class Trie{
	Trie[] wd;
	boolean is;
	Trie(){
		wd=new Trie[26];
	}
	void insert(String s,boolean st){
		int len=s.length();
		Trie T=this;
		for(int i=0;i<len;i++){
			int ch=s.charAt(i)-'a';
			if (T.wd[ch]==null)
				T.wd[ch]=new Trie();
			T=T.wd[ch];
			T.is=st;
		}
	}
	int[] query(String s,boolean st){
		int len=s.length();
		Trie T=this;
		ArrayList<Integer> v=new ArrayList<Integer>();
		for(int k=0;k<len;k++){
			int i=0;
			for(i=k;i<len;i++){
				int ch=s.charAt(i)-'a';
				if (T.wd[ch]==null) break;
				T=T.wd[ch];
				if (T.is!=st) break;
			}
			if (i>k)
				v.add(i);
		}
		
		int[] res=new int[v.size()];
		for(int i=0;i<v.size();i++)
			res[i]=v.get(i);
		return res;
	}
}
*/
public class Main_0 {
	/*static Trie tr;
	static String s0="anniversary";
	static PrintWriter out=new PrintWriter(System.out);
	static void init(){
		tr=new Trie();
		tr.insert(s0, false);
		for(int i=1;i<s0.length();i++)
			tr.insert(s0.substring(i),true);
	}
	static boolean solve(String s){
		int[] v1=new int[0];
		int[] v2=new int[0];
		int[] v3=new int[0];
		int len=s.length();
		v1=tr.query(s, false);
		int n1=v1.length;
		//out.println("n1="+n1);
		for(int i=0;i<n1;i++){
			int p=v1[i];
			//out.println(p);
			if  (p<s.length()-1) v2=tr.query(s.substring(p+1), true);
			
			int n2=v2.length;
			for(int j=0;j<n2;j++){
				int q=v2[j];
				//out.println("\t"+q);
				if (p+q<s.length()-1)
					v3=tr.query(s.substring(p+q), true);
				if (v3.length>0) return true; 
			}
		}
		//out.flush();//out.close();
		return false;
	}*/
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		
		init();
		int T=in.nextInt();
		while(T-->0){
			//v1.clear();
			//v2.clear();
			String s=new String(in.next());
			if (solve(s))
				out.println("YES");
			else
				out.println("NO");
		}
		out.flush();
		out.close();
	}*/

}