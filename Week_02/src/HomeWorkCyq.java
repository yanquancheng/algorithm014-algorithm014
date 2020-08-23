import java.util.Stack;

/**
 * 第二周作业
 * 1.写一个关于 HashMap 的小总结。
 * 写在ReadME.md中
 * 2.有效的字母异位词（亚马逊、Facebook、谷歌在半年内面试中考过）
 * 3.两数之和（近半年内，亚马逊考查此题达到 216 次、字节跳动 147 次、谷歌 104 次，Facebook、苹果、微软、腾讯也在近半年内面试常考）
 * 4.N 叉树的前序遍历（亚马逊在半年内面试中考过）
 * 5.HeapSort ：自学 https://www.geeksforgeeks.org/heap-sort/
 * 中等：
 * 1.字母异位词分组（亚马逊在半年内面试中常考）
 * 2.二叉树的中序遍历（亚马逊、字节跳动、微软在半年内面试中考过）
 * 3.二叉树的前序遍历（字节跳动、谷歌、腾讯在半年内面试中考过）
 * 4.N 叉树的层序遍历（亚马逊在半年内面试中考过）
 * 5.丑数（字节跳动在半年内面试中考过）
 * 6.前 K 个高频元素（亚马逊在半年内面试中常考）
 * 下周预习
 * 预习题目：
 * 1.爬楼梯
 * 2.括号生成
 * 3.Pow(x, n)
 * 4.子集
 * 5.N 皇后
 */
public class HomeWorkCyq {
    /**
     *2.有效的字母异位词
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        Arrays.sort(str1);
        Arrays.sort(str2);

        return Arrays.euqals(str1, str2);
    }
    /**
     * 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        if(nums.length == 0){
            return null;
        }
        //hashmap
        HashMap<Integer,Integer> mp = new HashMap<>();
        int[] res = new int[2];
        for(int i=0;i<nums.length;i++){
            if(mp.containsKey(target-nums[i])){
                // res[0]=i;
                // res[1]=mp.get(target-nums[i]);
                //return res;
                return new int[]{mp.get(target-numsp[i]),i};
            }else{
                mp.put(nums[i],i);
            }
        }
        throw new IllegalArgumentException("no");
    }

    /**
     * N 叉树的前序遍历
     * 使用一个栈来帮助我们得到前序遍历，需要保证栈顶的节点就是当前遍历到的节点。
     * 首先把根节点入栈，因为根节点是前序遍历中的第一个节点。
     * 随后每次我们从栈顶取出一个节点 u，它是我们当前遍历到的节点，并把 u 的所有子节点逆序推入栈中。
     * 例如 u 的子节点从左到右为 v1, v2, v3，那么推入栈的顺序应当为 v3, v2, v1，这样就保证了下一个遍历到的节点（即 u 的第一个子节点 v1）出现在栈顶的位置。
     *
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        if(root == null){
            return res;
        }
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            res.add(cur.val);
            for(int i=cur.children.size()-1;i>=0;i--){
                stack.push(cur.children.get(i));
            }
        }
        return res;
    }

    /**
     * 字母异位词分组
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> hp = new HashMap<>();
        for(int i = 0;i < strs.length;i++){
            char[] s_arr = strs[i].toCharArray();
            //排序
            Arrays.sort(s_arr);
            //映射
            Sting key = String.value(s_arr);
            if(hp.containsKey(key){
                hp.get(key).add(strs[i]);
            }else{
                List<String> l = new LinkedList<>();
                l.add(strs[i]);
                hp.put(key,l);
            }
        }
        return new ArrayList<List<String>>(hp.values());
//        if(strs.length == null) return new int[0];
//        List<Integer,List<String>> l = new ArrayList<>();
//        for(int i=0;i<strs.length;i++){
//            char[] s_arr = strs[i].toCharArray();
//            //排序
//            Arrays.sort(s_arr);
//
//            //找映射
//            String value = String.valueOf(s_arr);
//            if(value == s.arr[i]){
//                l.add(s_arr[i]);
//            }else if(){
//                l.push(i,s_arr[i]);
//            }
//
//
//        }
//        return  l;
    }

    /**
     * 二叉树的中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre =null;
        while(root != null){
            if(root.left != null){
                pre = root.left;
                while(pre.right != null){
                    pre= pre.right;
                }
                pre.right = root;
                TreeNode temp =root;
                root = root.left;
                temp.left = null;
            }else{
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * N叉树的中序遍历
     */

    /**
     * 丑数
     * */
    public int nthUglyNumber(int n) {

    }

    /**
     * 前 K 个高频元素
     */
    public int[] topKFrequent(int[] nums, int k) {

    }

}

