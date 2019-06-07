package Quotematching.moc;

import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class Tries extends AppCompatActivity {
    public static String makeTrie(String output, String z) {
        int flag = 0;
        int flag2 = 2;
        int i;
        String tmp;
        String total = "";
        TrieNode end = null;
        TrieNode temp = null;
        String beforeNewLine = null;
        while (output.length() > 0) {
            String[] split = output.split(" "); // String array, each element is text between dots
            beforeNewLine = split[0];
            //System.out.println(beforeNewLine);
            flag2++;
            if (beforeNewLine.contains(".") == true)
            {
                beforeNewLine = beforeNewLine.replace(".", "");
                flag2 = 0;
            }
            if (flag > 1 && (flag2 >= 2 || flag2 == 0))
            {
                if (temp.sub == null)
                {
                    temp.sub = new TrieNode();
                }
                Trie.insertSub(temp.sub, beforeNewLine); // inserting sub
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
        int j = 0;
        char m[] = new char[30];
        tmp = z;
        String original = tmp;
        for (i = 0; i < 20; i++)
        {
            end = Trie.get(tmp);
            if (end == null)
            {
                return total;
            }
            tmp = Trie.freq(end.sub, m);
            total = total + " " + tmp;
            //System.out.println(total);

        }

        return original + total;
    }
}
class Trie {
        static int flag1 = 1;
        static int flag2 = 1;
        static int count = 1;
        static final TrieNode root = new TrieNode();

        public static TrieNode get(String s){
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
             int rand = ThreadLocalRandom.current().nextInt(0, 3);
             flag1 = 1;
             int x = counter(temp);
             freqHelper(temp, st, 0, x);
             String m = "" + s;
             if (m == "")
             {
                 System.out.println("ooh");
             }
             System.out.println(m);
             s = "";
             count = 1;
             return m;
         }
         public static int counter(TrieNode temp)
         {
             counterHelper(temp);
             System.out.println(count);
             flag2 = 1;
             int x = count;
             return x;
         }
         public static void counterHelper(TrieNode temp)
         {
             if (temp == null)
             {
                 return;
             }
             int i;
             if (temp.end > 0 && flag2 == 0)
             {
                 count++;
             }
             if (temp.end > 0)
             {
                 flag2 = 0;
             }
             for (i = 0; i < 26; i++)
             {
                 counterHelper(temp.trieNodes[i]);
             }
         }

         public static void freqHelper(TrieNode temp, char[] st, int k, int x)
         {
             int rand = ThreadLocalRandom.current().nextInt(0, x);
                 char c;
                 if (temp == null)
                 {
                     return;
                 }
                 // uses random so the output is different each time.
                 if (temp.end > 0 && (rand == 0 || rand == 1))
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
                     freqHelper(temp.trieNodes[i], st, k + 1, x);
                 }
                 st[k] = 0;
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
    TrieNode sub;
    public TrieNode next(final char c)
    {
        return trieNodes[c - 'a'];
    }
}
