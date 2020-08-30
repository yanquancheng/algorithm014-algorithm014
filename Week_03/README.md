学习笔记
递归：4步骤：终结条件-》处理当前层逻辑-〉下钻下一层逻辑-》清扫当前层
    找最近重复子问题，找最间断的原型（不漏且不重复）
    模版
   Public void recur(int level,int param){
    if（level > MAX_LEVEL）{
        return;
    }

    process(level,param);

    recur(level:level+1;newParam);

   }
