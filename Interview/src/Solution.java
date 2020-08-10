import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        boolean[] marked = new boolean[wordList.size()]; // 可以set
        //检验是否存在beginWord，如果存在，就置为访问过了,没必要访问
        int idx = wordList.indexOf(beginWord);
        if (idx != -1)
            marked[idx] = true;
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            while (size-- > 0) {
                String start = q.poll();
                for (int i = 0; i < wordList.size(); i++) {
                    // 访问过了
                    if (marked[i])
                        continue;
                    String s = wordList.get(i);
                    //不满足和当前只差一个字符不同，跳过，访问下一个
                    if (!isConnect(start, s))
                        continue;
                    //和endWord匹配上了，进行返回，因为是bfs，所以找到了直接返回就是最短的
                    if (s.equals(endWord))
                        return cnt+1;
                    q.add(s);
                    marked[i] = true;
                }
            }
        }
        return 0;
    }

    private boolean isConnect(String s1, String s2) {
        int diffCnt = 0;
        for (int i = 0; i < s1.length() && diffCnt <= 1; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }
}
