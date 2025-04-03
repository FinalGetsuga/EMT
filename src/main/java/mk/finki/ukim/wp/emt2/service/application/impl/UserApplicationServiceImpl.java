package mk.finki.ukim.wp.emt2.service.application.impl;

import mk.finki.ukim.wp.emt2.dto.CreateUserDto;
import mk.finki.ukim.wp.emt2.dto.DisplayUserDto;
import mk.finki.ukim.wp.emt2.dto.LoginUserDto;
import mk.finki.ukim.wp.emt2.model.domain.User;
import mk.finki.ukim.wp.emt2.service.application.UserApplicationService;
import mk.finki.ukim.wp.emt2.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.confirmPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));

    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(DisplayUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));

    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }
}
