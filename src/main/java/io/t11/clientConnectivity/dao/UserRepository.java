package io.t11.clientConnectivity.dao;

import io.t11.clientConnectivity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository <User, Long> {

}
