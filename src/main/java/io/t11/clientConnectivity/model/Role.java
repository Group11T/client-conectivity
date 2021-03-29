package io.t11.clientConnectivity.model;

import org.springframework.stereotype.Indexed;

import javax.persistence.Id;

public class Role {
    @Id
    private String id;

//    @Indexed(Unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
//    private String email;
//    private String password;
//    private boolean enabled;
}
