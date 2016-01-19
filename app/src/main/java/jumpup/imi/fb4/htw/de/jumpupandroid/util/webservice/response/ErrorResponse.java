package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response;

import java.util.Arrays;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class ErrorResponse {
    public static final String FIELD_NAME_SUCCESS = "success";
    public static final String FIELD_NAME_ERRORS = "errors";
    public static final String FIELD_NAME_VALIDATION_FIELDNAME = "fieldName";

    private boolean success = false;
    private String[] errorMessages;
    private String failedValidationFieldName = "";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String[] getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String[] errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getFailedValidationFieldName() {
        return failedValidationFieldName;
    }

    public void setFailedValidationFieldName(String failedValidationFieldName) {
        this.failedValidationFieldName = failedValidationFieldName;
    }

    public boolean isValidationFailure() {
        return this.getFailedValidationFieldName().length() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorResponse that = (ErrorResponse) o;

        if (isSuccess() != that.isSuccess()) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getErrorMessages(), that.getErrorMessages())) return false;
        return !(getFailedValidationFieldName() != null ? !getFailedValidationFieldName().equals(that.getFailedValidationFieldName()) : that.getFailedValidationFieldName() != null);

    }

    @Override
    public int hashCode() {
        int result = (isSuccess() ? 1 : 0);
        result = 31 * result + (getErrorMessages() != null ? Arrays.hashCode(getErrorMessages()) : 0);
        result = 31 * result + (getFailedValidationFieldName() != null ? getFailedValidationFieldName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorResponse{");
        sb.append("success=").append(success);
        sb.append(", errorMessages=").append(Arrays.toString(errorMessages));
        sb.append(", failedValidationFieldName='").append(failedValidationFieldName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
