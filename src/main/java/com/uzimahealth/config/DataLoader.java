package com.uzimahealth.config;

import com.uzimahealth.model.Patient;
import com.uzimahealth.model.User;
import com.uzimahealth.repository.PatientRepository;
import com.uzimahealth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public DataLoader(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Sample users
        userRepository.save(new User("admin", "password", "ADMIN"));
        userRepository.save(new User("doctor1", "password", "DOCTOR"));
        userRepository.save(new User("patient1", "password", "PATIENT"));

        // Sample patients
        patientRepository.save(new Patient("John Doe", 30, "No known allergies", "john@example.com"));
        patientRepository.save(new Patient("Jane Smith", 25, "Asthma", "jane@example.com"));
    }
}