/**
 * May 1, 2016 1:11:58 PM
 * PrjName: 0501-B
 * @semprathlon
 */

import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Main {
	static BigInteger a;
	static BigInteger b;
	static BigInteger parse(BigInteger a,String s1,int p1){
		a=new BigInteger(s1.substring(0, p1));
		a=a.multiply(BigInteger.valueOf(10).pow(s1.length()-p1-1));
		a=a.add(new BigInteger(s1.substring(p1+1,s1.length())));
		return a;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
//		Scanner in=new Scanner(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int cas=0;
		while(in.hasNext()){
			String s1=new String(in.next());
			String s2=new String(in.next());
			a=BigInteger.ZERO;b=BigInteger.ZERO;
			int p1=0,p2=0;
			for(;p1<s1.length();p1++)
				if (s1.charAt(p1)=='.')
					break;
			for(;p2<s2.length();p2++)
				if (s2.charAt(p2)=='.')
					break;
			a=parse(a, s1, p1);
			b=parse(b, s2, p2);
			if (s1.length()-p1>s2.length()-p2){
				b=b.multiply(BigInteger.valueOf(10).pow(s1.length()-p1-s2.length()+p2));
			}
			else{
				a=a.multiply(BigInteger.valueOf(10).pow(s2.length()-p2-s1.length()+p1));
			}
//			out.println(a+","+b);
			if (a.compareTo(b)<0)
				out.println("Case "+(++cas)+": "+"Smaller");
			else  if (a.compareTo(b)>0)
				out.println("Case "+(++cas)+": "+"Bigger");
			else 
				out.println("Case "+(++cas)+": "+"Same");
//			out.println(s1.substring(0, p1)+","+s1.substring(p1+1,s1.length()));
			
//			out.println(a);
			//out.println(s1.substring(p1+1,s1.length()-1));
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