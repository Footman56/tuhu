import com.sun.org.glassfish.gmbal.Description;

/*** @PackageName:PACKAGE_NAME
 * 链表新建节点，开销不大
 *@ClassName: LinkedMain
 *@Description:
 *@author:裴立志
 * @date: 2020/9/139:38
 **/

class ListNode {
    int val;
    ListNode next;
    
    ListNode(int x) {
        val = x;
        next = null;
    }
    
    public ListNode getNext() {
        return next;
    }
    
    public void setNext(ListNode next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}

public class LinkedMain {
    
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        
        head.setNext(listNode1);
        listNode1.setNext(listNode5);
        listNode5.setNext(listNode2);
        listNode2.setNext(listNode4);
        listNode4.setNext(listNode3);
        
        LinkedMain linkedMain = new LinkedMain();
        
        // 输出
        linkedMain.displayList(head);
        ListNode listNode = linkedMain.insertionSortList(head);
        System.out.println("------------------------");
        linkedMain.displayList(listNode);
        
    }
    
    /***
     * 先找中间节点，之后将后面链表反序（借助栈进行逆序），之后将两链表合并
     * 快慢指针找到中间节点
     *
     *
     * ListNode slow=head;
     *         ListNode fast=head;
     *         while(fast.next!=null&&fast.next.next!=null){
     *             fast=fast.next.next;
     *             slow=slow.next;
     *         }
     *         slow 指向中间节点
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode p = head;
        int len = 0;
        // 先求长度
        while (p != null) {
            p = p.next;
            len++;
        }
        if (len <= 2) {
            return;
        }
        // 获取中间值
        ListNode mid = head;
        for (int i = 0; i < len / 2; i++) {
            mid = mid.next;
        }
        
        // 长度一半是要更改的
        for (int i = 0; i < len / 2; i++) {
            // 获取要插入的
            int j = len / 2 - i;
            int tempJ = 1;
            ListNode pre = mid;
            ListNode insert = mid.next;
            while (tempJ < j && insert.next != null) {
                pre = insert;
                insert = insert.next;
                tempJ++;
            }
            
            pre.next = null;
            if (insert == null) {
                break;
            }
            
            // 获取插入之前的
            int k = 2 * i;
            int tempK = 0;
            ListNode before = head;
            while (k > tempK && before.next != null) {
                before = before.next;
                tempK++;
            }
            insert.next = null;
            // 插入
            insert.next = before.next;
            
            before.next = insert;
        }
        mid.next = null;
        
    }
    
    /**
     * 求环的入口
     *
     * @param head
     * @return
     */
    ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 快慢指针找到相遇点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        // 快指针遍历到null
        if (fast == null || fast.next == null) {
            return null;
        }
        // 快慢指针分别从表头、相遇点以相同的速度行进，直到再次相遇
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    
    /***
     * 判断是否有环
     * 快慢指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && fast.next == null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * O(n log n)的时间内使用常数级空间复杂度对链表进行排序
     * 归并排序
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode sortList(ListNode head) {
        // write code here
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return head;
        }
        
        // 先查找中间值
        ListNode midNode = findMidNode(head);
        // 断开
        ListNode midNextNode = midNode.next;
        midNode.next = null;
        
        // 向左、右递归
        // 合并左右部分
        return mergerTwoList(sortList(head), sortList(midNextNode));
    }
    
    /***
     *  找链表的中间值（快慢指针）
     * @param head
     * @return
     */
    private ListNode findMidNode(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    /***
     *  合并两个链表，有序合并
     * @param listNodeA
     * @param listNodeB
     * @return
     */
    private ListNode mergerTwoList(ListNode listNodeA, ListNode listNodeB) {
        if (listNodeA == null && listNodeB == null) {
            return null;
        }
        if (listNodeA == null) {
            return listNodeB;
        }
        if (listNodeB == null) {
            return listNodeA;
        }
        ListNode pA = listNodeA;
        
        ListNode pB = listNodeB;
        
        ListNode result = null;
        if (pA.val < pB.val) {
            result = pA;
            pA = pA.next;
        }
        else {
            result = pB;
            pB = pB.next;
        }
        ListNode temp = result;
        
        while (pA != null && pB != null) {
            // A 加入新队列
            if (pA.val < pB.val) {
                temp.next = pA;
                pA = pA.next;
                
                temp = temp.next;
            }
            else {
                temp.next = pB;
                pB = pB.next;
                
                temp = temp.next;
            }
        }
        
        if (pA != null) {
            temp.next = pA;
        }
        else {
            temp.next = pB;
        }
        
        return result;
    }
    
    /**
     * 对链表进行插入排序
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode insertionSortList(ListNode head) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }
        //哑节点
        ListNode dumy = new ListNode(Integer.MIN_VALUE);
        // 遍历之前节点
        ListNode cur = head;
        // 遍历新结点
        ListNode pre = dumy;
        
        while (cur != null) {
            //保存当前节点下一个节点
            ListNode temp = cur.next;
            // 每次都从没有直到数开始比较
            pre = dumy;
            //寻找当前节点正确位置的一个节点
            // 插入时需要找前一个点
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            //将当前节点加入新链表中
            cur.next = pre.next;
            pre.next = cur;
            //处理下一个节点
            cur = temp;
        }
        return dumy.next;
        
    }
    
    /***
     * 输出列表
     * @param head
     */
    private void displayList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
