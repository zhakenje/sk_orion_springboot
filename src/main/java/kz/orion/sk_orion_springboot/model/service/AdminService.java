package kz.orion.sk_orion_springboot.model.service;

import kz.orion.sk_orion_springboot.model.entity.Admin;
import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.exception.PosException;
import kz.orion.sk_orion_springboot.model.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin login(String username, String password) {

        if (!StringUtils.hasText(username)) {
            throw new PosException("Please enter your username");
        }

        if (!StringUtils.hasText(password)) {
            throw new PosException("Please enter your password");
        }

        Admin admin = adminRepository.findById(username).
                orElseThrow(() -> new PosException("Please check your username"));

        if (!password.equals(admin.getPassword())) {
            throw new PosException("Please check the password you entered");
        }

        return admin;

    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
}
