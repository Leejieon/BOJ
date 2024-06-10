import java.util.*;

class Solution {
    PriorityQueue<Node> pq;
    Node root;
    int[][] answer;
    int index;
    
    public int[][] solution(int[][] nodeinfo) {

        answer = new int[2][nodeinfo.length];
        pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.y == o2.y) {
                return o1.x - o2.x;
            }
            return o2.y - o1.y;
        });
        
        for(int i = 0; i < nodeinfo.length; i++) {
            Node node = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
            pq.offer(node);
        }
        root = pq.poll();
        
        while(!pq.isEmpty()) {
            insertNode(root, pq.poll());
        }
        
        index = 0;
        preOrder(root);
        index = 0;
        postOrder(root);
        
        return answer;
    }
    
    void insertNode(Node parent, Node child) {
        if(child.x < parent.x) {
            if(parent.left == null) {
                parent.left = child;   
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if(parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        } 
    }
    
    void preOrder(Node cur) {
        if(cur != null) {
            answer[0][index++] = cur.index;
            preOrder(cur.left);
            preOrder(cur.right);
        }
    }
    
    void postOrder(Node cur) {
        if(cur != null) {
            postOrder(cur.left);
            postOrder(cur.right);
            answer[1][index++] = cur.index;
        }
    }
    
    class Node {
        int index;
        int x, y;
        Node left, right;
        
        Node(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
            left = null;
            right = null;
        }
    }
}