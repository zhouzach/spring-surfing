package org.rabbit.mvc;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Spitter {

    private Long id;

    @NotNull
    @Size(min = 5, max = 16, message = "{username.size}")
    private String username;

    @NotNull
    @Size(min = 5, max = 25, message = "{password.size}")
    private String password;

    @NotNull
    @Size(min = 2, max = 30, message = "{firstName.size}")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30, message = "{lastName.size}")
    private String lastName;

    @NotNull
    @Email(message = "email.valid")
    private String email;

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
