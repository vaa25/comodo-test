package com.comodo.vaa25.dto;

import com.comodo.vaa25.entity.Gender;
import com.comodo.vaa25.validator.Past;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
public class UserDto {

    @NotNull(message = "First name shouldn't be null")
    @Size(min = 1, max = 30, message = "First name size should be between 1 and 30 symbols")
    private String firstName;
    @NotNull(message = "Last name shouldn't be null")
    @Size(min = 1, max = 30, message = "Last name size should be between 1 and 30 symbols")
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "Invalid birth day")
    @Past(message = "Birth day should be in the past")
    private LocalDate birthDay;
    @NotNull(message = "Invalid gender")
    private Gender gender;

}
