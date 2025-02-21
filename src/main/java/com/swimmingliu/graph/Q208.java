package com.swimmingliu.graph;

import javax.swing.*;
import java.util.*;

/**
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，
 * 用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class Q208 {

    // 前缀树为26叉树 -> 本质仍然是一棵树
    // 为什么是26? -> 用于代表26个字母
    // 如何实现插入 -> 把字符串的每个字符作为一个节点，然后逐个连接，最后的节点isEnd为true
    class Trie {
        private TreeNode root;
        class TreeNode{
            boolean isEnd;
            TreeNode[] next = new TreeNode[26];
        }
        // 初始化根节点
        public Trie() {
            root = new TreeNode();
        }
        // 遍历word的所有字符，然后逐个插入到对应的位置，形成新的子树
        public void insert(String word) {
            TreeNode node = root;
            for(char c : word.toCharArray()){
                if (node.next[c - 'a'] == null)
                    node.next[c - 'a'] = new TreeNode();
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
        }

        // 遍历word字符串，过程中不能出现字符无对应节点的情况，判断是否能够读取到最后一位
        public boolean search(String word) {
            TreeNode node = root;
            for (char c : word.toCharArray()){
                if (node.next[c - 'a'] == null)
                    return false;
                node = node.next[c - 'a'];
            }
            return node.isEnd;
        }

        // 遍历word字符串，过程中不能出现字符无对应节点的情况
        public boolean startsWith(String prefix) {
            TreeNode node = root;
            for (char c : prefix.toCharArray()){
                if (node.next[c - 'a'] == null)
                    return false;
                node = node.next[c - 'a'];
            }
            return true;
        }
    }

    public static void main(String[] args) {
    }
}
