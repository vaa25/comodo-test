package com.comodo.vaa25.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Component
public class PastValidator implements ConstraintValidator<Past, TemporalAccessor> {

    private final Clock clock;

    @Autowired
    public PastValidator(final Clock clock) {
        this.clock = clock;
    }

    @Override
    public void initialize(final Past constraintAnnotation) {

    }

    @Override
    public boolean isValid(final TemporalAccessor value, final ConstraintValidatorContext context) {
        return Optional
                .ofNullable(value)
                .map(temporal -> LocalDate.from(temporal).isBefore(LocalDate.now(clock)))
                .orElse(true);
    }
}