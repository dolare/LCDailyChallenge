/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        ListNode newHead = head;
        ListNode oldTail = null;
        int i = 0;
        while(curr != null) {
            ListNode newCurr = curr;
            System.out.println("start: " + newCurr.val);
            boolean isFinish = false;
            int tempK = k;
            while(tempK-- > 0) {
                if (newCurr == null){
                    isFinish = true;
                    break;
                }
                newCurr = newCurr.next;
            }
            if (isFinish) break;
            List<ListNode> nodes = reverseKNodes(curr, k);
            System.out.println("out: " + curr.val);
            if (nodes != null) {
                for(ListNode node: nodes) {
                    if(node!=null) System.out.println("node: " + node.val);
                }
            }
            
            if (nodes == null) break; // less than k nodes, checked in reverseKNodes function
            if (i == 0) {
                newHead = nodes.get(1); // newHead is the first reversed nodes' tail
                i++;
            }
            curr = newCurr;
            if (curr == null) break;
            tempK = k;
            while(tempK-- > 1) {
                newCurr = newCurr.next;
                if (newCurr == null) break;
            }
            System.out.println("temp" + tempK);
            if (tempK == 0) nodes.get(0).next = newCurr; // has enough elements
            else nodes.get(0).next = curr; // no enough elements
            // System.out.println("tail: " + newCurr.val); 
        }

        return newHead;
    }
    
    public List<ListNode> reverseKNodes(ListNode node, int k) { // reverse nodes, return head and tail
        System.out.println("In: " + node.val);
        List<ListNode> list = new ArrayList();
        ListNode curr = node;
        ListNode prev = null;
        while(k-- > 0) {
            if (curr == null) return null;
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        list.add(node); // new tail(1) 3->2->1
        list.add(prev); // hew head 3
        return list;
    }
}
