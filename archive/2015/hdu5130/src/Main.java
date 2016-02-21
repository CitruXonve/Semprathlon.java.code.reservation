
/** Sep 9, 2015 3:23:33 PM
 * PrjName:hdu5130
 * @author Semprathlon
 */
import java.util.*;
import java.io.*;

public class Main {

	/**
	 * @param args
	 */
	static double sqr(double x) {
		return x * x;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int cas = 0;
		while (in.nextLine() != null) {
			int n = in.nextInt();
			double k = in.nextDouble();
			Polygon pl = new Polygon(n);
			for (int i = 0; i < n; i++)
				pl.v[i] = new Point(in.nextInt(), in.nextInt());
			pl.v[n] = pl.v[0];
			Point A = new Point(in.nextDouble(), in.nextDouble());
			Point B = new Point(in.nextDouble(), in.nextDouble());
			/*
			 * Point O=new Point(-(B.x-k*k*A.x)/(k*k-1),
			 * -(B.y-k*k*A.y)/(k*k-1)); double
			 * r=Math.sqrt(sqr((B.x-k*k*A.x)/(k*k-1))+sqr((B.y-k*k*A.y)/(k*k-1))
			 * -k*k*(sqr(A.x)+sqr(A.y))/(k*k-1)+(sqr(B.x)+sqr(B.y))/(k*k-1));
			 * Round R=new Round(r, O.x, O.y);
			 */
			double D = (2.0 * k * k * A.x - 2.0 * B.x) / (1.0 - k * k);
			double E = (2.0 * k * k * A.y - 2.0 * B.y) / (1.0 - k * k);
			double F = (B.x * B.x + B.y * B.y - k * k * (A.x * A.x + A.y * A.y)) / (1.0 - k * k);
			Round R = new Round(Math.sqrt(D * D + E * E - 4.0 * F) * 0.5, -D * 0.5, -E * 0.5);
			double ans = 0;
			for (int i = 0; i < n; i++)
				ans += Round.TriAngleCircleInsection(R, pl.v[i], pl.v[i + 1]);
			out.println(String.format("Case %d: %.6f", ++cas, Math.abs(ans)));
			// out.println(r+" "+O.x+" "+O.y);
			// out.println(R.GetCirclePolyIntersectionArea(pl));
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

class Line extends Vector {
	Point s, e;

	Line() {
	}

	Line(Point _s, Point _e) {
		s = _s;
		e = _e;
	}

	Line(double x1, double y1, double x2, double y2) {
		this(new Point(x1, y1), new Point(x2, y2));
	}

	Vector vector() {
		return new Vector(s, e);
	}

	static boolean isLineInter(Line l1, Line l2) {
		if (l1.s.equals(l1.e) || l2.s.equals(l2.e))
			return false;
		return dcmp(cross(l2.s, l1.s, l1.e) * cross(l2.e, l1.s, l1.e)) <= 0;
	}

	static boolean isSegInter(Point s1, Point e1, Point s2, Point e2) {
		if (dcmp(Math.min(s1.x, e1.x) - Math.max(s2.x, e2.x)) <= 0
				&& dcmp(Math.min(s1.y, e1.y) - Math.max(s2.y, e2.y)) <= 0
				&& dcmp(Math.min(s2.x, e2.x) - Math.max(s1.x, e1.x)) <= 0
				&& dcmp(Math.min(s2.y, e2.y) - Math.max(s1.y, e1.y)) <= 0
				&& dcmp(Vector.cross(s2, e2, s1) * Vector.cross(s2, e2, e1)) <= 0
				&& dcmp(Vector.cross(s1, e1, s2) * Vector.cross(s1, e1, e2)) <= 0)
			return true;
		return false;
	}

	static boolean isSegInter2(Point p1, Point p2, Point p3, Point p4) // 判断严格相交
	{
		return dcmp(cross(p3, p4, p1)) * dcmp(cross(p3, p4, p2)) == -1;
	}

	static boolean isSegInter(Line l1, Line l2) {
		return isSegInter(l1.s, l1.e, l2.s, l2.e);
	}

	static boolean isSegInter2(Line l1, Line l2) {
		return isSegInter2(l1.s, l1.e, l2.s, l2.e);
	}
}

class Polygon extends Vector {
	int num;
	Point[] v;

	Polygon() {
	}

	Polygon(int n) {
		num = n;
		v = new Point[num + 2];
	}

	boolean IsConvexBag() {
		int direction = 0;// 1:右手正螺旋，逆时针 -1:左手正螺旋，顺时针
		for (int i = 0; i < num; i++) {
			int tmp = dcmp(cross(v[i], v[i + 1], v[i + 1], v[i + 2]));

			if (direction == 0) // 避免最初的点出现共线的情况
				direction = tmp;

			if (direction * tmp < 0) // 只要Vec是凸包，那么无论Vec的旋转方向如何，direction*temp都会>=0
				return false;
		}
		return true;
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

	/* 判断圆与多边形的关系 */
	boolean IsFitPoly(Polygon pl) {
		for (int i = 0; i <= pl.num; i++) {
			int k = dcmp(Math.abs(cross(o, pl.v[i], o, pl.v[i + 1]) / dist(pl.v[i], pl.v[i + 1])) - r);
			if (k < 0)
				return false;
		}
		return true;
	}

	boolean IsInPoly(Polygon pl) {
		double CircleAngle = 0.0; // 环绕角
		for (int i = 1; i <= pl.num; i++) // 注意重复边不计算
			if (dcmp(cross(o, pl.v[i], o, pl.v[i + 1])) >= 0)
				CircleAngle += angle(o, pl.v[i], pl.v[i + 1]);
			else
				CircleAngle -= angle(o, pl.v[i], pl.v[i + 1]);

		if (dcmp(CircleAngle) == 0) // CircleAngle=0, Peg在多边形外部
			return false;
		else if (dcmp(CircleAngle - pi) == 0 || dcmp(CircleAngle + pi) == 0) // CircleAngle=180,
																				// Peg在多边形边上(不包括顶点)
		{
			if (dcmp(r) == 0)
				return true;
		} else if (dcmp(CircleAngle - 2 * pi) == 0 || dcmp(CircleAngle + 2 * pi) == 0) // CircleAngle=360,
																						// Peg在多边形边内部
			return true;
		else // CircleAngle=(0,360)之间的任意角， Peg在多边形顶点上
		{
			if (dcmp(r) == 0)
				return true;
		}
		return false;
	}

	static double TriAngleCircleInsection(Round C, Point A, Point B) {
		Vector OA = new Vector(A.sub(C.o)), OB = new Vector(B.sub(C.o));
		Vector BA = new Vector(A.sub(B)), BC = new Vector(C.o.sub(B));
		Vector AB = new Vector(B.sub(A)), AC = new Vector(C.o.sub(A));
		double DOA = OA.length(), DOB = OB.length(), DAB = AB.length(), r = C.r;
		if (dcmp(cross(OA, OB)) == 0)
			return 0;
		if (dcmp(DOA - C.r) < 0 && dcmp(DOB - C.r) < 0)
			return cross(OA, OB) * 0.5;
		else if (dcmp(DOB - r) < 0 && dcmp(DOA - r) >= 0) {
			double x = (dot(BA, BC) + Math.sqrt(r * r * DAB * DAB - cross(BA, BC) * cross(BA, BC))) / DAB;
			double TS = cross(OA, OB) * 0.5;
			return Math.asin(TS * (1 - x / DAB) * 2 / r / DOA) * r * r * 0.5 + TS * x / DAB;
		} else if (dcmp(DOB - r) >= 0 && dcmp(DOA - r) < 0) {
			double y = (dot(AB, AC) + Math.sqrt(r * r * DAB * DAB - cross(AB, AC) * cross(AB, AC))) / DAB;
			double TS = cross(OA, OB) * 0.5;
			return Math.asin(TS * (1 - y / DAB) * 2 / r / DOB) * r * r * 0.5 + TS * y / DAB;
		} else if (dcmp(Math.abs(cross(OA, OB)) - r * DAB) >= 0 || dcmp(dot(AB, AC)) <= 0 || dcmp(dot(BA, BC)) <= 0) {
			if (dcmp(dot(OA, OB)) < 0) {
				if (dcmp(cross(OA, OB)) < 0)
					return (-Math.acos(-1.0) - Math.asin(cross(OA, OB) / DOA / DOB)) * r * r * 0.5;
				else
					return (Math.acos(-1.0) - Math.asin(cross(OA, OB) / DOA / DOB)) * r * r * 0.5;
			} else
				return Math.asin(cross(OA, OB) / DOA / DOB) * r * r * 0.5;
		} else {
			double x = (dot(BA, BC) + Math.sqrt(r * r * DAB * DAB - cross(BA, BC) * cross(BA, BC))) / DAB;
			double y = (dot(AB, AC) + Math.sqrt(r * r * DAB * DAB - cross(AB, AC) * cross(AB, AC))) / DAB;
			double TS = cross(OA, OB) * 0.5;
			return (Math.asin(TS * (1 - x / DAB) * 2 / r / DOA) + Math.asin(TS * (1 - y / DAB) * 2 / r / DOB)) * r * r
					* 0.5 + TS * ((x + y) / DAB - 1);
		}
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
