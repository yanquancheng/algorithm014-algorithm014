学习笔记
*寻找最近可重复项，就可以考虑递归、分治和回溯
1.递归
模版
public void recur(int level, int param) {
  // 1.terminator  --终结条件
  if (level > MAX_LEVEL) {
    // process result
    return;
  }
  // 2.process current logic --当前处理逻辑
  process(level, param);
  // 3.drill down --下钻
  recur( level: level + 1, newParam);
  // 4.restore current status --清空状态，如果是全局变量或者入参的清空下需要清空

}
2.分治
private static int divide_conquer(Problem problem) {
  //1.terminal ---终结条件
  if (problem == NULL) {
    int res = process_last_result();
    return res;
  }
  //2.大问题分解为子问题
  subProblems = split_problem(problem)
  //3.分别处理每一个子问题
  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])
  //4.合并
  result = process_result(res0, res1);

  return result;
}
3.回溯，当下一层不符合期望的时候，回退上一层的结果并重新开始新一轮遍历