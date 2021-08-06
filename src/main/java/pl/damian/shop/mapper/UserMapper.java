package pl.damian.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.damian.shop.domain.dao.User;
import pl.damian.shop.domain.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToDao(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    UserDto daoToDto(User user);

}
