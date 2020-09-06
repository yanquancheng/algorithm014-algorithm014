import java.util.Arrays;

public class homeWork {
    /**
     * 平方根
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        //二分查找，y=x^2,单调递增且有上下界(0-x)
        if(x==0||x==1){
            return x;
        }
        long left=1,right=x,mid=1;
        while(left<=right){
            mid = left+(right-left)/2;
            if(mid*mid == x){
                return (int)mid;
            }else if(mid*mid>x){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return (int)right;
    }
    /**
     * 牛顿迭代法
     * 根据斜率公式推导res=(cur+a/cur)/2
     */
    public int mySqrt(int x){
        long cur=x;
        while(cur*cur>x){
            cur=(x+x/cur)/2;
        }
        return (int)cur;
    }
    /**
     * 1. 柠檬水找零
     * https://leetcode-cn.com/problems/lemonade-change/description/
     */
    public boolean lemonadeChange(int[] bills) {
        //栈，值是5就直接放入，非5，5的2倍则pop之前的5，在放入，4倍则pop5的3倍在放入
        //假设面额只有5，10，20。最多10000人(bills.length<10000)
        int five =0,ten=0;
        //1。特殊情况处理
        // if(bills.length == 0){
        //     return true;
        // }
        // if(bills.length == 1 && bills[0] ==5){
        //     return true;
        // }else if(bills.length == 1 && (bills[0] == 10 || bills[0] == 20)){
        //     return false;
        // }
        // //2.贪心算法判断循环内数据，20优先找10和5，其次找全是5的
        // for(int i=1;i<bills.length;i++){
        //     if(bills[i] == 5){
        //         five++;
        //     }
        //     if(bills[i] == 10){
        //         if(five>0){
        //             five--;
        //             ten++;
        //         }else{
        //             return false;
        //         }
        //     }
        //     if(bills[i] == 20){
        //         if(five>0 && ten>0){
        //             five--;
        //             ten--;
        //         }else if(five>2){
        //             five -= 3;
        //         }else{
        //             return false;
        //         }
        //     }
        // }
        // return true;

        //提交失败后抄写别人的
        //1.特殊处理
        if(bills.length < 1){
            return true;
        }
        if(bills[0] != 5){
            return false;
        }
        for(int i=0;i<bills.length;i++){
            if(bills[i] ==5){
                five++;
            }else if(bills[i] == 10){
                if(five<1){
                    return false;
                }
                five--;
                ten++;
            }else{
                if(five>0 && ten >0){
                    five--;
                    ten--;
                }else if(five>2){
                    five-=3;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 买卖股票的最佳时机。
     * 思路：贪心算法，获取局部最优解，然后只对局部是正数的求和
     */
    public int maxProfit(int[] prices) {
        //贪心算法，局部最优，局部为正则加入，否则不加
        int res=0;
        for(int i=0;i<prices.length-1;i++){
            if(prices[i+1]-prices[i]<=0){
                continue;
            }
            res+=prices[i+1]-prices[i];
        }
        return res;
    }
    /**
     * 分发饼干
     * 思路：贪心算法，优先满足胃口小的孩子
     */
    public int findContentChildren(int[] children, int[] cookies) {
        //1.暴力解决，匹配数组1在数据2中是否有对应项
        //2.贪心算法，局部满足需求最小的孩子
        //特殊情况处理。不需要，包含在循环中了
        // if(cookies.length ==0 || children.length == 0){
        //     return 0;
        // }
        //排序
        Arrays.sort(cookies);
        Arrays.sort(children);
        //定义最后可以满足的孩子数量
        int greedchild=0;
        //int child=0,cooke=0;
        //循环解决需求度最小的孩子
        // for(child=0,cooke=0;child<=children.length && cooke<=cookies.length;child++,cooke++){
        for(int cooke=0;greedchild<children.length && cooke<cookies.length;cooke++){
            if(children[greedchild]<= cookies[cooke]){
                greedchild++;
            }
        }
        // while(cooke<cookies.length && greedchild<children.length){
        //     if(cookies[cooke]>=children[greedchild]){
        //         greedchild++;
        //     }
        //     cooke++;
        // }
        return greedchild;
    }
    /**
     *模拟行走机器人
     * 思路：
     * 这个题的题解都没看懂
     */
    public int robotSim(int[] commands, int[][] obstacles) {

    }

    /**
     * 使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方
     * 思路：二分查找，比较中间值和左右相邻元素大小，如果排序的，则比较中间元素和首尾元素大小，判断无序的位置在左和右那一侧
     */
    public int findInorder(int[] nums){
        //特殊处理
        if(nums.length <= 2){
            return -1;
        }
        int left=0,right=nums.length-1,mid;
        while(left<right){
            mid = left+(right-left)/2;
            //当中间值大于右侧且小于左侧，就是乱序的位置
            if(nums[mid]>nums[mid+1] && nums[mid]<nums[mid-1]){
                return mid;
            }else if(nums[mid] > nums[mid-1]){
                //判断在右侧
                if(nums[mid]>nums[left]){
                    left = mid+1;
                }else {
                    right=mid-1;
                }
            }else if(nums[mid] < nums[mid+1]){
                //判断在左侧
                if(nums[mid]<nums[right]){
                    right =mid-1;
                }else{
                    left = mid+1;
                }

            }
        }
        return -1;
    }
}
