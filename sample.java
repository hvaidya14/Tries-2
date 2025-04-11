class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        List<String> startswith = new ArrayList<>();
    }
    TrieNode root = null;
    List<List<String>> result = new ArrayList<>();
    private void insert(String word) {
        TrieNode curr = root;
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if(curr.children[c-'a'] == null) {
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
            curr.startswith.add(word);
        }
    }
    private List<String> returnwords(String prefix) {
        TrieNode curr = root;
        for (int i=0;i<prefix.length();i++) {
            char c = prefix.charAt(i);
            if(curr.children[c-'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c-'a'];
        }
        return curr.startswith;
    }
    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();
        for (String word:words) {
            insert(word);
        }
        List<String> li = new ArrayList<>();
        for (String word:words) {
            li.add(word);
            backtrack(li);
            li.remove(0);
        }
        return result;
    }
    private void backtrack(List<String> li) {
        if (li.size() == li.get(0).length()) {
            result.add(new ArrayList<>(li));
            return;
        }
        StringBuilder sb = new StringBuilder("");
        for(String word : li) {
            sb.append(word.charAt(li.size()));
        }
        System.out.println(sb);
        List<String> wordslist = returnwords(sb.toString());
        System.out.println(wordslist);
        for (String word:wordslist) {
            li.add(word);
            backtrack(li);
            li.remove(li.size()-1);
        }
    }
}


class Solution {
    List<Boolean> result = new ArrayList<>();
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int i=0;
        for (String word:queries) {
            boolean flag = true;
            i=0;
            for (int j=0;j<word.length();j++) {
                char c = word.charAt(j);
                if ( i < pattern.length() && (c == pattern.charAt(i)) ) {
                    i++;
                } else if (Character.isUpperCase(c)) {
                    flag = false;
                }
            }
            if (i == pattern.length() && flag == true) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }
}


class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        int max=Integer.MIN_VALUE;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            if(!m.containsKey(nums[i])) {
                m.put(nums[i], 1);
            }else {
                m.put(nums[i], m.get(nums[i])+1);
            }
            max = Math.max(max, m.get(nums[i]));
        }
        System.out.println(m);
        System.out.println(max);
        List<List<Integer>> finallist= new ArrayList<>(max+1);
        for(int s= 0; s<max+1; s++) {
            finallist.add(new ArrayList<>());
        }
        for (int key:m.keySet()) {
            finallist.get(m.get(key)).add(key);
        }

        while(k>0) {
            List<Integer> temp = finallist.get(max);
            if (temp != null) {
                for (int j=0;j<temp.size();j++) {
                    result[k-1] = temp.get(j);
                    k--;
                }
            }
            max= max-1;
        }
        return result;
    }
}
