import java.util.Stack;

/**
 * �ڶ�����ҵ
 * 1.дһ������ HashMap ��С�ܽᡣ
 * д��ReadME.md��
 * 2.��Ч����ĸ��λ�ʣ�����ѷ��Facebook���ȸ��ڰ����������п�����
 * 3.����֮�ͣ��������ڣ�����ѷ�������ﵽ 216 �Ρ��ֽ����� 147 �Ρ��ȸ� 104 �Σ�Facebook��ƻ����΢����ѶҲ�ڽ����������Գ�����
 * 4.N ������ǰ�����������ѷ�ڰ����������п�����
 * 5.HeapSort ����ѧ https://www.geeksforgeeks.org/heap-sort/
 * �еȣ�
 * 1.��ĸ��λ�ʷ��飨����ѷ�ڰ����������г�����
 * 2.���������������������ѷ���ֽ�������΢���ڰ����������п�����
 * 3.��������ǰ��������ֽ��������ȸ衢��Ѷ�ڰ����������п�����
 * 4.N �����Ĳ������������ѷ�ڰ����������п�����
 * 5.�������ֽ������ڰ����������п�����
 * 6.ǰ K ����ƵԪ�أ�����ѷ�ڰ����������г�����
 * ����Ԥϰ
 * Ԥϰ��Ŀ��
 * 1.��¥��
 * 2.��������
 * 3.Pow(x, n)
 * 4.�Ӽ�
 * 5.N �ʺ�
 */
public class HomeWorkCyq {
    /**
     *2.��Ч����ĸ��λ��
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
     * ����֮��
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
                return new int[]{mp.get(target-nums[i]),i};
            }else{
                mp.put(nums[i],i);
            }
        }
        throw new IllegalArgumentException("no");
    }

    /**
     * N ������ǰ�����
     * ʹ��һ��ջ���������ǵõ�ǰ���������Ҫ��֤ջ���Ľڵ���ǵ�ǰ�������Ľڵ㡣
     * ���ȰѸ��ڵ���ջ����Ϊ���ڵ���ǰ������еĵ�һ���ڵ㡣
     * ���ÿ�����Ǵ�ջ��ȡ��һ���ڵ� u���������ǵ�ǰ�������Ľڵ㣬���� u �������ӽڵ���������ջ�С�
     * ���� u ���ӽڵ������Ϊ v1, v2, v3����ô����ջ��˳��Ӧ��Ϊ v3, v2, v1�������ͱ�֤����һ���������Ľڵ㣨�� u �ĵ�һ���ӽڵ� v1��������ջ����λ�á�
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
     * ��ĸ��λ�ʷ���
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> hp = new HashMap<>();
        for(int i = 0;i < strs.length;i++){
            char[] s_arr = strs[i].toCharArray();
            //����
            Arrays.sort(s_arr);
            //ӳ��
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
//            //����
//            Arrays.sort(s_arr);
//
//            //��ӳ��
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
     * ���������������
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
     * N�������������
     */

    /**
     * ����
     * */
    public int nthUglyNumber(int n) {

    }

    /**
     * ǰ K ����ƵԪ��
     */
    public int[] topKFrequent(int[] nums, int k) {

    }

}

