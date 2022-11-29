package org.tdd.katas.pwdvalidator;

class PasswordValidatorResponse {
    private String errors;
    PasswordValidatorResponse(String errors) {
        this.errors = errors;
    }
    public boolean isValid() {
        return "".equals(errors);
    }
    public String getErrors() {
        return errors;
    }

}
