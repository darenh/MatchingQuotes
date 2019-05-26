package Quotematching.moc;

import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class Tries extends AppCompatActivity {
    public static String buildTrie(String output, String z) {
        int flag = 0;
        int i;
        char buffer[] = new char[30];
        char st[] = new char[30];
        String tmp;
        String total = "";
        TrieNode end = null;
        TrieNode root = new TrieNode();
        TrieNode temp = null;
        String beforeNewLine = null;
        while (output.length() > 0) {
            String[] split = output.split(" "); // String array, each element is text between dots
            beforeNewLine = split[0];
            //System.out.println(beforeNewLine);
            if (flag > 1)
            {
                if (temp.subTrie == null)
                {
                    temp.subTrie = new TrieNode();
                }
                Trie.insertSub(temp.subTrie, beforeNewLine); // inserting subtrie
            }
            temp = Trie.insert(beforeNewLine, 0);
            // inserting regular trie
            flag++;
            output = output.substring(output.indexOf(" ") + 1);
            output = output.trim();
            // | in quotes file ends it.
            if (output.charAt(0) == '|') {
                break;
            }
        }
        char m[] = new char[30];
        tmp = z;
        String original = tmp;
        for (i = 0; i < 20; i++)
        {
            end = Trie.query(tmp);
            if (end == null)
            {
                return total;
            }
            tmp = Trie.freq(end.subTrie, m);
            total = total + " " + tmp;
            //System.out.println(total);

        }
        // System.out.println(Trie.freq(end.subTrie, m));
        // Trie.print(end.subTrie, buffer,0);

        return original + total;
    }
}
class Trie {
        static int flag1 = 1;
        static final TrieNode root = new TrieNode();

        public static TrieNode query(String s){
            TrieNode current = root;
            //System.out.println(s);
            for (int i = 0; i < s.length(); i++)
            {
                if (current == null || current.next(s.charAt(i)) == null){
                    return null;
                }
                current = current.next(s.charAt(i));
            }
            return current;
         }
         static String s = "";

        public static String freq(TrieNode temp, char[] st)
         {
             flag1 = 1;
             freqHelper(temp, st, 0);
             String m = "" + s;
             if (m == "")
             {
                 System.out.println("ooh");
             }
             System.out.println(m);
             s= "";
             return m;
         }

         public static void freqHelper(TrieNode temp, char[] st, int k)
         {
             int rand = ThreadLocalRandom.current().nextInt(0,4);
                 char c;
                 if (temp == null)
                 {
                     return;
                 }
                 if (temp.end > 0 && flag1 == 1 && rand % 2 == 0)
                 {
                     char[] str = Arrays.copyOf(st, k);
                     flag1 = 0; // can maybe get a different str with this being random.
                     s = String.valueOf(str);
                     return;
                 }
                 st[k + 1] = 0;
                 for (int i = 0; i < 26; i++)
                 {
                     c = (char) ('a' + i);
                     st[k] = c;
                     freqHelper(temp.trieNodes[i], st, k + 1);
                 }
                 st[k] = 0;
             }
         public static void print(TrieNode temp, char[] buffer, int k)
         {
             char c;
             if (temp == null)
             {
                 return;
             }
             if (temp.end > 0)
             {
                 char[] str = Arrays.copyOf(buffer, k);
                 System.out.println(str);
             }
             buffer [k + 1] = 0;
             for (int i = 0; i < 26; i++)
             {
                 c = (char) ('a' + i);
                 buffer[k] = c;
                 print(temp.trieNodes[i], buffer, k + 1);
             }
             buffer[k] = 0;
         }

    public static TrieNode insert(final String key, int flag){
        TrieNode current = root;
        int len = key.length();
        int i;
        for (i = 0; i < len; i++)
        {
            //System.out.println(key.charAt(i));
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
        TrieNode current = temp;
        //
        //System.out.println(key);
        int len = key.length();
        int i;
        for (i = 0; i < len; i++)
        {
            //System.out.println(key.charAt(i));
            if (current.trieNodes[key.charAt(i) - 'a'] == null)
            {
                current.trieNodes[key.charAt(i) - 'a'] = new TrieNode();
            }
            current = current.next(key.charAt(i));
        }
        current.end++;
    }

}

class TrieNode {
    int end = 0;
    final TrieNode[] trieNodes = new TrieNode[26];
    TrieNode subTrie;
    public TrieNode next(final char c)
    {
        return trieNodes[c - 'a'];
    }
}
