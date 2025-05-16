package com.zele.crspringboot.dtos.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResetPasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    public boolean validate() {
        return newPassword.equals(confirmNewPassword);
    }
}
