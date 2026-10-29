package com.example.recruitpro.config;

import com.example.recruitpro.entity.Candidate;
import com.example.recruitpro.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CandidateRepository candidateRepository;

    @Override
    public void run(String... args) {
        if (candidateRepository.count() == 0) {
            Candidate candidate = Candidate.builder()
                    .fullName("Nguyễn Công Hưởng")
                    .email("huongcaoha123@gmail.com")
                    .age(18)
                    .yearsOfExperience(10)
                    .build();
            candidateRepository.save(candidate);
        }
    }
}
