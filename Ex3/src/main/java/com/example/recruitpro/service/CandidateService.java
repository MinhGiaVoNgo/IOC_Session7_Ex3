package com.example.recruitpro.service;

import com.example.recruitpro.dto.CandidateCreateDTO;
import com.example.recruitpro.dto.CandidateUpdateDTO;
import com.example.recruitpro.entity.Candidate;

public interface CandidateService {
    Candidate createCandidate(CandidateCreateDTO dto);
    Candidate updateCandidate(Long id, CandidateUpdateDTO dto);
    Candidate getCandidateById(Long id);
}
