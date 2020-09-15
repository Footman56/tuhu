import java.util.*;

/**
 * 队列练习
 *
 * @PackageName:PACKAGE_NAME
 * @ClassName: StringMain
 * @Description:
 * @author:裴立志
 * @date: 2020/9/1120:26
 */

public class StringMain {
    
    public static void main(String[] args) {
        
        StringMain stringMain = new StringMain();
//        String s = stringMain.simplifyPath("/../");
//
//        System.out.println("s = " + s);
//        int i = stringMain
//                .lengthOfLongestSubstring("wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco");
//        System.out.println("i = " + i);
//        String convert = stringMain.convert("PAYPALISHIRING", 3);
//        System.out.println("convert = " + convert);
        String s = stringMain.countAndSay(4);
        System.out.println("s = " + s);
    }
    
    /**
     * 输出字符串中的所有回文数字
     *
     * @param s string字符串
     * @return string字符串ArrayList<ArrayList                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <>>
     */
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> list = new ArrayList<String>();
        
        // 异常输入判断
        if (s == null || s.length() == 0) {
            return result;
        }
        
        calResult(result, list, s);
        return result;
    }
    
    /**
     * 判断一个字符串是否是回文字符串
     */
    private boolean isPalindrome(String str) {
        
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
    /**
     * 回溯
     * 将完成的下一个状态作为结束标志，存入结果集中
     *
     * @param result : 最终要的结果集 ArrayList<ArrayList<String>>
     * @param list   : 当前已经加入的集合 ArrayList<String>
     * @param str    : 当前要处理的字符串
     */
    private void calResult(ArrayList<ArrayList<String>> result
            , ArrayList<String> list
            , String str) {
        //当处理到传入的字符串长度等于0,则这个集合list满足条件，加入到结果集中
        if (str.length() == 0) {
            result.add(new ArrayList<String>(list));
        }
        int len = str.length();
        //递归调用
        //字符串由前往后，先判断str.substring(0, i)是否是回文字符串
        //如果是的话，继续调用函数calResult，把str.substring(i)字符串传入做处理
        for (int i = 1; i <= len; ++i) {
            String subStr = str.substring(0, i);
            // 只有一部分构成结果集，之后才能继续执行
            if (isPalindrome(subStr)) {
                list.add(subStr);
                
                String restSubStr = str.substring(i);
                calResult(result, list, restSubStr);
                // 去掉之前加入的回文数
                list.remove(list.size() - 1);
            }
        }
    }
    
    /**
     * 简化unix文件路径
     * 有多个匹配时，使用字符串
     *
     * @param path string字符串
     * @return string字符串
     */
    //来自https://www.cnblogs.com/grandyang/p/4347125.html
    public String simplifyPath(String path) {
        Stack<String> s = new Stack<>();
        // 根据"/"划分
        String[] p = path.split("/");
        for (String t : p) {
            if (!s.isEmpty() && t.equals("..")) {
                s.pop();
            }
            else if (!t.equals(".") && !t.equals("") && !t.equals("..")) {
                // 是目录名
                s.push(t);
            }
        }
        List<String> list = new ArrayList<>(s);
        return "/" + String.join("/", list);//jdk8新方法String.join()
    }
    
    /**
     * 找出最长 重复字串
     * @param s string字符串
     * @return int整型
     */
//    public int lengthOfLongestSubstring(String s) {
//        // write code here
//        if ("".equals(s) || s == null) {
//            return 0;
//        }
//        char[] chars = s.toCharArray();
//        int maxLen = 1;
//        // 先找到重复的字符
//        for (int i = 0; i < chars.length; i++) {
//            int j = i + 1;
//            while (j < chars.length && chars[j] != chars[i]) {
//                j++;
//            }
//            if (j >= chars.length) {
//                // 没有找到相同的字符
//                continue;
//            }else {
//                // 之后横移，匹配相同。
//                int len = 1;
//                for (; j + len < chars.length && i + len < chars.length && chars[i + len] == chars[j + len]; len++) {
//
//                }
//                if (maxLen < len) {
//                    maxLen = len;
//                }
//            }
//        }
//        return maxLen;
//    }
    
    /**
     * @param s string字符串
     * @return int整型
     */
    public int lengthOfLongestSubstring(String s) {
        // 根据键的不同来选择
        Map<Character, Integer> map = new HashMap<>();
        // 异常处理
        if (s.length() == 0 || s == null) {
            return 0;
        }
        int len = 0;
        int maxLen = -1;
        int leftBound = 0;// 左边界，只有在左边界内才算有效
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch) && map.get(ch) >= leftBound) {
                // 如果这个元素出过，就尝试丢弃含元素的部分，要记录
                if (maxLen < len) {
                    maxLen = len;
                }
                len = i - map.get(ch);
                leftBound = map.get(ch) + 1;
                map.put(ch, i);
            }
            else {
                // 如果这个元素没出现过或者在左边界之外 就加入窗口
                len++;
                map.put(ch, i);
            }
        }
        return maxLen;
    }
    
    /**
     * Z字形输出
     * 根据原始数据输出特定格式问题，可以将每一行看出一个String
     * 之后按照原始字符串的顺序，在不同的String数组中移动，根据规律移动
     *
     * @/** *  从头开始迭代
     * *  根据连续相同的个数来确定下一个数字
     * * @param n int整型
     * * @return string字符串
     */
    
    public String countAndSay(int n) {
        // write code here
        // 异常情况
        if (n < 0) {
            return "";
        }
        String start = "1";
        String result = "";
        for (int i = 0; i < n - 1; i++) {
            
            int len = 1;
            char ch = start.charAt(0);
            // 遍历上一个的值
            for (int j = 1; j < start.length(); j++) {
                if (start.charAt(j) == ch) {
                    len++;
                }
                else {
                    // 连续的结束，添加到结果中
                    result += String.valueOf(len) + ch;
                    
                    ch = start.charAt(j);
                    len = 1;
                }
            }
            result += String.valueOf(len) + ch;
            
            start = result;
            result = "";
        }
        
        return start;
    }
    
    /***param s
     string字符串
     *
     @param
     nRows int整型
     *@return string字符串
     **/
    
    public String convert(String s, int nRows) {
        if (s == null || s.length() == 0 || nRows <= 1) {
            return s;
        }
        
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++)
            sb[i] = new StringBuffer();
        
        int len = s.length();
        int i = 0;
        while (i < len) {
            // 向下移动
            for (int j = 0; j < nRows && i < len; j++)
                sb[j].append(s.charAt(i++));
            // 向上移动
            for (int j = nRows - 2; j > 0 && i < len; j--)
                sb[j].append(s.charAt(i++));
        }
        
        for (int j = 1; j < nRows; j++)
            sb[0].append(sb[j]);
        
        return sb[0].toString();
    }
    
    
    
    
    
    
}




