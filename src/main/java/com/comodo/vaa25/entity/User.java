package com.comodo.vaa25.entity;

import com.comodo.vaa25.PostgreSqlEnumType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "user_data")
@Accessors(chain = true)
@TypeDef(
        name = "gender",
        typeClass = PostgreSqlEnumType.class
)
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty("userId")
    private Long id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDay;
    @Enumerated(value = STRING)
    @Type(type = "gender")
    private Gender gender;

}
