/**
 * 2015年7月26日 下午2:54:58
 * PrjName:0726-08
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
class Pair<A extends Comparable,B extends Comparable>{
	final A f;
	final B s;
	Pair(A first,B second){
		f=first;
		s=second;
	}
	A a(){
		return f;
	}
	B b(){
		return s;
	}
	public int Compare(Pair<A,B> p1,Pair<A,B> p2){
		if (p1.f==p2.f){
			return p1.s.compareTo(p2.s);
		}
		return -p1.f.compareTo(p2.f);
	}
	
	
	/*
	 * 
	 public static final Comparator<Vector<String>> VECTOR_COMPARATOR = new Comparator<Vector<String>>() {
  @Override
  public int compare(Vector<String> v1, Vector<String> v2) {
   if (v1 == null || v1.size() < 2) {
    return -1;
   }
   if (v2 == null || v2.size() < 2) {
    return 1;
   }
   return Integer.valueOf(v2.get(1)) - Integer.valueOf(v1.get(1));
  }
	 * 
	 */
}
class Sort{
	public static final Comparator<Pair<Integer,Integer>> VECTOR_COMPARATOR=new Comparator<Pair<Integer,Integer>>() {
		@Override
		public int compare(Pair<Integer,Integer> p1,Pair<Integer,Integer> p2){
			if (p1.f==p2.f){
				return p1.s.compareTo(p2.s);
			}
			return -p1.f.compareTo(p2.f);
		}
	};
}
public class Main {
	static int n,m,q;
	static int[] f;
	static Vector<Pair<Integer,Integer>> vec=new Vector<Pair<Integer,Integer>>();
	static Vector<Integer> v=new Vector<Integer>();
	static int bisearch(int key){
		int l=1,r=m+1,mid=1,a;
		while(l<r-1){
			mid=(l+r)>>1;
			a=vec.get(mid-1).a();
			if (a<key)
				r=mid;
			else
				l=mid;
		}
		return l-1;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			n=(int)in.nval;
			in.nextToken();
			m=(int)in.nval;
			in.nextToken();
			q=(int)in.nval;
			vec.clear();
			for(int i=1;i<=m;i++){
				in.nextToken();
				int u=(int)in.nval;
				in.nextToken();
				int v=(int)in.nval;
				vec.add(new Pair<Integer,Integer>(u,v));
			}
			Collections.sort(vec, Sort.VECTOR_COMPARATOR);
			//for(int i=0;i<m;i++) out.println((i+1)+":"+vec.get(i).a()+" "+vec.get(i).b());
			for(int i=1;i<=q;i++){
				in.nextToken();
				int p=(int)in.nval;
				f=new int[p+1];
				int h=bisearch(p);
				v.clear();
				for(int j=0;j<=h;j++){
					int k=vec.get(j).b();
					if (k<p) v.add(k);
				}
				if (v.size()<2){
					out.println(0);
					continue;
				}
				Collections.sort(v);
				out.println(p-v.get(1));
				//out.println(vec.get(bisearch(p)).a()+" "+vec.get(bisearch(p)).b());
			}
		}
		out.flush();
		out.close();
	}

}
