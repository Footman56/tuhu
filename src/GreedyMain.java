/**
 * @PackageName:PACKAGE_NAME
 * @ClassName: GreedyMain
 * @Description:
 * @author:裴立志
 * @date: 2020/9/1515:36
 */

public class GreedyMain {
    
    public static void main(String[] args) {
        GreedyMain main = new GreedyMain();
        int[] gas = {2, 3, 1};
        int[] cost = {3, 1, 2};
        
        System.out.println("main = " + main.canCompleteCircuit(gas, cost));
    }
    
    /**
     * @param gas  int整型一维数组
     * @param cost int整型一维数组
     * @return int整型
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
//         如果到某个加油站剩余油量不足0，就从下一个加油站重新开始。
        int size = gas.length;
        int sum = 0, current = 0, idx = -1;
        for (int i = 0; i < size; ++i) {
            sum += gas[i] - cost[i];
            current += gas[i] - cost[i];
            if (current < 0) {
                // 更新当前的值
                current = 0;
                idx = i;
            }
        }
        return sum >= 0 ? idx + 1 : -1;
    }
        // write code here
//        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
//            return -1;
//        }
//
//        int len = gas.length;
//        int gasSum = 0;
//        // 先找汽油量大于消耗量的位置
//        for (
//                int i = 0;
//                i < len; i++) {
//            if (gas[i] >= cost[i]) {
//                gasSum = gas[i] - cost[i];
//                int j = i;
//                int next = (i + 1) % len;
//                int pre = (i - 1 + len) % len;
//                //  之后向消耗小的方向移活动
//                if (cost[next] < cost[pre]) {
//                    // 向前循环一周
//                    for (j = next; j != i; j = (j + 1) % len) {
//                        if (gasSum + gas[j] >= cost[j]) {
//                            gasSum = gasSum + gas[j] - cost[j];
//                        }
//                        else {
//                            break;
//                        }
//                    }
//                }
//                else {
//                    // 向后循环一周
//                    for (j = pre; j != i; j = (j - 1 + len) % len) {
//                        if (gasSum + gas[j] >= cost[j]) {
//                            gasSum = gasSum + gas[j] - cost[j];
//                        }
//                        else {
//                            break;
//                        }
//                    }
//                }
//
//                if (j == i) {
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }
    
}
