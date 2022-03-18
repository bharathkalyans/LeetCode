public class RootSample {
    public static void main(String[] args) {
        Trie t = new Trie();

        String[] arr = {"zebra", "dog", "duck", "dove"};
        for (String w : arr) t.insert(w);

        for (String w : arr) {
            System.out.println(t.findShortestPrefix(w));
        }


    }
}
