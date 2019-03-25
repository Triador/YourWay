package com.triador.yourwayserver.utils;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ChitaiGorodParserTest {

    @Autowired
    ChitaiGorodParser cgr;

    @Test
    public void testParse() {
        List<String> urls = new ArrayList<>();
        urls.add("https://www.chitai-gorod.ru/catalog/books/9657/");

        cgr.parse(urls);
    }
}