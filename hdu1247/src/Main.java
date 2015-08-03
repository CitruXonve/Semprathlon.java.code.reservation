/**
 * 2015年7月15日 下午2:53:28
 * PrjName:hdu1247
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;

class Trie {  
    Trie[] child;   
    Trie() {  
        child = new Trie[26];
    }  
    void insert(String str) {
        Trie cur = this;
        for (char ch : str.toCharArray()) {
            int idx = ch - 'a';
            if (cur.child[idx] == null) {
                cur.child[idx] = new Trie();
            }
            cur = cur.child[idx];
        }
    }
    boolean query(String str) {
        Trie cur = this;
        for (char ch : str.toCharArray()) {
            int idx = ch - 'a';
            if (cur.child[idx] == null) {
                return false;
            }
            cur = cur.child[idx];
        }
        return true;
    }
}  

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(
                new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String[] str=new String[50001];
        Trie tr=new Trie();
        int n=0;
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            str[n++]=new String(in.sval);
            tr.insert(in.sval);
        }
        for(int k=0;k<n;k++){
        	String s=str[k];
            int len=s.length();
            for(int i=1;i<len;i++){
                String s1=s.substring(0, i);
                String s2=s.substring(i, len);
                if (tr.query(s1)&&tr.query(s2))
                    out.println(s);
            }
        }
            
        out.flush();
        out.close();
    }

}