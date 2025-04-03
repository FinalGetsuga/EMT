package mk.finki.ukim.wp.emt2.dto;

import mk.finki.ukim.wp.emt2.model.domain.User;
import mk.finki.ukim.wp.emt2.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String confirmPassword,
        String name,
        String surname,
        Role role) {

    public User toUser() {
        return new User(username,password,name,surname,role);
    }
}
