import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;

/**
 * 2015年7月25日 下午7:16:39
 * PrjName:0725-02
 * @ Semprathlon
 */
class Pair<A,B>{
	final A f;
	final B s;
	Pair(A first,B second){
		f=first;
		s=second;
	}
	A a(){
		return this.f;
	}
	B b(){
		return this.s;
	}
}
class Trie{
	Trie[] wd;
	int h;
	Trie(){
		wd=new Trie[26];
		h=-1;
	}
	void insert(String s,int p){
		int len=s.length();
		Trie T=this;
		for(int i=0;i<len;i++){
			int ch=s.charAt(i)-'a';
			if (T.wd[ch]==null)
				T.wd[ch]=new Trie();
			//T.h=Math.min(T.h,p);
			T=T.wd[ch];
			if (T.h>=0) T.h=Math.min(T.h,p);
			else T.h=p;
			//T=T.wd[ch];
		}
	}
	int query(String s){
		int len=s.length();
		Trie T=this;
		int res=-1,i;
			for(i=0;i<len;i++){
				int ch=s.charAt(i)-'a';
				if (T.wd[ch]==null) break;
				//if (T.wd[ch].is==st)v.add(i);
				//if (i==len-1) res=T.h;
				T=T.wd[ch];if (i==len-1) res=T.h;
			}
		return res+1;
		//return new Pair<Integer,Integer>(res+1, i);
	}
}

public class Main {
	static Trie tr;
	static String s0="anniversary";
	static PrintWriter out=new PrintWriter(System.out);
	static void init(){
		tr=new Trie();
		
	}
	static boolean solve(String s){
		int len=s0.length();
		int l=s.length();
		//for(int i=0;i<l;i++)
		for(int i=l;i>=0;--i)
			tr.insert(s.substring(i),i);
		//for(int i=l;i>=0;--i)
			//tr.insert(s.substring(0, l), i);
		int p1,p2,p3;
		p1=tr.query(s0);
		if (p1>0) return true;
	//out.println(p1);
		for(int i=len-1;i>0;--i){
			p1=tr.query(s0.substring(0, i));
			p2=tr.query(s0.substring(i));
	//out.println("\t"+p2);
			if (p1==0) continue;
			if (p1+i<=p2)  return true;
			for(int j=len-1;j>i;--j){
				p2=tr.query(s0.substring(i,j));
				p3=tr.query(s0.substring(j));
	//out.println("\t\t"+p3);
				if (p2==0) continue;
				if (p1+i<=p2&&p2+j-i<=p3) return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		
		int T=in.nextInt();
		while(T-->0){
			//v1.clear();
			//v2.clear();
			tr=new Trie();
			String s=new String(in.next());
			if (solve(s))
				out.println("YES");
			else
				out.println("NO");
		}
		out.flush();
		out.close();
	}

}

class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream){
           reader = new BufferedReader(new InputStreamReader(stream), 32768);
           tokenizer = null;
    }

    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (IOException e) {
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

}