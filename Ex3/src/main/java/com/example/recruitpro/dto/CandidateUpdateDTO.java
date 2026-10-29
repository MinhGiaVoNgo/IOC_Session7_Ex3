package com.example.recruitpro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateUpdateDTO {

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @Size(min = 10, max = 200, message = "size must be between 10 and 200")
    private String bio;
}
