package com.example.trainapp.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/view/trains")
public class TrainViewController {

    private final TrainService trainService;

    public TrainViewController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/all")
    public String showTrains(@RequestParam(required = false) String line,
            @RequestParam(required = false) String direction, Model model) {

        if (line != null) {
            List<TrainResponse> filteredTrains = trainService.getFilteredTrains(line, null);
            model.addAttribute("trains", filteredTrains);

        } else {
            List<TrainResponse> trains = trainService.getAllTrains();
            model.addAttribute("trains", trains);
        }

        return "main";
    }

}
