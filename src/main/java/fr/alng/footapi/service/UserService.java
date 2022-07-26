package fr.alng.footapi.service;

import fr.alng.footapi.model.Role;
import fr.alng.footapi.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();

}
