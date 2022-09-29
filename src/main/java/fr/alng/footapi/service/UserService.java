package fr.alng.footapi.service;

import fr.alng.footapi.dto.UserDTO;
import fr.alng.footapi.model.Role;
import fr.alng.footapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    Optional<User> getUserById(Long id);
    Boolean isUserInRoom(Long userId, Long roomId);

}
