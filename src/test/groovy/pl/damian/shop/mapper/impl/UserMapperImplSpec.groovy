package pl.damian.shop.mapper.impl


import pl.damian.shop.domain.dao.User
import pl.damian.shop.domain.dto.UserDto
import pl.damian.shop.mapper.UserMapperImpl
import spock.lang.Specification

class UserMapperImplSpec extends Specification{
    def userMapper=new UserMapperImpl()

    def 'test daoToDto'(){
        given:
        def user=new User(id: 1l,firstName: 'firstName',lastName: 'lastName',password:'qwerty',email: 'email@wp.pl',mobile: '123456789')

        when:
        def result= userMapper.daoToDto(user)

        then:
        result.getId()==user.getId()
        result.firstName==user.firstName
        result.lastName==user.lastName
        result.email==user.email
        result.mobile==user.mobile

    }

    def 'test dtoToDao'(){
        given:
        def userDto=new UserDto(id: 1l,firstName: 'firstName',lastName: 'lastName',password:'qwerty',email: 'email@wp.pl',mobile: '123456789')

        when:
        def result= userMapper.dtoToDao(userDto)

        then:
        result.getId()==userDto.getId()
        result.firstName==userDto.firstName
        result.lastName==userDto.lastName
        result.email==userDto.email
        result.mobile==userDto.mobile

    }


}
