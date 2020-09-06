学习笔记
1.BFS
pubulic class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x){
        val =x;
    }
}
public List<List<Integer>> levelOrder(TreeNode root){
    List<List<Integer>> allresults = new ArrayList<>();
    if(root == null){
        return allResults;
     }
     Queue<TreeNode> nodes = new LinkedList<>();
     nodes.add(root);
     while(!nodes.isEmpty()){
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        for(int i =0;i<size;i++){
            TreeNode node = nodes.poll();
            results.add(node.val);
            if(node.left != null){
                   nodes.add(node.left);
             }
             if(node.right != null){
                nodes.add(node.right);
             }
          }
          allResults.add(result);
        }
        return allResults;
 }

二分查找模版
//假设数组是有序递增
public int binarySearch(int[] array,int target){
    int left=0,right=array.length-1;
    while(left<=right){
        int mid = (right-left)/2+left;//注意这种方法处理了(y-x)/2越界问题
        if(array[mid] == target){
            return mid;
        }else if(array[mid]>target){
            right = mid-1;
        }else{
            left = mid+1;
        }
    }
    return right;
}

