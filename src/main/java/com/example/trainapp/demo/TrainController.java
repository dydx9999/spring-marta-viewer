package com.example.trainapp.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/first")
    public ResponseEntity<?> getFirstTrain() {

        List<TrainResponse> trains = trainService.getAllTrains();

        if (trains == null || trains.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trains);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTrains(
            @RequestParam(required = false) String line,
            @RequestParam(required = false) String direction) {

        List<TrainResponse> trains = trainService.getFilteredTrains(line, direction);

        if (trains.isEmpty()) {
            return ResponseEntity.badRequest().body("No trains found for provided filters");
        }

        return ResponseEntity.ok(trains);
    }

}
