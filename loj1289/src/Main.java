/**
 * Apr 26, 2016 8:17:08 PM
 * PrjName: loj1289
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	final static long mod = 1L << 32;
	final static long mask=0xffffffffL;
	final static int maxc = 5800000, maxn = 100000001;

	static int[] pri;
	static int[] fstp;
	static int[] mul;
//	static double[] log_pri;

	static Map<Integer, Integer> mp = new HashMap<Integer, Integer>();

	static void filter_prime() {
		pri = new int[maxc];
//		log_pri=new double[maxc];
		fstp = new int[(maxn>>5)+1];
		mul=new int[maxc];
		mul[0]=1+Integer.MIN_VALUE;
		for (int i = 2; i < maxn; i++) {
			if ((fstp[i>>5]>>(i&0x1f)&1)==0) {
				pri[++pri[0]] = i;
//				log_pri[pri[0]]=Math.log(i);
				mul[pri[0]]=(int)(((mul[pri[0]-1]-Integer.MIN_VALUE)*(long)i&mask)+Integer.MIN_VALUE);//&mask;
			}
			for (int j = 1; j <= pri[0] && i * pri[j] < maxn; j++) {
				int k = i * pri[j];
				fstp[k>>5]|= 1<<(k&0x1f);
			}
		}
		/*for(int i=2;i<maxn;i++)
			if (fstp[i]==false){
				pri[++pri[0]]=i;
				for(int j=i<<1;j<maxn;j+=i)
					fstp[j]=true;
			}
			*/
	}
	
	static int bisearch(int l,int r,int k){
		while(l<r){
			int mid=(l+r+1)>>1;
			if (pri[mid]>=k) r=mid-1;
			else l=mid;
		}
		return l;
	}

	static Vector<Pair<Integer, Integer>> get_prime_factor(int n) {
		Vector<Pair<Integer, Integer>> res = new Vector<Pair<Integer, Integer>>();
		res.clear();
		for (int i = 1; i <= pri[0] && pri[i] * pri[i] <= n; i++)
			if (n % pri[i] == 0) {
				int cnt = 0;
				while (n % pri[i] == 0) {
					n /= pri[i];
					cnt++;
				}
				res.add(Pair.make_pair(pri[i], cnt));
			}
		if (n > 1)
			res.add(Pair.make_pair(n, 1));
		return res;
	}

	static long pow_mod(long n, int m) {
		long res = 1L;
		while (m > 0) {
			if ((m & 1) > 0)
				res = res * n;
			n = n * n;
			m >>= 1;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		long startTime=System.currentTimeMillis();
		filter_prime();
//		out.println("T:"+(System.currentTimeMillis()-startTime));
//		out.println(pri[0]);
		int T = in.nextInt(), cas = 0;
		while (T-- > 0) {
			int n = in.nextInt();
			int tmp=bisearch(1, pri[0], n+1);
//			out.println(pri[tmp]);
			long ans = n>1?mul[tmp]-Integer.MIN_VALUE:1;
			mp.clear();
			double log_n=Math.log(n);
			int sqrt=(int)Math.sqrt(n);
			for (int i = 1; pri[i]<=sqrt&&i <= tmp; i++) {
//				int cnt = (int) (log_n / log_pri[i])-1;
				int cnt = (int)Math.floor(log_n / Math.log(pri[i])+1e-6)-1;
//				out.println(i+":"+(log_n / Math.log(pri[i])+1e-6));
				ans *= pow_mod(pri[i], cnt)&mask;
				ans&=mask;
				/*long pro=pri[i]*pri[i];
				long mul=pri[i];
				while(pro<=n){
					pro*=pri[i];
					mul*=pri[i];
				}
				ans*=mul/pri[i];
				ans&=mask;*/
			}
			out.println("Case " + (++cas) + ": " + ans);
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