/** Sep 2, 2015 5:58:24 PM
 * PrjName:hdu4998
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

    /**
     * @param args
     */
	final static double eps=1e-5;
    final static double pi=Math.acos(-1.0);
    static int dcmp(double d){
        if (Math.abs(d)<eps) return 0;
        return d>0?1:-1;
    }
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt();
        while(T-->0){
            int n=in.nextInt();
            Point p1=new Point(0, 0),q1=new Point(p1);
            Point p2=new Point(0, 1),q2=new Point(p2);
            for(int i=1;i<=n;i++){
                double x=in.nextDouble();
                double y=in.nextDouble();
                double a=in.nextDouble();
                q1=q1.rotate(x, y, a);
                q2=q2.rotate(x, y, a);
            }
            Vector v1=new Vector(p1, q1).normal();
            Vector v2=new Vector(p2, q2).normal();
            Point o=Vector.GetLineIntersection(Point.mid(p1, q1), v1, Point.mid(p2, q2), v2);
            double ans=Vector.angle(o,p1,q1);
            if (Vector.cross(new Vector(o, p1), new Vector(o, q1))<0)
                ans=2*pi-ans;
            out.printf("%.5f %.5f %.5f\n", o.x,o.y,ans);
        }
        out.flush();
        out.close();
    }
    static class Point{
        double x,y;
        Point(){}
        Point(double _x,double _y){
            x=_x;y=_y;
        }
        Point(Point p){
            this(p.x,p.y);
        }
        boolean equals(Point p){
            return dcmp(x-p.x)==0&&dcmp(y-p.y)==0;
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
    }
    static class Vector extends Point{
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
}


class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;
 
    public InputReader(InputStream stream){
           reader = new BufferedReader(
                   new InputStreamReader(stream), 32768);
           tokenizer = null;
    }
 
    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(
                           reader.readLine());
            }catch (IOException e) {
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
