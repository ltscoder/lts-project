package com.lts.arth.other.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author luotianshun
 * @date 2021/7/20
 * @menu 最短路径
 * 参考1https://blog.csdn.net/yu121380/article/details/79824692
 * 参考2https://www.cnblogs.com/thousfeet/p/9229395.html
 * <p>
 * ~图的常用表示方法：1邻接矩阵(二维数组)，2邻接表(每个节点链接所有相邻的节点)
 */
public class shortestPath {

    /**
     * 不可到达的距离
     */
    public static int UNREACHABLE = -1;

    public static void main(String[] args) {
        //输入邻接矩阵，这里节点序号从1开始,-1表示无法到达。inputArr[i,j]表示节点i到节点节点j的直接距离。
        int[][] inputArr = {
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, 0, 1, 12, -1, -1, -1},
                {-1, -1, 0, 9, 3, -1, -1},
                {-1, -1, -1, 0, -1, 5, -1},
                {-1, -1, -1, 4, 0, 13, 15},
                {-1, -1, -1, -1, -1, 0, 4},
                {-1, -1, -1, -1, -1, -1, 0}
        };

        //1回溯+dfs。
        //求两点间的最短距离。节点1到节点6
        int to6Distance = solution1(inputArr, 1, 6);
        int to5Distance = solution1(inputArr, 1, 5);
        System.out.println("dfs:");
        System.out.println("to 6：" + to6Distance);
        ;
        System.out.println("to 5：" + to5Distance);

        //2bfs
        //求两点间的最短距离。
        to6Distance = solution2(inputArr, 1, 6);
        to5Distance = solution2(inputArr, 1, 5);
        System.out.println("bfs:");
        System.out.println("to 6：" + to6Distance);
        System.out.println("to 5：" + to5Distance);

        //Floyd算法。求任意两点间的最短路
        //基本思想：最开始只允许经过1号顶点进行中转，接下来只允许经过1号和2号顶点进行中转......允许经过1~n号所有顶点进行中转，来不断动态更新任意两点之间的最短路程。
        //        即求从i号顶点到j号顶点只经过前k号点的最短路程。
        //核心代码~伪代码：
//        for(int k = 1; k <= n; k++)
//            for(int i = 1; i <= n; i++)
//                for(int j = 1; j <= n; j++)
//                    if(e[i][j] > e[i][k]+e[k][j])
//                        e[i][j] = e[i][k]+e[k][j];
        int[][] copiedInput = new int[inputArr.length][inputArr.length];
        for (int i = 0; i < inputArr.length; i++) {
            System.arraycopy(inputArr[i], 0, copiedInput[i], 0, inputArr[i].length);
        }
        solution3(copiedInput);
        System.out.println("Floyd:");
        System.out.println("to 6：" + copiedInput[1][6]);
        System.out.println("to 5：" + copiedInput[1][5]);

        //Dijkstra算法。求单源最短路径，即从一个源出发，求他到其余各个结点的最短路
//        整个算法的基本步骤是：
//        所有结点分为两部分：已确定最短路的结点集合P、未知最短路的结点集合Q。最开始，P中只有源点这一个结点。（可用一个book数组来维护是否在P中）
//        在Q中选取一个离源点最近的结点u（dis[u]最小）加入集合P。然后考察u的所有出边，做松弛操作。
//        重复第二步，直到集合Q为空。最终dis数组的值就是源点到所有顶点的最短路。

//        Dijkstra是一种基于贪心策略的算法。每次新扩展一个路径最短的点，更新与它相邻的所有点。
//        当所有边权为正时，由于不会存在一个路程更短的没扩展过的点，所以这个点的路程就确定下来了，这保证了算法的正确性。
//        但也正因为这样，这个算法不能处理负权边，因为扩展到负权边的时候会产生更短的路径，有可能破坏了已经更新的点路程不会改变的性质。
        int[] dis = solution4(inputArr, 1);
        System.out.println("Dijkstra:");
        System.out.println("to 6：" + dis[6]);
        System.out.println("to 5：" + dis[5]);

        //Bellman-Ford算法
        //这里的输入是图的另外一种表示方式吧，数据还是来自inputArr。
        //这里数组长度表示边的条数
        int[] u = {1, 1, 2, 2, 4, 4, 4, 5, 3}; //u存边的起点
        int[] v = {2, 3, 3, 4, 3, 5, 6, 6, 5}; //v存边的终点。
        int[] dist = {1, 12, 9, 3, 4, 13, 15, 4, 5}; //dist存边的权值。
        dis = solution5(1, u, v, dist, u.length, 6);
        System.out.println("Bellman-Ford:");
        System.out.println("to 6：" + dis[6]);
        System.out.println("to 5：" + dis[5]);
    }

    /**
     *
     * @param ori
     * @param u 边的起点
     * @param v 边的终点
     * @param w 边的权值
     * @param m 边的条数
     * @param n 节点个数
     * @return
     */
    private static int[] solution5(int ori, int[] u, int[] v, int[] w, int m, int n) {
//        初始化dis数组，只有源点的距离为0
        int[] dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE/2);  //~这里不能用Integer.MAX_VALUE,相加可能溢出。
        dis[ori] = 0;
//        for (int i = 0; i < u.length; i++) {
//            if (u[i] == ori) {
//                dis[v[i]] = w[i];
//            }
//        }
        //原理参考https://www.jianshu.com/p/b876fe9b2338
        //~在最好的情况(~遍历边的顺序最优)，依次进行1轮松弛就行了。
        //~最坏的情况，每一轮遍历，会增加至少一个已确认节点的最短路径。n-1轮后，就确定完了。
        for (int i = 1; i < n; i++) {//进行n-1轮松弛
            for (int j = 0; j < m; j++) {//枚举每一条边
                if (dis[v[j]] > dis[u[j]] + w[j]) {
                    dis[v[j]] = dis[u[j]] + w[j];
                }
            }
        }
        return dis;
    }

    /**
     * @param inputArr 邻接矩阵
     * @param ori      源点
     * @return
     */
    private static int[] solution4(int[][] inputArr, int ori) {
        //book把节点分为p，q两部分，=ture表示集合P，(已确定最短路的结点集合P、未知最短路的结点集合Q)
        boolean[] book = new boolean[inputArr.length];
        book[ori] = true;
        //初始化dis为源点到各点的距离
        int[] dis = new int[inputArr.length];
        for (int i = 1; i < inputArr.length; i++) {
            dis[i] = inputArr[ori][i];
        }
        for (int i = 1; i < inputArr.length - 1; i++) {//做n个节点做n-1遍就能把Q遍历空
            int minDis = Integer.MAX_VALUE;
            int minPos = 0;
            for (int j = 1; j < book.length; j++) {  //寻找Q中最近的结点
                if (!book[j] && dis[j] != UNREACHABLE && dis[j] < minDis) {
                    minPos = j;
                    minDis = dis[j];
                }
            }

            book[minPos] = true;  //加入到P集合
            dis[minPos] = minDis;
            //对u的所有出边进行松弛
            //~这里更新的都是源节点到其它节点的距离。
            for (int k = 1; k < book.length; k++) {
                if (!book[k] &&
                        dis[minPos] != UNREACHABLE && inputArr[minPos][k] != UNREACHABLE &&
                        (dis[k] == UNREACHABLE || dis[minPos] + inputArr[minPos][k] < dis[k])) {
                    dis[k] = dis[minPos] + inputArr[minPos][k];
                }
            }
        }
        return dis;
    }

    private static void solution3(int[][] inputArr) {
        for (int k = 1; k < inputArr.length; k++) { //先允许1号节点作为中转给所有两两松弛一波，再允许2号、3号...n号都做一遍
            for (int i = 1; i < inputArr.length; i++) {
                for (int j = 1; j < inputArr.length; j++) {
                    //i->k和k->可达 且 路径比i->j小 则更新i->j的路程
                    if (inputArr[i][k] != UNREACHABLE && inputArr[k][j] != UNREACHABLE &&
                            (inputArr[i][j] == UNREACHABLE || inputArr[i][k] + inputArr[k][j] < inputArr[i][j])) {
                        inputArr[i][j] = inputArr[i][k] + inputArr[k][j];
                    }
                }
            }
        }
    }

    /**
     * @param inputArr 邻接矩阵
     * @param ori      出发点
     * @param des      目的地。
     *                 by me
     */
    private static int solution2(int[][] inputArr, int ori, int des) {
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> dis = new LinkedList<>();
        //类似queue和dis，这里用used记录每种情况的路径。~
        Queue<List<Integer>> used = new LinkedList<>();
        queue.add(ori);
        dis.add(0);
        List<Integer> use = new ArrayList<>();
        use.add(ori);
        used.add(use);

        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Integer pos = queue.poll();
            Integer distance = dis.poll();
            List<Integer> usedPos = used.poll();
            if (distance > min) {
                continue;
            }
            if (pos == des) {
                if (distance < min) {
                    min = distance;
                }
                continue;
            }
            for (int i = 1; i < inputArr.length; i++) {
                //节点没用过，且上个节点到这个节点可达
                if (!usedPos.contains(i) && inputArr[usedPos.get(usedPos.size() - 1)][i] != UNREACHABLE) {
                    queue.add(i);
                    dis.add(distance + inputArr[usedPos.get(usedPos.size() - 1)][i]);
                    List<Integer> next = new ArrayList<>(usedPos);
                    next.add(i);
                    used.add(next);
                }
            }

        }

        return min;
    }

    /**
     * @param inputArr 邻接矩阵
     * @param ori      出发点
     * @param des      目的地。
     *                 by me
     */
    private static int solution1(int[][] inputArr, int ori, int des) {
        boolean[] used = new boolean[inputArr.length];
        used[0] = true;
        used[ori] = true;
        List<Integer> curPath = new ArrayList<>();
        curPath.add(ori);
        return dfsFindShortestPath(inputArr, used, des, curPath, 0, Integer.MAX_VALUE);
    }

    /**
     * @param inputArr 邻接矩阵
     * @param used     已经走过的节点
     * @param des      目的地。
     * @param curPath  目前经过的节点
     * @param curDis   目前经过的路程
     * @param minDis   最小距离
     * @return
     */
    private static int dfsFindShortestPath(int[][] inputArr, boolean[] used, int des, List<Integer> curPath, int curDis, int minDis) {
        //如果已经超过最小距离，或者已经到达目节点，则返回
        if (curDis >= minDis) {
            return minDis;
        }
        if (used[des]) {
            return curDis > minDis ? minDis : curDis;
        }
        for (int i = 1; i < inputArr.length; i++) {
            //节点没用过，且上个节点到这个节点可达
            if (!used[i] && inputArr[curPath.get(curPath.size() - 1)][i] != UNREACHABLE) {
                used[i] = true;
                curPath.add(i);
                minDis = dfsFindShortestPath(inputArr, used, des, curPath, curDis + inputArr[curPath.get(curPath.size() - 2)][i], minDis);
                curPath.remove(curPath.size() - 1);
                used[i] = false;
            }
        }
        return minDis;
    }
}
