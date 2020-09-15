import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/** 栈的练习
 * @PackageName:PACKAGE_NAME
 * @ClassName: StackMain
 * @Description:
 * @author:裴立志
 * @date: 2020/9/1019:58
 */

public class StackMain {
    
    public static void main(String[] args) {
        
        StackMain stackMain = new StackMain();
//        String[] tokens = {"0", "156", "-", "-89", "+"};
//        int sum = stackMain.evalRPN(tokens);
//        System.out.println("sum = " + sum);

//        int sum=stackMain.combination(1,6);
//        System.out.println("sum = " + sum);

//        stackMain.isValid("([])");
//        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int sum = stackMain.trap(heights);
//        System.out.println("sum = " + sum);
        int[] heights = {11, 11, 13, 5, 3, 11, 14, 8, 18, 9, 6, 5, 19, 17, 8, 7, 8, 3, 0, 18};
        int area = stackMain.largestRectangleArea(heights);
        System.out.println("area = " + area);
        
    }
    
    // 计算 n 的阶乘
    public int com(int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum = sum * i;
        }
        return sum;
    }
    
    // n 大
    public int combination(int r, int n) {
        // write code here
        if (r == n) {
            return 1;
        }
        // 消去最大值的阶乘
        if (n - r >= r) {
            int temp = 1;
            for (int i = n; i >= n - r + 1; i--) {
                temp = temp * i;
            }
            return temp / com(r);
        }
        else {
            int temp = 1;
            for (int i = n; i >= r + 1; i--) {
                temp = temp * i;
            }
            return temp / com(n - r);
        }
    }
    
    public int[] arrayMerge(int[] array1, int n, int[] array2, int m) {
        // write code here
        // array1从头开始遍历
        int[] array = new int[n + m];
        int i = 0;
        int j = m - 1;
        int k = 0;
        // array2从尾部开始遍历
        while (i < n && j >= 0) {
            if (array1[i] < array2[j]) {
                array[k] = array1[i];
                k++;
                i++;
            }
            else {
                array[k] = array2[j];
                k++;
                j--;
            }
            
        }
        
        if (i < n) {
            for (int ll = i; ll < n; ll++) {
                array[k] = array1[ll];
                k++;
            }
        }
        else {
            for (int ll = j; ll >= 0; ll--) {
                array[k] = array2[ll];
                k++;
            }
        }
        
        // 放入新的数组中
        
        return array;
    }
    
    /**
     * @param tokens string字符串一维数组
     * @return int整型
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < tokens.length; i++) {
            try {
                // 只接受正常的数据（正数，负数）
                int num = Integer.parseInt(tokens[i]);
                stack.push(num);
            }
            catch (Exception e) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(get(a, b, tokens[i]));
            }
        }
        return stack.pop();
    }
    
    private int get(int a, int b, String operator) {
        switch (operator) {
            case "+":
                // 直接返回
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            // 默认状态
            default:
                return 0;
        }
    }
    
    /**
     * 最大集水坑
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public boolean isValid(String s) {
        // write code here
        // 获取每个字符，栈空入栈
        // 不空比较栈顶元素是否匹配
        // 可以使用map 存储
        Map<Character, Integer> map = new HashMap<>();
        map.put('(', 0);
        map.put(')', 0);
        map.put('[', 1);
        map.put(']', 1);
        map.put('{', 2);
        map.put('}', 2);
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            }
            else {
                if (stack.peek() != c && map.get(stack.peek()) == map.get(c)) {
                    stack.pop();
                }
                else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }
    
    public int trap(int[] height) {
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, n = height.length, res = 0;
        
        while (i < n) {
            // 栈空或者当前高度小于等于栈顶高度，就入栈，之后遇到比栈顶大的就形成了坑
            // 栈中存坐标
            if (s.isEmpty() || height[i] <= height[s.peek()]) {
                s.push(i++);
            }
            // 将栈顶作为坑
            else {
                // 将坑出栈
                int t = s.pop();
                // 只有栈中有两个元素的时候才能构成坑
                if (s.isEmpty()) continue;
                
                // 每遇到一个比栈顶高的就消去一层
                // 容量=长度（min{栈顶,当前值}）*高度（min{栈顶,当前值}-坑）
                res += (Math.min(height[i], height[s.peek()]) - height[t]) * (i - s.peek() - 1);
            }
        }
        return res;
    }
    
    /**
     * 最大连续矩形
     *
     * @param height int整型一维数组
     * @return int整型
     */
    public int largestRectangleArea(int[] height) {
        // write code here
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[i] >= stack.peek()) {
                // 如果是增序就进栈或栈空
                stack.push(height[i]);
            }
            else {
                // 表示水平的长度
                int count = 0;
                // 最大面积
                // 计算连续的值，并将连续的 出栈
                while (!stack.isEmpty() && height[i] < stack.peek()) {// 右边界已经确定
                    // 如果遇到一个比栈顶小的元素，表示连续的结束了
                    // 不断地判断加上下一个是否构成最大数
                    count++;
                    max = Math.max(max, count * stack.pop());
                }
                // 将之前出栈的入栈，最大值可能存在多个连续的较小值中
                while (count>0) {
                    stack.push(height[i]);
                    count--;
                }
                
                // 将这次循环的值入栈
                stack.push(height[i]);
                
            }
        }
        // 栈内不空，含有最后的升序序列
        int count = 1;
        while (!stack.isEmpty()) {
            max = Math.max(max, count * stack.pop());
            count++;
        }
        return max;
    }
}

