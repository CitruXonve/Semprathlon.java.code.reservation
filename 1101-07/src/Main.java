/**
 * Nov 1, 2015 3:14:03 PM
 * PrjName: 1101-07
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Point[] pt;
	static HashMap<Integer, HashSet<Integer> > mp=new HashMap<Integer, HashSet<Integer> >();
	static float pi=(float)Math.acos(-1.0);
	static boolean has(Point p){
		if (mp.containsKey(p.x))
			return mp.get(p.x).contains(p.y);
		else
			return false;
	}
	static boolean find1(Point p1,Point p2){
		Vec v1=new Vec(p1, p2);
		Vec v2=new Vec(v1.rotate(pi/(float)2.0));
		Vec v3=new Vec(v1.rotate(-pi/(float)2.0));
		Point p3=p1.add(v2);
		Point p4=p3.add(v1);
		Point p5=p1.add(v3);
		Point p6=p5.add(v1);
		return has(p3)&&has(p4)||has(p5)&&has(p6);
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			pt=new Point[n+1];
			for(int i=1;i<=n;i++){
				pt[i]=new Point(in.nextInt(), in.nextInt());
				if (!mp.containsKey(pt[i].x)){
					HashSet<Integer> st=new HashSet<Integer>();
					st.add(pt[i].y);
					mp.put(pt[i].x, st);
				}
				else
					mp.get(pt[i].x).add(pt[i].y);
			}
			boolean ans=false;
			for(int i=1;i<n;i++)
				if (!ans)
					for(int j=i+1;j<=n;j++)
						if (find1(pt[i],pt[j])){
							ans=true;break;
						}
			out.println(n==4&&ans?"YES":"NO");
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

	public float nextfloat() {
		return Float.parseFloat(next());
	}
}
class Point {
    int x, y;

    Point() {
    }

    Point(float _x, float _y) {
        x = Math.round(_x);
        y = Math.round(_y);
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

    Point mul(float r) {
        return new Point(x * r, y * r);
    }

    Point move(float dx, float dy) {
        return new Point(x + dx, y + dy);
    }
    
    Point rotate(float a) {
        return new Point(x * (float)Math.cos(a) - y * (float)Math.sin(a), x * (float)Math.sin(a) + y * (float)Math.cos(a));
    }
    
    final static float eps = (float)1e-3;

    static int dcmp(float d) {
        if (Math.abs(d) < eps)
            return 0;
        return d > 0 ? 1 : -1;
    }

}
class Vec extends Point {
    Vec() {
    }

    Vec(float _x, float _y) {
        x = Math.round(_x);
        y = Math.round(_y);
    }

    Vec(Point a, Point b) {
        this(b.x - a.x, b.y - a.y);
    }

    Vec(Point p) {
        this(p.x, p.y);
    }

    static float angle(Vec a, Vec b) {
        return (float)Math.acos(dot(a, b) / a.length() / b.length());
    }

    static float angle(Point a, Point b) {
        return angle(new Vec(a), new Vec(b));
    }

    static float angle(Point o, Point a, Point b) {
        return angle(new Vec(o, a), new Vec(o, b));
    }

    float dot(Vec r) {
        return x * r.x + y * r.y;
    }

    float cross(Vec r) {
        return x * r.y - y * r.x;
    }

    float length() {
        return (float)Math.sqrt(this.dot(this));
    }

    Vec normal() {
        float len = this.length();
        return new Vec(-y / len, x / len);
    }

    static Point GetLineIntersection(Point p, Vec v, Point q, Vec w) {// 求直线交点
        Vec u = new Vec(p.sub(q));
        float t = cross(w, u) / cross(v, w);
        return p.add(v.mul(t));
    }

    static Point GetLineIntersection(Point p, Point v, Point q, Point w) {
        return GetLineIntersection(p, new Vec(v), q, new Vec(w));
    }

    static Vec add(Vec a, Vec b) {
        return new Vec(a.add(b));
    }

    static float dot(Vec a, Vec b) {
        return a.dot(b);
    }

    static float cross(Vec a, Vec b) {
        return a.cross(b);
    }

    static float cross(Point a, Point b) {
        return cross(new Vec(a), new Vec(b));
    }

    static float cross(Point o, Point a, Point b) {
        return cross(new Vec(o, a), new Vec(o, b));
    }

    static float cross(Point a, Point b, Point c, Point d) {
        return cross(new Vec(a, b), new Vec(c, d));
    }

    static float length(Vec r) {
        return r.length();
    }
}

