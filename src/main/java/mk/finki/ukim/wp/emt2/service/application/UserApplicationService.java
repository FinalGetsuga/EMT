package mk.finki.ukim.wp.emt2.service.application;

import mk.finki.ukim.wp.emt2.dto.CreateUserDto;
import mk.finki.ukim.wp.emt2.dto.DisplayUserDto;
import mk.finki.ukim.wp.emt2.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
