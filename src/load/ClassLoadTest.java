package load;

import java.util.ArrayList;

/**
 * @PackageName:load
 * @ClassName: ClassLoadTest
 * @Description:
 * @author:裴立志
 * @date: 2020/9/1622:56
 */

public class ClassLoadTest {
    
    // 1
    private static User user = new User();
    
    // 2
    static {
        System.err.println("static code block");
    }
    
    // 4
    {
        System.err.println("code block");
    }
    
    // 5
    private Student student = new Student();
    
    // 6
    public ClassLoadTest() {
        System.err.println("Constructor");
    }
    
    // 3
    public static void main(String[] args) {
        System.err.println("mian ==>");
        // 如果不调用构造函数的时候，不进行变量、代码块的初始化
//        new ClassLoadTest();
    }
}

class Student {
    public Student() {
        System.err.println("student initint===>");
    }
}

class User {
    public User() {
        System.err.println("user initing===>");
    }
}


