package ryskal.nikita.services

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import ryskal.nikita.models.Point
import ryskal.nikita.repositories.PointRepository
import ryskal.nikita.requsts.AddPointRequest

@Service
@RequiredArgsConstructor
class PointService(private val pointRepository: PointRepository) {

    fun addPointToDB(addPointRequest: AddPointRequest): List<Point> {
        val point = Point(x = addPointRequest.x, y = addPointRequest.y, r = addPointRequest.r, result = addPointRequest.result)
        pointRepository.save(point)
        return pointRepository.findAll()
    }

    fun getAllPoint(): List<Point> {
        return pointRepository.findAll()
    }
}
