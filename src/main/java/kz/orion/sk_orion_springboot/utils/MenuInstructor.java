package kz.orion.sk_orion_springboot.utils;

public enum MenuInstructor {
    instructor_home_view("МОЙ КАБИНЕТ"),
    instructor_myGroups_view("МОИ ГРУППЫ"),
    instructor_scheduler_view("РАСПИСАНИЕ ГРУПП"),
    instructor_groups_view("ГРУППЫ"),
    instructor_aboutUs_view("СПОРТ КОМПЛЕКС <ОРИОН>");

    private String title;

    MenuInstructor(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFxml(){
        return String.format("fxml/instructor/%s.fxml", name());
    }
}
