![ACM-ICPC](http://cs.ecust.edu.cn/acm/img/icpc_logo.png)

<h1 align="center">Java Code Template By Semprathlon</h1>


#Input 输入An enhanced `InputReader` supporting keeping reading data until the end of input while the number of input cases is unknown:  
一个加强版的`输入器` ，支持读到输入文件末尾的方式:

```class InputReader {	public BufferedReader reader;	public StringTokenizer tokenizer;	public InputReader(InputStream stream) {		reader = new BufferedReader(new InputStreamReader(stream), 32768);		tokenizer = null;	}	public String nextLine() {		String tmp = null;		try {			tmp = reader.readLine();			tokenizer = new StringTokenizer(tmp);		} catch (IOException e) {			throw new RuntimeException(e);		} catch (NullPointerException e) {			return null;		}		return tmp;	}	public String next() {		while (tokenizer == null || !tokenizer.hasMoreTokens()) {			try {				tokenizer = new StringTokenizer(reader.readLine());			} catch (IOException e) {				throw new RuntimeException(e);			}		}		return tokenizer.nextToken();	}	public int nextInt() {		return Integer.parseInt(next());	}	public long nextLong() {		return Long.parseLong(next());	}	public double nextDouble() {		return Double.parseDouble(next());	}}```

Keeping reading data until the end of input：  
读取数据直至文件末尾：

```
while(in.nextLine!=null){
//TODO ...
}
```#Data Structure 数据结构篇##Queue 队列`Queue<Integer> que=new LinkedList<Integer>();`##Priority Queue 优先队列
Please initialize it properly before using!   
使用时注意初始化！  `PriorityQueue<Integer> que=new PriorityQueue<Integer>();`##Heap 堆This is a minimum heap:  
这是一个小根堆：  

```class Heap {    private int maxn;    int[] data;    int r;     Heap(int size) {    	maxn=size;        data = new int[maxn];        r = 0;    }        void clear(){    	//Arrays.fill(data, 0);    	r=0;    }        public int size() {        return r;    }     void swap(int a, int b) {        int tmp = data[a];        data[a] = data[b];        data[b] = tmp;    }     void up(int p) {        if (!(p > 0))            return;        int q = p >> 1;        if (data[p] < data[q]) {            swap(p, q);            up(q);        }    }     void down(int p) {        int q;        if ((p << 1) >= r)            return;        else if ((p << 1) == r - 1) {            q = p << 1;        } else {q = (data[p << 1] < data[p << 1 | 1] ?     p << 1 : p << 1 | 1);        }        if (data[p] > data[q]) {            swap(p, q);            down(q);        }    }     void push(int n) {        data[r++] = n;        up(r - 1);    }     int pop() {        int res = data[0];        swap(0, r - 1);        r--;        down(0);        return res;    }     int top() {        return data[0];    }    }```##Adjacent Table 链式前向星（邻接表）
```class Edge{	int v,w,nx;	Edge(int _u,int _v,int _w){		v=_u;w=_v;nx=_w;	}}class Graph{	int[] h;	int sz;	Edge[] edge;	Graph(int size){		sz=size;		h=new int[sz+1];		edge=new Edge[sz+1];		Arrays.fill(h, -1);		h[0]=0;	}	void add(int u,int v,int w){		edge[h[0]]=new Edge(v,w,h[u]);		h[u]=h[0]++;	}}```#Modify,Update & Query 区间修改、更新与查询篇##RMQ 区间最值查询（静态，离线）
```void RMQ(){ //initializing->O(nlogn){    for(int i = 1; i != maxm; ++i)        for(int j = 1; j <= n; ++j)            if(j + (1 << i) - 1 <= n){                maxsum[i][j] = Math.max(maxsum[i - 1][j],                             maxsum[i - 1][j + (1 << i >> 1)]);                minsum[i][j] = Math.min(minsum[i - 1][j],                             minsum[i - 1][j + (1 << i >> 1)]);                }    }     int query(int src,int des){        int k = (int)(Math.log(des - src + 1.0) / Math.log(2.0));        int maxres = Math.max(maxsum[k][src],                           maxsum[k][des - (1 << k) + 1]);        int minres = Math.min(minsum[k][src],                           minsum[k][des - (1 << k) + 1]);        return maxres-minres;    }
```

##Segment Tree 线段树
Suppose there are n distinct integers ranging from 1 to n,and the kth largest one is required.  
假设有范围在[1,n]内的n个不同整数，求第k大的数。

```
class ST {
	int[] l, r, m, v;
	int sz;

	ST() {
	}

	ST(int _sz) {
		sz = _sz << 2;
		l = new int[sz];
		r = new int[sz];
		m = new int[sz];
		v = new int[sz];
	}

	void build(int k, int x, int y) {
		l[k] = x;
		r[k] = y;
		m[k] = (x + y) >> 1;
		if (x < y) {
			build(k << 1, x, m[k]);
			build(k << 1 | 1, m[k] + 1, y);
		}
		v[k] = y - x + 1;
	}

	int query(int k, int x, int y, int q) {
		if (l[k] == r[k]) {
			v[k] = 0;
			return l[k];
		}
		int res = 0;
		if (v[k << 1 | 1] >= q)
			res = query(k << 1 | 1, m[k] + 1, y, q);
		else
			res = query(k << 1, x, m[k], q - v[k << 1 | 1]);
		v[k] = v[k << 1] + v[k << 1 | 1];
		return res;
	}

}
```

##Binary Indexed Tree 树状数组
Suppose there are n distinct integers ranging from 1 to n,and the kth largest one is required(use `query(k-1)`).  
假设有范围在[1,n]内的n个不同整数，求第k大的数（调用`query(k-1)`）。

```
class BIT {
	int[] data;
	int sz;

	BIT() {
	}

	BIT(int _sz) {
		sz = _sz;
		data = new int[sz + 1];
	}

	int lowbit(int x) {
		return x & (-x);
	}

	void add(int p, int v) {
		while (p <= sz) {
			data[p] += v;
			p += lowbit(p);
		}
	}

	int sum(int p) {
		int res = 0;
		while (p > 0) {
			res += data[p];
			p -= lowbit(p);
		}
		return res;
	}

	int find(int p) {
		int l = 1, r = sz;
		while (l < r) {
			int mid = (l + r) >> 1;
			if (sum(mid) <= p)
				l = mid + 1;
			else
				r = mid;
		}

		return l;
	}
}
```#Graph Theory 图论篇##The Maximum Matching of Bipartite Graph 二分图最大匹配（匈牙利算法）
```boolean dfs(int u){        for(int v:adj[u]){            if (vis[v]) continue;            vis[v]=true;            if (match[v]<0||dfs(match[v])){                match[v]=u;                return true;            }        }        return false;    }int maxmatch(){        int res=0;        Arrays.fill(match, -1);        for(int i=1;i<=cnt;i++){            Arrays.fill(vis, false);            if (dfs(i)) res++;        }        return res;    }
```#Number Theory 数论篇##Quick power and modulo 快速幂取模To calculate `n^m%mod`:  
计算`n^m%mod`：  

```long pow_mod(long n, long m, long mod) {		long res = 1L;		n %= mod;		while (m > 0L) {			if ((m & 1L) > 0L)				res = res * n % mod;			n = n * n % mod;			m >>= 1;		}		return res;}```##Multiply and modulo 乘法取模To calculate `(nm)%mod`:  
计算`(nm)%mod`：

```long mul_mod(long n, long m, long mod) {		long ans = 0L;		n %= mod;		while (m > 0L) {			if ((m & 1L) > 0L)				ans = (ans + n) % mod;			m >>= 1;			n = (n + n) % mod;		}		return ans;}
```
##Division and modulo 除法取模To calculate `n/m%mod` correctly (mod is a prime number thus `φ(mod)=mod-1`):  
计算`n/m%mod`（当mod是一个质数时有`φ(mod)=mod-1`）：

```long div_mod(long n, long m, long mod) {		return n * pow_mod(m, mod - 2, mod) % mod;
		// return n * pow_mod(m, phi(mod) - 1, mod) % mod;}
```
##Factorial and modulo 阶乘取模
```void Get_Fac(long n, long mod) {		fac[0] = 1;		for (int i = 1; i <= n; i++) {			fac[i] = fac[i - 1] * i;			fac[i] %= mod;		}}
```##Prime filtering (linear) 线性筛质数
```	int[] pri,fstp;	void filter_prime(){		pri=new int[maxn];		fstp=new int[maxn];		for(int i=2;i<maxn;i++){			if (fstp[i]==0){				pri[++pri[0]]=i;			}			for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){				int k=i*pri[j];				fstp[k]=pri[j];			}		}	}```

##Calculating Euler function `φ(n)` 求欧拉函数值

```
	int[] pri,phi,fstp;
	void cal_euler(){
        pri=new int[maxn];
        fstp=new int[maxn];
        phi=new int[maxn];
        phi[1]=1;
        for(int i=2;i<maxn;i++){
            if (fstp[i]==0){
                pri[++pri[0]]=i;
                phi[i]=i-1;
            }
            for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){
                int k=i*pri[j];
                fstp[k]=pri[j];
                if (i%pri[j]==0){
                    phi[k]=phi[i]*pri[j];
                    break;
                }
                else{
                    phi[k]=phi[i]*(pri[j]-1);
                }
            }
        }
    }
```

##Calculating Möbius function `μ(n)` 求莫比乌斯函数值

```
	int[] pri,fstp,miu;
	void cal_euler(){
        pri=new int[maxn];
        fstp=new int[maxn];
        miu=new int[maxn];
        miu[1]=1;
        for(int i=2;i<maxn;i++){
            if (fstp[i]==0){
                pri[++pri[0]]=i;
                miu[i]=-1;
            }
            for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){
                int k=i*pri[j];
                fstp[k]=pri[j];
                if (i%pri[j]==0){
                    miu[k]=0;
                    break;
                }
                else{
                    miu[k]=-miu[i];
                }
            }
        }
    }
```##Integer Prime Factorization 分解质因数
```Vector<Integer> get_prime_factor(int n){        Vector<Integer> res=new Vector<Integer>();        res.clear();        for(int i=2;i*i<=n;i++)            if (n%i==0){                res.add(i);                while(n%i==0)                    n/=i;            }        if (n>1) res.add(n);        return res;    }
```

##Quick Greatest Common Divisor 快速求最大公约数
```
int kgcd(int a, int b) {
		if (a == 0)
			return b;
		if (b == 0)
			return a;
		if ((a & 1) == 0 && (b & 1) == 0)
			return kgcd(a >> 1, b >> 1) << 1;
		else if ((b & 1) == 0)
			return kgcd(a, b >> 1);
		else if ((a & 1) == 0)
			return kgcd(a >> 1, b);
		else
			return kgcd(Math.abs(a - b), Math.min(a, b));
}
```
##Extended Euclid Theorem 扩展欧几里得定理Suppose `ax+by=gcd⁡(a,b)`,and the value of `x` and of `y` are required.  
已知`ax+by=gcd⁡(a,b)`，求x与y的值。  

```void extgcd(long a, long b) {		if (b == 0L) {			x = 1L;			y = 0L;			return;		}		extgcd(b, a % b);		long t = x;		x = y;		y = t - a / b * y;}
```##Lucas Theorem 卢卡斯定理`C(n, m, mod)` refers to `C_n^m%mod`.  
`C(n, m, mod)`表示`C_n^m%mod`。  

```long Lucas(long n, long m, long mod) {		long ret = 1L;		while (n > 0L && m > 0L) {			if (n % mod < m % mod)				return 0L;			ret = mul_mod(ret, C(n, m, mod), mod);			ret %= mod;			n /= mod;			m /= mod;		}		return ret;}
```
##Chinese Remainer Theorem 中国剩余定理Resolving `x ≡a_1  (mod m_1),x ≡a_2  (mod m_2 ),…,x ≡a_n  (mod m_n))`.  
解同余方程组`x ≡a_1  (mod m_1),x ≡a_2  (mod m_2 ),…,x ≡a_n  (mod m_n))`。  

```long CRT(long n, long[] a, long[] m) {		long pro = 1L, res = 0L;		for (int i = 0; i < n; i++)			pro *= m[i];		for (int i = 0; i < n; i++) {			long w = pro / m[i];			extgcd(m[i], w);			res = (res + mul_mod(y, mul_mod(w, a[i], pro), pro)) % pro;		}		return (res + pro) % pro;}
```#Combinatorial Mathematics 组合数学篇##Combination Calculation #1 组合数计算1
```double[][] c;void init(){		c=new double[maxn][maxn];		c[0][0]=1;		for(int i=1;i<maxn;i++){			c[i][0]=c[i][i]=1;			for(int j=1;j<i;j++)				c[i][j]=c[i-1][j]+c[i-1][j-1];		}	}
```
	##Combination Calculation #2 组合数计算2It is guaranteed that n≥m,but n! should be computed in advance.

```long C(long n, long m, long mod) {		int a = (int) (n % mod), b = (int) (m % mod);		return div_mod(fac[a], mul_mod(fac[a - b], fac[b], mod), mod);}
```#Computational Geometry 计算几何篇##Double Comparing 实数的比较
```final static double eps = 1e-3;	static int dcmp(double d) {		if (Math.abs(d) < eps)			return 0;		return d > 0 ? 1 : -1;	}
```

##Platform 几何框架###Point 点
```class Point {	double x, y;	Point() {	}	Point(double _x, double _y) {		x = _x;		y = _y;	}	Point(Point p) {		this(p.x, p.y);	}
	
	static class Comp implements Comparator<Point> {		Point prep;		Comp(Point p) {			prep = p;		}		public int compare(Point a, Point b) {			double tmp = Point.cross(prep, a, b);			if (dcmp(tmp) == 0)				return -dcmp(dist(a, prep) - dist(b, prep));			return -dcmp(tmp);		}	}
```
###Vector 向量
```class Vector extends Point {	Vector() {	}	Vector(double _x, double _y) {		x = _x;		y = _y;	}	Vector(Point a, Point b) {		this(b.x - a.x, b.y - a.y);	}	Vector(Point p) {		this(p.x, p.y);	}}
```
###Line 直线/线段
```class Line extends Vector {	Point s, e;	Line() {	}	Line(Point _s, Point _e) {		s = _s;		e = _e;	}	Line(double x1, double y1, double x2, double y2) {		this(new Point(x1, y1), new Point(x2, y2));	}	Vector vector() {		return new Vector(s, e);	}}
```
###Polygon 多边形
```class Polygon extends Vector {	int num;	Point[] v;	Polygon() {	}	Polygon(int n) {		num = n;		v = new Point[num + 2];	}}
```
###Round 圆
```class Round extends Vector {	double r;	Point o;	Round(double _r, double _x, double _y) {		r = _r;		x = _x;		y = _y;		o = new Point(x, y);	}	double Area() {		return pi * r * r;	}}
```
##Function 函数计算
###Arithmetic 代数运算
```
	double pi = Math.acos(-1.0);

	double Rad2Deg(double rad) {		return rad * 180.0 / pi;	}	double Deg2Rad(double deg) {		return deg * pi / 180.0;	}

	double dist(Point a, Point b) {		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));	}
	
	double angle(Vector a, Vector b) {		return Math.acos(dot(a, b) / a.length() / b.length());	}	double angle(Point a, Point b) {		return angle(new Vector(a), new Vector(b));	}	double angle(Point o, Point a, Point b) {		return angle(new Vector(o, a), new Vector(o, b));	}
		
	double dot(Vector r) {		return x * r.x + y * r.y;	}	double cross(Vector r) {		return x * r.y - y * r.x;	}	double length() {		return Math.sqrt(this.dot(this));	}
	
	public boolean equals(Point p) {		return dcmp(Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y))) == 0;	}
	
	double CommonArea(Round A, Round B) {		double area = 0.0;		Round M = dcmp(A.r - B.r) > 0 ? A : B;		Round N = dcmp(A.r - B.r) > 0 ? B : A;		double D = dist(M.o, N.o);		if (dcmp(M.r + N.r - D) > 0 && dcmp(M.r - N.r - D) < 0) {			double cosM = (M.r * M.r + D * D - N.r * N.r) / (2.0 * M.r * D);			double cosN = (N.r * N.r + D * D - M.r * M.r) / (2.0 * N.r * D);			double alpha = 2.0 * Math.acos(cosM);			double beta = 2.0 * Math.acos(cosN);			double TM = 0.5 * M.r * M.r * Math.sin(alpha);			double TN = 0.5 * N.r * N.r * Math.sin(beta);			double FM = 0.5 * alpha / pi * M.Area();			double FN = 0.5 * beta / pi * N.Area();			area = FM + FN - TM - TN;		} else if (dcmp(M.r - N.r - D) >= 0) {			area = N.Area();		}		return area;	}
	
	double TriAngleCircleInsection(Round C, Point A, Point B) {		Vector OA = new Vector(A.sub(C.o)), OB = new Vector(B.sub(C.o));		Vector BA = new Vector(A.sub(B)), BC = new Vector(C.o.sub(B));		Vector AB = new Vector(B.sub(A)), AC = new Vector(C.o.sub(A));		double DOA = OA.length(), DOB = OB.length(), DAB = AB.length(), r = C.r;		if (dcmp(cross(OA, OB)) == 0)			return 0;		if (dcmp(DOA - C.r) < 0 && dcmp(DOB - C.r) < 0)			return cross(OA, OB) * 0.5;		else if (dcmp(DOB - r) < 0 && dcmp(DOA - r) >= 0) {			double x = (dot(BA, BC) + Math.sqrt(r * r * DAB * DAB - cross(BA, BC) * cross(BA, BC))) / DAB;			double TS = cross(OA, OB) * 0.5;			return Math.asin(TS * (1 - x / DAB) * 2 / r / DOA) * r * r * 0.5 + TS * x / DAB;		} else if (dcmp(DOB - r) >= 0 && dcmp(DOA - r) < 0) {			double y = (dot(AB, AC) + Math.sqrt(r * r * DAB * DAB - cross(AB, AC) * cross(AB, AC))) / DAB;			double TS = cross(OA, OB) * 0.5;			return Math.asin(TS * (1 - y / DAB) * 2 / r / DOB) * r * r * 0.5 + TS * y / DAB;		} else if (dcmp(Math.abs(cross(OA, OB)) - r * DAB) >= 0 || dcmp(dot(AB, AC)) <= 0 || dcmp(dot(BA, BC)) <= 0) {			if (dcmp(dot(OA, OB)) < 0) {				if (dcmp(cross(OA, OB)) < 0)					return (-Math.acos(-1.0) - Math.asin(cross(OA, OB) / DOA / DOB)) * r * r * 0.5;				else					return (Math.acos(-1.0) - Math.asin(cross(OA, OB) / DOA / DOB)) * r * r * 0.5;			} else				return Math.asin(cross(OA, OB) / DOA / DOB) * r * r * 0.5;		} else {			double x = (dot(BA, BC) + Math.sqrt(r * r * DAB * DAB - cross(BA, BC) * cross(BA, BC))) / DAB;			double y = (dot(AB, AC) + Math.sqrt(r * r * DAB * DAB - cross(AB, AC) * cross(AB, AC))) / DAB;			double TS = cross(OA, OB) * 0.5;			return (Math.asin(TS * (1 - x / DAB) * 2 / r / DOA) + Math.asin(TS * (1 - y / DAB) * 2 / r / DOB)) * r * r					* 0.5 + TS * ((x + y) / DAB - 1);		}	}```
###Geometry 几何运算
```
	Point add(Point r) {		return new Point(x + r.x, y + r.y);	}	Point sub(Point r) {		return new Point(x - r.x, y - r.y);	}	Point mul(double r) {		return new Point(x * r, y * r);	}
	
	Point move(double dx, double dy) {		return new Point(x + dx, y + dy);	}
		Point rotate(double a) {		return new Point(x * Math.cos(a) - y * Math.sin(a), x * Math.sin(a) + y * Math.cos(a));	}	Point rotate(double dx, double dy, double a) {		return this.move(-dx, -dy).rotate(a).move(dx, dy);	}	Point mid(Point a, Point b) {		return new Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);	}	Vector normal() {		double len = this.length();		return new Vector(-y / len, x / len);	}
		Point GetLineIntersection(Point p, Vector v, Point q, Vector w) {		Vector u = new Vector(p.sub(q));		double t = cross(w, u) / cross(v, w);		return p.add(v.mul(t));	}	Point GetLineIntersection(Point p, Point v, Point q, Point w) {		return GetLineIntersection(p, new Vector(v), q, new Vector(w));	}
	
```
###Judge 关系判断
```
	boolean isLineInter(Line l1, Line l2) {		if (l1.s.equals(l1.e) || l2.s.equals(l2.e))			return false;		return dcmp(cross(l2.s, l1.s, l1.e) * cross(l2.e, l1.s, l1.e)) <= 0;	}	boolean isSegInter(Point s1, Point e1, Point s2, Point e2) {		if (dcmp(Math.min(s1.x, e1.x) - Math.max(s2.x, e2.x)) <= 0				&& dcmp(Math.min(s1.y, e1.y) - Math.max(s2.y, e2.y)) <= 0				&& dcmp(Math.min(s2.x, e2.x) - Math.max(s1.x, e1.x)) <= 0				&& dcmp(Math.min(s2.y, e2.y) - Math.max(s1.y, e1.y)) <= 0				&& dcmp(Vector.cross(s2, e2, s1) * Vector.cross(s2, e2, e1)) <= 0				&& dcmp(Vector.cross(s1, e1, s2) * Vector.cross(s1, e1, e2)) <= 0)			return true;		return false;	}	boolean isSegInter2(Point p1, Point p2, Point p3, Point p4) // vigorous intersection	{		return dcmp(cross(p3, p4, p1)) * dcmp(cross(p3, p4, p2)) == -1;	}	boolean isSegInter(Line l1, Line l2) {		return isSegInter(l1.s, l1.e, l2.s, l2.e);	}	boolean isSegInter2(Line l1, Line l2) {		return isSegInter2(l1.s, l1.e, l2.s, l2.e);	}
	
	boolean IsConvexBag() {		int direction = 0;// 1:counter-clockwise -1:clockwise		for (int i = 0; i < num; i++) {			int tmp = dcmp(cross(v[i], v[i + 1], v[i + 1], v[i + 2]));			if (direction == 0) // prevent co-line				direction = tmp;			if (direction * tmp < 0) // as Vec is a ConvexBag,direction*temp>=0 no matter how direction rotates				return false;		}		return true;	}
	
	boolean IsInPoly(Polygon pl) {		double CircleAngle = 0.0; // rotation angle		for (int i = 1; i <= pl.num; i++) // ignore the repetitive edges			if (dcmp(cross(o, pl.v[i], o, pl.v[i + 1])) >= 0)				CircleAngle += angle(o, pl.v[i], pl.v[i + 1]);			else				CircleAngle -= angle(o, pl.v[i], pl.v[i + 1]);		if (dcmp(CircleAngle) == 0) // CircleAngle=0, Peg outside			return false;		else if (dcmp(CircleAngle - pi) == 0 || dcmp(CircleAngle + pi) == 0) // CircleAngle=180,																				// Peg inside(excluding vertices)		{			if (dcmp(r) == 0)				return true;		} else if (dcmp(CircleAngle - 2 * pi) == 0 || dcmp(CircleAngle + 2 * pi) == 0) // CircleAngle=360, Peg inside			return true;		else // CircleAngle in range (0,360)， Peg on the vertex		{			if (dcmp(r) == 0)				return true;		}		return false;	}
	
	boolean IsFitPoly(Polygon pl) {		for (int i = 0; i <= pl.num; i++) {			int k = dcmp(Math.abs(cross(o, pl.v[i], o, pl.v[i + 1]) / dist(pl.v[i], pl.v[i + 1])) - r);			if (k < 0)				return false;		}		return true;	}
```
#String 字符串篇##The Knuth-Morris-Pratt Algorithm KMP算法
```class KMP {	static int next[];	static void getNext(String T) {		next = new int[T.length() + 1];		int j = 0, k = -1;		next[0] = -1;		while (j < T.length())			if (k == -1 || T.charAt(j) == T.charAt(k))				next[++j] = ++k;			else				k = next[k];	}	static int Index(String S, String T) {		int i = 0, j = 0;		getNext(T);		for (i = 0; i < S.length() && j < T.length(); i++) {			while (j > 0 && S.charAt(i) != T.charAt(j))				j = next[j];			if (S.charAt(i) == T.charAt(j))				j++;		}		if (j == T.length())			return i - T.length();		else			return -1;	}	static int Count(String S, String T) {		int res = 0, j = 0;		if (S.length() == 1 && T.length() == 1) {			if (S.charAt(0) == T.charAt(0))				return 1;			else				return 0;		}		getNext(T);		for (int i = 0; i < S.length(); i++) {			while (j > 0 && S.charAt(i) != T.charAt(j))				j = next[j];			if (S.charAt(i) == T.charAt(j))				j++;			if (j == T.length()) {				res++;				j = next[j];			}		}		return res;	}}```##Aho-Corasick automaton AC自动机
```class AC {	final int maxl = 500010, maxc = 26;	final char fstc = 'a';	int root, L;	int[][] next;	int[] fail, end;	Queue<Integer> que = new LinkedList<Integer>();	AC() {		next = new int[maxl][maxc];		fail = new int[maxl];		end = new int[maxl];		L = 0;		root = newnode();	}	void clear() {		Arrays.fill(fail, 0);		Arrays.fill(end, 0);		L = 0;		root = newnode();	}	int newnode() {		Arrays.fill(next[L], -1);		end[L++] = 0;		return L - 1;	}	void insert(String str) {		int now = root;		for (int i = 0; i < str.length(); i++) {			char ch = str.charAt(i);			if (next[now][ch - fstc] == -1)				next[now][ch - fstc] = newnode();			now = next[now][ch - fstc];		}		end[now]++;	}	void build() {		que.clear();		fail[root] = root;		for (int i = 0; i < maxc; i++)			if (next[root][i] == -1)				next[root][i] = root;			else {				fail[next[root][i]] = root;				que.add(next[root][i]);			}		while (!que.isEmpty()) {			int now = que.poll();			for (int i = 0; i < maxc; i++)				if (next[now][i] == -1)					next[now][i] = next[fail[now]][i];				else {					fail[next[now][i]] = next[fail[now]][i];					que.add(next[now][i]);				}		}	}	int query(String str) {		int now = root, res = 0;		for (int i = 0; i < str.length(); i++) {			char ch = str.charAt(i);			now = next[now][ch - fstc];			int tmp = now;			while (tmp != root) {				res += end[tmp];				// end[tmp] = 0;				tmp = fail[tmp];			}		}		return res;	}}
```#Network Flowing 网络流篇##Dinic 最大流/最小割
```class Dinic{    public Dinic(int sourse , int meet){                 this.sourse = sourse ;                  this.meet = meet ;                 Arrays.fill(g,  0) ;                 id = 1 ;    }     static final  int  maxn = 2008 , maxm = 500000 ;    static class Edge{           int v , f ,next ;           Edge(){}           Edge(int _v , int _f , int _next){                 this.v = _v ;                 this.f = _f ;                 this.next = _next ;           }    };    int  sourse , meet ;    int  id ;    static Edge[] e = new Edge[maxm*2 + 10] ;    static int[] g = new int[maxn + 10] ;     public void  add(int u , int v , int f){              e[++id] = new Edge(v , f ,g[u]) ;              g[u] = id ;              e[++id] = new Edge(u , 0 , g[v]) ;              g[v] = id ;    }     Queue<Integer> que  = new Queue<Integer>(maxm);    static boolean[] vis = new boolean[maxn + 10] ;    static int[]  dist = new int[maxn + 10] ;     void bfs(){             Arrays.fill(dist, 0) ;             while(! que.empty()) que.pop() ;             que.push(sourse) ;             vis[sourse] = true ;             while(! que.empty()){                  int u = que.pop() ;                  for(int i = g[u] ; i > 0 ; i = e[i].next){                       int v = e[i].v ;                       if(e[i].f  > 0 && !vis[v]){                             que.push(v) ;                             dist[v] = dist[u] + 1 ;                             vis[v] = true ;                       }                  }             }    }     int  dfs(int u , int delta){             if(u == meet) return delta ;             int ans = 0 ;             for(int i = g[u] ; i > 0  && delta > 0  ; i = e[i].next){                   int  v = e[i].v ;                   if(e[i].f  > 0 && dist[v] == dist[u] + 1){                        int d = dfs(v , Math.min(delta , e[i].f)) ;                        e[i].f -= d ;                        e[i^1].f += d ;                        delta -= d ;                        ans += d ;                   }             }             return ans ;    }     public int  maxflow(){                 int ans = 0 ;                 while(true){                     Arrays.fill(vis, false) ;                     bfs() ;                     if(! vis[meet]) return ans  ;                     ans += dfs(sourse , Integer.MAX_VALUE) ;                 }    } }```