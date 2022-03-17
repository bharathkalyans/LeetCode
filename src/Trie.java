import java.util.HashMap;

class TrieNode {

    HashMap<Character, TrieNode> map = new HashMap<>();
    //TrieNode[] links = new TrieNode[26];
    boolean flag = false;

    TrieNode() {
    }

    boolean containsKey(char c) {
        return map.containsKey(c);
//        return links[c - 'a'] != null;
    }

    TrieNode get(char c) {
        return map.get(c);
        //return links[c - 'a'];
    }


    void put(char c, TrieNode node) {
        map.put(c, node);
        //links[c - 'a'] = node;
    }

    boolean isEnd() {
        return flag;
    }

    void setFlag() {
        flag = true;
    }

}

public class Trie {

    private static TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    public void insert(String word) {
        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            if (!temp.containsKey(c))
                temp.put(c, new TrieNode());
            temp = temp.get(c);
        }
        temp.setFlag();
    }

    public boolean search(String word) {
        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            if (!temp.containsKey(c)) return false;
            temp = temp.get(c);
        }
        return temp.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for (char c : prefix.toCharArray()) {
            if (!temp.containsKey(c)) return false;
            temp = temp.get(c);
        }
        return true;
    }


}