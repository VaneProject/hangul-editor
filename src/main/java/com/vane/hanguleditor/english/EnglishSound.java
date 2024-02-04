package com.vane.hanguleditor.english;

import com.vane.hanguleditor.HangulEditor;
import com.vane.hanguleditor.HangulSplitItem;

import java.util.Stack;

public final class EnglishSound {
    private EnglishSound() {}

    public static String engToKor(String words) {
        int i;
        // convertor eng to kor
        StringBuilder builder = new StringBuilder(words.toLowerCase());
        for (English english : EnglishRepository.engToKorList) {
            while ((i = builder.indexOf(english.eng)) != -1)
                builder.replace(i, i + english.eng.length(), english.kor);
        }
        char[] chars = builder.toString().toCharArray();
        if (builder.length() <= 1 && checkNoStackConvertor(chars))
            return builder.toString();
        // stack to word
        return korToStack(chars);
    }

    public static String ipaToKor(String words) {
        int i;
        // convertor ipa to kor
        StringBuilder builder = new StringBuilder(words.toLowerCase());
        for (English english : EnglishRepository.ipaList) {
            while ((i = builder.indexOf(english.eng)) != -1)
                builder.replace(i, i + english.eng.length(), english.kor);
        }
        char[] chars = builder.toString().toCharArray();
        if (builder.length() <= 1 && checkNoStackConvertor(chars))
            return builder.toString();
        // stack to word
        return korToStack(chars);
    }

    private static boolean checkNoStackConvertor(char[] words) {
        for (int i = 1; i < words.length; i++) {
            if (HangulEditor.isConsonant(words[i])) {
                char w = words[i-1];
                if ('가' <= w && w <= '힣' && HangulSplitItem.getThread(w) == ' ')
                    return false;
            }
        }
        return true;
    }

    private static String korToStack(char[] words) {
        Stack<HangulSplitItem> stack = new Stack<>();
        for (char word : words) {
            HangulSplitItem item = new HangulSplitItem(word);
            if (stack.isEmpty())
                stack.add(item);
            else if (item.isConsonant()) {
                HangulSplitItem previous = stack.pop();
                if (previous.getThread() == ' ') {
                    previous.setThread(item.getFirst());
                    stack.add(previous);
                } else {
                    stack.add(previous);
                    stack.add(item);
                }
            } else
                stack.add(item);
        }
        // stack to String
        StringBuilder builder = new StringBuilder();
        for (HangulSplitItem item : stack)
            builder.append(item);
        return builder.toString();
    }
}
