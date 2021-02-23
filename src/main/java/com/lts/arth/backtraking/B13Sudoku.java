package com.lts.arth.backtraking;

/**
 * @author luotianshun
 * @date 2021/2/19
 * @menu 数独问题 lc 37
 */
public class B13Sudoku {

    /**
     * 编写一个程序，通过已填充的空格来解决数独问题。
     * 一个数独的解法需遵循如下规则：
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。(两条横线和两条纵线分割成的)
     * Note:
     * 给定的数独序列只包含数字 1-9 和字符 '.' 。
     * 你可以假设给定的数独只有唯一解。
     * 给定数独永远是 9x9 形式的。
     *
     * 回溯+dfs(~注意这个题虽然是二维数组，但是深度的维度是每个(row,col)对应的位置)
     * 参考【37. 解数独（深度优先）】 by 李小白~
     * @param args
     */
    public static void main(String[] args) {
        char[][] input = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        solution(input);
//        System.out.println(new ArrayList(Arrays.asList(input)));
        for (char[] chars : input) {
            System.out.println(chars);
        }
    }

    private static void solution(char[][] board) {
         //三个标记。分别存放每行/每列/每个3*3的方格内出现1-9的次数。
         int[][] rowMark = new int[9][10];
         int[][] colMark = new int[9][10];
         int[][] gridMark = new int[9][10];
         //根据输入数据，初始化这个变量
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                int gridIndex = (i/3)*3+j/3;
                rowMark[i][num]++;
                colMark[j][num]++;
                gridMark[gridIndex][num]++;
            }
        }
        if (dfsSudoku(0, 0, board, rowMark, colMark, gridMark)) {
            System.out.println("找到一个解");
        }
    }

    private static boolean dfsSudoku(int row, int col, char[][] board, int[][] rowMark, int[][] colMark, int[][] gridMark) {
        //计算row，col
        row += col/9;
        col %= 9;

        //每一行都遍历了，则返回true，说明找到一个解
        if (row == 9) {
            return true;
        }
        //如果这个位置已经有数字了，则深度下一个位置
        if (board[row][col] != '.') {
            return dfsSudoku(row, col + 1, board, rowMark, colMark, gridMark);
        }
        int gridIndex = (row/3) * 3 + col/3;
        for (int i = 0; i <= 9; i++) {
            //判断这个位置能否填这个数字
            if (rowMark[row][i] == 0 && colMark[col][i] == 0 && gridMark[gridIndex][i] == 0) {
                //填上这个数字
                rowMark[row][i]++;
                colMark[col][i]++;
                gridMark[gridIndex][i]++;
                board[row][col] = (char)('0' + i);
                //dfs深度遍历，~这里深度的维度不是行，而是每一个(row,col),然后在对数字1-9进行遍历。
                if (dfsSudoku(row, col + 1, board, rowMark, colMark, gridMark)) {
                    //找到一个直接返回。
                    return true;
                } else {
                    //回溯
                    rowMark[row][i]--;
                    colMark[col][i]--;
                    gridMark[gridIndex][i]--;
                    board[row][col] = '.';
                }
            }
        }
        return false;
    }
}
