package kz.orion.sk_orion_springboot.model.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import kz.orion.sk_orion_springboot.model.entity.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    protected String username;

    protected String password;

    protected String firstName;

    protected String lastName;

    @Enumerated(EnumType.STRING)
    protected Role role;

    protected LocalDate dateOfBirth;

    protected String phoneNumber;

    protected String email;

    protected String fileNameImage;

}
