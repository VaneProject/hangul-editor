package com.vane.hanguleditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class HangulEditor {
    private final static int HANGUL_START = 44032;
    private final static int HANGUL_END = 55204;

    // 한글인지 확인하는 메소드
    public static boolean isOnlyHangul(String words) {
        for (char word : words.toCharArray())
            if (! isHangul(word))
                return false;
        return true;
    }

    // 한글이 포함되어 있는지 확인하는 메소드
    public static boolean isInOnlyHangul(String words) {
        for (char word : words.toCharArray())
            if (isHangul(word))
                return true;
        return false;
    }

    // check is Hangul
    public static boolean isHangul(char word) {
        return ('ㄱ' <= word && word <= 'ㅣ') || ('가' <= word && word <= '힣');
    }

    // 한글 램던 함수
    public static String randomHangul(int len) {
        Random random = new Random();
        StringBuilder total = new StringBuilder();
        for (int i = 0; i < len; i++)
            total.append((char) random.nextInt(HANGUL_START, HANGUL_END));
        return total.toString();
    }

    public static String randomHangul() {
        return randomHangul(16);
    }

    public static HangulSplitItem splitHangul(char word) {
        return new HangulSplitItem(word);
    }

    public static List<HangulSplitItem> splitHangul(String words) {
        List<HangulSplitItem> list = new ArrayList<>();
        for (char word : words.toCharArray())
            list.add(new HangulSplitItem(word));
        return list;
    }
}
