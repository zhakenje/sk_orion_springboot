package kz.orion.sk_orion_springboot.model.service;

import kz.orion.sk_orion_springboot.model.entity.Group;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.Subscription;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.entity.enums.InstructorRole;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeGroup;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void save(Group group) {

        if (group.getTypeOfGroup() == null) {
            throw new PosException("Пожалуйста, выберите тип группы!");
        } else if (group.getDaysOfWeek() == null) {
            throw new PosException("Пожалуйста, выберите дни занятие группы!");
        } else if (group.getTimeClass() == null) {
            throw new PosException("Пожалуйста, выберите время занятие!");
        } else if (group.getInstructor() == null) {
            throw new PosException("Пожалуйста, выберите инструктора группы!");
        } else if (group.getInstructor().getInstructorRole() == InstructorRole.INSTRUCTOR_SWIM) {
            if (!(group.getTypeOfGroup().toString().equals(TypeGroup.TYPE_GROUP_SWIM.toString())
                    || group.getTypeOfGroup().toString().equals(TypeGroup.TYPE_GROUP_SWIM_KIDS.toString()))) {
                throw new PosException("У инструктора другая специальность!");
            }
        } else if (group.getInstructor().getInstructorRole() == InstructorRole.INSTRUCTOR_GYM) {
            if (!(group.getTypeOfGroup().toString().equals(TypeGroup.TYPE_GROUP_GYM.toString())
                    || group.getTypeOfGroup().toString().equals(TypeGroup.TYPE_GROUP_GYM_KIDS.toString()))) {
                throw new PosException("У инструктора другая специальность!");
            } else {
                for (Group gr : groupRepository.findAll()) {
                    if (gr.getInstructor().toString().equals(group.getInstructor().toString())) {
                        if (gr.getDaysOfWeek().toString().equals(group.getDaysOfWeek().toString())) {
                            if (gr.getTimeClass().toString().equals(group.getTimeClass().toString())) {
                                throw new PosException("У данного инструктора есть группа в это время!");
                            }
                        }
                    }
                }
            }
        }
        groupRepository.save(group);


    }

    public List<Group> searchByTypeGroup(TypeGroup typeGroup) {
        List<Group> groupList = new ArrayList<>();
        if (typeGroup != null) {
            for (Group gr : groupRepository.findAll()) {
                if (typeGroup == gr.getTypeOfGroup()) {
                    groupList.add(gr);
                }
            }
            return groupList;
        } else {
            throw new PosException("Пожалуйста введите тип группы!");
        }
    }

    public List<Group> findAll() {
        List<Group> groupList = groupRepository.findAll();
        return groupList;
    }

    public List<Group> searchByInstructorGroups(Instructor instructor){
        List<Group> groupList = new ArrayList<>();
        if (instructor != null){
            for (Group group : groupRepository.findAll()){
                if (group.getInstructor().toString().equals(instructor.toString())){
                    groupList.add(group);
                }
            }
            return groupList;
        }else {
            throw new PosException("Инструктор не выбран!");
        }
    }

    public List<Group> findAllByVisitor(Visitor vs) {
        List<Group> groupList = new ArrayList<>();
        if (vs != null){
            for (Group gr:groupRepository.findAll()) {
                for (Subscription sb:gr.getSubscriptionList()) {
                    if (sb.getVisitor().toString().equals(vs.toString()))
                    {
                        groupList.add(gr);
                        break;
                    }
                }
            }
        }
        return groupList;
    }
}
