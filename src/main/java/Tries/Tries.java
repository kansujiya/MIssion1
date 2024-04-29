package Tries;

import java.util.HashMap;
import java.util.Map;

//use this class only in case, if we need to store data at each node
// else we can keep store within hashmap
class TriesNode {
    //To store data at each node
    int data;
    Map<Character, TriesNode> children;

    public TriesNode() {
        this.children = new HashMap<>();
    }
}

public class Tries {

    public static void main(String[] args) {
        //build tries data structure
        System.out.println(buildTries(new String[] {"My", "Name", "is", "suresh", "kansujiya"}));

        //2. Sort list of words
        String arr[] = { "abc", "aaa", "abe", "xy", "bcd" };

        int n = arr.length;

        printSorted(arr, n);
    }

    public static TriesNode buildTries(String[] words) {
        TriesNode root = new TriesNode();
        for (String word : words) {
            TriesNode curr = root;
            for(char c: word.toCharArray()) {
                if(!curr.children.containsKey(c)){
                    curr.children.put(c, new TriesNode());
                }

                curr = curr.children.get(c);
            }
            // at this point, you have a full word at curr
            // you can perform more logic here to give curr an attribute if you want
        }
        return root;
    }

        // Alphabet size
        static final int MAX_CHAR = 26;

        // trie node
        static class Trie {

            // index is set when node is a leaf
            // node, otherwise -1;
            int index;

            Trie child[] = new Trie[MAX_CHAR];

            /*to make new trie*/
            Trie() {

                for (int i = 0; i < MAX_CHAR; i++)
                    child[i] = null;
                index = -1;
            }
        }

        /* function to insert in trie */
        static void insert(Trie root, String str, int index) {

            Trie node = root;

            for (int i = 0; i < str.length(); i++) {
            /* taking ascii value to find index of
          child node */
                int ind = str.charAt(i) - 'a';

                /* making new path if not already */
                if (node.child[ind] == null)
                    node.child[ind] = new Trie();

                // go to next node
                node = node.child[ind];
            }

            // Mark leaf (end of word) and store
            // index of word in arr[]
            node.index = index;
        }

        /* function for preorder traversal */
        static boolean preorder(Trie node, String arr[]) {

            if (node == null) {

                return false;
            }

            for (int i = 0; i < MAX_CHAR; i++) {

                if (node.child[i] != null) {

                    /* if leaf node then print key*/
                    if (node.child[i].index != -1) {

                        System.out.print(
                                arr[node.child[i].index] + " ");
                    }

                    preorder(node.child[i], arr);
                }
            }
            return false;
        }

        static void printSorted(String arr[], int n) {

            Trie root = new Trie();

            // insert all keys of dictionary into trie
            for (int i = 0; i < n; ++i) {

                insert(root, arr[i], i);
            }

            // print keys in lexicographic order
            preorder(root, arr);
        }
}
