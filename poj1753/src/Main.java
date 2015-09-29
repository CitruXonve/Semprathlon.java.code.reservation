/** Sep 28, 2015 7:33:04 PM
 * PrjName:poj1753
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static PrintWriter out=new PrintWriter(System.out);
	static G g=new G(new int[4][4]);
	static G w=new G(new int[4][4]);
	static G b=new G(new int[][]{{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}});
	static int[][] dir={{0,0},{1,0},{-1,0},{0,1},{0,-1}};
	static HashSet<G> st=new HashSet<G>();
	static boolean can(int x,int y){
		return x>-1&&x<4&&y>-1&&y<4;
	}
	static int spfa(){
		Queue<G> que=new LinkedList<G>();
		que.add(g);
		while(!que.isEmpty()){
			G u=que.poll();
			//u.debug(out);
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++){
					G v=new G(u.mat);
					v.step=u.step+1;
					for(int k=0;k<5;k++){
						int x=i+dir[k][0];
						int y=j+dir[k][1];
						if (!can(x,y))
							continue;
						v.mat[x][y]=1-v.mat[x][y];
					}
					//v.debug(out);
					if (v.equals(b)||v.equals(w)){
						return v.step;
					}
					if (!st.contains(v)){
						st.add(v);
						que.add(v);
					}
				}
		}
		return -1;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		//PrintWriter out=new PrintWriter(System.out);
		for(int i=0;i<4;i++){
			String s=in.next();
			for(int j=0;j<4;j++)
				if (s.charAt(j)=='b')
					g.mat[i][j]=1;
				else 
					g.mat[i][j]=0;
		}
		st.add(g);
		//g.debug(out);
		if (g.equals(b)||g.equals(w))
			out.println(0);
		else{
			int ans=spfa();
			out.println(ans>0?ans:"Impossible");
		}
		out.flush();
		out.close();
	}

}
class G{
	int mat[][];
	int step,h;
	G(){
		step=0;
		h=-1;
	};
	G(int[][] mt){
		mat=new int[4][4];
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				mat[i][j]=mt[i][j];
		step=0;
		h=-1;
	}
	//@Override
	public int hashCode(){
		if (h>=0)
			return h;
		int res=0;
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				res<<=1;
				res+=mat[i][j];
			}
		h=res;
		return res;
	}
	public boolean equals(Object obj){
		if (obj==null||!(obj instanceof G))
			return false;
		return (this.hashCode()==obj.hashCode());
	}
	void debug(PrintWriter out){
		for(int i=0;i<4;i++){
			out.println();
			for(int j=0;j<4;j++)
				out.print(this.mat[i][j]);
		}
		out.println(" "+this.hashCode());
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
