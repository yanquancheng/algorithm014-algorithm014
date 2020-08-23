学习笔记
1.HashMap的小结
1.1 大背景：先说一下java中的集合"List、Set、Map"，继承结构如下，Map没有继承Collection
Collection
├List
│├LinkedList，LinkedList实现了List接口，允许null元素。此外LinkedList提供额外的get，remove，insert方法在 LinkedList的首部或尾部。这些操作使LinkedList可被用作堆栈（stack），队列（queue）或双向队列（deque）。特点是寻址困难、插入和删除容易
│├ArrayList，ArrayList实现了可变大小的数组(允许null)
│└Vector Vector非常类似ArrayList，但是Vector是同步的。由Vector创建的Iterator，虽然和ArrayList创建的 Iterator是同一接口，但是，因为Vector是同步的，当一个Iterator被创建而且正在被使用，另一个线程改变了Vector的状态（例 如，添加或删除了一些元素），这时调用Iterator的方法时将抛出ConcurrentModificationException，因此必须捕获该 异常。
│　└Stack Stack继承自Vector，实现一个后进先出的堆栈。Stack提供5个额外的方法使得 Vector得以被当作堆栈使用。基本的push和pop 方法，还有peek方法得到栈顶的元素，empty方法测试堆栈是否为空，search方法检测一个元素在堆栈中的位置。Stack刚创建后是空栈。
└Set
 |-HashSet 基于Hash算法实现，性能优于TreeSet；不允许出现重复元素；不保证集合顺序；允许null元素但由于不重复性最多允许一个；不同步的
 └TreeSet 按照升序排列，根据构造方法的不同实现，肯能会按照元素的自然顺序排序，或者按照创建set时所提供的比较器进行排序。
LinkedList和ArrayList的区别：1、ArrayList的实现是基于数组，LinkedList的实现是基于双向链表。2、对于随机访问，ArrayList优于LinkedList 3、对于插入和删除操作，LinkedList优于ArrayList 4、LinkedList比ArrayList更占内存，因为LinkedList的节点除了存储数据，还存储了两个引用，一个指向前一个元素，一个指向后一个元素。
Map
├Hashtable 1.Hashtable继承Map接口，实现一个key-value映射的哈希表。任何非空（non-null）的对象都可作为key或者value。添加数据使用put(key, value)，取出数据使用get(key)，这两个基本操作的时间开销为常数。2.Hashtable 通过initial capacity和load factor两个参数调整性能。通常缺省的load factor 0.75较好地实现了时间和空间的均衡。增大load factor可以节省空间但相应的查找时间将增大，这会影响像get和put这样的操作。3.作为key的对象将通过计算其散列函数来确定与之对应的value的位置，因此任何作为key的对象都必须实现hashCode和equals方法。4.Hashtable是同步的。
├HashMap HashMap和Hashtable类似，不同之处在于HashMap是非同步的，并且允许null（即null value和null key）。capacity和load factor两个参数影响性能
└WeakHashMap WeakHashMap是一种改进的HashMap，它对key实行“弱引用”，如果一个key不再被外部所引用，那么该key可以被GC回收。

HashMap的两种遍历方式，推荐使用entrySet（效率高）.
Map map = new HashMap();
　　     Iterator iter = map.entrySet().iterator();
　　     while (iter.hasNext()) {
　　     Map.Entry entry = (Map.Entry) iter.next();
　　     Object key = entry.getKey();
　　     Object val = entry.getValue();
　　}
Map map = new HashMap();
  　　Iterator iter = map.keySet().iterator();
  　　while (iter.hasNext()) {
  　　Object key = iter.next();
  　　Object val = map.get(key);
  　　}
1.2 小背景
java.util.Map 接口，分别被AbstractMap、SortedMap、Hashtable(同时继承自Dictionary)实现；分别被AbstractMap被HashMap继承，LikedHashMap又继承自HashMap，TreeMap继承自AbstractMap同时实现了NavigableMap接口(继承自SortedMap)

(1) HashMap：它根据键的hashCode值存储数据，大多数情况下可以直接定位到它的值，因而具有很快的访问速度，但遍历顺序却是不确定的。 HashMap最多只允许一条记录的键为null，允许多条记录的值为null。HashMap非线程安全，即任一时刻可以有多个线程同时写HashMap，可能会导致数据的不一致。如果需要满足线程安全，可以用 Collections的synchronizedMap方法使HashMap具有线程安全的能力，或者使用ConcurrentHashMap。
(2) Hashtable：Hashtable是遗留类，很多映射的常用功能与HashMap类似，不同的是它承自Dictionary类，并且是线程安全的，任一时间只有一个线程能写Hashtable，并发性不如ConcurrentHashMap，因为ConcurrentHashMap引入了分段锁。Hashtable不建议在新代码中使用，不需要线程安全的场合可以用HashMap替换，需要线程安全的场合可以用ConcurrentHashMap替换。
(3) LinkedHashMap：LinkedHashMap是HashMap的一个子类，保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的，也可以在构造时带参数，按照访问次序排序。
(4) TreeMap：TreeMap实现SortedMap接口，能够把它保存的记录根据键排序，默认是按键值的升序排序，也可以指定排序的比较器，当用Iterator遍历TreeMap时，得到的记录是排过序的。如果使用排序的映射，建议使用TreeMap。在使用TreeMap时，key必须实现Comparable接口或者在构造TreeMap传入自定义的Comparator，否则会在运行时抛出java.lang.ClassCastException类型的异常。

1.3 HashMap分析
hashmap中存储的是健值对，从源码的结构看，Hash桶数组中存储的是Node对象(健值对的映射)。当存储Node到数组中时候通过hashCode()方法用key对数组长度取膜，发生Hash碰撞(多个不同key经过hashcode之后需要放在数组的同一个位置)则在数组当前位置扩展出来链表，当链表长度大于8时候转换成红黑树。

static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;    //用来定位数组索引位置
        final K key;
        V value;
        Node<K,V> next;   //链表的下一个node

        Node(int hash, K key, V value, Node<K,V> next) { ... }
        public final K getKey(){ ... }
        public final V getValue() { ... }
        public final String toString() { ... }
        public final int hashCode() { ... }
        public final V setValue(V newValue) { ... }
        public final boolean equals(Object o) { ... }
}
// HashMap默认构造函数源码
int threshold;             // 所能容纳的key-value对极限 threshold = length * Load factor，Node[] table的初始化长度length(默认值是16，这个值总是2的n次方)，Load factor为负载因子(默认值是0.75)
final float loadFactor;    // 负载因子，平衡空间和时间的因子，如果内存空间很多而又对时间效率要求很高，可以降低负载因子Load factor的值；相反，如果内存空间紧张而对时间效率要求不高，可以增加负载因子loadFactor的值，这个值可以大于1。
int modCount;              //用来记录HashMap内部结构发生变化的次数，主要用于迭代的快速失败。强调一点，内部结构发生变化指的是结构发生变化，例如put新键值对，但是某个key对应的value值被覆盖不属于结构变化。
int size;                  //是HashMap中实际存在的键值对数量

当length总是2的n次方时，h& (length-1)运算等价于对length取模，也就是h%length，但是&比%具有更高的效率。"在JDK1.8的实现中，优化了高位运算的算法，通过hashCode()的高16位异或低16位实现的：(h = k.hashCode()) ^ (h >>> 16)，主要是从速度、功效、质量来考虑的，这么做可以在数组table的length比较小的时候，也能保证考虑到高低Bit都参与到Hash的计算中，同时不会有太大的开销。"没太理解其好处





