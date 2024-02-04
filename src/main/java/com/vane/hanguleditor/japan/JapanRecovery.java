package com.vane.hanguleditor.japan;

public final class JapanRecovery {
    public static String change(String text) {
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray())
            builder.append(convertor(c));
        return builder.toString();
    }

    private static String convertor(char c) {
        String value = JapanRepository.back1.get(c);
        if (value == null) value = JapanRepository.back2.get(c);
        if (value == null) value = JapanRepository.back3.get(c);
        return (value == null) ? Character.toString(c) : value;
    }
}
