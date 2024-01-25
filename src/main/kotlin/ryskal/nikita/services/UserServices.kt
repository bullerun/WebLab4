package ryskal.nikita.services

import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ryskal.nikita.models.User
import ryskal.nikita.repositories.UserRepository


@Service
@RequiredArgsConstructor
class UserServices(private val userRepository: UserRepository? = null) {

    fun save(user: User): User {
        return userRepository!!.save(user)
    }

    fun create(user: User): User {
        if (userRepository!!.existsByUsername(user.username)) {
            throw RuntimeException("Пользователь с таким именем уже существует")
        }
        if (userRepository.existsByEmail(user.email)) {
            throw RuntimeException("Пользователь с таким email уже существует")
        }
        return save(user)
    }

    fun getByUsername(username: String?): User {
        return userRepository!!.findByUsername(username!!)
            ?.orElseThrow {
                UsernameNotFoundException(
                    "Пользователь не найден"
                )
            }!!
    }

    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username: String? -> getByUsername(username) }
    }
}
