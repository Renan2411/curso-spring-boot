package br.com.gerenciaautoeletrica.domain.validation;

import br.com.gerenciaautoeletrica.domain.exception.generic.GenericValidationExceptionList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Validator<T> {

    private final T t;
    private final List<Throwable> exceptions = new ArrayList<>();

    public Validator(T t) {
        this.t = t;
    }

    public static <T> Validator<T> of(T t){
        return new Validator<>(Objects.requireNonNull(t));
    }

    public Validator<T> validate(Boolean assertion, String message){
        if(!assertion){
            exceptions.add(new IllegalStateException(message));
        }

        return this;
    }

    public T get(){
        if(ok()){
            return t;
        }

        throw new GenericValidationExceptionList(exceptions);
    }

    public boolean ok() {
        return exceptions.isEmpty();
    }
}
