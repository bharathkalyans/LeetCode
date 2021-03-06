import java.util.HashMap;

class TrieNode {

    HashMap<Character, TrieNode> map = new HashMap<>();
    boolean flag = false;
    int frequency = 0;

    TrieNode() {
    }

    boolean containsKey(char c) {
        return this.map.containsKey(c);
    }

    TrieNode get(char c) {
        return this.map.get(c);
    }

    void put(char c, TrieNode node) {
        this.map.put(c, node);
    }

    boolean isEnd() {
        return this.flag;
    }

    void setFlag() {
        this.flag = true;
    }

    int getMapSize() {
        return this.map.size();
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

    public String findLongestCommonPrefix() {
        StringBuilder sb = new StringBuilder();
        TrieNode temp = root;

        while (temp != null && !temp.isEnd() && temp.map.size() == 1) {
            for (var entry : temp.map.entrySet()) {
                sb.append(entry.getKey());
                temp = entry.getValue();
            }
        }

        return sb.toString();
    }

}