/**
 * 2015年7月17日 下午5:41:50
 * PrjName:acd1726
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer cin = new StreamTokenizer(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		HashSet<Long> map = new HashSet<Long>();
		while (cin.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) cin.nval;
			cin.nextToken();
			int m = (int) cin.nval;
			map.clear();
			int[] num = new int[n];
			for (int i = 0; i < n; i++) {
				cin.nextToken();
				num[i] = (int) cin.nval;
			}
			int t = (n + 1) / 2;
			for (int i = 0; i < (1 << t); i++) {
				long sum = 0;
				for (int j = 0; j < t; j++) {
					if ((i & (1 << j)) > 0)
						sum += num[j];
				}
				if (sum > m)
					continue;
				map.add(sum);
			}
			int tt = n - t;
			boolean flag = map.contains(m);
			for (int i = 0; i < (1 << tt); i++) {
				long sum = 0;
				for (int j = 0; j < tt; j++) {
					if ((i & (1 << j)) > 0)
						sum += num[t + j];
				}
				if (sum > m)
					continue;
				if (map.contains(m - sum)) {
					flag = true;
					break;
				}
			}
			if (flag)
				out.println("Yes");
			else
				out.println("No");
			// out.flush();
		}
		out.flush();
	}

}