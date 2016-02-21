/**
 * Nov 14, 2015 7:35:56 PM
 * PrjName: Bc62-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Point[] pt=new Point[5];
	static PrintWriter out=new PrintWriter(System.out);
	static boolean check1(){
		return  !Point.isPointInRect(pt[0], pt[1], pt[2], pt[3], pt[4])&&
				!Point.isPointInRect(pt[1], pt[2], pt[3], pt[4], pt[0])&&
				!Point.isPointInRect(pt[2], pt[3], pt[4], pt[0], pt[1])&&
				!Point.isPointInRect(pt[3], pt[4], pt[0], pt[1], pt[2])&&
				!Point.isPointInRect(pt[4], pt[0], pt[1], pt[2], pt[3]);
	}
	static boolean check0(Point p,Point a,Point b,Point c,Point d){
		double v1=Math.acos(-1.0)/5.0,v2=v1*2.0,v3=v1*3.0;
		double angle=Vector.angle2(p, a, b)+Vector.angle2(p, b, c)+Vector.angle2(p, c, d);
		angle=Math.abs(angle);
//		out.println(v1+","+v2+","+v3+","+angle);
		return Point.dcmp(angle-v1)==0||Point.dcmp(angle-v2)==0||Point.dcmp(angle-v3)==0||Point.dcmp(angle)==0;
	}
	static boolean check2(){
		return check0(pt[0], pt[1], pt[2], pt[3], pt[4])&&
				check0(pt[1], pt[2], pt[3], pt[4], pt[0])&&
				check0(pt[2], pt[3], pt[4], pt[0], pt[1])&&
				check0(pt[3], pt[4], pt[0], pt[1], pt[2])&&
				check0(pt[4], pt[0], pt[1], pt[2], pt[3]);
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		
		int T=in.nextInt();
		while(T-->0){
			for(int i=0;i<5;i++)
				pt[i]=new Point(in.nextDouble(), in.nextDouble());
			if (pt[0].equals(pt[1])&&pt[1].equals(pt[2])&&pt[2].equals(pt[3])&&pt[3].equals(pt[4])&&pt[4].equals(pt[0])){
				out.println("Yes");continue;
			}
			out.println(check2()?"Yes":"No");
		}
		out.flush();
		out.close();
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
class Point {
    double x, y;

    Point() {
    }

    Point(double _x, double _y) {
        x = _x;
        y = _y;
    }

    Point(Point p) {
        this(p.x, p.y);
    }

    Point add(Point r) {
        return new Point(x + r.x, y + r.y);
    }

    Point sub(Point r) {
        return new Point(x - r.x, y - r.y);
    }

    Point mul(double r) {
        return new Point(x * r, y * r);
    }

    Point move(double dx, double dy) {
        return new Point(x + dx, y + dy);
    }

    Point rotate(double a) {
        return new Point(x * Math.cos(a) - y * Math.sin(a), x * Math.sin(a) + y * Math.cos(a));
    }

    Point rotate(double dx, double dy, double a) {
        return this.move(-dx, -dy).rotate(a).move(dx, dy);
    }

    static Point mid(Point a, Point b) {
        return new Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);
    }

    static double dist(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public boolean equals(Point p) {
        return dcmp(Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y))) == 0;
    }

    static void swap(Point a, Point b) {
        double t1 = a.x;
        a.x = b.x;
        b.x = t1;
        double t2 = a.y;
        a.y = b.y;
        b.y = t2;
    }

    static class Comp implements Comparator<Point> {
        Point prep;

        Comp(Point p) {
            prep = p;
        }

        public int compare(Point a, Point b) {
            double tmp = Vector.cross(prep, a, b);
            if (dcmp(tmp) == 0)
                return -dcmp(dist(a, prep) - dist(b, prep));
            return -dcmp(tmp);
        }
    }
    
    final static double eps = 1e-5;

    static int dcmp(double d) {
        if (Math.abs(d) < eps)
            return 0;
        return d > 0 ? 1 : -1;
    }
    
    static boolean isPointInRect(Point p,Point A,Point B,Point C,Point D){
    	return isPointInRect(p.x, p.y, A, B, C, D);
    }
    
	static boolean isPointInRect(double x, double y,Point A,Point B,Point C,Point D) {
		final double a = (B.x - A.x)*(y - A.y) - (B.y - A.y)*(x - A.x);
		final double b = (C.x - B.x)*(y - B.y) - (C.y - B.y)*(x - B.x);
		final double c = (D.x - C.x)*(y - C.y) - (D.y - C.y)*(x - C.x);
		final double d = (A.x - D.x)*(y - D.y) - (A.y - D.y)*(x - D.x);
		if((dcmp(a) >= 0 && dcmp(b) >= 0 && dcmp(c) >= 0 && dcmp(d) >= 0) || (dcmp(a) <= 0 && dcmp(b) <= 0 && dcmp(c) <= 0 && dcmp(d) <= 0)) {
			return true;
		}
		
//		AB X AP = (b.x - a.x, b.y - a.y) x (p.x - a.x, p.y - a.y) = (b.x - a.x) * (p.y - a.y) - (b.y - a.y) * (p.x - a.x);
//		BC X BP = (c.x - b.x, c.y - b.y) x (p.x - b.x, p.y - b.y) = (c.x - b.x) * (p.y - b.y) - (c.y - b.y) * (p.x - b.x);
		return false; 
	}
}
class Vector extends Point {
    Vector() {
    }

    Vector(double _x, double _y) {
        x = _x;
        y = _y;
    }

    Vector(Point a, Point b) {
        this(b.x - a.x, b.y - a.y);
    }

    Vector(Point p) {
        this(p.x, p.y);
    }

    static double angle(Vector a, Vector b) {
        return Math.acos(dot(a, b) / a.length() / b.length());
    }
    
    static double angle2(Vector a, Vector b) {
    	double tmp=cross(a, b);
    	return Math.signum(tmp)*Math.abs(Math.asin(tmp / a.length() / b.length()));
    }

    static double angle(Point a, Point b) {
        return angle(new Vector(a), new Vector(b));
    }

    static double angle(Point o, Point a, Point b) {
        return angle(new Vector(o, a), new Vector(o, b));
    }
    
    static double angle2(Point a, Point b) {
        return angle2(new Vector(a), new Vector(b));
    }

    static double angle2(Point o, Point a, Point b) {
        return angle2(new Vector(o, a), new Vector(o, b));
    }

    double dot(Vector r) {
        return x * r.x + y * r.y;
    }

    double cross(Vector r) {
        return x * r.y - y * r.x;
    }

    double length() {
        return Math.sqrt(this.dot(this));
    }

    Vector normal() {
        double len = this.length();
        return new Vector(-y / len, x / len);
    }

    static Point GetLineIntersection(Point p, Vector v, Point q, Vector w) {// 求直线交点
        Vector u = new Vector(p.sub(q));
        double t = cross(w, u) / cross(v, w);
        return p.add(v.mul(t));
    }

    static Point GetLineIntersection(Point p, Point v, Point q, Point w) {
        return GetLineIntersection(p, new Vector(v), q, new Vector(w));
    }

    static Vector add(Vector a, Vector b) {
        return new Vector(a.add(b));
    }

    static double dot(Vector a, Vector b) {
        return a.dot(b);
    }

    static double cross(Vector a, Vector b) {
        return a.cross(b);
    }

    static double cross(Point a, Point b) {
        return cross(new Vector(a), new Vector(b));
    }

    static double cross(Point o, Point a, Point b) {
        return cross(new Vector(o, a), new Vector(o, b));
    }

    static double cross(Point a, Point b, Point c, Point d) {
        return cross(new Vector(a, b), new Vector(c, d));
    }

    static double length(Vector r) {
        return r.length();
    }
}


