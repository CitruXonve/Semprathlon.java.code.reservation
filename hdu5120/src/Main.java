
/** Sep 9, 2015 2:30:50 PM
 * PrjName:hdu5120
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt(), cas = 0;
		while (T-- > 0) {
			int r = in.nextInt();
			int R = in.nextInt();
			int x1 = in.nextInt();
			int y1 = in.nextInt();
			int x2 = in.nextInt();
			int y2 = in.nextInt();
			Round r1 = new Round(r, x1, y1);
			Round r2 = new Round(r, x2, y2);
			Round R1 = new Round(R, x1, y1);
			Round R2 = new Round(R, x2, y2);
			out.println(String.format("Case #%d: %.6f", ++cas, Round.CommonArea(R1, R2) - Round.CommonArea(r1, R2)
					- Round.CommonArea(R1, r2) + Round.CommonArea(r1, r2)));
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

class Round extends Vector {
	double r;
	Point o;
	final static double pi = Math.acos(-1.0);

	Round(double _r, double _x, double _y) {
		r = _r;
		x = _x;
		y = _y;
		o = new Point(x, y);
	}

	static double Rad2Deg(double rad) {
		return rad * 180.0 / pi;
	}

	static double Deg2Rad(double deg) {
		return deg * pi / 180.0;
	}

	double Area() {
		return pi * r * r;
	}

	static double CommonArea(Round A, Round B) {
		double area = 0.0;
		Round M = dcmp(A.r - B.r) > 0 ? A : B;
		Round N = dcmp(A.r - B.r) > 0 ? B : A;
		double D = dist(M.o, N.o);
		if (dcmp(M.r + N.r - D) > 0 && dcmp(M.r - N.r - D) < 0) {
			double cosM = (M.r * M.r + D * D - N.r * N.r) / (2.0 * M.r * D);
			double cosN = (N.r * N.r + D * D - M.r * M.r) / (2.0 * N.r * D);
			double alpha = 2.0 * Math.acos(cosM);
			double beta = 2.0 * Math.acos(cosN);

			double TM = 0.5 * M.r * M.r * Math.sin(alpha);
			double TN = 0.5 * N.r * N.r * Math.sin(beta);
			double FM = 0.5 * alpha / pi * M.Area();
			double FN = 0.5 * beta / pi * N.Area();
			area = FM + FN - TM - TN;
		} else if (dcmp(M.r - N.r - D) >= 0) {
			area = N.Area();
		}
		return area;
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
