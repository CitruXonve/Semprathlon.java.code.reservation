/**
 * May 4, 2016 8:06:20 PM
 * PrjName: 0504-C
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	static Map<String, Pair<String,Integer>> mp=new HashMap<String, Pair<String,Integer>>();
	static Vector<String> v1=new Vector<String>();
	static Vector<String> v2=new Vector<String>();
	static Vector<String> v3=new Vector<String>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			mp.clear();
			String s1=in.nextLine();
			String s2=in.nextLine();
			s1=s1.substring(1, s1.length()-1);
			for(String s:s1.split(",")){
				int p=0;
				for(;p<s.length();p++)
					if (s.charAt(p)==':')
						break;
				String t=s.substring(0,p);
				mp.put(t, Pair.make_pair(s.substring(p+1), 2));
			}
			
			v1.clear();
			v2.clear();
			v3.clear();
			s2=s2.substring(1, s2.length()-1);
			for(String s:s2.split(",")){
				
				int p=0;
				for(;p<s.length();p++)
					if (s.charAt(p)==':')
						break;
				String t=s.substring(0,p);
				if (mp.containsKey(t)){
					String tmp=mp.get(t).left;
					mp.remove(t);
					if (s.substring(p+1).compareTo(tmp)==0)
						mp.put(t, Pair.make_pair(tmp, 0));
					else
						v3.add(t);
				}
				else
					v1.add(t);
			}
			
			for(Map.Entry<String, Pair<String,Integer>> ent:mp.entrySet()){
				if (ent.getValue().right==2)
					v2.add(ent.getKey());
			}
			
			if (v1.size()>0){
				out.print('+');
				for(int i=0;i<v1.size();i++)
					out.print((i>0?",":"")+v1.get(i));
				out.println();
			}
			
			if (v2.size()>0){
				out.print('-');
				for(int i=0;i<v2.size();i++)
					out.print((i>0?",":"")+v2.get(i));
				out.println();
			}
			
			if (v3.size()>0){
				out.print('*');
				for(int i=0;i<v3.size();i++)
					out.print((i>0?",":"")+v3.get(i));
				out.println();
			}
			
			if (v1.size()==0&&v2.size()==0&&v3.size()==0)
				out.println("No changes");
			out.println();
		}
		out.flush();
		out.close();
	}

}
class Pair<TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>> implements Comparable<Pair<TypeA, TypeB>> {
    TypeA left;
    TypeB right;
 
    public Pair(TypeA first, TypeB second) {
        left = first;
        right = second;
    }
 
    public static <TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>> Pair<TypeA, TypeB> make_pair(
            TypeA first, TypeB second) {
        return new Pair<TypeA, TypeB>(first, second);
    }
 
    public String toString() {
        return "[" + left.toString() + "," + right.toString() + "]";
    }
 
    boolean equals(Pair<TypeA, TypeB> p) {
        return this.left.equals(p.left) && this.right.equals(p.right);
    }
 
    public int compareTo(Pair<TypeA, TypeB> p) {
        if (this.left.equals(p.left))
            return this.right.compareTo(p.right);
        return this.left.compareTo(p.left);
    }
}
 
class Pair_Comp<TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>>
        implements Comparator<Pair<TypeA, TypeB>> {
    public int compare(Pair<TypeA, TypeB> p1, Pair<TypeA, TypeB> p2) {
        return p1.compareTo(p2);
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