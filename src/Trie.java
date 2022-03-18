import java.util.HashMap;

class TrieNode {

    private HashMap<Character, TrieNode> map = new HashMap<>();
    boolean flag = false;
    int frequency = 0;

    TrieNode() {
    }

    boolean containsKey(char c) {
        return map.containsKey(c);
    }

    TrieNode get(char c) {
        return map.get(c);
    }

    void put(char c, TrieNode node) {
        map.put(c, node);
    }

    boolean isEnd() {
        return flag;
    }

    void setFlag() {
        flag = true;
    }

}

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    public void insert(String word) {
        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            if (!temp.containsKey(c)) temp.put(c, new TrieNode());

            temp.get(c).frequency++;

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

    public String findShortestPrefix(String word) {
        StringBuilder sb = new StringBuilder();
        TrieNode temp = root;

        for (char c : word.toCharArray()) {
            if (temp.get(c).frequency == 1) {
                sb.append(c);
                break;
            }
            sb.append(c);
            temp = temp.get(c);
        }

        return sb.toString();
    }

}