package com.comodo.vaa25.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PastValidatorTest {

    private final PastValidator validator = new PastValidator(Clock.fixed(LocalDate
            .of(2018, 1, 1)
            .atStartOfDay()
            .toInstant(ZoneOffset.UTC), ZoneId.of("UTC")));
    @Mock
    private ConstraintValidatorContext context;

    @Test
    public void validIfDateIsNull() {
        assertThat(validator.isValid(null, context), is(true));
    }

    @Test
    public void validIfDateInPast() {
        assertThat(validator.isValid(LocalDate.of(2017, 1, 1), context), is(true));
    }

    @Test
    public void invalidIfDateInFuture() {
        assertThat(validator.isValid(LocalDate.of(2019, 1, 1), context), is(false));
    }

    @Test
    public void invalidIfDateIsToday() {
        assertThat(validator.isValid(LocalDate.of(2018, 1, 1), context), is(false));
    }
}