public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> str1 = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            str1.addLast(word.charAt(i));
        }
        return str1;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> str2 = wordToDeque(word);

        return isPalindromeHelp(str2);
    }

    private boolean isPalindromeHelp(Deque<Character> str2) {
        if (str2.size() <= 1) {
            return true;
        }

        Character front = str2.removeFirst();
        Character tail = str2.removeLast();

        return (front == tail) && isPalindromeHelp(str2);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> str3 = wordToDeque(word);

        return isPalindromeHelp(str3, cc);
    }

    private boolean isPalindromeHelp(Deque<Character> str3, CharacterComparator cc) {
        if (str3.size() <= 1) {
            return true;
        }

        Character front = str3.removeFirst();
        Character tail = str3.removeLast();

        return (cc.equalChars(front, tail) && isPalindromeHelp(str3, cc));
    }
}
