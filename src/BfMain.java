/**
 * @PackageName:PACKAGE_NAME
 * @ClassName: BfMain
 * @Description:
 * @author:裴立志
 * @date: 2020/9/1320:33
 */
class Point {
    int x;
    int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BfMain {
    public static void main(String[] args) {
        BfMain main = new BfMain();
        Point[] points = {new Point(0, 0), new Point(1, 1),
                          new Point(0, 0),new Point(2,2),
                          new Point(3,1),new Point(1,1)};
        int i = main.maxPoints(points);
        System.out.println("i = " + i);
    }
    
    /**
     * 最多能有多少个点位于同一直线上
     *
     * @param points Point类一维数组
     * @return int整型
     */
    public int maxPoints(Point[] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        // write code here
        // 求出斜率，判断有多少点在直线上，之后就求出数量与最大值比较
        int maxPoints = 1;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int pointsLen = 2;
                if (points[i].x == points[j].x) {
                    // 竖直直线
                    for (int k = 0; k < points.length; k++) {
                        if (k == i || k == j) {
                            continue;
                        }
                        if (points[k].x == points[i].x) {
                            pointsLen++;
                        }
                    }
                }
                else {
                    // 正常求斜率
                    double k = getK(points[i], points[j]);
                    // 比较其他点，判断是否在直线上
                    for (int l = 0; l < points.length; l++) {
                        if (l == i || l == j) {
                            continue;
                        }
                        boolean flag = ((points[l].y - points[i].y) == k * (points[l].x - points[i].x));
                        if (flag) {
                            pointsLen++;
                        }
                    }
                }
                
                // 更新最大值
                maxPoints = maxPoints > pointsLen ? maxPoints : pointsLen;
            }
            
        }
        return maxPoints ;
    }
    
    public double getK(Point pointA, Point pointB) {
        return 1.0 * (pointA.y - pointB.y) / (pointA.x - pointB.x);
    }
    
}
