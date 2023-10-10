package kz.orion.sk_orion_springboot.model.service;

import kz.orion.sk_orion_springboot.model.entity.Statistic;
import kz.orion.sk_orion_springboot.model.repository.CustomRepository;
import kz.orion.sk_orion_springboot.model.repository.StatisticRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private final StatisticRepository statisticRepository;

    public ReportService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\Projects_FX\\sk_orion_springboot\\src\\main\\resources\\reports";
        List<Statistic[]> statistics = statisticRepository.getCombinedTable();

        File file = ResourceUtils.getFile("D:\\Projects_FX\\sk_orion_springboot\\src\\main\\resources\\jrxml\\visit_list_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(statistics);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("created by", "Java AJSA");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            String reportFileName = UUID.randomUUID().toString();
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\" + reportFileName + ".html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            String reportFileName = UUID.randomUUID().toString();
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\" + reportFileName + ".pdf");
        }
        return "report generated in path : " + path;
    }
}
