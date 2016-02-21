/** Sep 7, 2015 8:14:13 PM
 * PrjName:POJ3304
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static Line[] ln;
	static int n;
	static boolean check(Line l){
		//if (l.s.equals(l.e)) return false;
		for(int i=1;i<=n;i++)
			if (!Line.isLineInter(l, ln[i]))
				return false;
		return true;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			ln=new Line[n+1];
			for(int i=1;i<=n;i++)
				ln[i]=new Line(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble());
			boolean ans=false;
			for(int i=1;i<=n;i++)
				for(int j=i+1;j<=n;j++)
					if (!ans){
						Line l1=new Line(ln[i].s, ln[j].s);
						Line l2=new Line(ln[i].s, ln[j].e);
						Line l3=new Line(ln[i].e, ln[j].s);
						Line l4=new Line(ln[i].e, ln[j].e);
						if (check(l1)||check(l2)||check(l3)||check(l4)){
							ans=true;break;
						}
					}
			if (n==1) ans=true;
			out.println(ans?"Yes!":"No!");
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
    public boolean equals(Point p){
    	return dcmp(Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)))==0;
    }
    final static double eps=1e-8;
    static int dcmp(double d){
            if (Math.abs(d)<eps) return 0;
            return d>0?1:-1;
    }
}
class Vector extends Point{
	Vector(){}
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
class Line extends Vector{
	Point s,e;
	Line(Point _s,Point _e){
		s=_s;
		e=_e;
	}
	Line(double x1,double y1,double x2,double y2){
		this(new Point(x1, y1), new Point(x2, y2));
	}
	static boolean isLineInter(Line l1,Line l2){
		if (l1.s.equals(l1.e)||l2.s.equals(l2.e)) return false;
		return dcmp(cross(l2.s, l1.s, l1.e)*cross(l2.e, l1.s, l1.e))<=0;
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
