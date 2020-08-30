import java.util.Stack;

public class HomeWorkCyq {
    /*
    * 1.删除排序数组中的重复项
    * */
    public int deleteRepeat(int[] nums){

        if(nums.length == 0 ){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        int i = 0;
        for(int j=1 ; j<nums.length; j++){
            if(nums[i] == nums[j]){
                continue;;
            }else {
                i++;
                nums[i]=nums[j];
            }
        }
        return i+1;
//        for(int i = 0;i<nums.length; i++){
//            for(int j = 0;j<nums.length;j++){
//                if(nums[j] == nums[i]){
//                    j=i;
//                }
//            }
//        }
    }
    /*
    * 2.旋转数组
    * */
    public void rotate(int[] nums, int k){
        if(nums.length == 0 || k == 0){
            return;
        }
        int temp=0,pre=0;
        for(int i =0 ;i<k;i++){
            pre = nums[nums.length-1];
            for( int j =0 ;j<nums.length;j++){
                temp = nums[j];
                nums[j] = pre;
                pre =temp;

            }
        }
    }
    /*
    * 3.合并两个有序链表
    * */
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //遍历两个连表，比较两个有有序连表中元素大小，新建一个连表
        if(l1 == null){
            return l2;
        }else if( l2 == null){
            return l1;
        }else if(l1.val<l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }

    }
    /*
    * 合并两个有序数组
    * */

    public void merge(int[] nums1, int m, int[] nums2, int n){
        int len1 = m-1;
        int len2 = n-1;
        int len = m+n-1;
        while (len1 >= 0 && len2 >= 0){
            nums1[len--] = nums1[len1]>nums2[len2]?nums1[len1--]:nums2[len2--];
        }
        System.arraycopy(nums2,0,nums1,0,len2+1);
    }

    /*
    * 5.两数之和
    * */
    public int[] twoSum(int[] nums, int target){
        if(nums.length == 0){
            return null;
        }
        int[] res = new int[];
        for(int i = 0;i<nums.length;i++){
            for( int j=i;j<nums.length;j++){
                if(nums[j] == target - nums[i]){
                    res[0]=i;
                    res[1]=j;
                    return res;
                }
            }
        }
    }

    /*
    *6. 移动零
    * */
    public void moveZeroes(int[] nums){
        if(nums.length ==0){
            return;
        }
        int j = 0;
        for(int i = 0;i<nums.length;i++){
            if(0 != nums[i]){
                nums[j] = nums[i];
                if(i!=j){
                    nums[i]=0;
                }
                j++;
            }
        }
//        for(int i = 0; i<nums.length;i++){
//            if(0 != nums[i]){
//                nums[j]=nums[i];
//                j++;
//            }
//        }
//        for(int i=j;i<nums.length;i++){
//            nums[i]=0;
//        }
    }

    /*
    * 7.加一
    * */
    public int[] plusOne(int[] digits){
        //输入的数组是9存在进位
        for(int i = digits.length -1; i >= 0; i--){
            digits[i]++;
            digits[i] %= 10;
            if(digits[i] != 0){
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
//        if(digits == [0]){
//            return [1];
//        }
//
//        for(int i = 0 ; i<digits.length;i++){
//            if(digits[digits.length-1] != 9){
//                digits[digits.length-1]++;
//            }else{
//
//            }
//        }
    }


    /*
    * 接雨水
    * */
    public int trap(int[] A){
        if(A == null || A.length == 0){
            return 0;
        }
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while(i<A.length){
            if(s.isEmpty() || A[i]<=A[s.peek()]){
                s.push(i++);
            }else{
                int bot = s.pop();
                maxBotWater = s.isEmpty()?0:(Math.min(A[s.peek(),A[i]]-A[bot])*(i-s.peek()-1));
                maxWater += maxBotWater;
            }
        }
        return  maxWater;
    }


}
/*
 *设计循环双端队列
 * */
class MyCircularDeuqe {
    private  int[] data;
    private  int head;
    private  int tail;
    private int capacity;
    //初始化
    public  MyCircularDeuqe(int k){
        data = new int[k+1];
        capacity = k+1;
    }
    //
    public boolean insertFront(int value){
        if(isFull()){
            return false;
        }
        data[head = ((head-1+capacity)%capacity)] =value;
        return true;
    }
    //
    public boolean insertLast(int value){
        if(isFull()){
            return false;
        }
        data[tail]=value;
        tail =(tail+1)%capacity;
        return true;
    }
    public boolean deleteLast(){
        if(isEmpty()){
            return false;
        }
        head = (head+1)%capacity;
        return true;
    }
    public boolean deleteLast(){
        if(isEmpty()){
            return false;
        }
        tail = (tail-1+capacity)%capacity;
        return true;
    }
    //
    public int getRear(){
        return  isEmpty()? -1:data[(tail-1+capacity)%capacity]
    }
    public  boolean isEmpty(){
        return head == tail;
    }
    public boolean isFull(){
        return (tail+1)%capacity == head;
    }
}