import javax.swing.tree.TreeNode;
import java.util.*;

public class HomeWork {
/**
 * 1.二叉树的最近公共祖先
 * 给定一个二叉树，找到给定两个节点的最近公共祖先，节点可以是自己的祖先
 * 思路：考虑通过递归对二叉树进行后序遍历，当遇到节点 pp 或 qq 时返回。从底至顶回溯，当节点 p, qp,q 在节点 rootroot 的异侧时(即分别在左、右子树中）)，节点 rootroot 即为最近公共祖先，则向上返回 rootroot 。
 *终止条件：
 * 当越过叶节点，则直接返回 null ；
 * 当 root 等于 p, q，则直接返回 root ；
 * 递推工作：
 * 开启递归左子节点，返回值记为 leftleft ；
 * 开启递归右子节点，返回值记为 rightright ；
 * 返回值： 根据 leftleft 和 rightright ，可展开为四种情况；
 * 当 leftleft 和 rightright 同时为空 ：说明 rootroot 的左 / 右子树中都不包含 p,qp,q ，返回 nullnull ；
 * 当 leftleft 和 rightright 同时不为空 ：说明 p, qp,q 分列在 rootroot 的 异侧 （分别在 左 / 右子树），因此 rootroot 为最近公共祖先，返回 rootroot ；
 * 当 leftleft 为空 ，rightright 不为空 ：p,qp,q 都不在 rootroot 的左子树中，直接返回 rightright 。具体可分为两种情况：
 * p,qp,q 其中一个在 rootroot 的 右子树 中，此时 rightright 指向 pp（假设为 pp ）；
 * p,qp,q 两节点都在 rootroot 的 右子树 中，此时的 rightright 指向 最近公共祖先节点 ；
 * 当 leftleft 不为空 ， rightright 为空 ：与情况 3. 同理；
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
     * 2.从前序遍历和中序遍历中构造二叉树，假设没有重复元素
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 思路：
     * 前序遍历的特点是， 根节点 始终出现在数组的第一位，而中序遍历中 根节点 出现在数组的中间位置。
     * 根据上面给出的两个数组，首先我们就可以拼出 根节点，它就是 1。
     * 题目上已说明数组中不存在重复元素，那么由 1 就可以定位到中序数组的中间位置，中序数组中 1 左边的部分就是左子树，1 右边部分就是右子树。
     *前序数组的 左子树部分+根节点 是 1,2,4,5，中序数组的 左子树部分+根节点 是 4,2,5,1。这两者的数组长度是一样的。
     * 我们可以根据中序数组的中间位置 1，来确定前序数组的左右部分，由于前序数组第一个是根节点，
     * 所以其左边部分是：[1:mid_index]，右半部分是 [mid_index+1:]
     * 这里的 mid_index 是中序数组的中间下标位置。
     * 递归函数实现如下：
     * 终止条件:前序和中序数组为空
     * 根据前序数组第一个元素，拼出根节点，再将前序数组和中序数组分成两半，递归的处理前序数组左边和中序数组左边，递归的处理前序数组右边和中序数组右边。
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
        //根据前序遍历第一个元素确立跟节点
        TreeNode root = new TreeNode(preorder[0]);
        //2.current logical
        //循环遍历中序找到和前序相等的元素，把树切分为左、右子树
        for(int i = 0;i<preorder.length;++i){
            if(preorder[0] == inorder[i]){
                //讲前序数组分成左右两半，再将中序数组分成左右两半
                int[] pre_left = Arrays.copyOfRange(preorder,1,i+1);
                int[] pre_right = Arrays.copyOfRange(preorder,i+1,preorder.length);
                int[] in_left = Arrays.copyOfRange(inorder,0,i);
                int[] in_right = Arrays.copyOfRange(inorder,i+1,inorder.length);
                //3.drill down
                //分别处理左数组和右数组
                root.left = buildTree(pre_left,in_left);
                root.right = buildTree(pre_right,in_right);
                break;
            }
        }

        return root;

        //4.reverse state
    }
    /**
     * 3.组合，给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * 思路：回溯
     * 通过遍历所有可能成员来寻找全部可行解的算法。若候选 不是 可行解 (或者至少不是 最后一个 解)，回溯法会在前一步进行一些修改以舍弃该候选，换而言之， 回溯 并再次尝试。
     * 这是一个回溯法函数，它将第一个添加到组合中的数和现有的组合作为参数。 backtrack(first, curr)
     * 若组合完成- 添加到输出中。遍历从 first t到 n的所有整数。将整数 i 添加到现有组合 curr中。
     * 继续向组合中添加更多整数 。backtrack(i + 1, curr).
     * 将 i 从 curr中移除，实现回溯。
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
        //1.终止条件
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
    //         //不选择填入
    //         dfs(n,k,res,index+1,temp);
    //         //选择填入

    //         for(int i=0;i<k;i++){
    //             temp.add(index);
    //             ++i;
    //         }
    //         dfs(n,k,res,index+1,temp);

    //     //4.reverse state
    //     temp.remove(temp.size()-1);
    // }
    /**
     * 4.全排列，给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     */
    //回溯解决,
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
        //visit作为标记数子防止排列中出现重复的数字
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
    //     //不选择填入
    //     //dfs(nums,res,index+1,temp);
    //     //选择填入
    //     temp.add(nums[index]);
    //     dfs(nums,res,index+1,temp);
    //     //4.reverse state
    //     te
    //}
    /**
     * 5.全排列2
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * 剪支的思路需要在看，暂时没看懂
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
            //剪支条件
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
