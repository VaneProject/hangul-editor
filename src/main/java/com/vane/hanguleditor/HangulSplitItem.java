package com.vane.hanguleditor;

import java.util.Arrays;
import java.util.List;

public final class HangulSplitItem {
    public final static char ㄹㄱ = 'ㄺ';
    public final static char ㄹㅁ = 'ㄻ';
    public final static char ㄱㅅ = 'ㄳ';
    public final static char ㄴㅈ = 'ㄵ';
    public final static char ㄴㅎ = 'ㄶ';
    public final static char ㄹㅂ = 'ㄼ';
    public final static char ㄹㅅ = 'ㄽ';
    public final static char ㄹㅌ = 'ㄾ';
    public final static char ㄹㅍ = 'ㄿ';
    public final static char ㄹㅎ = 'ㅀ';
    public final static char ㅂㅅ = 'ㅄ';
    private final static List<Character> firstWords = Arrays.asList(
        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ',
        'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ');
    private final static List<Character> secondWords = Arrays.asList(
        'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ' ,'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ',
        'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ' ,'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ');
    private final static List<Character> threadWords = Arrays.asList(
        ' ', 'ㄱ', 'ㄲ', ㄱㅅ, 'ㄴ', ㄴㅈ, ㄴㅎ, 'ㄷ', 'ㄹ', ㄹㄱ, ㄹㅁ, ㄹㅂ, ㄹㅅ, ㄹㅌ,
        ㄹㅍ, ㄹㅎ, 'ㅁ', 'ㅂ', ㅂㅅ, 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ');

    private char first;
    private char second;
    private char thread;

    public HangulSplitItem() {
        this.first = ' ';
        this.second = ' ';
        this.thread = ' ';
    }

    public HangulSplitItem(char words) {
        int text = words - 0xAC00;
        int p1 = text / 28 / 21, p2 = text / 28 % 21, p3 = text % 28;
        this.first = 0 <= p1 && p1 < firstWords.size() ? firstWords.get(p1) : words;
        this.second = 0 <= p1 && p1 < secondWords.size() ? secondWords.get(p2) : ' ';
        this.thread = 0 <= p1 && p1 < threadWords.size() ? threadWords.get(p3) : ' ';
    }

    // 분리된 값을 다시 합치는 작업
    public char getWord() {
        int first = getFirstPos(this.first);
        int second = getSecondPos(this.second);
        int thread = getThreadPos(this.thread);
        if (-1 == (second | thread)) return this.first;
        return (char) (this.thread == ' '
            ? (first * 21 + second) * 28 + 0xAC00
            : (first * 21 + second) * 28 + thread + 0xAC00);
    }

    public static int getFirstPos(char text) {
        return firstWords.indexOf(text);
    }

    public static int getSecondPos(char text) {
        return secondWords.indexOf(text);
    }

    public static int getThreadPos(char text) {
        return threadWords.indexOf(text);
    }

    public static char getThread(char words) {
        int text = words - 0xAC00;
        int p1 = text / 28 / 21, p3 = text % 28;
        return 0 <= p1 && p1 < threadWords.size() ? threadWords.get(p3) : ' ';
    }

    public boolean isConsonant() {
        return second == ' ' && thread == ' ' && HangulEditor.isConsonant(first);
    }

    public boolean isVowels() {
        return second == ' ' && thread == ' ' && HangulEditor.isVowels(first);
    }

    // Property Getter Setter
    public void setFirst(char first) {
        this.first = first;
    }

    public void setFirst(int first) {
        this.first = firstWords.get(first);
    }

    public void setSecond(char second) {
        this.second = second;
    }

    public void setSecond(int second) {
        this.first = secondWords.get(first);
    }

    public void setThread(char thread) {
        this.thread = thread;
    }

    public void setThread(int thread) {
        this.thread = threadWords.get(thread);
    }

    // Getter
    public char getFirst() {
        return first;
    }

    public char getSecond() {
        return second;
    }

    public char getThread() {
        return thread;
    }

    @Override
    public String toString() {
        return Character.toString(getWord());
    }
}
