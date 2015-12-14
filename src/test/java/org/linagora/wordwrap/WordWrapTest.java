package org.linagora.wordwrap;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class WordWrapTest {

    /**
     *   Word Wrap: (in word processing) a feature that automatically moves a word that is too long to fit on a line to the beginning of the next line.
     *   
     */

    private WordWrap wordWrap;

    @Before
    public void setup() {
        wordWrap = new WordWrap();
    }

    @Test
    public void shouldIgnoreEmptyLine() {
        // given 
        String line = "";
        int lineLength = 42;
        // when
        String result = wordWrap.wrap(line, lineLength);
        // then
        assertThat(result).isEqualTo("");
    }

    @Test
    public void shouldNotWrapLineShorterThanLineLength() {
        // given 
        String line = "word";
        int lineLength = 42;
        // when
        String result = wordWrap.wrap(line, lineLength);
        // then
        assertThat(result).isEqualTo("word");
    }

    @Test
    public void shouldBreakLineLongerThanLineLengthAtLineLength() {
        // given
        String line = "word";
        int lineLength = 2;
        // when
        String result = wordWrap.wrap(line, lineLength);
        // then
        assertThat(result).isEqualTo("wo\nrd");
    }

    @Test
    public void shouldBreakLineLongerThanTwoLineLengthsTwice() {
        // given
        String line = "longword";
        int lineLength = 3;
        // when
        String result = wordWrap.wrap(line, lineLength);
        // then
        assertThat(result).isEqualTo("lon\ngwo\nrd");
    }

    @Test
    public void shouldBreakLineLongerThanThreeLineLengthsTrice() {
        // given
        String line = "longword";
        int lineLength = 2;
        // when
        String result = wordWrap.wrap(line, lineLength);
        // then
        assertThat(result).isEqualTo("lo\nng\nwo\nrd");
    }

    @Test
    public void shouldBreakLineAtBlankAtLineLength() {
        // given
        String line = "word word";
        int lineLength = 5;
        // when
        String result = wordWrap.wrap(line, lineLength);
        // then
        assertThat(result).isEqualTo("word\nword");
    }

    @Test
    public void shouldBreakLineAtLastBlankBeforeLineLength() {
        // given
        String line = "word word word";
        int lineLength = 10;
        // when
        String result = wordWrap.wrap(line, lineLength);
        // then
        assertThat(result).isEqualTo("word word\nword");
    }
}
