package com.example.recruitpro.controller;

import com.example.recruitpro.dto.CandidateUpdateDTO;
import com.example.recruitpro.entity.Candidate;
import com.example.recruitpro.exception.GlobalExceptionHandler;
import com.example.recruitpro.service.CandidateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CandidateController.class)
@Import(GlobalExceptionHandler.class)
class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;

    @Test
    @DisplayName("PUT /api/v1/candidates/update/1 - Form Data with invalid bio (< 10 chars) matching Image 4")
    void testUpdateCandidate_InvalidBio_Image4() throws Exception {
        mockMvc.perform(put("/api/v1/candidates/update/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("address", "Hà Nhì")
                        .param("bio", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Dữ liệu không hợp lệ !"))
                .andExpect(jsonPath("$.status").value("400 BAD_REQUEST"))
                .andExpect(jsonPath("$.data.bio").value("size must be between 10 and 200"));
    }

    @Test
    @DisplayName("PUT /api/v1/candidates/update/1 - Valid Form Data matching Image 5")
    void testUpdateCandidate_ValidData_Image5() throws Exception {
        Candidate updatedCandidate = Candidate.builder()
                .id(1L)
                .fullName("Nguyễn Công Hưởng")
                .email("huongcaoha123@gmail.com")
                .age(18)
                .yearsOfExperience(10)
                .address("Hà Nhì")
                .bio("Với 10 năm kinh nghiệm trong lập trình Java")
                .build();

        when(candidateService.updateCandidate(eq(1L), any(CandidateUpdateDTO.class))).thenReturn(updatedCandidate);

        mockMvc.perform(put("/api/v1/candidates/update/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("address", "Hà Nhì")
                        .param("bio", "Với 10 năm kinh nghiệm trong lập trình Java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Nguyễn Công Hưởng"))
                .andExpect(jsonPath("$.email").value("huongcaoha123@gmail.com"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.yearsOfExperience").value(10))
                .andExpect(jsonPath("$.address").value("Hà Nhì"))
                .andExpect(jsonPath("$.bio").value("Với 10 năm kinh nghiệm trong lập trình Java"));
    }
}
