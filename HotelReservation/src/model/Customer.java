package model;

import javax.xml.bind.ValidationEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String lastName;
    private String firstName;
    private String email;

    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getEmail() {
        return email;
    }

    public Customer(String lastName, String firstName, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        validateEmail(email);
    }

    public void validateEmail(String email) {
        Matcher matcher = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$",
                Pattern.CASE_INSENSITIVE).matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }
    @Override
    public String toString() {
        return String.format("Customer\nfirstName=%s\nlastName=%s\nemail=%s\n", firstName, lastName, email);
    }

}
