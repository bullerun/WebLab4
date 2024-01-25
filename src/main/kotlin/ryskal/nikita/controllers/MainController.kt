package ryskal.nikita.controllers

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import ryskal.nikita.models.Point
import ryskal.nikita.requsts.AddPointRequest
import ryskal.nikita.services.PointService

@Controller
@RequiredArgsConstructor
class MainController(private val pointService: PointService) {

    @PostMapping("/addpoint")
    fun addPoint(@RequestBody addPointRequest: AddPointRequest): ResponseEntity<List<Point>> {
        return ResponseEntity.ok(pointService.addPointToDB(addPointRequest))
    }

    @GetMapping("/get/points")
    fun getAllPoint() :ResponseEntity<List<Point>> {
        println(pointService.getAllPoint())
        return ResponseEntity.ok(pointService.getAllPoint())
    }
}
