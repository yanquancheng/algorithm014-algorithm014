import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        //先考虑2n格子放括号
        //在可考虑括号有效性
        ArrayList<String > res = new ArrayList<String>();
        _generator(0, 0, n, "");
        //_generator(0,n, "");
        return res;
    }

//    private void _generator(int level, int max, String s) {
    private void _generator(int left, int right, int n, String s) {
        //terminate
        if(left == n && right ==n ){
            System.out.println(s);
            return;;
        }
        //process
//        String s1 = s + "(";
//        String s2 = s + ")";

        //drill donw
//        _generator(level+1,max,s+"(");
////        _generator(level+1,max,s+")");
        if(left < n){
            _generator(left+1,right,n,s+"(");
        }
        if(left > right ){
            _generator(left,right+1,n,s+")");
        }

        //revers
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println(sol.generateParenthesis(3));
    }
}