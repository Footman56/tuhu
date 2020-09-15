import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;

/**
 * @PackageName:PACKAGE_NAME
 * @ClassName: TestMain
 * @Description:
 * @author:裴立志
 * @date: 2020/9/149:01
 */

public class TestMain {
    
    
    @Test
    public void testDouble(){
        double x1=0.333;
        double x2=0.5;
        // 浮点数直接运算容易失真
//        double result=x1-x2;
//        // 比较浮点数，需要指定误差范围，
//        assertEquals(result,-0.17,0.001) ;
        BigDecimal bg1=new BigDecimal(Double.toString(x1));
        BigDecimal bg2=new BigDecimal(Double.toString(x2));
        BigDecimal result=bg1.subtract(bg2);
        
        assertEquals(-0.167,result.doubleValue(),0.0001);
        
        bg1.hashCode();
    }
    
    
    @Test
    public void testXOR(){
        int a=159;
        int b=159;
        
        // 如果两个整型相同，返回0
        int i = a ^ b;
        System.out.println("i = " + i);
        
        float c=6.5f;
        float d=6.5f;
    
//        int i1 = c ^ d; 报错
        
        char e='a';
        char f='a';
        // char 类型也可以使用异或，同int
        int i1 = e ^ f;
        System.out.println("i1 = " + i1);
        
        String g="abc";
        String h="abc";
       
        
        
        
        
    
    }
}
