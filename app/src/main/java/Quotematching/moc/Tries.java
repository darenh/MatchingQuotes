package Quotematching.moc;

import android.support.v7.app.AppCompatActivity;


public class Tries extends AppCompatActivity {
    public static String buildTrie(String output) {
        int flag = 0;
        TrieNode root = new TrieNode();
        TrieNode temp = null;
        String beforeNewLine = null;
        while (output.length() > 0) {
            String[] split = output.split(" "); // String array, each element is text between dots
            beforeNewLine = split[0];
            //System.out.println(beforeNewLine);
            if (temp != null)
            {
                Trie.insertSub(temp, beforeNewLine); // inserting subtrie
            }
            temp = Trie.insert(beforeNewLine, 0); // inserting regular trie
            flag = 1;
            output = output.substring(output.indexOf(" ") + 1);
            output = output.trim();
            if (output.charAt(0) == '|') {
                break;
            }
        }
        if (Trie.query("infinitea") == 0)
        {
            System.out.println("Hooray!!!");
        }
        return beforeNewLine;
    }
}
class Trie {
        static final TrieNode root = new TrieNode();
         public static int query(final String s){
            TrieNode current = root;
            for (int i = 0; i < s.length(); i++)
            {
                if (current == null || current.next(s.charAt(i)) == null){
                    return 0;
                }
                current = current.next(s.charAt(i));
            }
            return current.end;
         }

    public static TrieNode insert(final String key, int flag){
        TrieNode current = root;
        int len = key.length();
        int i;
        for (i = 0; i < len; i++)
        {
            System.out.println(key.charAt(i));
            if (current.trieNodes[key.charAt(i) - 'a'] == null)
            {
                current.trieNodes[key.charAt(i) - 'a'] = new TrieNode();
            }
            current = current.next(key.charAt(i));
        }
        current.end++;
        return current;
    }

    public static void insertSub(TrieNode temp, final String key){
        TrieNode current = temp.subTrie;
        //
        int len = key.length();
        int i;
        for (i = 0; i < len; i++)
        {
            System.out.println(key.charAt(i));
            if (temp.trieNodes[key.charAt(i) - 'a'] == null)
            {
                temp.trieNodes[key.charAt(i) - 'a'] = new TrieNode();
            }
            temp = temp.next(key.charAt(i));
        }
        temp.end++;
    }

}

class TrieNode {
    int end = 0;
    final TrieNode[] trieNodes = new TrieNode[26];
    final TrieNode[] subTrie = new TrieNode[26];
    public TrieNode next(final char c)
    {
        return trieNodes[c - 'a'];
    }
}
