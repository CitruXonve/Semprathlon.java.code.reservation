/**
 * Mar 25, 2016 8:58:00 PM
 * PrjName: ECNA2015-B
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	static int r,c,n,m;
	static Map<Integer, Integer> mp1=new HashMap<Integer, Integer>();
	static Map<Integer, Integer> mp2=new HashMap<Integer, Integer>();
	static Vector<Pt> v1=new Vector<Pt>();
	static Vector<Pt> v2=new Vector<Pt>();
	static BIT[] bx1,bx2;
	final static int w=15,h=9;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			r=in.nextInt();
			c=in.nextInt();
			n=in.nextInt();
			m=in.nextInt();
			bx1=new BIT[n];
			bx2=new BIT[m];
			mp1.clear();mp2.clear();
			v1.clear();v2.clear();
			for(int i=1;i<=n;i++){
				int x=in.nextInt();
				int y=in.nextInt();
				v1.add(new Pt(x,y,i));
				mp1.put(x, 0);
			}
			int cx1=0;
			for(Map.Entry<Integer, Integer> ent:mp1.entrySet()){
				ent.setValue(cx1);
				bx1[cx1]=new BIT(c);
				cx1++;
			}
			for(int i=1;i<=n;i++){
				int x=v1.get(i).x;
				int y=v1.get(i).y;
				bx1[mp1.get(x)].add(y, 1);
			}
			
			for(int i=1;i<=m;i++){
				int x=in.nextInt();
				int y=in.nextInt();
				v2.add(new Pt(x,y,n+i));
				mp2.put(x, 0);
			}
			
			int cx2=0;
			for(Map.Entry<Integer, Integer> ent:mp2.entrySet()){
				ent.setValue(cx2);
				bx2[cx2]=new BIT(c);
				cx2++;
			}
			
			for(int i=1;i<=m;i++){
				int x=v2.get(i).x;
				int y=v2.get(i).y;
				bx2[mp2.get(x)].add(y, 1);
			}
			
			int ans=0x3fffffff;
			for(int i=1;i<=n;i++){
				int tmp=0;
				if (!v1.get(i).get_center().inside(0,0,r, c)) tmp++;
				for(int j=i+1;j<=n;j++){
					if (!v1.get(j).get_center().inside(0,0,r, c)) tmp++;
					int x1=v1.get(i).get_center().x;
					int y1=v1.get(i).get_center().y;
					int x2=v1.get(j).get_center().x;
					int y2=v1.get(j).get_center().y;
					if (x1>x2){
						int t=x1;x1=x2;x2=t;
					}
					if (y1>y2){
						int t=y1;y1=y2;y2=t;
					}
					for(int k=1;k<=n;k++){
						if (i==j||i==k) continue;
					}
				}
			}
		}
		out.flush();
		out.close();
	}

}
class Pt{
	int x,y,id;
	final int w=15,h=9;
	Pt(){}
	Pt(int _x,int _y,int _id){
		x=_x;y=_y;id=_id;
	}
	static class Comp implements Comparator<Pt>{
		public int compare(Pt p1,Pt p2){
			return Integer.compare(p1.x, p2.x);
		}
	}
	Pt get_center(){
		int a=(this.x<<1+w)>>1;
		int b=(this.y<<1+h)>>1;
		return new Pt(a,b,0);
	}
	boolean inside(int x1,int y1,int x2,int y2){
		return x>=x1&&x<x2&&y>=y1&&y<y2;
	}
}
class BIT{
	int[] data;
	int sz;
	BIT(){}
	BIT(int _sz){
		sz=_sz;
		data=new int[sz];
	}
	int lowbit(int x){
		return x&(-x);
	}
	void add(int x,int v){
		for(;x<sz;x+=lowbit(x))
			data[x]+=v;
	}
	int sum(int x){
		int res=0;
		for(;x>0;x-=lowbit(x))
			res+=data[x];
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
