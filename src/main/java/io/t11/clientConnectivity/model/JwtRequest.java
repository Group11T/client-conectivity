package io.t11.clientConnectivity.model;

import java.io.Serializable;

public class JwtRequest  implements Serializable {

    private String emailAddress;
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public JwtRequest()
    {

    }

    public JwtRequest(String emailAddress, String password) {
        this.setEmailAddress(emailAddress);
        this.setPassword(password);
    }
}
