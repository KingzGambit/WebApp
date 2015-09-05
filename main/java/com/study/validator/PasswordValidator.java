package com.study.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    private static final String VERIFY = "verify";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;
        String verifyPassword = (String) component.getAttributes().get(VERIFY);

        if (password == null || verifyPassword == null) {
            return;
        }

        if (!password.equals(verifyPassword)) {
            throw new ValidatorException(new FacesMessage("Passwords do not match!"));
        }

        if (password.length() < 5) {
            throw new ValidatorException(new FacesMessage("The password must be at least 5 characters."));
        }

        if (password.length() > 50) {
            throw new ValidatorException(new FacesMessage("The password must be 50 characters or shorter."));
        }

        if (!password.matches(".*\\d.*")) {
            throw new ValidatorException(new FacesMessage("The password must contain at least one number."));
        }
    }

}
