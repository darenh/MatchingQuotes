package Quotematching.moc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tries {
    static class Node {
        Node[] Trie = new Node[26];
        boolean end;
        char data;
        int number;
        Node(){
            end = false;
            for (int i = 0; i < 26; i++)
            {
                Trie[i] = null;
            }
        }
    }
    static Node root;
    static void insert(String key){
        int length = key.length();
        int index;
        int i;
        Node temp = root;
        for (i = 0; i < length; i++)
        {
            index = key.charAt(i) - 'a';
            if (temp.Trie[index] == null)
            {
                temp.Trie[index] = new Node();
            }
            temp = temp.Trie[index];
        }
        temp.end = true;
    }

    static boolean search(String key)
    {
        int level;
        int length = key.length();
        int index;
        Node pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (pCrawl.Trie[index] == null)
                return false;

            pCrawl = pCrawl.Trie[index];
        }

        return (pCrawl != null && pCrawl.end);
    }
}

