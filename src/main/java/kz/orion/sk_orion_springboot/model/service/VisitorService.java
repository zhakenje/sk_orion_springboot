package kz.orion.sk_orion_springboot.model.service;

import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import kz.orion.sk_orion_springboot.model.entity.enums.Role;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitorService {

    private final VisitorRepository visitorRepository;

    public Visitor login(String username, String password) {

        if (!StringUtils.hasText(username)) {
            throw new PosException("Please enter your username");
        }

        if (!StringUtils.hasText(password)) {
            throw new PosException("Please enter your password");
        }

        Visitor visitor = visitorRepository.findById(username).
                orElseThrow(() -> new PosException("Please check your username"));

        if (!password.equals(visitor.getPassword())) {
            throw new PosException("Please check the password you entered");
        }

        return visitor;

    }

    public void registerVisitor(Visitor visitor) {

        if (visitor.getLastName() == null) {
            throw new PosException("Пожалуйста, введите свое имя!");
        }

        if (visitor.getFirstName() == null) {
            throw new PosException("Пожалуйста, введите свою фамилию!");
        }

        if (visitor.getDateOfBirth() == null) {
            throw new PosException("Пожалуйста, введите дату рождения!");
        } else {
            LocalDate d1 = LocalDate.parse(visitor.getDateOfBirth().toString());
            LocalDate d2 = LocalDate.parse(LocalDate.now().toString());
            if (ChronoUnit.YEARS.between(d1, d2) < 7)
            {
                throw new PosException("Вам еще не исполнилось 7 лет!");
            }
        }

        if (visitor.getPhoneNumber() == null) {
            throw new PosException("Пожалуйста, введите номер телефона!");
        }

        if (visitor.getEmail() == null) {
            throw new PosException("Пожалуйста, введите почту!");
        }

        if (visitor.getUsername() == null) {
            throw new PosException("Пожалуйста, введите логин!");
        }

        if (visitor.getPassword() == null) {
            throw new PosException("Пожалуйста, введите пароль!");
        }

//        if (!StringUtils.hasText(fileNameImage)) {
//            throw new PosException("Пожалуйста, выберите файл для фото профиля!");
//        } else {
//            visitor.setFileNameImage(fileNameImage);
//        }
//
//        if (!StringUtils.hasText(fileNameDoc)) {
//            throw new PosException("Пожалуйста, выберите файл для прикрепления документа!");
//        } else {
//            visitor.setFileNameDoc(fileNameDoc);
//        }

        visitor.setRole(Role.VISITOR);

        try {
            visitorRepository.save(visitor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Visitor> searchByFirstName(String text) {
        if (StringUtils.hasText(text)) {
            List<Visitor> visitors = new ArrayList<>();
            for (Visitor visitor : visitorRepository.findAllByFirstName(text)) {
                visitors.add(visitor);
            }
            return visitors;
        } else {
            throw new PosException("Пожалуйста введите фамилию!");
        }
    }

    public List<Visitor> findAll(){
        return visitorRepository.findAll();
    }

    public void save(Visitor visitor) {
        visitorRepository.save(visitor);
    }
}
