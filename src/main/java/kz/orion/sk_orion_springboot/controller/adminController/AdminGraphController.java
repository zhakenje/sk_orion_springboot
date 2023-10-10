package kz.orion.sk_orion_springboot.controller.adminController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.model.entity.Statistic;
import kz.orion.sk_orion_springboot.model.entity.enums.*;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.bytedeco.opencv.presets.opencv_core;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.text.DateFormatSymbols;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@Component
@RequiredArgsConstructor
public class AdminGraphController extends AbstractController {

    @FXML
    private AnchorPane anchorPaneDay;

    @FXML
    private AnchorPane anchorPaneMonth;

    @FXML
    private AnchorPane anchorPaneYear;

    @FXML
    private StackedBarChart<String, Integer> barChart;

    @FXML
    private StackedBarChart<String, Integer> barChart1;

    @FXML
    private StackedBarChart<String, Integer> barChart11;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private CategoryAxis categoryAxis1;

    @FXML
    private NumberAxis numberAxis1;

    @FXML
    private CategoryAxis categoryAxis11;

    @FXML
    private NumberAxis numberAxis11;

    @FXML
    private ComboBox<Months> monthComboBox = new ComboBox<>();

    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private ComboBox<TypeGraph> typeGraph;

    @FXML
    private ComboBox<String> dayComboBox;

    @FXML
    private ComboBox<Years_gr> yearComboBox;

    private final int SIZE_TIMES = 12;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    private ObservableList<String> dayNames = FXCollections.observableArrayList();

    private ObservableList<String> dayNamesForTimeClasses = FXCollections.observableArrayList();

    private ObservableList<String> timeClasses = FXCollections.observableArrayList();

    private final StatisticService statisticService;

    @FXML
    private void initialize() {

        monthComboBox.getItems().clear();
        monthComboBox.setItems(FXCollections.observableArrayList(Months.values()));
        monthComboBox.getItems().setAll(Months.values());

        typeGraph.getItems().clear();
        typeGraph.setItems(FXCollections.observableArrayList(TypeGraph.values()));
        typeGraph.getItems().setAll(TypeGraph.values());

        yearComboBox.getItems().clear();
        yearComboBox.setItems(FXCollections.observableArrayList(Years_gr.values()));
        yearComboBox.getItems().setAll(Years_gr.values());

//        typeOfGroup.getItems().clear();
//        typeOfGroup.setItems(FXCollections.observableArrayList(SportsClasses.values()));
//        typeOfGroup.getItems().setAll(SportsClasses.values());
    }

    @FXML
    private void showGraph() {
        categoryAxis.getCategories().clear();
        categoryAxis.getCategories().removeAll();
        barChart.getData().clear();
        barChart.getData().removeAll();
        categoryAxis1.getCategories().clear();
        categoryAxis1.getCategories().removeAll();
        barChart1.getData().clear();
        barChart1.getData().removeAll();
        categoryAxis11.getCategories().clear();
        categoryAxis11.getCategories().removeAll();
        barChart11.getData().clear();
        barChart11.getData().removeAll();
        message.setText("");

        try {

            if (typeGraph.getValue() != null) {
                if (typeGraph.getValue() == TypeGraph.TYPE_YEAR) {
                    anchorPaneYear.setVisible(true);
                    anchorPaneMonth.setVisible(false);
                    anchorPaneDay.setVisible(false);

                    String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();

                    if (monthNames != null)
                        monthNames.clear();
                    monthNames.addAll(months);
                    categoryAxis.setCategories(monthNames);

                    if (yearComboBox.getValue() != null) {
                        setNumberOfVisitsByYear(yearComboBox.getValue());
                    } else {
                        throw new PosException("Введите год!");
                    }
                } else if (typeGraph.getValue() == TypeGraph.TYPE_MONTH) {
                    if (monthComboBox.getValue() != null && yearComboBox.getValue() != null) {
                        anchorPaneYear.setVisible(false);
                        anchorPaneMonth.setVisible(true);
                        anchorPaneDay.setVisible(false);

                        YearMonth yearMonth = YearMonth.of(Integer.parseInt(yearComboBox.getValue().getStr())
                                , monthComboBox.getValue().getId());
                        int max = yearMonth.lengthOfMonth();
                        String[] daysOfMonths = new String[max];
                        for (int i = 0; i < max; i++) {
                            daysOfMonths[i] = Integer.toString(i + 1);
                        }
                        if (dayNames != null)
                            dayNames.clear();
                        dayNames.addAll(daysOfMonths);
                        categoryAxis1.setCategories(dayNames);

                        setNumberOfVisitsByYearAndMonth(yearComboBox.getValue()
                                , monthComboBox.getValue(), max);
                    } else {
                        throw new PosException("Выберите месяц или год! ");
                    }
                } else if (typeGraph.getValue() == TypeGraph.TYPE_DAY) {
                    if (monthComboBox.getValue() != null && yearComboBox.getValue() != null
                            && dayComboBox.getValue() != null) {
                        anchorPaneYear.setVisible(false);
                        anchorPaneMonth.setVisible(false);
                        anchorPaneDay.setVisible(true);

                        String[] times = new String[SIZE_TIMES];
                        TimeClass[] timeClassEnum = TimeClass.values();
                        int i = 0;
                        for (TimeClass t : timeClassEnum) {
                            times[i++] = t.getStr();
                        }
                        timeClasses.addAll(times);
                        categoryAxis11.setCategories(timeClasses);

                        setNumberOfVisitsTimeClasses(yearComboBox.getValue()
                                , monthComboBox.getValue(), dayComboBox.getValue());
                    } else {
                        throw new PosException("Выберите год, месяц, день!");
                    }

                }
            } else {
                throw new PosException("Выберите тип графика!");
            }
        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectTypeGraph(ActionEvent event) {
        if (typeGraph.getValue() == TypeGraph.TYPE_YEAR) {
            monthComboBox.setDisable(true);
            dayComboBox.setDisable(true);
        } else if (typeGraph.getValue() == TypeGraph.TYPE_MONTH) {
            monthComboBox.setDisable(false);
            dayComboBox.setDisable(true);
        } else if (typeGraph.getValue() == TypeGraph.TYPE_DAY) {
            monthComboBox.setDisable(false);
            dayComboBox.setDisable(false);
        }
    }

    @FXML
    private void setItemsDayComboBox(ActionEvent event) {
        try {
            if (typeGraph.getValue() == TypeGraph.TYPE_DAY) {
                if (yearComboBox.getValue() != null && monthComboBox.getValue() != null) {
                    YearMonth yearMonth = YearMonth.of(Integer.parseInt(yearComboBox.getValue().getStr())
                            , monthComboBox.getValue().getId());
                    int max = yearMonth.lengthOfMonth();
                    String[] daysOfMonths = new String[max];
                    for (int i = 0; i < max; i++) {
                        daysOfMonths[i] = Integer.toString(i + 1);
                    }
                    if (dayNamesForTimeClasses != null)
                        dayNamesForTimeClasses.clear();
                    dayNamesForTimeClasses.addAll(daysOfMonths);

//                dayComboBox.getItems().clear();
                    dayComboBox.setItems(dayNamesForTimeClasses);
                    dayComboBox.getItems().setAll(daysOfMonths);
                } else {
                    throw new PosException("Не указан год или месяц!");
                }
            }
        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setNumberOfVisitsByYear(Years_gr years_gr) {
        int[] monthCounterPool = new int[12];
        int[] monthCounterGym = new int[12];

        for (Statistic stat : groupByYearVisits(years_gr)) {
            if ((stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_GYM) ||
                    (stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_GYM)) {
                int month = stat.getDate().getMonthValue() - 1;
                monthCounterGym[month]++;
            } else if ((stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_POOL) ||
                    (stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_POOL)) {
                int month = stat.getDate().getMonthValue() - 1;
                monthCounterPool[month]++;
            }
        }

        barChart.getData().addAll(createSeries(monthNames, monthCounterPool, "БАССЕЙН", "#1E90FF;")
                , (createSeries(monthNames, monthCounterGym, "ТРЕНАЖЕРНЫЙ ЗАЛ", "#FF4500;")));
//        barChart.getData().get(0).getData().forEach((item) -> {
//            item.getNode().setStyle("-fx-background-color: #1E90FF");
//        });
//        barChart.getData().get(1).getData().forEach((item) -> {
//            item.getNode().setStyle("-fx-background-color: #FF4500");
//        });
    }

    private void setNumberOfVisitsByYearAndMonth(Years_gr years_gr, Months months, int max) {
        int[] daysCounterPool = new int[max];
        int[] daysCounterGym = new int[max];

        for (Statistic stat : groupByMonths(years_gr, months)) {
            if ((stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_GYM) ||
                    (stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_GYM)) {
                int day = stat.getDate().getDayOfMonth() - 1;
                daysCounterGym[day]++;
            } else if ((stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_POOL) ||
                    (stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_POOL)) {
                int day = stat.getDate().getDayOfMonth() - 1;
                daysCounterPool[day]++;
            }
        }

        barChart1.getData().addAll((createSeries(dayNames, daysCounterPool, "БАССЕЙН", "#1E90FF"))
                , createSeries(dayNames, daysCounterGym, "ТРЕНАЖЕРНЫЙ ЗАЛ", "#FF4500"));

//        barChart.getData().get(0).getData().forEach((item) -> {
//            item.getNode().setStyle("-fx-background-color: #1E90FF");
//        });
//        barChart.getData().get(1).getData().forEach((item) -> {
//            item.getNode().setStyle("-fx-background-color: #FF4500");
//        });


    }

    private void setNumberOfVisitsTimeClasses(Years_gr years_gr, Months months, String day) {
        int[] timeClassesCounterPool = new int[SIZE_TIMES];
        int[] timeClassesCounterGym = new int[SIZE_TIMES];
        String[] times = {"08:30", "09:30", "10:30", "11:30", "12:30", "13:30", "14:30"
                , "15:30", "16:30", "17:30", "18:30", "19:30"};

        for (Statistic stat : groupByTimeClasses(years_gr, months, day)) {
            if ((stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_GYM) ||
                    (stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_GYM)) {
                for (int i = 0; i < SIZE_TIMES; i++) {
//                    String s1 = null;
//                    s1 = String.valueOf("0" + stat.getDate().getHour()) + ":" + "0" +
//                            String.valueOf(stat.getDate().getMinute());
                    LocalTime lt1 = stat.getDate().toLocalTime();
                    LocalTime lt2 = LocalTime.parse(times[i]);
                    if (Math.abs(ChronoUnit.MINUTES.between(lt1, lt2)) < 60) {
                        timeClassesCounterGym[i]++;
                        break;
                    } else {
                        continue;
                    }
                }

            } else if ((stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_MONTH_POOL) ||
                    (stat.getTypeSubscription() == TypeSubscription.SUBSCRIPTION_DAY_POOL)) {
                for (int i = 0; i < SIZE_TIMES; i++) {
//                    String s1 = null;
//                    s1 = String.valueOf("0" + stat.getDate().getHour()) + ":" + "0" +
//                            String.valueOf(stat.getDate().getMinute());
                    LocalTime lt1 = stat.getDate().toLocalTime();
                    LocalTime lt2 = LocalTime.parse(times[i]);
                    if (Math.abs(ChronoUnit.MINUTES.between(lt1, lt2)) < 60) {
                        timeClassesCounterPool[i]++;
                        break;
                    } else {
                        continue;
                    }
                }
            }
        }

        barChart11.getData().addAll(createSeries(timeClasses, timeClassesCounterPool, "БАССЕЙН", "#1E90FF")
                , createSeries(timeClasses, timeClassesCounterGym, "ТРЕНАЖЕРНЫЙ ЗАЛ", "#FF4500"));

    }


    private XYChart.Series<String, Integer> createSeries(ObservableList<String> names, int[] counter
            , String name, String color) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(name);
        series.getData().forEach(data -> data.getNode().setStyle("-fx-bar-fill: " + color));
        for (int i = 0; i < counter.length; i++) {
            series.getData().add(new XYChart.Data<>(names.get(i), counter[i]));
        }
        return series;
    }

    private List<Statistic> groupByYearVisits(Years_gr years_gr) {
        List<Statistic> stList = new ArrayList<>();
        for (Statistic st : statisticService.findAll()) {
            if (Integer.toString(st.getDate().getYear()).equals(years_gr.toString())) {
                stList.add(st);
            }
        }

        return stList;
    }

    private List<Statistic> groupByMonths(Years_gr years_gr, Months months) {
        List<Statistic> stList = new ArrayList<>();
        for (Statistic st : statisticService.findAll()) {
            if (Integer.toString(st.getDate().getYear()).equals(years_gr.toString()) &&
                    Integer.toString(st.getDate().getMonthValue()).equals(Integer.toString(months.getId()))) {
                stList.add(st);
            }
        }

        return stList;
    }

    private List<Statistic> groupByTimeClasses(Years_gr years_gr, Months months, String day) {
        List<Statistic> stList = new ArrayList<>();
        for (Statistic st : statisticService.findAll()) {
            if (Integer.toString(st.getDate().getYear()).equals(years_gr.toString()) &&
                    Integer.toString(st.getDate().getMonthValue()).equals(Integer.toString(months.getId())) &&
                    Integer.toString(st.getDate().getDayOfMonth()).equals(day)) {
                stList.add(st);
            }
        }

        return stList;
    }


}
