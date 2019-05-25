package Quotematching.moc;

import android.support.v7.app.AppCompatActivity;


public class Tries extends AppCompatActivity {
    public static String buildTrie(String output) {
        int flag = 0;
        TrieNode root = new TrieNode();
        String beforeNewLine = null;
        while (output.length() > 0) {
            String[] split = output.split(" "); // String array, each element is text between dots
            beforeNewLine = split[0];
            //System.out.println(beforeNewLine);
            Trie.insert(beforeNewLine, flag);
            flag = 1;
            output = output.substring(output.indexOf(" ") + 1);
            output = output.trim();
            if (output.charAt(0) == '|') {
                break;
            }
        }
        return beforeNewLine;
    }
}
class Trie {
        static TrieNode root;
        public Trie() {
            this.root = new TrieNode();
         }
         public int query(final String s){
            TrieNode current = root;
            for (int i = 0; i < s.length(); i++)
            {
                if (current == null){
                    return 0;
                }
                current = current.next(s.charAt(i));
            }
            return current.end;
         }
    public static void insert(final String key, int flag){
        TrieNode current = root;
        int len = key.length();
        int i;
        for (i = 0; i < len; i++)
        {
            if (current.trieNodes[key.charAt(i)] == null)
            {
                System.out.println(key);
                current.trieNodes[key.charAt(i)] = new TrieNode();
            }
            current = current.next(key.charAt(i));
        }
        //current.end++;
    }

}

class TrieNode {
    int end = 0;
    final TrieNode[] trieNodes = new TrieNode[26];
    public TrieNode next(final char c) {return trieNodes[c - 'a'];}
}
