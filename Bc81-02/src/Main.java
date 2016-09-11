import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=1010;
	static long[] h=new long[maxn];
	static long[] v=new long[maxn];
	static long[][] a=new long[maxn][maxn];
//	static Vector<Pair> vh=new Vector<Pair>();
//	static Vector<Pair> vv=new Vector<Pair>();
//	static int[] sh=new int[maxn];
//	static int[] sv=new int[maxn];
	/*static void swap(int[] f,int x,int y){
		if (f[x]==x&&f[y]==y){
			f[x]=y;f[y]=x;
		}
		else if (f[x]!=y&&f[y]==y){
			f[f[x]]
		}
	}*/
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		boolean has=false;
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			int q=in.nextInt();
			for(int i=1;i<=n;i++)
				for(int j=1;j<=m;j++)
					a[i][j]=in.nextLong();
			Arrays.fill(h, 0);
			Arrays.fill(v, 0);
//			for(int i=1;i<=n;i++)
//				sh[i]=sv[i]=i;
//			vh.clear();vv.clear();
			long tmp=0;
			int x,y;
			for(int t=0;t<q;t++){
				int k=in.nextInt();
				if (k==1){
					x=in.nextInt();y=in.nextInt();
					tmp=h[x];h[x]=h[y];h[y]=tmp;
					for(int j=1;j<=m;j++){
						tmp=a[x][j];a[x][j]=a[y][j];a[y][j]=tmp;
					}
//					vh.add(new Pair(x,y));
				}
				else if (k==2){
					x=in.nextInt();y=in.nextInt();
					tmp=v[x];v[x]=v[y];v[y]=tmp;
					for(int i=1;i<=n;i++){
						tmp=a[i][x];a[i][x]=a[i][y];a[i][y]=tmp;
					}
//					vv.add(new Pair(x,y));
				}
				else if (k==3){
					h[in.nextInt()]+=in.nextLong();
				}
				else if (k==4){
					v[in.nextInt()]+=in.nextLong();
				}
			}
			//out.println("--");
			for(int i=1;i<=n;i++){
				/*if (has) */out.println();
				for(int j=1;j<=m;j++)
					out.print(a[i][j]+h[i]+v[j]+(j<m?" ":""));
				has=true;
			}
			//out.println("--");
		}
		out.flush();
		out.close();
	}
}
class Pair{
	int l,r;
	Pair(){}
	Pair(int _l,int _r){
		l=_l;r=_r;
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