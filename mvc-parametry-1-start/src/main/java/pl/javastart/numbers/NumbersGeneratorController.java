package pl.javastart.numbers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
class NumbersGeneratorController {

    private final NumberService numberService;

    public NumbersGeneratorController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/random")
    @ResponseBody
    String getRandomNumber(@RequestParam(required = false) Integer from,
                           @RequestParam(required = false) Integer to) {
        if (from > to) {
            return "Dolne ograniczenie musi być mniejsze lub równe górnemu ograniczeniu.";
        } else if (from == null && to != null) {
            from = to - 100;
        } else if (to == null && from != null) {
            to = from + 100;
        } else if (from == null && to == null) {
            from = 0;
            to = 1000;
        }
        int randomNumberResult = numberService.getRandomNumberFromRange(from, to);
        return String.format("Losowa liczba z przedziału [%d; %d]: %d", from, to, randomNumberResult);
    }

    @GetMapping("/even-numbers")
    @ResponseBody
    String getEvenNumbers(@RequestParam(required = false) Integer from,
                          @RequestParam(required = false) Integer to) {

        if (from > to) {
            return "Dolne ograniczenie musi być mniejsze lub równe górnemu ograniczeniu.";
        } else if (from == null && to != null) {
            from = to - 100;
        } else if (to == null && from != null) {
            to = from + 100;
        } else if (from == null && to == null) {
            from = 0;
            to = 1000;
        }
        List<Integer> evenNumbersResult = numberService.getEvenNumbersFromRange(from, to);
        return String.format("Parzyste liczby z przedziału [%d; %d]: ", from, to) + evenNumbersResult;
    }

    @GetMapping("/odd-numbers")
    @ResponseBody
    String getOddNumbers(@RequestParam(required = false) Integer from,
                         @RequestParam(required = false) Integer to) {
        if (incorrectParams(from, to)) {
            return "Dolne ograniczenie musi być mniejsze lub równe górnemu ograniczeniu.";
        } else if (from == null && to != null) {
            from = to - 100;
        } else if (to == null && from != null) {
            to = from + 100;
        } else if (from == null && to == null) {
            from = 0;
            to = 1000;
        }
        List<Integer> evenNumbersResult = numberService.getOddNumbersFromRange(from, to);
        return String.format("Nieparzyste liczby z przedziału [%d; %d]: ", from, to) + evenNumbersResult;
    }

    private boolean incorrectParams(Integer from, Integer to) {
        return from != null && to != null && from > to;
    }
}
