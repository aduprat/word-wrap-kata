package org.linagora.wordwrap;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class WordWrap {

    private static final String SPACE = " ";
    private static final String SEPARATOR = "\n";
    private static final String EMPTY_STRING = "";

    public String wrap(String line, int lineLength) {
        if (line == null || line.isEmpty()) {
            return EMPTY_STRING;
        }
        if (line.length() < lineLength) {
            return line;
        }
        return StringFixedLengthStream
                .from(line, lineLength)
                .toStream()
                .reduce(new BinaryOperator<String>() {

                    public String apply(String first, String second) {
                        return new StringJoiner(SEPARATOR)
                                .add(first)
                                .add(second)
                                .toString();
                    }
                    
                })
                .get();
    }
    
    private static class StringFixedLengthStream {

        public static StringFixedLengthStream from(String line, int lineLength) {
            return new StringFixedLengthStream(line, lineLength);
        }

        private final String line;
        private final int lineLength;

        private StringFixedLengthStream(String line, int lineLength) {
            this.line = line;
            this.lineLength = lineLength;
        }

        public Stream<String> toStream() {
            List<String> list = new ArrayList<String>();
            int numberOfTokens = line.length() / lineLength + 1;
            for (int i = 0; i < numberOfTokens; i++) {
                String tokenForIndex = tokenForIndex(i);
                if (!tokenForIndex.isEmpty()) {
                    list.add(tokenForIndex);
                }
            }
            return list.stream();
        }
        
        private String tokenForIndex(int index) {
            int start = index * lineLength;
            int end = ((start + lineLength) > line.length()) ? line.length() : start + lineLength;
            return line.substring(start, end).trim();
        }
    }
}
