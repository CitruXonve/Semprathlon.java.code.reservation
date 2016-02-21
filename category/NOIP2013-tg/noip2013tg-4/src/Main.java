/**
 * 2015年7月20日 下午3:07:38
 * PrjName:noip2013tg-4
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
class Stack{
	int[] data;
	int t,size;
	Stack(int sz){
		size=sz;
		data=new int[sz];
		t=0;
	}
	boolean empty(){
		return t==0;
	}
	void push(int n){
		data[t++]=n;
	}
	int top(){
		if (!empty())
			return data[t-1];
		return 0;
	}
	void pop(){
		t--;
	}
}
public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		Stack s=new Stack(n+5);
		s.push(0);
		int ans=0;
		for(int i=1;i<=n+1;i++){
			int h=(i<=n?in.nextInt():0);
			if (h<s.top()) 
				ans+=s.top()-h;
			while(h<s.top()&&!s.empty())
				s.pop();
			if (h>s.top())
				s.push(h);
		}
		out.print(ans);
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