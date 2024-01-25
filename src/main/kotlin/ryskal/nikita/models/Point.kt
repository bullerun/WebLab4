package ryskal.nikita.models

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable



@Data
@NoArgsConstructor
@Entity
@Table(name = "point")
data class Point (
    @field:Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_id_seq")
    @SequenceGenerator(name = "point_id_seq", sequenceName = "point_id_seq", allocationSize = 1)
    private var id: Long? = null,

    @Column(name = "x", nullable = false)
    private var x: Float,

    @Column(name = "y", nullable = false)
    private var y: Float,

    @Column(name = "r", nullable = false)
    private var r: Float,

    @Column(name = "result", nullable = false)
    private var result: Boolean
): Serializable {
    constructor() : this(null, 0.0f, 0.0f, 0.0f, false)

    override fun toString(): String {
        return "Point(id=$id, x=$x, y=$y, r=$r, result=$result)"
    }
    fun idResult(): Boolean {
        return this.result;
    }
    fun getId(): Long? {
        return id
    }

    fun getX(): Float {
        return x
    }

    fun getY(): Float {
        return y
    }

    fun getR(): Float {
        return r
    }

    fun getResult(): Boolean {
        return result
    }

    // Сеттеры
    fun setId(id: Long?) {
        this.id = id
    }

    fun setX(x: Float) {
        this.x = x
    }

    fun setY(y: Float) {
        this.y = y
    }

    fun setR(r: Float) {
        this.r = r
    }

    fun setResult(result: Boolean) {
        this.result = result
    }
}
