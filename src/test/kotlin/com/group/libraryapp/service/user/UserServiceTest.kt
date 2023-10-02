package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat

@SpringBootTest
class UserServiceTest @Autowired constructor(  // 앞에 @Autowired를 붙이면 생성자 주입이 가능하다.
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    @Test
    fun saveUserTest() {
        // given
        val request = UserCreateRequest("최태현", null)

        // when
        userService.saveUser(request)

        // then
        val results = userRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("최태현")

        // 플랫폼타입 관련 에러. 자바에서 가져올때 null가능한지 여부를 알수 없음.
        //  코틀린은 일단 null이 아니겠지 하고 가져왔는데 막상 null이라서 에러
        // -> 자바코드의 가져오려는 데이터에 @Nullable 어노테이션을 붙여주면 해결
        assertThat(results[0].age).isNull()
    }

}