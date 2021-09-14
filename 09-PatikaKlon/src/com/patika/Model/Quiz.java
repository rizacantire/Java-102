package com.patika.Model;

public class Quiz {
    private int id;
    private int course_content_id;
    private String quesiton1;
    private String quesiton2;
    private String quesiton3;
    private String quesiton4;
    private String answer;

    private CourseContent content;

    public Quiz(int id, int course_content_id, String quesiton1, String quesiton2, String quesiton3, String quesiton4, String answer) {
        this.id = id;
        this.course_content_id = course_content_id;
        this.quesiton1 = quesiton1;
        this.quesiton2 = quesiton2;
        this.quesiton3 = quesiton3;
        this.quesiton4 = quesiton4;
        this.answer = answer;
        this.content = CourseContent.getFetch(course_content_id);
    }
}
