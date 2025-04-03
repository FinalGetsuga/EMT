package mk.finki.ukim.wp.emt2.service.domain;

import mk.finki.ukim.wp.emt2.model.domain.User;
import mk.finki.ukim.wp.emt2.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    mk.finki.ukim.wp.emt2.model.domain.User register(String username, String password, String confirmPassword, String name, String surname, Role role);

    User login(String username, String password);

    mk.finki.ukim.wp.emt2.model.domain.User findByUsername(String username);

}
