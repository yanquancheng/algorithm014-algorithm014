import javax.swing.tree.TreeNode;
import java.util.*;

public class HomeWork {
/**
 * 1.�������������������
 * ����һ�����������ҵ����������ڵ������������ȣ��ڵ�������Լ�������
 * ˼·������ͨ���ݹ�Զ��������к���������������ڵ� pp �� qq ʱ���ء��ӵ��������ݣ����ڵ� p, qp,q �ڽڵ� rootroot �����ʱ(���ֱ������������У�)���ڵ� rootroot ��Ϊ����������ȣ������Ϸ��� rootroot ��
 *��ֹ������
 * ��Խ��Ҷ�ڵ㣬��ֱ�ӷ��� null ��
 * �� root ���� p, q����ֱ�ӷ��� root ��
 * ���ƹ�����
 * �����ݹ����ӽڵ㣬����ֵ��Ϊ leftleft ��
 * �����ݹ����ӽڵ㣬����ֵ��Ϊ rightright ��
 * ����ֵ�� ���� leftleft �� rightright ����չ��Ϊ���������
 * �� leftleft �� rightright ͬʱΪ�� ��˵�� rootroot ���� / �������ж������� p,qp,q ������ nullnull ��
 * �� leftleft �� rightright ͬʱ��Ϊ�� ��˵�� p, qp,q ������ rootroot �� ��� ���ֱ��� �� / ������������� rootroot Ϊ����������ȣ����� rootroot ��
 * �� leftleft Ϊ�� ��rightright ��Ϊ�� ��p,qp,q ������ rootroot ���������У�ֱ�ӷ��� rightright ������ɷ�Ϊ���������
 * p,qp,q ����һ���� rootroot �� ������ �У���ʱ rightright ָ�� pp������Ϊ pp ����
 * p,qp,q ���ڵ㶼�� rootroot �� ������ �У���ʱ�� rightright ָ�� ����������Ƚڵ� ��
 * �� leftleft ��Ϊ�� �� rightright Ϊ�� ������� 3. ͬ��
 *
 * /
 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        //1.terminal
        if(root == null ||root == p || root == q){
            return root;
        }
        //2.current logical
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        //3.drill down
        if(left == null && right ==null ){
            return  null;
        }
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        return root;
        //4.reverse state
    }

    /**
     * 2.��ǰ���������������й��������������û���ظ�Ԫ��
     * ǰ����� preorder = [3,9,20,15,7]
     * ������� inorder = [9,3,15,20,7]
     * ˼·��
     * ǰ��������ص��ǣ� ���ڵ� ʼ�ճ���������ĵ�һλ������������� ���ڵ� ������������м�λ�á�
     * ��������������������飬�������ǾͿ���ƴ�� ���ڵ㣬������ 1��
     * ��Ŀ����˵�������в������ظ�Ԫ�أ���ô�� 1 �Ϳ��Զ�λ������������м�λ�ã����������� 1 ��ߵĲ��־�����������1 �ұ߲��־�����������
     *ǰ������� ����������+���ڵ� �� 1,2,4,5����������� ����������+���ڵ� �� 4,2,5,1�������ߵ����鳤����һ���ġ�
     * ���ǿ��Ը�������������м�λ�� 1����ȷ��ǰ����������Ҳ��֣�����ǰ�������һ���Ǹ��ڵ㣬
     * ��������߲����ǣ�[1:mid_index]���Ұ벿���� [mid_index+1:]
     * ����� mid_index ������������м��±�λ�á�
     * �ݹ麯��ʵ�����£�
     * ��ֹ����:ǰ�����������Ϊ��
     * ����ǰ�������һ��Ԫ�أ�ƴ�����ڵ㣬�ٽ�ǰ���������������ֳ����룬�ݹ�Ĵ���ǰ��������ߺ�����������ߣ��ݹ�Ĵ���ǰ�������ұߺ����������ұߡ�
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //1.terminal
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }
        //����ǰ�������һ��Ԫ��ȷ�����ڵ�
        TreeNode root = new TreeNode(preorder[0]);
        //2.current logical
        //ѭ�����������ҵ���ǰ����ȵ�Ԫ�أ������з�Ϊ��������
        for(int i = 0;i<preorder.length;++i){
            if(preorder[0] == inorder[i]){
                //��ǰ������ֳ��������룬�ٽ���������ֳ���������
                int[] pre_left = Arrays.copyOfRange(preorder,1,i+1);
                int[] pre_right = Arrays.copyOfRange(preorder,i+1,preorder.length);
                int[] in_left = Arrays.copyOfRange(inorder,0,i);
                int[] in_right = Arrays.copyOfRange(inorder,i+1,inorder.length);
                //3.drill down
                //�ֱ����������������
                root.left = buildTree(pre_left,in_left);
                root.right = buildTree(pre_right,in_right);
                break;
            }
        }

        return root;

        //4.reverse state
    }
    /**
     * 3.��ϣ������������� n �� k������ 1 ... n �����п��ܵ� k ��������ϡ�
     * ˼·������
     * ͨ���������п��ܳ�Ա��Ѱ��ȫ�����н���㷨������ѡ ���� ���н� (�������ٲ��� ���һ�� ��)�����ݷ�����ǰһ������һЩ�޸��������ú�ѡ��������֮�� ���� ���ٴγ��ԡ�
     * ����һ�����ݷ�������������һ����ӵ�����е��������е������Ϊ������ backtrack(first, curr)
     * ��������- ��ӵ�����С������� first t�� n������������������ i ��ӵ�������� curr�С�
     * �������������Ӹ������� ��backtrack(i + 1, curr).
     * �� i �� curr���Ƴ���ʵ�ֻ��ݡ�
     */
    List<List<Integer>> output = new LinkedList();
    int n,k;

    public List<List<Integer>> combine(int n, int k) {
        // List<List<Integer>> res = new ArrayList<>();
        // dfs(n,k,res,0,new ArrayList<Integer>());
        // return res;
        this.n = n;
        this.k = k;
        backtrack(1,new LinkedList<Integer>());
        return output;
    }
    public void backtrack(int first,LinkedList<Integer> curr){
        //1.��ֹ����
        if(curr.size() == k){
            output.add(new LinkedList(curr));
        }
        for(int i=first;i<=n;++i){
            //2.current logical
            curr.add(i);
            //3.drill down
            backtrack(i+1,curr);
            //4.revsers state
            curr.removeLast();
        }
    }
    // public void dfs(int n,int k,List<List<Integer>> res,int index,List<Integer> temp){
    //     //1.terminal
    //     if(index == n){
    //         res.add(temp);
    //         return;
    //     }

    //         //2.current logical

    //         //3.drill down
    //         //��ѡ������
    //         dfs(n,k,res,index+1,temp);
    //         //ѡ������

    //         for(int i=0;i<k;i++){
    //             temp.add(index);
    //             ++i;
    //         }
    //         dfs(n,k,res,index+1,temp);

    //     //4.reverse state
    //     temp.remove(temp.size()-1);
    // }
    /**
     * 4.ȫ���У�����һ�� û���ظ� ���ֵ����У����������п��ܵ�ȫ���С�
     */
    //���ݽ��,
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] visit = new int[nums.length];
        backtrack(res,nums,new ArrayList<Integer>(),visit);
        return res;
        // if(nums.length == 0){
        //     return res;
        // }
        // dfs(nums,res,0, new LinkedList<Integer>());
        // return res;

    }
    public void backtrack(List<List<Integer>> res,int[] nums,ArrayList<Integer> temp,int[] visit){
        //visit��Ϊ������ӷ�ֹ�����г����ظ�������
        if(temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(visit[i] == 1) continue;
            visit[i] = 1;
            temp.add(nums[i]);
            backtrack(res,nums,temp,visit);

            visit[i] = 0;
            temp.remove(temp.size()-1);

        }
    }
    // public void dfs(int nums,List<List<Integer>> res,int index,List<Integer> temp){
    //     //1.terminal
    //     if(index == nums.length){
    //         res.add(temp);
    //         return;
    //     }
    //     //2.current logical

    //     //3.drill down
    //     //��ѡ������
    //     //dfs(nums,res,index+1,temp);
    //     //ѡ������
    //     temp.add(nums[index]);
    //     dfs(nums,res,index+1,temp);
    //     //4.reverse state
    //     te
    //}
    /**
     * 5.ȫ����2
     * ����һ���ɰ����ظ����ֵ����У��������в��ظ���ȫ���С�
     * ��֧��˼·��Ҫ�ڿ�����ʱû����
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if(len == 0){
            return res;
        }

        Arrays.sort(nums);

        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums,res,len,0,path,used);
        return res;
    }
    public void dfs(int[] nums,List<List<Integer>> res,int len,int depth,Deque<Integer> path,boolean[] used){
        if(depth == len){
            res.add(new ArrayList<>(path));
            return ;
        }
        for(int i=0;i<len;i++){
            if(used[i]){
                continue;
            }
            //��֧����
            if(i>0 && nums[i] == nums[i-1] && used[i-1] == false){
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums,res,len,depth+1,path,used);

            used[i] = false;
            path.removeLast();

        }
    }
}
