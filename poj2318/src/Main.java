/** Sep 7, 2015 7:36:52 PM
 * PrjName:poj2318
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static double eps=1e-3;
    static int dcmp(double d){
        if (Math.abs(d)<eps) return 0;
        return d>0?1:-1;
    }
	static int n,m,x1,y1,x2,y2;
	static Line[] ln;
	static int[] f;
	static void init(InputReader in){
		m=in.nextInt();
		x1=in.nextInt();
		y1=in.nextInt();
		x2=in.nextInt();
		y2=in.nextInt();
		ln=new Line[n+1];
		f=new int[n+1];
		for(int i=0;i<n;i++)
			ln[i]=new Line(in.nextInt(), y1, in.nextInt(), y2);
		ln[n]=new Line(x2, y1, x2, y2);
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			n=in.nextInt();
			if (n==0) break;
			init(in);
			while(m-->0){
				Point p=new Point(in.nextInt(), in.nextInt());
				int l=0,r=n,pos=0;
				while(l<=r){
					int mid=(l+r)>>1;
					if (dcmp(Vector.cross(p, ln[mid].s, ln[mid].e))<0){
						pos=mid;
						r=mid-1;
					}
					else
						l=mid+1;
				}
				f[pos]++;
			}
			for(int i=0;i<=n;i++)
				out.println(i+": "+f[i]);
			out.println();
		}
		out.flush();
		out.close();
	}

}
class Point{
    double x,y;
    Point(){}
    Point(double _x,double _y){
        x=_x;y=_y;
    }
    Point(Point p){
        this(p.x,p.y);
    }
    boolean equals(Point p){
        return dcmp(x-p.x)==0&&dcmp(y-p.y)==0;
    }
    Point add(Point r){
        return new Point(x+r.x, y+r.y);
    }
    Point sub(Point r){
        return new Point(x-r.x, y-r.y);
    }
    Point mul(double r){
        return new Point(x*r,y*r);
    }
    Point move(double dx,double dy){
        return new Point(x+dx,y+dy);
    }
    Point rotate(double a){
        return new Point(x*Math.cos(a)-y*Math.sin(a),x*Math.sin(a)+y*Math.cos(a));
    }
    Point rotate(double dx,double dy,double a){
        return this.move(-dx, -dy).rotate(a).move(dx, dy);
    }
    static Point mid(Point a,Point b){
        return new Point((a.x+b.x)/2.0,(a.y+b.y)/2.0);
    }
    final static double eps=1e-3;
    static int dcmp(double d){
        if (Math.abs(d)<eps) return 0;
        return d>0?1:-1;
    }
}
class Vector extends Point{
    Vector(double _x,double _y){
        x=_x;y=_y;
    }
    Vector(Point a,Point b){
        this(b.x-a.x,b.y-a.y);
    }
    Vector(Point p){
        this(p.x, p.y);
    }
    static double angle(Vector a,Vector b){
        return Math.acos(dot(a, b)/a.length()/b.length());
    }
    static double angle(Point o,Point a,Point b){
    	return angle(new Vector(o, a), new Vector(o, b));
    }
    double dot(Vector r){
        return x*r.x+y*r.y;
    }
    
    double cross(Vector r){
        return x*r.y-y*r.x;
    }
    
    double length(){
        return Math.sqrt(this.dot(this));
    }
    Vector normal(){
        double len=this.length();
        return new Vector(-y/len, x/len);
    }
    static Point GetLineIntersection(Point p, Vector v, Point q, Vector w){//求直线交点
        Vector u=new Vector(p.sub(q));
        double t=cross(w, u)/cross(v, w);
        return p.add(v.mul(t));
    }
    static Point GetLineIntersection(Point p, Point v, Point q, Point w){
        return GetLineIntersection(p, new Vector(v), q, new Vector(w));
    }
    static Vector add(Vector a,Vector b){
        return new Vector(a.add(b));
    }
    static double dot(Vector a,Vector b){
        return a.dot(b);
    }
    static double cross(Vector a,Vector b){
        return a.cross(b);
    }
    static double cross(Point o,Point a,Point b){
    	return cross(new Vector(o, a),new Vector(o, b));
    }
    static double length(Vector r){
        return r.length();
    }
}
class Line{
	Point s,e;
	Line(){}
	Line(Point _s,Point _e){
		s=_s;
		e=_e;
	}
	Line(double x1,double y1,double x2,double y2){
		this(new Point(x1, y1), new Point(x2, y2));
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
