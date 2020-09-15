import java.util.*;

/**
 * @PackageName:PACKAGE_NAME
 * @ClassName: ArrayMain
 * @Description:
 * @author:裴立志
 * @date: 2020/9/1222:38
 */

public class ArrayMain {
    
    public static void main(String[] args) {
        ArrayMain arrayMain = new ArrayMain();
//        int[] array = {100, 4, 200, 1, 3, 2};
////        int i = arrayMain.longestConsecutive(array);
////        System.out.println("i = " + i);

//        HashSet<String> dict = new HashSet<>();
//        dict.add("hot");
//        dict.add("dot");
//        dict.add("dog");
//        dict.add("lot");
//        dict.add("log");
//        int i = arrayMain.ladderLength("hit", "cog", dict);
//        System.out.println("i = " + arrayMain.isPalindrome("race a car"));
        
        int[] array = {5, 3, 1};
        System.out.println("arrayMain = " + arrayMain.candy(array));
    }
    
    /**
     * 使用set存储，之后删除，之后向上或向下删除，如果删除成功就说明存在连续
     * 删除之后就没有说明是连续的
     *
     * @param num int整型一维数组
     * @return int整型
     */
    public int longestConsecutive(int[] num) {
        // write code here
        Set<Integer> set = new HashSet<>();
        
        // 存入set
        for (int n : num) {
            set.add(n);
        }
        int max = 0;
        // 遍历的数组，删除的是set，不影响
        for (int i = 0; i < num.length; i++) {
            int len = 0;
            // 删除
            int delNum = num[i];
            if (set.remove(delNum)) {
                len++;
                int delNumBefore = num[i] - 1;
                int delNumAfter = num[i] + 1;
                // 向前删除
                while (set.remove(delNumBefore)) {
                    delNumBefore--;
                    len++;
                }
                // 向后删除
                while (set.remove(delNumAfter)) {
                    delNumAfter++;
                    len++;
                }
                
                if (max < len) {
                    max = len;
                }
            }
            
        }
        return max;
    }
    
    /**
     * 最短匹配，将初始串根据字典转换程目标串
     * 广度优先
     *
     * @param start
     * @param end
     * @param dict
     * @return
     */
    public int ladderLength(String start, String end, HashSet<String> dict) {
        // 将初始串转化1步的字典，进入队列，只有全部进入队列的时候，才+1
        // 在出队列再找
        // 直到找到
        // 使用linkedList,poll（删除队列头，空返回null）,offer（添加到尾部，返回boolean值），peek(获取队列头，空返回null)方法
        if (end.equals(start)) {
            return 0;
        }
        // 队列
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int count = 1;
        while (!queue.isEmpty()) {
            // size 保存每一层的个数，只有在检查完一层之后才步长+1
            int size = queue.size();
            while (size > 0) {
                String temp = queue.poll();
                size--;
                // 只有一个不同,就结束
                if (isDiffOne(temp, end)) {
                    // 正常退出
                    return count + 1;
                }
                
                // 找到合适的字典，加入队列中
                for (Iterator<String> it = dict.iterator(); it.hasNext(); ) {
                    String string = it.next();
                    
                    // 只有一个位置不同
                    if (isDiffOne(temp, string)) {
                        queue.offer(string);
                        // 只转换一次
                        it.remove();
                    }
                }
            }
            // 找完一层；
            count++;
        }
        
        return 0;
    }
    
    // 判断两字符串是否只差一个
    public boolean isDiffOne(String w1, String w2) {
        int count = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }
    
    /**
     * 判断一个字符串是否是回文串，仅考虑字母和数字
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public boolean isPalindrome(String s) {
        // write code here
        if ("".equals(s)) {
            return true;
        }
        // 忽略大小写,toUpperCase() 要将返回值给本身
        
        s = s.toUpperCase();
        
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            // 在左侧找第一个字符
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            char chL = s.charAt(i);
            while (j > i && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            char chR = s.charAt(j);
            
            if (chL != chR) {
                return false;
            }
            else {
                i++;
                j--;
            }
        }
        return true;
    }
    
    /**
     * @param ratings int整型一维数组
     * @return int整型
     */
    public int candy(int[] ratings) {
        // write code here
        if (ratings == null) {
            return 0;
        }
        
        int len = ratings.length;
        int[] v = new int[len];
        for (int i = 0; i < len; i++) {
            v[i] = 1;
        }
        //从左向右扫描，保证一个方向上分数更大的糖果更多
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                v[i] = v[i - 1] + 1;
            }
        }
        //从右向左扫描，保证另一个方向上分数更大的糖果更多
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && v[i] <= v[i + 1]) {
                v[i] = v[i + 1] + 1;
            }
        }
        
        // 求和
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += v[i];
        }
        return sum;
    }
}
