package br.com.gerenciaautoeletrica.domain.exception.generic;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GenericValidationExceptionList extends RuntimeException{

    private final List<GenericValidationException> exceptions;

    public GenericValidationExceptionList(String message, List<GenericValidationException> exceptions) {
        super(message);
        this.exceptions = exceptions;
    }

    public GenericValidationExceptionList(List<Throwable> throwables) {
        super("");
        this.exceptions = new ArrayList<>();

        if (!CollectionUtils.isEmpty(throwables)) {
            throwables.forEach(throwable -> this.exceptions.add(new GenericValidationException(throwable)));
        }
    }

}
