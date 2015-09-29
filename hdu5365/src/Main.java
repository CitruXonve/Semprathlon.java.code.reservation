/** Sep 28, 2015 9:27:46 PM
 * PrjName:hdu5365
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static double eps = 0;

    static int dcmp(double d) {
        if (Math.abs(d) <= eps)
            return 0;
        return d > 0 ? 1 : -1;
    }
	static ArrayList<Point> vec=new ArrayList<Point>();
	static Point[] pt;
	static boolean check1(Vector v1,Vector v2){
		return dcmp(v1.length()-v2.length())==0&&dcmp(v1.cross(v2))==0;
	}
	static boolean check2(Point p1,Point p2,Point p3,Point p4){
		return dcmp(Point.dist(p1, p2)-Point.dist(p3, p4))==0;
	}
	static boolean solve(Point p1,Point p2,Point p3,Point p4){
		Vector v1=new Vector(p1, p2);
		Vector v2=new Vector(p3, p4);
		Vector v3=new Vector(p1, p3);
		Vector v4=new Vector(p2, p4);
		Vector v5=new Vector(p1, p4);
		Vector v6=new Vector(p2, p3);
		return check1(v1, v2)&&check2(p1,p3,p2,p4)||check1(v3, v4)&&check2(p1, p2, p3,p4)||check1(v5, v6)&&check2(p1, p2, p4,p3);
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			int n=in.nextInt();
			vec.clear();
			for(int i=1;i<=n;i++)
				vec.add(new Point(in.nextInt(), in.nextInt()));
			pt=vec.toArray(new Point[0]);
			int ans=0;
			for(int i1=0;i1<n;i1++)
				for(int i2=i1+1;i2<n;i2++)
					for(int i3=i2+1;i3<n;i3++)
						for(int i4=i3+1;i4<n;i4++)
							if (solve(pt[i1], pt[i2], pt[i3], pt[i4]))
								ans++;
			out.println(ans);
		}
		out.flush();
		out.close();
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
    
    final static double eps = 1e-3;

    static int dcmp(double d) {
        if (Math.abs(d) < eps)
            return 0;
        return d > 0 ? 1 : -1;
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

    static double angle(Point a, Point b) {
        return angle(new Vector(a), new Vector(b));
    }

    static double angle(Point o, Point a, Point b) {
        return angle(new Vector(o, a), new Vector(o, b));
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
    
    static double dot(Point o,Point a,Point b){
    	return dot(new Vector(o, a), new Vector(o, b));
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
