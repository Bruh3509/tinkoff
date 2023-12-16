package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

public class HackersNewsTest {
    @Test
    @DisplayName("HackerNews Test")
    void testHackerNews() {
        // act
        long[] resultArr = HackerNews.hackerNewsTopStories();

        // assert
        System.out.println(Arrays.toString(resultArr));
        System.out.println(resultArr.length);
    }

    @Test
    @DisplayName("Hacker News Name Test")
    void testHackerNewsName() {
        // arrange
        long id1 = 37570037;
        long id2 = 38289935;

        // act
        var result1 = HackerNews.news(id1);
        var result2 = HackerNews.news(id2);

        // assert
        assertThat(result1)
            .isEqualTo("JDK 21 Release Notes");
        assertThat(result2)
            .isEqualTo("ChatGPT Plus subscriptions are being listed on eBay after OpenAI blocked signups");
    }
}
