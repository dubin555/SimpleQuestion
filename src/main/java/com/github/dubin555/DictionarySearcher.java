package com.github.dubin555;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * assume M: the length of dictionary, N: how many words in the document
 * If we build a hashmap on top of the input file, the time complex: O(M) to build the HashMap, O(N^2) to do the search --> but it waste lots of operations to check the 'long' sentence
 * we can build a Trie Tree on the dictionary, the time complex will be O(N^2) at worse, but is's usually better because it can cut off the unnecessary iteration
 *
 */
public class DictionarySearcher {

    private Trie trie;

    public DictionarySearcher(String filename) {
        List<String> dict = FileUtil.getStringListFromFile(filename);
        this.trie = new Trie();
        for (String s: dict) {
            trie.insert(s);
        }
    }

    public Map<String, List<Integer>> search(String document) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < document.length(); i++) {
            String word = document.substring(i);
            List<String> searchRes = trie.search(word);
            for (String w: searchRes) {
                map.putIfAbsent(w, new ArrayList<>());
                map.get(w).add(i);
            }
        }
        return map;
    }

    private static class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode(' ');
        }
        public void insert(String word) {
            TrieNode node = root;
            for (char c: word.toCharArray()) {
                if (!node.hasChild(c)) {
                    node.putChild(c);
                }
                node = node.getChild(c);
            }
            node.isWord = true;
            node.word = word;
        }

        public List<String> search(String word) {
            TrieNode node = root;
            List<String> list = new ArrayList<>();
            for (char c: word.toCharArray()) {
                if (!node.hasChild(c)) {
                    return list;
                } else {
                    node = node.getChild(c);
                    if (node.isWord) {
                        list.add(node.word);
                    }
                }
            }
            return list;
        }
    }

    private static class TrieNode {
        char c;
        boolean isWord;
        String word;
        Map<Character, TrieNode> children;
        public TrieNode(char c) {
            this.c = c;
            this.isWord = false;
            this.children = new HashMap<>();
        }

        public boolean hasChild(char c) {
            return this.children.containsKey(c);
        }

        public TrieNode getChild(char c) {
            return this.children.get(c);
        }

        public void putChild(char c) {
            this.children.putIfAbsent(c, new TrieNode(c));
        }
    }

    public static void main(String[] args) {
        DictionarySearcher searcher = new DictionarySearcher("dict.txt");
        String document = "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任";
        Map<String, List<Integer>> map = searcher.search(document);

        System.out.println(map);
        /**
         * output
         * {美国=[0, 16, 50], 浙江大学=[28, 98, 111], 浙江=[28, 98, 111]}
         */
    }
}
