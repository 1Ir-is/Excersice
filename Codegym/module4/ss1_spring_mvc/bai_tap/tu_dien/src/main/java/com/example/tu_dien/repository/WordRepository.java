package com.example.tu_dien.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class WordRepository {
    private final Map<String, String> dictionary = new HashMap<>();

    public WordRepository() {
        dictionary.put("apple", "quả táo");
        dictionary.put("book", "cuốn sách");
        dictionary.put("cat", "con mèo");
        dictionary.put("dog", "con chó");
        dictionary.put("elephant", "con voi");
        dictionary.put("fish", "con cá");
        dictionary.put("green", "màu xanh lá");
        dictionary.put("house", "ngôi nhà");
        dictionary.put("ice", "nước đá");
        dictionary.put("juice", "nước ép");
        dictionary.put("key", "chìa khóa");
        dictionary.put("lion", "sư tử");
        dictionary.put("moon", "mặt trăng");
        dictionary.put("notebook", "quyển vở");
        dictionary.put("orange", "quả cam");
        dictionary.put("pen", "bút mực");
        dictionary.put("queen", "nữ hoàng");
        dictionary.put("river", "con sông");
        dictionary.put("sun", "mặt trời");
        dictionary.put("tree", "cái cây");
    }

    public String findMeaning(String word) {
        return dictionary.get(word.toLowerCase());
    }
}