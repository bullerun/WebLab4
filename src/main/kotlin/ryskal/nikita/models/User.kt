package ryskal.nikita.models

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    var id: Long? = null,
    @get:JvmName("getUSERNAME")
    @Column(name = "username", unique = true, nullable = false)
    var username: String,
    @get:JvmName("getPASSWORD")
    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "email", unique = true, nullable = false)
    var email: String,
) : UserDetails {
    constructor() : this(0, "", "", "") {

    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return null
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', email='$email')"
    }
}
