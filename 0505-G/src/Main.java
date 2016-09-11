/**
 * May 5, 2016 6:59:07 PM
 * PrjName: 0505-G
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Vector<Pair<Integer,String>> vec=new Vector<Pair<Integer,String>>();
	static void handle(int l,int r,int p,PrintWriter out){
		int L=vec.get(l).left,R=vec.get(r).left;
		if ((R-L)%2==0&&(L+R)/2==p){
			out.println("middle of "+vec.get(l).right+" and "+vec.get(r).right);
			return;
		}
		if (p<=(L+R)/2){
			for(int i=1;i<=p-L;i++)
				out.print("right of ");
			out.println(vec.get(l).right);
			return;
		}
		for(int i=1;i<=R-p;i++)
			out.print("left of ");
		out.println(vec.get(r).right);
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		for(int i=0;i<n;i++){
			String s=new String(in.next());
			if (s.charAt(0)!='?')
				vec.add(Pair.make_pair(i, s));
		}
		int q=in.nextInt();
		while(q-->0){
			int p=in.nextInt();
			p--;
			if (p<vec.get(0).left){
				for(int k=1;k<=vec.get(0).left-p;k++)
					out.print("left of ");
				out.println(vec.get(0).right);
				continue;
			}
			if (p>vec.get(vec.size()-1).left){
				for(int k=1;k<=p-vec.get(vec.size()-1).left;k++)
					out.print("right of ");
				out.println(vec.get(vec.size()-1).right);
				continue;
			}
			for(int i=0;i<vec.size();i++)
				if (vec.get(i).left==p){
					out.println(vec.get(i).right);
					break;
				}
				else if (i<vec.size()-1&&vec.get(i).left<p&&vec.get(i+1).left>p){
//					out.print("middle");
					handle(i, i+1, p, out);
					break;
				}
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