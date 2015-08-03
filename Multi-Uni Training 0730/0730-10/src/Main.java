/**
 * 2015年7月30日 下午2:32:29
 * PrjName:0730-10
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int R,C,T;
	static int[][] g,h;
	static int[][] dir=new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
	static Vector<Drop> V=new Vector<Drop>();
	static PrintWriter out=new PrintWriter(System.out);
	static boolean can(int x,int y){
		if (x<1||x>R||y<1||y>C) return false;
		return true;
	}
	static boolean can(Drop p){
		return can(p.x, p.y);
	}
	static boolean has(Drop p){
		return g[p.x][p.y]>0;
	}
	static boolean add(Drop p){
		g[p.x][p.y]++;
		if (g[p.x][p.y]>4){
			h[p.x][p.y]=p.t;
			g[p.x][p.y]=0;return true;
		}
		return false;
	}
	static boolean disp(Drop p){
		if (g[p.x][p.y]==0&&h[p.x][p.y]==p.t) return true;
		return false;
	}
	static void solve(int x0,int y0){
		Queue<Drop> que=new LinkedList<Drop>();
		for(int i=0;i<4;i++)
			que.add(new Drop(x0, y0, i,0));
		while(!que.isEmpty()){
			Drop u=que.poll();
			u=u.move();
			if (!can(u)||disp(u)||u.t>T) continue;
			//out.println(u.x+" "+u.y+" "+u.d+" "+u.t);
			if (has(u)){
				if (add(u))
					for(int i=0;i<4;i++){
						Drop v=new Drop(u);
						v.d=i;
						que.add(v);
					};
			}
			else
				que.add(u);
		}
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			R=(int)in.nval;
			in.nextToken();
			C=(int)in.nval;
			in.nextToken();
			int n=(int)in.nval;
			in.nextToken();
			T=(int)in.nval;
			g=new int[R+1][C+1];
			h=new int[R+1][C+1];
			V.clear();
			for(int i=1;i<=n;i++){
				in.nextToken();
				int x=(int)in.nval;
				in.nextToken();
				int y=(int)in.nval;
				in.nextToken();
				int w=(int)in.nval;
				V.add(new Drop(x,y,-1,0));
				g[x][y]=w;
			}
			in.nextToken();
			int x0=(int)in.nval;
			in.nextToken();
			int y0=(int)in.nval;
			solve(x0, y0);
			for(int i=1;i<=n;i++){
				Drop p=V.get(i-1);
				if (g[p.x][p.y]==0)
					out.println(0+" "+h[p.x][p.y]);
				else
					out.println(1+" "+g[p.x][p.y]);
			}
		}
		out.flush();
		out.close();
	}

}

class Drop{
	int x,y,d,t;
	static int[][] dir=new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
	Drop(Drop p){
		this.x=p.x;
		this.y=p.y;
		this.d=p.d;
		this.t=p.t;
	}
	Drop(int u,int v,int w,int tt){
		x=u;y=v;d=w;t=tt;
	}
	Drop move(){
		return new Drop(x+dir[d][0], y+dir[d][1], d,t+1);
	}
}
class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream){
           reader = new BufferedReader(new InputStreamReader(stream), 32768);
           tokenizer = null;
    }

    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (IOException e) {
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

}