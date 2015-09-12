# About this reposition
Synchronization and backup

What all these projects are concerning about are the followings:

* Bestcoder weekly contest
* Multiple University training 2015 summer
* Program Practise from HDU

# Code Templates for ACM-ICPC
## InputAn "Inputreader" for common use:

```class InputReader{    public BufferedReader reader;    public StringTokenizer tokenizer;     public InputReader(InputStream stream){           reader = new BufferedReader(                   new InputStreamReader(stream), 32768);           tokenizer = null;    }     public String next(){        while(tokenizer == null || !tokenizer.hasMoreTokens()){            try{                tokenizer = new StringTokenizer(                           reader.readLine());            }catch (IOException e) {                throw new RuntimeException(e);            }        }        return tokenizer.nextToken();    }     public int nextInt() {        return Integer.parseInt(next());    }         public long nextLong() {        return Long.parseLong(next());    } }
```An enhanced edition supporting keeping reading data until the end of input while the number of input cases is unknown:

```class InputReader {	public BufferedReader reader;	public StringTokenizer tokenizer;	public InputReader(InputStream stream) {		reader = new BufferedReader(new InputStreamReader(stream), 32768);		tokenizer = null;	}	public String nextLine() {		String tmp = null;		try {			tmp = reader.readLine();			tokenizer = new StringTokenizer(tmp);		} catch (IOException e) {			throw new RuntimeException(e);		} catch (NullPointerException e) {			return null;		}		return tmp;	}	public String next() {		while (tokenizer == null || !tokenizer.hasMoreTokens()) {			try {				tokenizer = new StringTokenizer(reader.readLine());			} catch (IOException e) {				throw new RuntimeException(e);			}		}		return tokenizer.nextToken();	}	public int nextInt() {		return Integer.parseInt(next());	}	public long nextLong() {		return Long.parseLong(next());	}	public double nextDouble() {		return Double.parseDouble(next());	}}```##Data Structure###PointThe followings are used to describe a point on a plane,while the maximum value of x-coordinate or y-coordinate is `n-1`:

```class Pt{    int x,y,n;    Pt(int _x,int _y,int _n){        x=_x;y=_y;n=_n;    }    Pt(int hash,int _n){        n=_n;        y=hash%n;        x=hash/n;    }    public int hashCode(){        return x*n+y;    }}```###Queue

```Queue<Integer> que=new LinkedList<Integer>();
```###Priority Queue
```
PriorityQueue<Integer> que=new PriorityQueue<Integer>();
```###Heap
This is a minimum heap:

```class Heap {    private int maxn;    int[] data;    int r;     Heap(int size) {    	maxn=size;        data = new int[maxn];        r = 0;    }        void clear(){    	//Arrays.fill(data, 0);    	r=0;    }        public int size() {        return r;    }     void swap(int a, int b) {        int tmp = data[a];        data[a] = data[b];        data[b] = tmp;    }     void up(int p) {        if (!(p > 0))            return;        int q = p >> 1;        if (data[p] < data[q]) {            swap(p, q);            up(q);        }    }     void down(int p) {        int q;        if ((p << 1) >= r)            return;        else if ((p << 1) == r - 1) {            q = p << 1;        } else {q = (data[p << 1] < data[p << 1 | 1] ?     p << 1 : p << 1 | 1);        }        if (data[p] > data[q]) {            swap(p, q);            down(q);        }    }     void push(int n) {        data[r++] = n;        up(r - 1);    }     int pop() {        int res = data[0];        swap(0, r - 1);        r--;        down(0);        return res;    }     int top() {        return data[0];    }    }```###Adjacent Table
```class Edge{	int v,w,nx;	Edge(int _u,int _v,int _w){		v=_u;w=_v;nx=_w;	}}class Graph{	int[] h;	int sz;	Edge[] edge;	Graph(int size){		sz=size;		h=new int[sz+1];		edge=new Edge[sz+1];		Arrays.fill(h, -1);		h[0]=0;	}	void add(int u,int v,int w){		edge[h[0]]=new Edge(v,w,h[u]);		h[u]=h[0]++;	}}```#Search
```final int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};boolean can(int x,int y){		if (x<1||x>R||y<1||y>C) return false;		return true;	}```#Modify,Update & Query##RMQ```void RMQ(){ //pre-proceeding->O(nlogn){    for(int i = 1; i != maxm; ++i)        for(int j = 1; j <= n; ++j)            if(j + (1 << i) - 1 <= n){                maxsum[i][j] = Math.max(maxsum[i - 1][j],                             maxsum[i - 1][j + (1 << i >> 1)]);                minsum[i][j] = Math.min(minsum[i - 1][j],                             minsum[i - 1][j + (1 << i >> 1)]);                }    }     int query(int src,int des){        int k = (int)(Math.log(des - src + 1.0) / Math.log(2.0));        int maxres = Math.max(maxsum[k][src],                           maxsum[k][des - (1 << k) + 1]);        int minres = Math.min(minsum[k][src],                           minsum[k][des - (1 << k) + 1]);        return maxres-minres;    }```#Graph Theory##The Maximum Matching of Bipartite Graph
```boolean dfs(int u){        for(int v:adj[u]){            if (vis[v]) continue;            vis[v]=true;            if (match[v]<0||dfs(match[v])){                match[v]=u;                return true;            }        }        return false;    }int maxmatch(){        int res=0;        Arrays.fill(match, -1);        for(int i=1;i<=cnt;i++){            Arrays.fill(vis, false);            if (dfs(i)) res++;        }        return res;    }```#Number Theory##Quick power and moduloTo calculate n^m%mod:

```long pow(long n,long m,long mod){        long res=1L;        while(m>0L){            if ((m&1)>0L) res=res*n%mod;            n=n*n%mod;            m>>=1;        }        return res;    }
```
##Normal pow with double
Just to calculate n^m:

```static double pow(double n, int m) {		double res = 1;		while (m > 0) {			if ((m & 1) > 0)				res = res * n;			n = n * n;			m >>= 1;		}		return res;	}```##Prime filtering (linear) && Calculating Euler function φ(x)
```	int[] pri,phi,fstp;	void get_prime(){		pri=new int[maxn];		fstp=new int[maxn];		phi=new int[maxn];		phi[1]=1;		for(int i=2;i<maxn;i++){			if (fstp[i]==0){				pri[++pri[0]]=i;				phi[i]=i-1;			}			for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){				int k=i*pri[j];				fstp[k]=pri[j];				//if (fstp[i]==pri[j]){				if (i%pri[j]==0){					phi[k]=phi[i]*pri[j];					break;				}				else					phi[k]=phi[i]*(pri[j]-1);			}		}	}	Vector<Integer> get_prime_factor(int n){		Vector<Integer> res=new Vector<Integer>();		while(n>1&&fstp[n]>0){			res.add(fstp[n]);			n/=fstp[n];		}		if (n>1) res.add(n);		return res;	}```##Integer Prime Factorization
for normal purpose:

```Vector<Integer> get_prime_factor(int n){        Vector<Integer> res=new Vector<Integer>();        res.clear();        for(int i=2;i*i<=n;i++)            if (n%i==0){                res.add(i);                while(n%i==0)                    n/=i;            }        if (n>1) res.add(n);        return res;    }
```
##Division and moduloTo calculate a/b%mod correctly (mod is a prime number thus φ(mod)=mod-1)

```long div(long a,long b,long mod){        return a*pow(b,mod-2,mod)%mod;    }
```#Combinatorial Mathematics##Permutation & Combination
```double[][] c;void init(){		c=new double[maxn][maxn];		c[0][0]=1;		for(int i=1;i<maxn;i++){			c[i][0]=c[i][i]=1;			for(int j=1;j<i;j++)				c[i][j]=c[i-1][j]+c[i-1][j-1];		}	}
```#Computational Geometry##Double Comparing
```	final static double eps = 1e-3;	static int dcmp(double d) {		if (Math.abs(d) < eps)			return 0;		return d > 0 ? 1 : -1;	}
```##Point(2-dimension)```class Point {	double x, y;	Point() {	}	Point(double _x, double _y) {		x = _x;		y = _y;	}	Point(Point p) {		this(p.x, p.y);	}	Point add(Point r) {		return new Point(x + r.x, y + r.y);	}	Point sub(Point r) {		return new Point(x - r.x, y - r.y);	}	Point mul(double r) {		return new Point(x * r, y * r);	}	Point move(double dx, double dy) {		return new Point(x + dx, y + dy);	}	Point rotate(double a) {		return new Point(x * Math.cos(a) - y * Math.sin(a), x * Math.sin(a) + y * Math.cos(a));	}	Point rotate(double dx, double dy, double a) {		return this.move(-dx, -dy).rotate(a).move(dx, dy);	}	static Point mid(Point a, Point b) {		return new Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);	}	static double dist(Point a, Point b) {		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));	}	public boolean equals(Point p) {		return dcmp(Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y))) == 0;	}	static void swap(Point a, Point b) {		double t1 = a.x;		a.x = b.x;		b.x = t1;		double t2 = a.y;		a.y = b.y;		b.y = t2;	}	static class Comp implements Comparator<Point> {		Point prep;		Comp(Point p) {			prep = p;		}		public int compare(Point a, Point b) {			double tmp = Vector.cross(prep, a, b);			if (dcmp(tmp) == 0)				return -dcmp(dist(a, prep) - dist(b, prep));			return -dcmp(tmp);		}	}}
```##Vector
```class Vector extends Point {	Vector() {	}	Vector(double _x, double _y) {		x = _x;		y = _y;	}	Vector(Point a, Point b) {		this(b.x - a.x, b.y - a.y);	}	Vector(Point p) {		this(p.x, p.y);	}	static double angle(Vector a, Vector b) {		return Math.acos(dot(a, b) / a.length() / b.length());	}	static double angle(Point a, Point b) {		return angle(new Vector(a), new Vector(b));	}	static double angle(Point o, Point a, Point b) {		return angle(new Vector(o, a), new Vector(o, b));	}	double dot(Vector r) {		return x * r.x + y * r.y;	}	double cross(Vector r) {		return x * r.y - y * r.x;	}	double length() {		return Math.sqrt(this.dot(this));	}	Vector normal() {		double len = this.length();		return new Vector(-y / len, x / len);	}	static Point GetLineIntersection(Point p, Vector v, Point q, Vector w) {// 求直线交点		Vector u = new Vector(p.sub(q));		double t = cross(w, u) / cross(v, w);		return p.add(v.mul(t));	}	static Point GetLineIntersection(Point p, Point v, Point q, Point w) {		return GetLineIntersection(p, new Vector(v), q, new Vector(w));	}	static Vector add(Vector a, Vector b) {		return new Vector(a.add(b));	}	static double dot(Vector a, Vector b) {		return a.dot(b);	}	static double cross(Vector a, Vector b) {		return a.cross(b);	}	static double cross(Point a, Point b) {		return cross(new Vector(a), new Vector(b));	}	static double cross(Point o, Point a, Point b) {		return cross(new Vector(o, a), new Vector(o, b));	}	static double cross(Point a, Point b, Point c, Point d) {		return cross(new Vector(a, b), new Vector(c, d));	}	static double length(Vector r) {		return r.length();	}}
```##Line```class Line extends Vector {	Point s, e;	Line() {	}	Line(Point _s, Point _e) {		s = _s;		e = _e;	}	Line(double x1, double y1, double x2, double y2) {		this(new Point(x1, y1), new Point(x2, y2));	}	Vector vector() {		return new Vector(s, e);	}	static boolean isLineInter(Line l1, Line l2) {		if (l1.s.equals(l1.e) || l2.s.equals(l2.e))			return false;		return dcmp(cross(l2.s, l1.s, l1.e) * cross(l2.e, l1.s, l1.e)) <= 0;	}	static boolean isSegInter(Point s1, Point e1, Point s2, Point e2) {		if (dcmp(Math.min(s1.x, e1.x) - Math.max(s2.x, e2.x)) <= 0				&& dcmp(Math.min(s1.y, e1.y) - Math.max(s2.y, e2.y)) <= 0				&& dcmp(Math.min(s2.x, e2.x) - Math.max(s1.x, e1.x)) <= 0				&& dcmp(Math.min(s2.y, e2.y) - Math.max(s1.y, e1.y)) <= 0				&& dcmp(Vector.cross(s2, e2, s1) * Vector.cross(s2, e2, e1)) <= 0				&& dcmp(Vector.cross(s1, e1, s2) * Vector.cross(s1, e1, e2)) <= 0)			return true;		return false;	}	static boolean isSegInter2(Point p1, Point p2, Point p3, Point p4) // 判断严格相交	{		return dcmp(cross(p3, p4, p1)) * dcmp(cross(p3, p4, p2)) == -1;	}	static boolean isSegInter(Line l1, Line l2) {		return isSegInter(l1.s, l1.e, l2.s, l2.e);	}	static boolean isSegInter2(Line l1, Line l2) {		return isSegInter2(l1.s, l1.e, l2.s, l2.e);	}}
```##Polygon```class Polygon extends Vector {	int num;	Point[] v;	Polygon() {	}	Polygon(int n) {		num = n;		v = new Point[num + 2];	}	boolean IsConvexBag() {		int direction = 0;// 1:右手正螺旋，逆时针 -1:左手正螺旋，顺时针		for (int i = 0; i < num; i++) {			int tmp = dcmp(cross(v[i], v[i + 1], v[i + 1], v[i + 2]));			if (direction == 0) // 避免最初的点出现共线的情况				direction = tmp;			if (direction * tmp < 0) // 只要Vec是凸包，那么无论Vec的旋转方向如何，direction*temp都会>=0				return false;		}		return true;	}}
```##Round
```class Round extends Vector {	double r;	Point o;	final static double pi = Math.acos(-1.0);	Round(double _r, double _x, double _y) {		r = _r;		x = _x;		y = _y;		o = new Point(x, y);	}	static double Rad2Deg(double rad) {		return rad * 180.0 / pi;	}	static double Deg2Rad(double deg) {		return deg * pi / 180.0;	}	double Area() {		return pi * r * r;	}	static double CommonArea(Round A, Round B) {		double area = 0.0;		Round M = dcmp(A.r - B.r) > 0 ? A : B;		Round N = dcmp(A.r - B.r) > 0 ? B : A;		double D = dist(M.o, N.o);		if (dcmp(M.r + N.r - D) > 0 && dcmp(M.r - N.r - D) < 0) {			double cosM = (M.r * M.r + D * D - N.r * N.r) / (2.0 * M.r * D);			double cosN = (N.r * N.r + D * D - M.r * M.r) / (2.0 * N.r * D);			double alpha = 2.0 * Math.acos(cosM);			double beta = 2.0 * Math.acos(cosN);			double TM = 0.5 * M.r * M.r * Math.sin(alpha);			double TN = 0.5 * N.r * N.r * Math.sin(beta);			double FM = 0.5 * alpha / pi * M.Area();			double FN = 0.5 * beta / pi * N.Area();			area = FM + FN - TM - TN;		} else if (dcmp(M.r - N.r - D) >= 0) {			area = N.Area();		}		return area;	}	/* 判断圆与多边形的关系 */	boolean IsFitPoly(Polygon pl) {		for (int i = 0; i <= pl.num; i++) {			int k = dcmp(Math.abs(cross(o, pl.v[i], o, pl.v[i + 1]) / dist(pl.v[i], pl.v[i + 1])) - r);			if (k < 0)				return false;		}		return true;	}	boolean IsInPoly(Polygon pl) {		double CircleAngle = 0.0; // 环绕角		for (int i = 1; i <= pl.num; i++) // 注意重复边不计算			if (dcmp(cross(o, pl.v[i], o, pl.v[i + 1])) >= 0)				CircleAngle += angle(o, pl.v[i], pl.v[i + 1]);			else				CircleAngle -= angle(o, pl.v[i], pl.v[i + 1]);		if (dcmp(CircleAngle) == 0) // CircleAngle=0, Peg在多边形外部			return false;		else if (dcmp(CircleAngle - pi) == 0 || dcmp(CircleAngle + pi) == 0) // CircleAngle=180,																				// Peg在多边形边上(不包括顶点)		{			if (dcmp(r) == 0)				return true;		} else if (dcmp(CircleAngle - 2 * pi) == 0 || dcmp(CircleAngle + 2 * pi) == 0) // CircleAngle=360,																						// Peg在多边形边内部			return true;		else // CircleAngle=(0,360)之间的任意角， Peg在多边形顶点上		{			if (dcmp(r) == 0)				return true;		}		return false;	}	static double TriAngleCircleInsection(Round C, Point A, Point B) {		Vector OA = new Vector(A.sub(C.o)), OB = new Vector(B.sub(C.o));		Vector BA = new Vector(A.sub(B)), BC = new Vector(C.o.sub(B));		Vector AB = new Vector(B.sub(A)), AC = new Vector(C.o.sub(A));		double DOA = OA.length(), DOB = OB.length(), DAB = AB.length(), r = C.r;		if (dcmp(cross(OA, OB)) == 0)			return 0;		if (dcmp(DOA - C.r) < 0 && dcmp(DOB - C.r) < 0)			return cross(OA, OB) * 0.5;		else if (dcmp(DOB - r) < 0 && dcmp(DOA - r) >= 0) {			double x = (dot(BA, BC) + Math.sqrt(r * r * DAB * DAB - cross(BA, BC) * cross(BA, BC))) / DAB;			double TS = cross(OA, OB) * 0.5;			return Math.asin(TS * (1 - x / DAB) * 2 / r / DOA) * r * r * 0.5 + TS * x / DAB;		} else if (dcmp(DOB - r) >= 0 && dcmp(DOA - r) < 0) {			double y = (dot(AB, AC) + Math.sqrt(r * r * DAB * DAB - cross(AB, AC) * cross(AB, AC))) / DAB;			double TS = cross(OA, OB) * 0.5;			return Math.asin(TS * (1 - y / DAB) * 2 / r / DOB) * r * r * 0.5 + TS * y / DAB;		} else if (dcmp(Math.abs(cross(OA, OB)) - r * DAB) >= 0 || dcmp(dot(AB, AC)) <= 0 || dcmp(dot(BA, BC)) <= 0) {			if (dcmp(dot(OA, OB)) < 0) {				if (dcmp(cross(OA, OB)) < 0)					return (-Math.acos(-1.0) - Math.asin(cross(OA, OB) / DOA / DOB)) * r * r * 0.5;				else					return (Math.acos(-1.0) - Math.asin(cross(OA, OB) / DOA / DOB)) * r * r * 0.5;			} else				return Math.asin(cross(OA, OB) / DOA / DOB) * r * r * 0.5;		} else {			double x = (dot(BA, BC) + Math.sqrt(r * r * DAB * DAB - cross(BA, BC) * cross(BA, BC))) / DAB;			double y = (dot(AB, AC) + Math.sqrt(r * r * DAB * DAB - cross(AB, AC) * cross(AB, AC))) / DAB;			double TS = cross(OA, OB) * 0.5;			return (Math.asin(TS * (1 - x / DAB) * 2 / r / DOA) + Math.asin(TS * (1 - y / DAB) * 2 / r / DOB)) * r * r					* 0.5 + TS * ((x + y) / DAB - 1);		}	}}```