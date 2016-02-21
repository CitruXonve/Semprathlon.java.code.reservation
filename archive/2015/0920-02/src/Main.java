/** Sep 20, 2015 1:36:24 PM
 * PrjName:0920-02
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static Text ed=new Text();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int lim=in.nextInt();
			String str=in.next();
			ed=new Text(lim);
			for(char ch:str.toCharArray()){
				if (ch>='a'&&ch<='z'){
						ed.add(ch);
					
				}
				else if (ch=='L')
					ed.moveleft();
				else if (ch=='R')
					ed.moveright();
				else if (ch=='S')
					ed.switchmode();
				else if (ch=='D'){
						ed.delright();
				}
				else if (ch=='B'){
						ed.delleft();
				}
				else if (ch=='C'){
					try {
						ed.copy();
					} catch (Exception e) {
						// TODO: handle exception
					}
						
//					out.println("C "+ed.cop);ed.debug(out);
				}
				else if (ch=='V'){
					try {
						ed.paste();
					} catch (Exception e) {
					}
					//out.print("V ");ed.print(out);ed.debug(out);
				}
				//ed.debug(out);
			}
			if (ed.s.size()<1)
				out.println("NOTHING");
			else
				ed.print(out);
		}
		out.flush();
		out.close();
	}
}
class Text{
	LinkedList<Character> s;
	String cop;
	ListIterator<Character> it;
	boolean isInsert,isCopy;
	int maxsize,clip;
	Text(){
		clear();
	}
	Text(int m){
		clear();
		maxsize=m;
	}
	void clear(){
		s=new LinkedList<Character>();
		it=s.listIterator();
		isInsert=true;
		isCopy=false;
	}
	/*void addleft(char ch){
		//it.previous();
		if (!can()) return;
		it.add(ch);
	}
	void addright(char ch){
		if (!can()) return;
		it.next();
		it.add(ch);
		it.previous();
	}*/
	void sync(int op){
		if (isCopy){
			if (op>0){
				if (it.nextIndex()<=clip) clip++;
			}
			if (op<0){
				if (it.nextIndex()<=clip) clip--;
			}
		}
	}
	void add(char ch){
		if (isInsert){
			if (!can()) return; 
			it.add(ch);
		}
		else{
			if (it.hasNext()){
				it.next();
				it.remove();
			}
			else if (!can()) return; 
			it.add(ch);
		}
		isCopy=false;
	}
	void moveleft(){//L
		if (!it.hasPrevious()) return;
		it.previous();
	}
	void moveright(){//R
		if (!it.hasNext()) return;
		it.next();
	}
	void switchmode(){//S
		isInsert=!isInsert;
		isCopy=false;
	}
	void delleft(){//B
		if (!it.hasPrevious()) return;
		it.previous();
		it.remove();
		isCopy=false;
	}
	void delright(){//D
		if (!it.hasNext()) return;
		if (isCopy){
			if (it.nextIndex()<clip){
				sync(-1);
				for(;it.nextIndex()!=clip&&it.hasNext()&&clip>-1;){
					it.next();
					clip--;
					it.remove();
				}
			}
		}
		if (!it.hasNext()) return;
		sync(-1);
		it.next();
		it.remove();
		isCopy=false;
		//clip.clear
	}
	void copy(){//C
		if (!isCopy){
			isCopy=true;
			clip=it.nextIndex();
		}
		else{
			isCopy=false;
			cop=new String();
			if (it.nextIndex()>clip)
				for(Iterator<Character> iter=s.listIterator(clip);iter!=it&&iter.hasNext();)
					cop+=iter.next();
			else{
				for(Iterator<Character> iter=it;it.nextIndex()!=clip&&iter.hasNext();)
					cop+=iter.next();
			}
		}
	}
	void paste(){//V
		isCopy=false;
		if (isInsert){
			if (maxsize-s.size()<cop.length()) return;
			try {
				for(char ch:cop.toCharArray())
					it.add(ch);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		else{
			if (s.size()-Math.min(cop.length(), s.size()-it.nextIndex())+cop.length()>maxsize) return;
			int len=cop.length();
			try {
				for(;it.hasNext()&&len>0;){
					it.next();
					it.remove();
					len--;
				}
				for(char ch:cop.toCharArray())
					it.add(ch);
			} catch (Exception e) {
//				return;
				// TODO: handle exception
			}
		}
		
	}
	void debug(PrintWriter out){
		try {
			out.print(it.previousIndex()+"il");
		} catch (java.lang.NullPointerException e) {
			// TODO: handle exception
		}
		try {
			out.print(it.nextIndex()+"ir");
		} catch (java.lang.NullPointerException e) {
			// TODO: handle exception
		}
		out.println(clip);
	}
	void print(PrintWriter out){
		/*for(Iterator<Character> iter=s.iterator();it.hasNext();)
			out.print(iter.next());*/
		for(Character ch:s.toArray(new Character[0]))
			out.print(ch);
		out.println();
	}
	boolean can(){
		return s.size()<maxsize;
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
