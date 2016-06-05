package by.mprotas.dto;

import by.mprotas.validator.annotation.BalancedParentheses;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalTime;

public class Call {
    @Size(max = 30)
    private String firstName;

    @NotEmpty
    @Size(max = 30)
    private String lastName;

    @Null
    private LocalTime time;

    @NotEmpty
//    @Pattern(regexp = "(?:\\+|00)?(\\(?\\d{1,3}\\)?)?[ -]?((?:[ \\-\\(]?\\d{3}[ \\-\\)]?){3})")
    @BalancedParentheses
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
