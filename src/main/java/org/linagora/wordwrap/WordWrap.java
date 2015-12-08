package org.linagora.wordwrap;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;

public class WordWrap {

    private static final String SPACE = " ";
    private static final String SEPARATOR = "\n";
    private static final String EMPTY_STRING = "";

    public String wrap(String line, int lineLength) {
        if (Strings.isNullOrEmpty(line)) {
            return EMPTY_STRING;
        }
        if (line.length() < lineLength) {
            return line;
        }
        return Joiner.on(SEPARATOR)
            .join(removeExtraSpaces(line, lineLength));
    }

    private Iterable<String> splitLine(String line, int lineLength) {
        return Splitter.fixedLength(lineLength).split(line);
    }

    private FluentIterable<String> removeExtraSpaces(String line, int lineLength) {
        return FluentIterable.from(splitLine(line, lineLength))
                .transform(new Function<String, String>() {

                    public String apply(String input) {
                        if (input.endsWith(SPACE)) {
                            return input.substring(0, input.length() - 1);
                        }
                        return input;
                    }
                });
    }
}
