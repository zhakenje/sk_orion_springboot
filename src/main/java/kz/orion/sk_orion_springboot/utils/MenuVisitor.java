package kz.orion.sk_orion_springboot.utils;

public enum MenuVisitor {
    visitor_home_view("МОЙ КАБИНЕТ"),
    visitor_mySubscription_view("МОИ АБОНЕМЕНТЫ"),
    visitor_scheduler_view("РАСПИСАНИЕ ГРУПП"),
    visitor_groups_view("ГРУППЫ"),
    visitor_buy_subscription_view("ПРИОБРЕТЕНИЕ АБОНЕМЕНТА"),
    visitor_aboutUs_view("СПОРТ КОМПЛЕКС <ОРИОН>");

    private String title;

    MenuVisitor(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFxml(){
        return String.format("fxml/visitor/%s.fxml", name());
    }
}
