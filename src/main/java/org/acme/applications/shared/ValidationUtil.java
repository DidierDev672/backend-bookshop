package org.acme.applications.shared;

import java.util.Objects;

public class ValidationUtil {

    public static void validateField(Object field, String errorMessage) throws CustomException {
        if(Objects.isNull(field)) {
            throw new CustomException(errorMessage);
        }
    }

    //! Validate if an empty string
    public static void validateString(String field, String errorMessage) throws CustomException {
       if(Objects.isNull(field) || field.trim().isEmpty()){
           throw new CustomException(errorMessage);
       }
    }
}
