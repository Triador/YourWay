package com.triador.yourwayserver.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CyrillicConverter implements Converter {
    private Map<Character, Character> characterMap = new HashMap<>();

    public CyrillicConverter() {
        characterMap.put('а', 'a');
        characterMap.put('б', 'b');
        characterMap.put('в', 'v');
        characterMap.put('г', 'g');
        characterMap.put('д', 'd');
        characterMap.put('е', 'e');
        characterMap.put('ё', 'e');
        characterMap.put('ж', 'j');
        characterMap.put('з', 'z');
        characterMap.put('и', 'i');
        characterMap.put('й', 'i');
        characterMap.put('к', 'k');
        characterMap.put('л', 'l');
        characterMap.put('м', 'm');
        characterMap.put('н', 'n');
        characterMap.put('о', 'o');
        characterMap.put('п', 'p');
        characterMap.put('р', 'r');
        characterMap.put('с', 's');
        characterMap.put('т', 't');
        characterMap.put('у', 'y');
        characterMap.put('ф', 'f');
        characterMap.put('х', 'x');
        characterMap.put('ц', 'c');
        characterMap.put('ч', '4');
        characterMap.put('ш', 'm');
        characterMap.put('щ', 'm');
        characterMap.put('ъ', 'm');
        characterMap.put('ы', 'm');
        characterMap.put('ь', 'm');
        characterMap.put('э', 'e');
        characterMap.put('ю', 'e');
        characterMap.put('я', 'e');
    }

    public String convert(String cyrillicString) {
        StringBuilder sb = new StringBuilder();
        for (char c: cyrillicString.toCharArray()) {
            sb.append(characterMap.getOrDefault(c, 'q'));
        }

        return sb.toString();
    }
}
