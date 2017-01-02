package eu.needtocode.validation;

public interface ContextualValidator<CONTEXT, TYPE> {

    void validate(CONTEXT context, TYPE type);
}
