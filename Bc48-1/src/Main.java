import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
public class Main {
	static boolean check(String str){
		int res=0;
		int i,len=str.length();
		for(i=0;i<len-1;i++)
			if (str.charAt(i)=='w'||str.charAt(i)=='v'&&str.charAt(i+1)=='v'){
				res|=1;break;
			}
		for(;i<len;i++)
			if (str.charAt(i)=='y'){
				res|=2;break;
			}
		for(;i<len;i++)
			if (str.charAt(i)=='h'){
				res|=4;break;
			}
		return res==7;
	}
    public static void main(String[] args){
        // TODO Auto-generated method stub
         InputReader in = new InputReader(System.in)  ;
         PrintWriter out = new PrintWriter(System.out) ;
         int T=in.nextInt();
         while(T-->0){
             String str=in.next();
             out.println(check(str)?"Yes":"No");
             out.flush();
         }
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