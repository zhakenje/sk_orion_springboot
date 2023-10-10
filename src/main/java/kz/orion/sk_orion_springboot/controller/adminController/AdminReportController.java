package kz.orion.sk_orion_springboot.controller.adminController;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.controller.report.Report;
import kz.orion.sk_orion_springboot.db.DBConnection;
import kz.orion.sk_orion_springboot.db.DataAccessObject;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminReportController extends AbstractController {

    private final ReportService reportService;

    private DBConnection database;

    DataAccessObject dao = new DataAccessObject();

    private Connection connect;

    private Map<String, Object> parameters;

    @FXML
    private Label message;

    @FXML
    private DatePicker selectEndDate;

    @FXML
    private DatePicker selectEndDateF;

    @FXML
    private DatePicker selectStartDate;

    @FXML
    private DatePicker selectStartDateF;

    @FXML
    private Label title;

//    @FXML
//    private void generateReport() throws JRException, FileNotFoundException {
//        reportService.exportReport("pdf");
//    }

    @FXML
    private void generateVisitListReport() {
        Date d1 = null;
        Date d2 = null;
        try {
            if (selectStartDate.getValue() != null && selectEndDate.getValue() != null) {
                if ((selectStartDate.getValue().compareTo(selectEndDate.getValue())) <= 0) {
                    LocalDate start_date = selectStartDate.getValue();
                    LocalDate end_date = selectEndDate.getValue();
                    ZonedDateTime zdt1 = start_date.atStartOfDay(ZoneId.systemDefault());
                    ZonedDateTime zdt2 = end_date.atStartOfDay(ZoneId.systemDefault());
                    d1 = Date.from(zdt1.toInstant());
                    d2 = Date.from(zdt2.toInstant());
                    printReport("visit_list_report"
                            , "report_jasper", d1, d2);
                } else {
                    throw new PosException("Некорректно выбранная дата!");
                }
            } else {
                throw new PosException("Даты не выбраны!");
            }
        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void generateFinanceReport() {
        Date d1 = null;
        Date d2 = null;

        try {
            if (selectStartDateF.getValue() != null && selectEndDateF.getValue() != null) {
                if ((selectStartDateF.getValue().compareTo(selectEndDateF.getValue())) <= 0) {
                    LocalDate start_date = selectStartDateF.getValue();
                    LocalDate end_date = selectEndDateF.getValue();
                    ZonedDateTime zdt1 = start_date.atStartOfDay(ZoneId.systemDefault());
                    ZonedDateTime zdt2 = end_date.atStartOfDay(ZoneId.systemDefault());
                    d1 = Date.from(zdt1.toInstant());
                    d2 = Date.from(zdt2.toInstant());
                    printReport("finance_report"
                            , "report_jasper", d1, d2);
                } else {
                    throw new PosException("Некорректоно выбранная дата!");
                }

            } else {
                throw new PosException("Даты не выбраны!");
            }
        } catch (PosException e) {
            message.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void printReport(String reportName, String reportJasper, Date start_date, Date end_date) {
        database = new DBConnection();
        connect = database.getConnection();
        parameters = new HashMap<String, Object>();
        parameters.put("start_date", start_date);
        parameters.put("end_date", end_date);

        Report.createReport(connect, parameters, dao.getReport(reportName
                , reportJasper));
        Report.showReport();
    }

}
