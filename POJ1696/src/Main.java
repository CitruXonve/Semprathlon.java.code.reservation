/** Sep 7, 2015 8:54:03 PM
 * PrjName:POJ1696
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;

import org.omg.CORBA.INITIALIZE;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
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
    static double dist(Point a,Point b){
    	return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
    }
    public boolean equals(Point p){
    	return dcmp(dist(this, p))==0;
    }
    final static double eps=1e-3;
    static int dcmp(double d){
            if (Math.abs(d)<eps) return 0;
            return d>0?1:-1;
    }
    static class Comp implements Comparator<Point>{
    	Point prep;
    	Comp(Point p){
    		prep=p;
    	}
    	public int compare(Point a,Point b){
    		double tmp=Vector.cross(prep, a, b);
    		if (dcmp(tmp)==0) return Double.compare(dcmp(dist(a, prep)-dist(b,prep)),0);
    		return Double.compare(tmp, 0);
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
    static int cmpslope(Vector v1,Vector v2)
    {
        double s1=dcmp(v1.x)==0?0:Math.cos(Math.atan2(v1.y, v1.x));
        double s2=dcmp(v2.x)==0?0:Math.cos(Math.atan2(v2.y, v2.x));
        return dcmp(s2-s1);
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
