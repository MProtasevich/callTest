package by.mprotas.dto;

import by.mprotas.validator.annotation.BalancedParentheses;
import by.mprotas.validator.annotation.Phone;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalTime;

public class Call {
    @Size(max = 30)
    private String firstName;

    @NotEmpty
    @NotNull
    @Size(max = 30)
    private String lastName;

    @Null
    private LocalTime time;

    @NotEmpty
    @NotNull
//    @Pattern(regexp = "(?:\\+|00)?(\\(?\\d{1,3}\\)?)?[ -]?((?:[ \\-\\(]?\\d{3}[ \\-\\)]?){3})")
    @BalancedParentheses
    @Phone(availableSymbols = " -()", prefixPattern = "^(\\+|00)?", numberOfAreaCodeDigits = 3, numberOfLocalCodeDigits = 9)
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
