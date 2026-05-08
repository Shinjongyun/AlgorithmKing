import java.util.*;

class Solution {
    
    static int[][] answer;
    static List<Integer>[] graph;
    static List<int[]>[] map;
    static int[] xPos;
    
    static int rootLevel = 0;
    static int length;
    static int root;
    
    static int preIdx = 0;
    static int postIdx = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        
        rootLevel = 0;
        length = nodeinfo.length;
        
        for (int[] n : nodeinfo) {
            rootLevel = Math.max(rootLevel, n[1]);
        }
        
        map = new ArrayList[rootLevel + 1];
        for (int i = 0; i <= rootLevel; i++) {
            map[i] = new ArrayList<>();
        }
        
        xPos = new int[length + 1];
        
        int idx = 1;
        for (int[] n : nodeinfo) {
            int x = n[0];
            int y = n[1];
            
            map[y].add(new int[] {idx, x});
            xPos[idx] = x;
            
            idx++;
        }
        
        // 같은 y 안에서는 x 작은 순서로 정렬
        for (int i = 0; i <= rootLevel; i++) {
            map[i].sort((a, b) -> a[1] - b[1]);
        }
        
        graph = new ArrayList[length + 1];
        for (int i = 1; i <= length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 가장 높은 y에 있는 노드가 root
        root = map[rootLevel].get(0)[0];
        
        // rootLevel부터 내려오면서 노드들을 트리에 삽입
        for (int y = rootLevel - 1; y >= 0; y--) {
            for (int[] cur : map[y]) {
                int child = cur[0];
                insert(root, child);
            }
        }
        
        // graph[parent] 안에 왼쪽 자식, 오른쪽 자식 순서 보장
        for (int i = 1; i <= length; i++) {
            graph[i].sort((a, b) -> xPos[a] - xPos[b]);
        }
        
        answer = new int[2][length];
        
        preorder(root);
        postorder(root);
        
        return answer;
    }
    
    static void insert(int parent, int child) {
        
        while (true) {
            
            // child가 parent의 왼쪽에 있어야 함
            if (xPos[child] < xPos[parent]) {
                
                int left = getLeftChild(parent);
                
                if (left == 0) {
                    graph[parent].add(child);
                    return;
                }
                
                parent = left;
            }
            
            // child가 parent의 오른쪽에 있어야 함
            else {
                
                int right = getRightChild(parent);
                
                if (right == 0) {
                    graph[parent].add(child);
                    return;
                }
                
                parent = right;
            }
        }
    }
    
    static int getLeftChild(int parent) {
        for (int child : graph[parent]) {
            if (xPos[child] < xPos[parent]) {
                return child;
            }
        }
        return 0;
    }
    
    static int getRightChild(int parent) {
        for (int child : graph[parent]) {
            if (xPos[child] > xPos[parent]) {
                return child;
            }
        }
        return 0;
    }
    
    static void preorder(int node) {
        answer[0][preIdx++] = node;
        
        for (int next : graph[node]) {
            preorder(next);
        }
    }
    
    static void postorder(int node) {
        for (int next : graph[node]) {
            postorder(next);
        }
        
        answer[1][postIdx++] = node;
    }
}