package ryskal.nikita.repositories

import jakarta.validation.constraints.NotNull
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ryskal.nikita.models.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(@NotNull username: String): Optional<User?>?
    fun existsByUsername(@NotNull username: String): Boolean
    fun existsByEmail(@NotNull email: String): Boolean
}
