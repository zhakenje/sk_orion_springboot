package kz.orion.sk_orion_springboot.utils;

public enum MenuAdmin {
    admin_home_view("МОЙ КАБИНЕТ"),
    admin_employees_view("СОТРУДНИКИ"),
    admin_visitor_view("ПОСЕТИТЕЛИ"),
    admin_graph_view("ГРАФИК ПОСЕЩЕНИЙ"),
    admin_schedule_view ("РАСПИСАНИЕ ГРУПП"),
    admin_groups_view("ГРУППЫ"),
    admin_report_view("ОТЧЕТЫ"),
    admin_qrRead_view("Сканер QR Code");

    private String title;

    MenuAdmin(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFxml(){
        return String.format("fxml/admin/%s.fxml", name());
    }
}
