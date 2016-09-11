import java.io.*;
import java.util.*;
public class Main {
	static Obj[] a;
	static long[] s;
	static int check(int p,int n){
		long s0=s[n-1],s1=s[p-1],s2=s[n-1]-s[p];
		if ((s1<<1)<s0&&(s2<<1)<=s0) return 0;
		return ((s1<<1)>=s0?1:-1);
	}
	static int bisearch(int n){
		int l=1,r=n-2;
		while(l<r){
			int mid=(l+r)>>1;
			int tmp=check(mid,n);
			if (tmp==0) return mid;
			else if (tmp>0) r=mid-1;
			else l=mid+1;
		}
		return l;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
//		int T=in.nextInt();
		while(in.hasNext()){
			int n=in.nextInt();
			a=new Obj[n];
			s=new long[n];
			for(int i=0;i<n;i++){
				a[i]=new Obj();
				a[i].x=in.nextInt();
			}
			for(int i=0;i<n;i++)
				a[i].w=in.nextInt();
			Arrays.sort(a, new Obj.Comp());
			s[0]=a[0].w;
			for(int i=1;i<n;i++)
				s[i]=s[i-1]+a[i].w;
			/*for(int i=0;i<n;i++)
				out.print(a[i].x+" ");out.println();
			for(int i=0;i<n;i++)
				out.print(s[i]+" ");out.println();*/
				
			out.println(a[bisearch(n)].x);
		}
		out.flush();
		out.close();
	}
}
class Obj{
	long x,w;
	/*public int compareTo(Object o){
		Obj obj=(Obj) o;
		return Long.compare(this.x, obj.x);
	}*/
	static class Comp implements Comparator<Obj>{
		public int compare(Obj o1,Obj o2){
			if (o1.x==o2.x)
				return 0;
			if (o1.x<o2.x)
				return -1;
			return 1;
		}
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