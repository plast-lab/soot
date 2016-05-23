package soot.validation;

import soot.SootClass;

import java.util.List;

/**
 * Implement this interface if you want to provide your own class validator
 */
public interface ClassValidator {
    /**
     * Validates the given class and saves all validation errors in the given list.
     *
     * @param exceptions the list of exceptions
     */
    void validate(SootClass sc, List<ValidationException> exceptions);

    /**
     * Basic validators run essential checks and are run always if validate is called.<br>
     * If this method returns false and the caller of the validator respects this property,<br>
     * the checks will only be run if the debug or validation option is activated.
     *
     * @return whether this validator is a basic validator
     */
    boolean isBasicValidator();
}
