/** Sep 8, 2015 6:53:49 PM
 * PrjName:poj1066
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static Point[] pt;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		pt=new Point[n<<1];
		for(int i=0;i<n;i++){
			pt[i<<1]=new Point(in.nextDouble(), in.nextDouble());
			pt[i<<1|1]=new Point(in.nextDouble(),in.nextDouble());
		}
		Point p0=new Point(in.nextDouble(), in.nextDouble());
		int ans=n>0?0x3fffffff:0;
		for(int i=0;i<(n<<1);i++){
			int tmp=0;
			for(int j=0;j<n;j++)
				if (Line.isSegInter2(p0, pt[i], pt[j<<1], pt[j<<1|1]))
					tmp++;
			ans=Math.min(ans, tmp);
		}
		out.println("Number of doors = "+(ans+1));
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
    static double dist(Point a,Point b){
    	return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
    }
    public boolean equals(Point p){
    	return dcmp(Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)))==0;
    }
    final static double eps=1e-3;
    static int dcmp(double d){
            if (Math.abs(d)<eps) return 0;
            return d>0?1:-1;
    }
    static void swap(Point a,Point b){
    	double t1=a.x;a.x=b.x;b.x=t1;
    	double t2=a.y;a.y=b.y;b.y=t2;
    }
    static class Comp implements Comparator<Point>{
    	Point prep;
    	Comp(Point p){
    		prep=p;
    	}
	    public int compare(Point a,Point b){
	    	double tmp=Vector.cross(prep, a, b);
	    	if (dcmp(tmp)==0) return -dcmp(dist(a, prep)-dist(b,prep));
	    	return -dcmp(tmp);
	    }
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
	int lab;
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
	static boolean isSegInter(Point s1, Point e1, Point s2, Point e2){
		if( dcmp(Math.min(s1.x, e1.x) - Math.max(s2.x, e2.x))<=0 &&
				dcmp(Math.min(s1.y, e1.y) - Math.max(s2.y, e2.y))<=0 &&
						dcmp(Math.min(s2.x, e2.x) - Math.max(s1.x, e1.x))<=0 &&
								dcmp(Math.min(s2.y, e2.y) - Math.max(s1.y, e1.y))<=0 &&
		        dcmp(Vector.cross(s2, e2, s1) * Vector.cross(s2, e2, e1)) <= 0 &&
		        		dcmp(Vector.cross(s1, e1, s2) * Vector.cross(s1, e1, e2)) <= 0)
		        return true;
		return false;
	}
	static boolean isSegInter2(Point p1, Point p2, Point p3, Point p4) // 判断严格相交
	{
	    return dcmp(cross(p3, p4, p1)) * dcmp(cross(p3, p4, p2)) == -1;
	}
	static boolean isSegInter(Line l1,Line l2){
		return isSegInter(l1.s, l1.e, l2.s, l2.e);
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