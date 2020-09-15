/**
 * @PackageName:PACKAGE_NAME
 * @ClassName: MathMain
 * @Description:
 * @author:裴立志
 * @date: 2020/9/1514:23
 */

public class MathMain {
    
    public static void main(String[] args) {
        
        MathMain main = new MathMain();
        int[] y = {15, 0, 15};
        int i = main.singleNumber(y);
        System.out.println("i = " + i);
    
    }
    
    /**
     * 逻辑异或能够找出两个的不同
     * 两个相同的数异或之后是0
     * 0与一个数异或之后还是这个数
     *
     * @param A int整型一维数组
     * @return int整型
     */
    public int singleNumber(int[] A) {
        if (A==null){
            return -1;
        }
        // write code here
        int temp = A[0];
        for (int i = 1; i < A.length; i++) {
            temp = temp ^ A[i];
        }
        
        return temp;
        
    }
}



