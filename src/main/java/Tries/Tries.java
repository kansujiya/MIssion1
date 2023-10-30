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

}
