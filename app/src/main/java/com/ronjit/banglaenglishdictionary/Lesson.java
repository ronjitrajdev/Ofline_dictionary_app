package com.ronjit.banglaenglishdictionary;

public class Lesson {
    private String section, content, example;

    public Lesson(String section, String content, String example) {
        this.section = section;
        this.content = content;
        this.example = example;
    }

    public String getSection() { return section; }
    public String getContent() { return content; }
    public String getExample() { return example; }
}
