package kz.orion.sk_orion_springboot.model.service;

import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.enums.InstructorRole;
import kz.orion.sk_orion_springboot.model.entity.enums.Role;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public Instructor login(String username, String password) {

        if (!StringUtils.hasText(username)) {
            throw new PosException("Please enter your username");
        }

        if (!StringUtils.hasText(password)) {
            throw new PosException("Please enter your password");
        }

        Instructor instructor = instructorRepository.findById(username).
                orElseThrow(() -> new PosException("Please check your username"));

        if (!password.equals(instructor.getPassword())) {
            throw new PosException("Please check the password you entered");
        }

        return instructor;

    }

    public void save(Instructor instructor) {

        if (instructor.getFirstName() == null) {
            throw new PosException("Пожалуйста, пишите свою фамилию!");
        }

        if (instructor.getLastName() == null) {
            throw new PosException("Пожалуйста, пишите свое имя!");
        }

        if (instructor.getDateOfBirth() == null) {
            throw new PosException("Пожалуйста, укажите дату рождения!");
        }else {
            LocalDate d1 = LocalDate.parse(instructor.getDateOfBirth().toString());
            LocalDate d2 = LocalDate.parse(LocalDate.now().toString());
            if (ChronoUnit.YEARS.between(d1, d2) < 20){
              throw new PosException("Данному сотруднику еще не исполнилось 20 лет!");
            }

        }

        if (instructor.getPhoneNumber() == null) {
            throw new PosException("Пожалуйста, пишите свой номер телефона!");
        }

        if (instructor.getEmail() == null) {
            throw new PosException("Пожалуйста, пишите свою электронную почту!");
        }

        if (null == instructor.getInstructorRole()) {
            throw new PosException("Пожалуйста, пишите свое направление деятельности!");
        }

        if (instructor.getExperience() == null) {
            throw new PosException("Пожалуйста, укажите опыт работы!");
        }

        if (instructor.getUsername() == null) {
            throw new PosException("Пожалуйста, пишите свой логин!");
        }

        if (instructor.getPassword() == null) {
            throw new PosException("Пожалуйста, пишите свой пароль!");
        }

        if (instructor.getFileNameImage() == null) {
            throw new PosException("Пожалуйста, выберите фото для профиля!");
        }

        instructorRepository.save(instructor);
    }


    public List<Instructor> searchByInstructorRole(InstructorRole instructorRole) {
        List<Instructor> instructorList = new ArrayList<>();
        for (Instructor instructor : instructorRepository
                .findAllByRoleAndInstructorRole(Role.INSTRUCTOR, instructorRole)) {
            instructorList.add(instructor);
        }
        return instructorList;
    }

    public List<Instructor> searchByFirstName(String text) {
        if (StringUtils.hasText(text)) {
            List<Instructor> instructorList = new ArrayList<>();
            for (Instructor instructor : instructorRepository.findAllByFirstNameAndRole(text, Role.INSTRUCTOR)) {
                instructorList.add(instructor);
            }
            return instructorList;
        } else {
            throw new PosException("Пожалуйста введите фамилию!");
        }
    }

    public List<Instructor> findAllByRoleInstructor() {
        List<Instructor> instructorList = instructorRepository.findAll();
        return instructorList;
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }
}
