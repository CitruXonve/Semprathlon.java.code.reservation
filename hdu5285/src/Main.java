/**
 * 2015年7月19日 下午3:59:11
 * PrjName:hdu5285
 * @ Semprathlon
 */

import java.io.*;
import java.util.*;
class Edge{
	int to,next;
	Edge(){}
	Edge(int v,int w){
		to=v;next=w;
	}
}
class Queue{
	int[] data;
	int size,h,r;
	Queue(int sz){
		size=sz;
		data=new int[size];
		h=r=0;
	}
	void push(int n){
		if ((r+size-h)%size<size-1)
			data[r++]=n;
	}
	int pop(){
		return data[h++];
	}
	boolean empty(){
		return (r-h+size)%size==0;
	}
}
public class Main {
	static Edge[] edge;
	static int[] head;
	static byte[] col;
	static int n,m;
	static void addedge(int u,int v){
		edge[head[0]]=new Edge(v, head[u]);
		head[u]=head[0]++;
	}
	static int bfs(int st){
		Queue que=new Queue(m<<1);
		que.push(st);
		col[st]=0;
		int[] sum=new int[]{0,0};
		while(!que.empty()){
			int u=que.pop();
			sum[col[u]]++;
			for(int i=head[u];i>-1;i=edge[i].next){
				int v=edge[i].to;
				if (col[v]>=0){
					if (col[v]==col[u])
						return -1;
				}
				else{
					col[v]=(byte)(col[u]^1);
					que.push(v);
				}
			}
		}
		return Math.max(sum[0], sum[1]);
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in)  ;
        PrintWriter out = new PrintWriter(System.out) ;
        int T=in.nextInt();
        while(T-->0){
        	n=in.nextInt();
        	m=in.nextInt();
        	edge=new Edge[m<<2];
        	head=new int[m<<2];
        	Arrays.fill(head, -1);
        	head[0]=1;
        	for(int i=0;i<m;i++){
        		int u=in.nextInt();
        		int v=in.nextInt();
        		addedge(u, v);
        		addedge(v, u);
        	}
        	col=new byte[n+1];
        	Arrays.fill(col, (byte)-1);
        	int sum=0;
        	for(int i=1;i<=n;i++)
        		if (col[i]<0){
        			int tmp=bfs(i);
        			if (tmp<0){
        				sum=-1;break;
        			}
        			sum+=tmp;
        		}
        	if (n==sum) sum--;
        	if (sum<1||n-sum<1)
        		out.println("Poor wyh");
        	else
        		out.println(sum+" "+(n-sum));
        }
        out.flush();
        out.close();
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