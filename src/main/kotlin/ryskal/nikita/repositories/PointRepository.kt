package ryskal.nikita.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ryskal.nikita.models.Point


interface PointRepository : JpaRepository<Point, Long>
{

}
