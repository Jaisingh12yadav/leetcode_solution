
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        // Build the original tree
        List<Integer>[] children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] ans = new int[n];

        // Stack for each character
        List<Integer>[] path = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            path[i] = new ArrayList<>();
        }

        // Explicit DFS stack
        int[] stackNode = new int[2 * n + 5];
        int[] stackParent = new int[2 * n + 5];
        int[] stackState = new int[2 * n + 5];

        int top = 0;

        // State 0 = entering node
        // State 1 = exiting node
        stackNode[top] = 0;
        stackParent[top] = -1;
        stackState[top] = 0;

        while (top >= 0) {

            int u = stackNode[top];
            int par = stackParent[top];
            int state = stackState[top];

            top--;

            int idx = s.charAt(u) - 'a';

            if (state == 0) {

                // Enter node
                ans[u] = 1;

                // Add node to its character path
                path[idx].add(u);

                // Exit event after all children
                top++;
                stackNode[top] = u;
                stackParent[top] = par;
                stackState[top] = 1;

                // Process children
                for (int j = children[u].size() - 1; j >= 0; j--) {

                    int v = children[u].get(j);

                    top++;
                    stackNode[top] = v;
                    stackParent[top] = u;
                    stackState[top] = 0;
                }

            } else {

                // Exit node: all children are processed

                List<Integer> list = path[idx];

                int target;

                if (list.size() > 1) {

                    // Nearest same-character ancestor
                    target = list.get(list.size() - 2);

                } else {

                    // No same-character ancestor
                    target = par;
                }

                // Add subtree size to its final parent
                if (target != -1) {
                    ans[target] += ans[u];
                }

                // Remove current node from path
                list.remove(list.size() - 1);
            }
        }

        return ans;
    }
}
