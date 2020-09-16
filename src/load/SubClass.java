package load;

/**
 * @PackageName:load
 * @ClassName: SubClass
 * @Description:
 * @author:裴立志
 * @date: 2020/9/1623:04
 */

public class SubClass extends Parent {
    
    /* 静态变量 */
    public static String s_StaticField = "子类--静态变量";
    /* 变量 */
    public String s_Field = "子类--变量";
    
    /* 静态初始化块 */
    static {
        System.out.println(s_StaticField);
        System.out.println("子类--静态初始化块");
    }
    
    /* 初始化块 */ {
        System.out.println(s_Field);
        System.out.println("子类--初始化块");
    }
    
    /* 构造器 */
    public SubClass() {
        System.out.println("子类--构造器");
        System.out.println("i=" + i + ",j=" + j);
    }
    
    /* 程序入口 */
    public static void main(String[] args) {
        System.out.println("子类main方法");
        // 如果不调用构造函数的时候，不进行变量、代码块的初始化
//        new SubClass();
    }
}
