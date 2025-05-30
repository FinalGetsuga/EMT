package mk.finki.ukim.wp.emt2.service.domain.impl;

import mk.finki.ukim.wp.emt2.model.domain.User;
import mk.finki.ukim.wp.emt2.model.enumerations.Role;
import mk.finki.ukim.wp.emt2.model.exceptions.*;
import mk.finki.ukim.wp.emt2.repository.UserRepository;
import mk.finki.ukim.wp.emt2.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User register(
            String username,
            String password,
            String confirmPassword,
            String name,
            String surname,
            Role role
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }
        if (!password.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(username, passwordEncoder.encode(password), name, surname, role);

        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }
}

