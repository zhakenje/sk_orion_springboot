package kz.orion.sk_orion_springboot.controller.adminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import kz.orion.sk_orion_springboot.controller.AbstractController;
import kz.orion.sk_orion_springboot.model.entity.Group;
import kz.orion.sk_orion_springboot.model.entity.enums.DaysOfWeek;
import kz.orion.sk_orion_springboot.model.entity.enums.TimeClass;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeGroup;
import kz.orion.sk_orion_springboot.model.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.function.Supplier;

@Controller
@RequiredArgsConstructor
public class AdminScheduleController extends AbstractController {

    @FXML
    private AnchorPane anchorPaneGym;

    @FXML
    private AnchorPane anchorPaneGymKids;

    @FXML
    private AnchorPane anchorPanePool;

    @FXML
    private AnchorPane anchorPanePoolKids;

    @FXML
    private TextArea fri1030;

    @FXML
    private TextArea fri1030g;

    @FXML
    private TextArea fri1030gk;

    @FXML
    private TextArea fri1030pk;

    @FXML
    private TextArea fri1130;

    @FXML
    private TextArea fri1130g;

    @FXML
    private TextArea fri1130gk;

    @FXML
    private TextArea fri1130pk;

    @FXML
    private TextArea fri1230;

    @FXML
    private TextArea fri1230g;

    @FXML
    private TextArea fri1230gk;

    @FXML
    private TextArea fri1230pk;

    @FXML
    private TextArea fri1330;

    @FXML
    private TextArea fri1330g;

    @FXML
    private TextArea fri1330gk;

    @FXML
    private TextArea fri1330pk;

    @FXML
    private TextArea fri1430;

    @FXML
    private TextArea fri1430g;

    @FXML
    private TextArea fri1430gk;

    @FXML
    private TextArea fri1430pk;

    @FXML
    private TextArea fri1530;

    @FXML
    private TextArea fri1530g;

    @FXML
    private TextArea fri1530gk;

    @FXML
    private TextArea fri1530pk;

    @FXML
    private TextArea fri1630;

    @FXML
    private TextArea fri1630g;

    @FXML
    private TextArea fri1630gk;

    @FXML
    private TextArea fri1630pk;

    @FXML
    private TextArea fri1730;

    @FXML
    private TextArea fri1730g;

    @FXML
    private TextArea fri1730gk;

    @FXML
    private TextArea fri1730pk;

    @FXML
    private TextArea fri1830;

    @FXML
    private TextArea fri1830g;

    @FXML
    private TextArea fri1830gk;

    @FXML
    private TextArea fri1830pk;

    @FXML
    private TextArea fri1930;

    @FXML
    private TextArea fri1930g;

    @FXML
    private TextArea fri1930gk;

    @FXML
    private TextArea fri1930pk;

    @FXML
    private TextArea fri830;

    @FXML
    private TextArea fri830g;

    @FXML
    private TextArea fri830gk;

    @FXML
    private TextArea fri830pk;

    @FXML
    private TextArea fri930;

    @FXML
    private TextArea fri930g;

    @FXML
    private TextArea fri930gk;

    @FXML
    private TextArea fri930pk;

    @FXML
    private TextArea mn1030;

    @FXML
    private TextArea mn1030g;

    @FXML
    private TextArea mn1030gk;

    @FXML
    private TextArea mn1030pk;

    @FXML
    private TextArea mn1130;

    @FXML
    private TextArea mn1130g;

    @FXML
    private TextArea mn1130gk;

    @FXML
    private TextArea mn1130pk;

    @FXML
    private TextArea mn1230;

    @FXML
    private TextArea mn1230g;

    @FXML
    private TextArea mn1230gk;

    @FXML
    private TextArea mn1230pk;

    @FXML
    private TextArea mn1330;

    @FXML
    private TextArea mn1330g;

    @FXML
    private TextArea mn1330gk;

    @FXML
    private TextArea mn1330pk;

    @FXML
    private TextArea mn1430;

    @FXML
    private TextArea mn1430g;

    @FXML
    private TextArea mn1430gk;

    @FXML
    private TextArea mn1430pk;

    @FXML
    private TextArea mn1530;

    @FXML
    private TextArea mn1530g;

    @FXML
    private TextArea mn1530gk;

    @FXML
    private TextArea mn1530pk;

    @FXML
    private TextArea mn1630;

    @FXML
    private TextArea mn1630g;

    @FXML
    private TextArea mn1630gk;

    @FXML
    private TextArea mn1630pk;

    @FXML
    private TextArea mn1730;

    @FXML
    private TextArea mn1730g;

    @FXML
    private TextArea mn1730gk;

    @FXML
    private TextArea mn1730pk;

    @FXML
    private TextArea mn1830;

    @FXML
    private TextArea mn1830g;

    @FXML
    private TextArea mn1830gk;

    @FXML
    private TextArea mn1830pk;

    @FXML
    private TextArea mn1930;

    @FXML
    private TextArea mn1930g;

    @FXML
    private TextArea mn1930gk;

    @FXML
    private TextArea mn1930pk;

    @FXML
    private TextArea mn830;

    @FXML
    private TextArea mn830g;

    @FXML
    private TextArea mn830gk;

    @FXML
    private TextArea mn830pk;

    @FXML
    private TextArea mn930;

    @FXML
    private TextArea mn930g;

    @FXML
    private TextArea mn930gk;

    @FXML
    private TextArea mn930pk;

    @FXML
    private TextArea sat1030;

    @FXML
    private TextArea sat1030g;

    @FXML
    private TextArea sat1030gk;

    @FXML
    private TextArea sat1030pk;

    @FXML
    private TextArea sat1130;

    @FXML
    private TextArea sat1130g;

    @FXML
    private TextArea sat1130gk;

    @FXML
    private TextArea sat1130pk;

    @FXML
    private TextArea sat1230;

    @FXML
    private TextArea sat1230g;

    @FXML
    private TextArea sat1230gk;

    @FXML
    private TextArea sat1230pk;

    @FXML
    private TextArea sat1330;

    @FXML
    private TextArea sat1330g;

    @FXML
    private TextArea sat1330gk;

    @FXML
    private TextArea sat1330pk;

    @FXML
    private TextArea sat1430;

    @FXML
    private TextArea sat1430g;

    @FXML
    private TextArea sat1430gk;

    @FXML
    private TextArea sat1430pk;

    @FXML
    private TextArea sat1530;

    @FXML
    private TextArea sat1530g;

    @FXML
    private TextArea sat1530gk;

    @FXML
    private TextArea sat1530pk;

    @FXML
    private TextArea sat1630;

    @FXML
    private TextArea sat1630g;

    @FXML
    private TextArea sat1630gk;

    @FXML
    private TextArea sat1630pk;

    @FXML
    private TextArea sat1730;

    @FXML
    private TextArea sat1730g;

    @FXML
    private TextArea sat1730gk;

    @FXML
    private TextArea sat1730pk;

    @FXML
    private TextArea sat1830;

    @FXML
    private TextArea sat1830g;

    @FXML
    private TextArea sat1830gk;

    @FXML
    private TextArea sat1830pk;

    @FXML
    private TextArea sat1930;

    @FXML
    private TextArea sat1930g;

    @FXML
    private TextArea sat1930gk;

    @FXML
    private TextArea sat1930pk;

    @FXML
    private TextArea sat830;

    @FXML
    private TextArea sat830g;

    @FXML
    private TextArea sat830gk;

    @FXML
    private TextArea sat830pk;

    @FXML
    private TextArea sat930;

    @FXML
    private TextArea sat930g;

    @FXML
    private TextArea sat930gk;

    @FXML
    private TextArea sat930pk;

    @FXML
    private TextArea thu1030;

    @FXML
    private TextArea thu1030g;

    @FXML
    private TextArea thu1030gk;

    @FXML
    private TextArea thu1030pk;

    @FXML
    private TextArea thu1130;

    @FXML
    private TextArea thu1130g;

    @FXML
    private TextArea thu1130gk;

    @FXML
    private TextArea thu1130pk;

    @FXML
    private TextArea thu1230;

    @FXML
    private TextArea thu1230g;

    @FXML
    private TextArea thu1230gk;

    @FXML
    private TextArea thu1230pk;

    @FXML
    private TextArea thu1330;

    @FXML
    private TextArea thu1330g;

    @FXML
    private TextArea thu1330gk;

    @FXML
    private TextArea thu1330pk;

    @FXML
    private TextArea thu1430;

    @FXML
    private TextArea thu1430g;

    @FXML
    private TextArea thu1430gk;

    @FXML
    private TextArea thu1430pk;

    @FXML
    private TextArea thu1530;

    @FXML
    private TextArea thu1530g;

    @FXML
    private TextArea thu1530gk;

    @FXML
    private TextArea thu1530pk;

    @FXML
    private TextArea thu1630;

    @FXML
    private TextArea thu1630g;

    @FXML
    private TextArea thu1630gk;

    @FXML
    private TextArea thu1630pk;

    @FXML
    private TextArea thu1730;

    @FXML
    private TextArea thu1730g;

    @FXML
    private TextArea thu1730gk;

    @FXML
    private TextArea thu1730pk;

    @FXML
    private TextArea thu1830;

    @FXML
    private TextArea thu1830g;

    @FXML
    private TextArea thu1830gk;

    @FXML
    private TextArea thu1830pk;

    @FXML
    private TextArea thu1930;

    @FXML
    private TextArea thu1930g;

    @FXML
    private TextArea thu1930gk;

    @FXML
    private TextArea thu1930pk;

    @FXML
    private TextArea thu830;

    @FXML
    private TextArea thu830g;

    @FXML
    private TextArea thu830gk;

    @FXML
    private TextArea thu830pk;

    @FXML
    private TextArea thu930;

    @FXML
    private TextArea thu930g;

    @FXML
    private TextArea thu930gk;

    @FXML
    private TextArea thu930pk;

    @FXML
    private TextArea tu1030;

    @FXML
    private TextArea tu1030g;

    @FXML
    private TextArea tu1030gk;

    @FXML
    private TextArea tu1030pk;

    @FXML
    private TextArea tu1130;

    @FXML
    private TextArea tu1130g;

    @FXML
    private TextArea tu1130gk;

    @FXML
    private TextArea tu1130pk;

    @FXML
    private TextArea tu1230;

    @FXML
    private TextArea tu1230g;

    @FXML
    private TextArea tu1230gk;

    @FXML
    private TextArea tu1230pk;

    @FXML
    private TextArea tu1330;

    @FXML
    private TextArea tu1330g;

    @FXML
    private TextArea tu1330gk;

    @FXML
    private TextArea tu1330pk;

    @FXML
    private TextArea tu1430;

    @FXML
    private TextArea tu1430g;

    @FXML
    private TextArea tu1430gk;

    @FXML
    private TextArea tu1430pk;

    @FXML
    private TextArea tu1530;

    @FXML
    private TextArea tu1530g;

    @FXML
    private TextArea tu1530gk;

    @FXML
    private TextArea tu1530pk;

    @FXML
    private TextArea tu1630;

    @FXML
    private TextArea tu1630g;

    @FXML
    private TextArea tu1630gk;

    @FXML
    private TextArea tu1630pk;

    @FXML
    private TextArea tu1730;

    @FXML
    private TextArea tu1730g;

    @FXML
    private TextArea tu1730gk;

    @FXML
    private TextArea tu1730pk;

    @FXML
    private TextArea tu1830;

    @FXML
    private TextArea tu1830g;

    @FXML
    private TextArea tu1830gk;

    @FXML
    private TextArea tu1830pk;

    @FXML
    private TextArea tu1930;

    @FXML
    private TextArea tu1930g;

    @FXML
    private TextArea tu1930gk;

    @FXML
    private TextArea tu1930pk;

    @FXML
    private TextArea tu830;

    @FXML
    private TextArea tu830g;

    @FXML
    private TextArea tu830gk;

    @FXML
    private TextArea tu830pk;

    @FXML
    private TextArea tu930;

    @FXML
    private TextArea tu930g;

    @FXML
    private TextArea tu930gk;

    @FXML
    private TextArea tu930pk;

    @FXML
    private TextArea wed1030;

    @FXML
    private TextArea wed1030g;

    @FXML
    private TextArea wed1030gk;

    @FXML
    private TextArea wed1030pk;

    @FXML
    private TextArea wed1130;

    @FXML
    private TextArea wed1130g;

    @FXML
    private TextArea wed1130gk;

    @FXML
    private TextArea wed1130pk;

    @FXML
    private TextArea wed1230;

    @FXML
    private TextArea wed1230g;

    @FXML
    private TextArea wed1230gk;

    @FXML
    private TextArea wed1230pk;

    @FXML
    private TextArea wed1330;

    @FXML
    private TextArea wed1330g;

    @FXML
    private TextArea wed1330gk;

    @FXML
    private TextArea wed1330pk;

    @FXML
    private TextArea wed1430;

    @FXML
    private TextArea wed1430g;

    @FXML
    private TextArea wed1430gk;

    @FXML
    private TextArea wed1430pk;

    @FXML
    private TextArea wed1530;

    @FXML
    private TextArea wed1530g;

    @FXML
    private TextArea wed1530gk;

    @FXML
    private TextArea wed1530pk;

    @FXML
    private TextArea wed1630;

    @FXML
    private TextArea wed1630g;

    @FXML
    private TextArea wed1630gk;

    @FXML
    private TextArea wed1630pk;

    @FXML
    private TextArea wed1730;

    @FXML
    private TextArea wed1730g;

    @FXML
    private TextArea wed1730gk;

    @FXML
    private TextArea wed1730pk;

    @FXML
    private TextArea wed1830;

    @FXML
    private TextArea wed1830g;

    @FXML
    private TextArea wed1830gk;

    @FXML
    private TextArea wed1830pk;

    @FXML
    private TextArea wed1930;

    @FXML
    private TextArea wed1930g;

    @FXML
    private TextArea wed1930gk;

    @FXML
    private TextArea wed1930pk;

    @FXML
    private TextArea wed830;

    @FXML
    private TextArea wed830g;

    @FXML
    private TextArea wed830gk;

    @FXML
    private TextArea wed830pk;

    @FXML
    private TextArea wed930;

    @FXML
    private TextArea wed930g;

    @FXML
    private TextArea wed930gk;

    @FXML
    private TextArea wed930pk;



    @FXML
    private Label title;

    @FXML
    private ComboBox<TypeGroup> typeOfGroup = new ComboBox<>();



    private final GroupService groupService;

    @FXML
    private void initialize() {
        Supplier<TypeGroup[]> typeGroups = TypeGroup::values;
        typeOfGroup.getItems().addAll(typeGroups.get());
        typeOfGroup.setValue(TypeGroup.TYPE_GROUP_SWIM);
        if (typeOfGroup.getValue() == TypeGroup.TYPE_GROUP_SWIM) {
            showSchedulePool();
        }
    }

    @FXML
    private void selectSchedule(ActionEvent event) {

        if (typeOfGroup.getValue() == TypeGroup.TYPE_GROUP_SWIM) {
            clearTextArea();
            showSchedulePool();
        } else if (typeOfGroup.getValue() == TypeGroup.TYPE_GROUP_GYM) {
            clearTextArea();
            showScheduleGym();
        }else if (typeOfGroup.getValue() == TypeGroup.TYPE_GROUP_GYM_KIDS) {
            clearTextArea();
            showScheduleGymKids();
        }else if (typeOfGroup.getValue() == TypeGroup.TYPE_GROUP_SWIM_KIDS) {
            clearTextArea();
            showSchedulePoolKids();
        }
    }

    private void showSchedulePool() {
        anchorPanePool.setVisible(true);
        anchorPaneGym.setVisible(false);
        anchorPaneGymKids.setVisible(false);
        anchorPanePoolKids.setVisible(false);
        for (Group gr : groupService.findAll()) {
            if (gr.getTypeOfGroup() == TypeGroup.TYPE_GROUP_SWIM) {
                if (gr.getDaysOfWeek() == DaysOfWeek.MN_WD_FR) {
                    if (gr.getTimeClass() == TimeClass.FIRST_TIME) {
                        mn830.appendText(gr.getInstructor().toString() + "\n");
                        wed830.appendText(gr.getInstructor().toString() + "\n");
                        fri830.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SECOND_TIME) {
                        mn930.appendText(gr.getInstructor().toString() + "\n");
                        wed930.appendText(gr.getInstructor().toString() + "\n");
                        fri930.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.THIRD_TIME) {
                        mn1030.appendText(gr.getInstructor().toString() + "\n");
                        wed1030.appendText(gr.getInstructor().toString() + "\n");
                        fri1030.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FOURTH_TIME) {
                        mn1130.appendText(gr.getInstructor().toString() + "\n");
                        wed1130.appendText(gr.getInstructor().toString() + "\n");
                        fri1130.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FIFTH_TIME) {
                        mn1230.appendText(gr.getInstructor().toString() + "\n");
                        wed1230.appendText(gr.getInstructor().toString() + "\n");
                        fri1230.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SIXTH_TIME) {
                        mn1330.appendText(gr.getInstructor().toString() + "\n");
                        wed1330.appendText(gr.getInstructor().toString() + "\n");
                        fri1330.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SEVENTH_TIME) {
                        mn1430.appendText(gr.getInstructor().toString() + "\n");
                        wed1430.appendText(gr.getInstructor().toString() + "\n");
                        fri1430.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.EIGHTH_TIME) {
                        mn1530.appendText(gr.getInstructor().toString() + "\n");
                        wed1530.appendText(gr.getInstructor().toString() + "\n");
                        fri1530.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.NINTH_TIME) {
                        mn1630.appendText(gr.getInstructor().toString() + "\n");
                        wed1630.appendText(gr.getInstructor().toString() + "\n");
                        fri1630.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TENTH_TIME) {
                        mn1730.appendText(gr.getInstructor().toString() + "\n");
                        wed1730.appendText(gr.getInstructor().toString() + "\n");
                        fri1730.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.ELEVENTH_TIME) {
                        mn1830.appendText(gr.getInstructor().toString() + "\n");
                        wed1830.appendText(gr.getInstructor().toString() + "\n");
                        fri1830.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TWELFTH_TIME) {
                        mn1930.appendText(gr.getInstructor().toString() + "\n");
                        wed1930.appendText(gr.getInstructor().toString() + "\n");
                        fri1930.appendText(gr.getInstructor().toString() + "\n");
                    }
                }
                if (gr.getDaysOfWeek() == DaysOfWeek.TU_TH_SA) {
                    if (gr.getTimeClass() == TimeClass.FIRST_TIME) {
                        tu830.appendText(gr.getInstructor().toString() + "\n");
                        thu830.appendText(gr.getInstructor().toString() + "\n");
                        sat830.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SECOND_TIME) {
                        tu930.appendText(gr.getInstructor().toString() + "\n");
                        thu930.appendText(gr.getInstructor().toString() + "\n");
                        sat930.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.THIRD_TIME) {
                        tu1030.appendText(gr.getInstructor().toString() + "\n");
                        thu1030.appendText(gr.getInstructor().toString() + "\n");
                        sat1030.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FOURTH_TIME) {
                        tu1130.appendText(gr.getInstructor().toString() + "\n");
                        thu1130.appendText(gr.getInstructor().toString() + "\n");
                        sat1130.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FIFTH_TIME) {
                        tu1230.appendText(gr.getInstructor().toString() + "\n");
                        thu1230.appendText(gr.getInstructor().toString() + "\n");
                        sat1230.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SIXTH_TIME) {
                        tu1330.appendText(gr.getInstructor().toString() + "\n");
                        thu1330.appendText(gr.getInstructor().toString() + "\n");
                        sat1330.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SEVENTH_TIME) {
                        tu1430.appendText(gr.getInstructor().toString() + "\n");
                        thu1430.appendText(gr.getInstructor().toString() + "\n");
                        sat1430.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.EIGHTH_TIME) {
                        tu1530.appendText(gr.getInstructor().toString() + "\n");
                        thu1530.appendText(gr.getInstructor().toString() + "\n");
                        sat1530.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.NINTH_TIME) {
                        tu1630.appendText(gr.getInstructor().toString() + "\n");
                        thu1630.appendText(gr.getInstructor().toString() + "\n");
                        sat1630.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TENTH_TIME) {
                        tu1730.appendText(gr.getInstructor().toString() + "\n");
                        thu1730.appendText(gr.getInstructor().toString() + "\n");
                        sat1730.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.ELEVENTH_TIME) {
                        tu1830.appendText(gr.getInstructor().toString() + "\n");
                        thu1830.appendText(gr.getInstructor().toString() + "\n");
                        sat1830.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TWELFTH_TIME) {
                        tu1930.appendText(gr.getInstructor().toString() + "\n");
                        thu1930.appendText(gr.getInstructor().toString() + "\n");
                        sat1930.appendText(gr.getInstructor().toString() + "\n");
                    }
                }
            }
        }
    }

    private void showScheduleGym() {
        anchorPaneGym.setVisible(true);
        anchorPanePool.setVisible(false);
        anchorPanePoolKids.setVisible(false);
        anchorPaneGymKids.setVisible(false);
        for (Group gr : groupService.findAll()) {
            if (gr.getTypeOfGroup() == TypeGroup.TYPE_GROUP_GYM) {
                if (gr.getDaysOfWeek() == DaysOfWeek.MN_WD_FR) {
                    if (gr.getTimeClass() == TimeClass.FIRST_TIME) {
                        mn830g.appendText(gr.getInstructor().toString() + "\n");
                        wed830g.appendText(gr.getInstructor().toString() + "\n");
                        fri830g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SECOND_TIME) {
                        mn930g.appendText(gr.getInstructor().toString() + "\n");
                        wed930g.appendText(gr.getInstructor().toString() + "\n");
                        fri930g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.THIRD_TIME) {
                        mn1030g.appendText(gr.getInstructor().toString() + "\n");
                        wed1030g.appendText(gr.getInstructor().toString() + "\n");
                        fri1030g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FOURTH_TIME) {
                        mn1130g.appendText(gr.getInstructor().toString() + "\n");
                        wed1130g.appendText(gr.getInstructor().toString() + "\n");
                        fri1130g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FIFTH_TIME) {
                        mn1230g.appendText(gr.getInstructor().toString() + "\n");
                        wed1230g.appendText(gr.getInstructor().toString() + "\n");
                        fri1230g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SIXTH_TIME) {
                        mn1330g.appendText(gr.getInstructor().toString() + "\n");
                        wed1330g.appendText(gr.getInstructor().toString() + "\n");
                        fri1330g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SEVENTH_TIME) {
                        mn1430g.appendText(gr.getInstructor().toString() + "\n");
                        wed1430g.appendText(gr.getInstructor().toString() + "\n");
                        fri1430g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.EIGHTH_TIME) {
                        mn1530g.appendText(gr.getInstructor().toString() + "\n");
                        wed1530g.appendText(gr.getInstructor().toString() + "\n");
                        fri1530g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.NINTH_TIME) {
                        mn1630g.appendText(gr.getInstructor().toString() + "\n");
                        wed1630g.appendText(gr.getInstructor().toString() + "\n");
                        fri1630g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TENTH_TIME) {
                        mn1730g.appendText(gr.getInstructor().toString() + "\n");
                        wed1730g.appendText(gr.getInstructor().toString() + "\n");
                        fri1730g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.ELEVENTH_TIME) {
                        mn1830g.appendText(gr.getInstructor().toString() + "\n");
                        wed1830g.appendText(gr.getInstructor().toString() + "\n");
                        fri1830g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TWELFTH_TIME) {
                        mn1930g.appendText(gr.getInstructor().toString() + "\n");
                        wed1930g.appendText(gr.getInstructor().toString() + "\n");
                        fri1930g.appendText(gr.getInstructor().toString() + "\n");
                    }
                }
                if (gr.getDaysOfWeek() == DaysOfWeek.TU_TH_SA) {
                    if (gr.getTimeClass() == TimeClass.FIRST_TIME) {
                        tu830g.appendText(gr.getInstructor().toString() + "\n");
                        thu830g.appendText(gr.getInstructor().toString() + "\n");
                        sat830g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SECOND_TIME) {
                        tu930g.appendText(gr.getInstructor().toString() + "\n");
                        thu930g.appendText(gr.getInstructor().toString() + "\n");
                        sat930g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.THIRD_TIME) {
                        tu1030g.appendText(gr.getInstructor().toString() + "\n");
                        thu1030g.appendText(gr.getInstructor().toString() + "\n");
                        sat1030g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FOURTH_TIME) {
                        tu1130g.appendText(gr.getInstructor().toString() + "\n");
                        thu1130g.appendText(gr.getInstructor().toString() + "\n");
                        sat1130g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FIFTH_TIME) {
                        tu1230g.appendText(gr.getInstructor().toString() + "\n");
                        thu1230g.appendText(gr.getInstructor().toString() + "\n");
                        sat1230g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SIXTH_TIME) {
                        tu1330g.appendText(gr.getInstructor().toString() + "\n");
                        thu1330g.appendText(gr.getInstructor().toString() + "\n");
                        sat1330g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SEVENTH_TIME) {
                        tu1430g.appendText(gr.getInstructor().toString() + "\n");
                        thu1430g.appendText(gr.getInstructor().toString() + "\n");
                        sat1430g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.EIGHTH_TIME) {
                        tu1530g.appendText(gr.getInstructor().toString() + "\n");
                        thu1530g.appendText(gr.getInstructor().toString() + "\n");
                        sat1530g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.NINTH_TIME) {
                        tu1630g.appendText(gr.getInstructor().toString() + "\n");
                        thu1630g.appendText(gr.getInstructor().toString() + "\n");
                        sat1630g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TENTH_TIME) {
                        tu1730g.appendText(gr.getInstructor().toString() + "\n");
                        thu1730g.appendText(gr.getInstructor().toString() + "\n");
                        sat1730g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.ELEVENTH_TIME) {
                        tu1830g.appendText(gr.getInstructor().toString() + "\n");
                        thu1830g.appendText(gr.getInstructor().toString() + "\n");
                        sat1830g.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TWELFTH_TIME) {
                        tu1930g.appendText(gr.getInstructor().toString() + "\n");
                        thu1930g.appendText(gr.getInstructor().toString() + "\n");
                        sat1930g.appendText(gr.getInstructor().toString() + "\n");
                    }
                }
            }
        }
    }

    private void showScheduleGymKids() {
        anchorPaneGym.setVisible(false);
        anchorPanePool.setVisible(false);
        anchorPanePoolKids.setVisible(false);
        anchorPaneGymKids.setVisible(true);
        for (Group gr : groupService.findAll()) {
            if (gr.getTypeOfGroup() == TypeGroup.TYPE_GROUP_GYM_KIDS) {
                if (gr.getDaysOfWeek() == DaysOfWeek.MN_WD_FR) {
                    if (gr.getTimeClass() == TimeClass.FIRST_TIME) {
                        mn830gk.appendText(gr.getInstructor().toString() + "\n");
                        wed830gk.appendText(gr.getInstructor().toString() + "\n");
                        fri830gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SECOND_TIME) {
                        mn930gk.appendText(gr.getInstructor().toString() + "\n");
                        wed930gk.appendText(gr.getInstructor().toString() + "\n");
                        fri930gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.THIRD_TIME) {
                        mn1030gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1030gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1030gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FOURTH_TIME) {
                        mn1130gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1130gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1130gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FIFTH_TIME) {
                        mn1230gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1230gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1230gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SIXTH_TIME) {
                        mn1330gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1330gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1330gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SEVENTH_TIME) {
                        mn1430gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1430gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1430gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.EIGHTH_TIME) {
                        mn1530gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1530gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1530gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.NINTH_TIME) {
                        mn1630gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1630gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1630gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TENTH_TIME) {
                        mn1730gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1730gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1730gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.ELEVENTH_TIME) {
                        mn1830gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1830gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1830gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TWELFTH_TIME) {
                        mn1930gk.appendText(gr.getInstructor().toString() + "\n");
                        wed1930gk.appendText(gr.getInstructor().toString() + "\n");
                        fri1930gk.appendText(gr.getInstructor().toString() + "\n");
                    }
                }
                if (gr.getDaysOfWeek() == DaysOfWeek.TU_TH_SA) {
                    if (gr.getTimeClass() == TimeClass.FIRST_TIME) {
                        tu830gk.appendText(gr.getInstructor().toString() + "\n");
                        thu830gk.appendText(gr.getInstructor().toString() + "\n");
                        sat830gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SECOND_TIME) {
                        tu930gk.appendText(gr.getInstructor().toString() + "\n");
                        thu930gk.appendText(gr.getInstructor().toString() + "\n");
                        sat930gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.THIRD_TIME) {
                        tu1030gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1030gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1030gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FOURTH_TIME) {
                        tu1130gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1130gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1130gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FIFTH_TIME) {
                        tu1230gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1230gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1230gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SIXTH_TIME) {
                        tu1330gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1330gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1330gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SEVENTH_TIME) {
                        tu1430gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1430gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1430gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.EIGHTH_TIME) {
                        tu1530gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1530gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1530gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.NINTH_TIME) {
                        tu1630gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1630gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1630gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TENTH_TIME) {
                        tu1730gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1730gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1730gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.ELEVENTH_TIME) {
                        tu1830gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1830gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1830gk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TWELFTH_TIME) {
                        tu1930gk.appendText(gr.getInstructor().toString() + "\n");
                        thu1930gk.appendText(gr.getInstructor().toString() + "\n");
                        sat1930gk.appendText(gr.getInstructor().toString() + "\n");
                    }
                }
            }
        }
    }

    private void showSchedulePoolKids() {
        anchorPaneGym.setVisible(false);
        anchorPanePool.setVisible(false);
        anchorPanePoolKids.setVisible(true);
        anchorPaneGymKids.setVisible(false);
        for (Group gr : groupService.findAll()) {
            if (gr.getTypeOfGroup() == TypeGroup.TYPE_GROUP_SWIM_KIDS) {
                if (gr.getDaysOfWeek() == DaysOfWeek.MN_WD_FR) {
                    if (gr.getTimeClass() == TimeClass.FIRST_TIME) {
                        mn830pk.appendText(gr.getInstructor().toString() + "\n");
                        wed830pk.appendText(gr.getInstructor().toString() + "\n");
                        fri830pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SECOND_TIME) {
                        mn930pk.appendText(gr.getInstructor().toString() + "\n");
                        wed930pk.appendText(gr.getInstructor().toString() + "\n");
                        fri930pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.THIRD_TIME) {
                        mn1030pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1030pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1030pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FOURTH_TIME) {
                        mn1130pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1130pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1130pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FIFTH_TIME) {
                        mn1230pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1230pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1230pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SIXTH_TIME) {
                        mn1330pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1330pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1330pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SEVENTH_TIME) {
                        mn1430pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1430pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1430pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.EIGHTH_TIME) {
                        mn1530pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1530pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1530pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.NINTH_TIME) {
                        mn1630pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1630pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1630pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TENTH_TIME) {
                        mn1730pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1730pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1730pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.ELEVENTH_TIME) {
                        mn1830pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1830pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1830pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TWELFTH_TIME) {
                        mn1930pk.appendText(gr.getInstructor().toString() + "\n");
                        wed1930pk.appendText(gr.getInstructor().toString() + "\n");
                        fri1930pk.appendText(gr.getInstructor().toString() + "\n");
                    }
                }
                if (gr.getDaysOfWeek() == DaysOfWeek.TU_TH_SA) {
                    if (gr.getTimeClass() == TimeClass.FIRST_TIME) {
                        tu830pk.appendText(gr.getInstructor().toString() + "\n");
                        thu830pk.appendText(gr.getInstructor().toString() + "\n");
                        sat830pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SECOND_TIME) {
                        tu930pk.appendText(gr.getInstructor().toString() + "\n");
                        thu930pk.appendText(gr.getInstructor().toString() + "\n");
                        sat930pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.THIRD_TIME) {
                        tu1030pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1030pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1030pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FOURTH_TIME) {
                        tu1130pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1130pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1130pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.FIFTH_TIME) {
                        tu1230pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1230pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1230pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SIXTH_TIME) {
                        tu1330pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1330pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1330pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.SEVENTH_TIME) {
                        tu1430pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1430pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1430pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.EIGHTH_TIME) {
                        tu1530pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1530pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1530pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.NINTH_TIME) {
                        tu1630pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1630pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1630pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TENTH_TIME) {
                        tu1730pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1730pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1730pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.ELEVENTH_TIME) {
                        tu1830pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1830pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1830pk.appendText(gr.getInstructor().toString() + "\n");
                    } else if (gr.getTimeClass() == TimeClass.TWELFTH_TIME) {
                        tu1930pk.appendText(gr.getInstructor().toString() + "\n");
                        thu1930pk.appendText(gr.getInstructor().toString() + "\n");
                        sat1930pk.appendText(gr.getInstructor().toString() + "\n");
                    }
                }
            }
        }
    }

    private void clearTextArea() {
        mn830.clear();
        mn830g.clear();
        mn830gk.clear();
        mn830pk.clear();
        mn930.clear();
        mn930g.clear();
        mn930gk.clear();
        mn930pk.clear();
        mn1030.clear();
        mn1030g.clear();
        mn1030gk.clear();
        mn1030pk.clear();
        mn1130.clear();
        mn1130g.clear();
        mn1130gk.clear();
        mn1130pk.clear();
        mn1230.clear();
        mn1230g.clear();
        mn1230gk.clear();
        mn1230pk.clear();
        mn1330.clear();
        mn1330g.clear();
        mn1330gk.clear();
        mn1330pk.clear();
        mn1430.clear();
        mn1430g.clear();
        mn1430gk.clear();
        mn1430pk.clear();
        mn1530.clear();
        mn1530g.clear();
        mn1530gk.clear();
        mn1530pk.clear();
        mn1630.clear();
        mn1630g.clear();
        mn1630gk.clear();
        mn1630pk.clear();
        mn1730.clear();
        mn1730g.clear();
        mn1730gk.clear();
        mn1730pk.clear();
        mn1830.clear();
        mn1830g.clear();
        mn1830gk.clear();
        mn1830pk.clear();
        mn1930.clear();
        mn1930g.clear();
        mn1930gk.clear();
        mn1930pk.clear();

        tu830.clear();
        tu830g.clear();
        tu830gk.clear();
        tu830pk.clear();
        tu930.clear();
        tu930g.clear();
        tu930gk.clear();
        tu930pk.clear();
        tu1030.clear();
        tu1030g.clear();
        tu1030gk.clear();
        tu1030pk.clear();
        tu1130.clear();
        tu1130g.clear();
        tu1130gk.clear();
        tu1130pk.clear();
        tu1230.clear();
        tu1230g.clear();
        tu1230gk.clear();
        tu1230pk.clear();
        tu1330.clear();
        tu1330g.clear();
        tu1330gk.clear();
        tu1330pk.clear();
        tu1430.clear();
        tu1430g.clear();
        tu1430gk.clear();
        tu1430pk.clear();
        tu1530.clear();
        tu1530g.clear();
        tu1530gk.clear();
        tu1530pk.clear();
        tu1630.clear();
        tu1630g.clear();
        tu1630gk.clear();
        tu1630pk.clear();
        tu1730.clear();
        tu1730g.clear();
        tu1730gk.clear();
        tu1730pk.clear();
        tu1830.clear();
        tu1830g.clear();
        tu1830gk.clear();
        tu1830pk.clear();
        tu1930.clear();
        tu1930g.clear();
        tu1930gk.clear();
        tu1930pk.clear();

        wed830.clear();
        wed830g.clear();
        wed830gk.clear();
        wed830pk.clear();
        wed930.clear();
        wed930g.clear();
        wed930gk.clear();
        wed930pk.clear();
        wed1030.clear();
        wed1030g.clear();
        wed1030gk.clear();
        wed1030pk.clear();
        wed1130.clear();
        wed1130g.clear();
        wed1130gk.clear();
        wed1130pk.clear();
        wed1230.clear();
        wed1230g.clear();
        wed1230gk.clear();
        wed1230pk.clear();
        wed1330.clear();
        wed1330g.clear();
        wed1330gk.clear();
        wed1330pk.clear();
        wed1430.clear();
        wed1430g.clear();
        wed1430gk.clear();
        wed1430pk.clear();
        wed1530.clear();
        wed1530g.clear();
        wed1530gk.clear();
        wed1530pk.clear();
        wed1630.clear();
        wed1630g.clear();
        wed1630gk.clear();
        wed1630pk.clear();
        wed1730.clear();
        wed1730g.clear();
        wed1730gk.clear();
        wed1730pk.clear();
        wed1830.clear();
        wed1830g.clear();
        wed1830gk.clear();
        wed1830pk.clear();
        wed1930.clear();
        wed1930g.clear();
        wed1930gk.clear();
        wed1930pk.clear();

        thu830.clear();
        thu830g.clear();
        thu830gk.clear();
        thu830pk.clear();
        thu930.clear();
        thu930g.clear();
        thu930gk.clear();
        thu930pk.clear();
        thu1030.clear();
        thu1030g.clear();
        thu1030gk.clear();
        thu1030pk.clear();
        thu1130.clear();
        thu1130g.clear();
        thu1130gk.clear();
        thu1130pk.clear();
        thu1230.clear();
        thu1230g.clear();
        thu1230gk.clear();
        thu1230pk.clear();
        thu1330.clear();
        thu1330g.clear();
        thu1330gk.clear();
        thu1330pk.clear();
        thu1430.clear();
        thu1430g.clear();
        thu1430gk.clear();
        thu1430pk.clear();
        thu1530.clear();
        thu1530g.clear();
        thu1530gk.clear();
        thu1530pk.clear();
        thu1630.clear();
        thu1630g.clear();
        thu1630gk.clear();
        thu1630pk.clear();
        thu1730.clear();
        thu1730g.clear();
        thu1730gk.clear();
        thu1730pk.clear();
        thu1830.clear();
        thu1830g.clear();
        thu1830gk.clear();
        thu1830pk.clear();
        thu1930.clear();
        thu1930g.clear();
        thu1930gk.clear();
        thu1930pk.clear();

        fri830.clear();
        fri930.clear();
        fri1030.clear();
        fri1130.clear();
        fri1230.clear();
        fri1330.clear();
        fri1430.clear();
        fri1530.clear();
        fri1630.clear();
        fri1730.clear();
        fri1830.clear();
        fri1930.clear();
        fri830g.clear();
        fri930g.clear();
        fri1030g.clear();
        fri1130g.clear();
        fri1230g.clear();
        fri1330g.clear();
        fri1430g.clear();
        fri1530g.clear();
        fri1630g.clear();
        fri1730g.clear();
        fri1830g.clear();
        fri1930g.clear();
        fri830gk.clear();
        fri930gk.clear();
        fri1030gk.clear();
        fri1130gk.clear();
        fri1230gk.clear();
        fri1330gk.clear();
        fri1430gk.clear();
        fri1530gk.clear();
        fri1630gk.clear();
        fri1730gk.clear();
        fri1830gk.clear();
        fri1930gk.clear();
        fri830pk.clear();
        fri930pk.clear();
        fri1030pk.clear();
        fri1130pk.clear();
        fri1230pk.clear();
        fri1330pk.clear();
        fri1430pk.clear();
        fri1530pk.clear();
        fri1630pk.clear();
        fri1730pk.clear();
        fri1830pk.clear();
        fri1930pk.clear();

        sat830.clear();
        sat930.clear();
        sat1030.clear();
        sat1130.clear();
        sat1230.clear();
        sat1330.clear();
        sat1430.clear();
        sat1530.clear();
        sat1630.clear();
        sat1730.clear();
        sat1830.clear();
        sat1930.clear();
        sat830g.clear();
        sat930g.clear();
        sat1030g.clear();
        sat1130g.clear();
        sat1230g.clear();
        sat1330g.clear();
        sat1430g.clear();
        sat1530g.clear();
        sat1630g.clear();
        sat1730g.clear();
        sat1830g.clear();
        sat1930g.clear();
        sat830gk.clear();
        sat930gk.clear();
        sat1030gk.clear();
        sat1130gk.clear();
        sat1230gk.clear();
        sat1330gk.clear();
        sat1430gk.clear();
        sat1530gk.clear();
        sat1630gk.clear();
        sat1730gk.clear();
        sat1830gk.clear();
        sat1930gk.clear();
        sat830pk.clear();
        sat930pk.clear();
        sat1030pk.clear();
        sat1130pk.clear();
        sat1230pk.clear();
        sat1330pk.clear();
        sat1430pk.clear();
        sat1530pk.clear();
        sat1630pk.clear();
        sat1730pk.clear();
        sat1830pk.clear();
        sat1930pk.clear();
    }

}