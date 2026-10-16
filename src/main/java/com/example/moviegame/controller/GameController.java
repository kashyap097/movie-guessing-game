package com.example.moviegame.controller;

import com.example.moviegame.model.GameState;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/game")
public class GameController {

    String randomMovie = "";
    private final List<String> bollywoodPool = new ArrayList<>();
    private final List<String> hollywoodPool = new ArrayList<>();

    @PostConstruct
    public void loadMoviesFromExcel() {
        try {
            ClassPathResource resource = new ClassPathResource("movies.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line;

            // पहली हेडर लाइन को स्किप करने के लिए (Bollywood Movie,Hollywood Movie)
            br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                // कॉमा (,) के आधार पर Column A और Column B को अलग करेंगे
                String[] columns = line.split(",", -1); // -1 ताकि खाली कॉलम भी हैंडल हो सकें

                // 🎬 Column A: Bollywood Movie
                if (columns.length > 0 && !columns[0].trim().isEmpty()) {
                    bollywoodPool.add(columns[0].trim().toUpperCase());
                }

                // 🎥 Column B: Hollywood Movie
                if (columns.length > 1 && !columns[1].trim().isEmpty()) {
                    hollywoodPool.add(columns[1].trim().toUpperCase());
                }
            }
            br.close();
            System.out.println("🔥 डेटा सफलतापूर्वक लोड हुआ! बॉलीवुड: " + bollywoodPool.size() + ", हॉलीवुड: " + hollywoodPool.size());
        } catch (Exception e) {
            System.err.println("❌ एक्सेल फ़ाइल रीड करने में एरर आया: " + e.getMessage());
            // फ़ॉलबैक: अगर फ़ाइल न मिले तो कम से कम एक-एक मूवी रहे
            bollywoodPool.add("SHOLAY");
            hollywoodPool.add("INCEPTION");
        }
    }
//    @GetMapping("/new")
//    public String startNewGame(HttpSession session) {
//        Random rand = new Random();
//        String movieType;
////        randomMovie = movies.get(rand.nextInt(movies.size()));
//        if (rand.nextBoolean()) {
//            randomMovie = bollywoodMovies.get(rand.nextInt(bollywoodMovies.size()));
//            movieType = "bollywood";
//        } else {
//            randomMovie = hollywoodMovies.get(rand.nextInt(hollywoodMovies.size()));
//            movieType = "hollywood";
//        }
//        if (randomMovie.contains("-"))
//            randomMovie = randomMovie.replace("-"," ");
//        // सेशन में नया गेम स्टेट सेव करें
//        session.setAttribute("gameState", new GameState(randomMovie,movieType));
//        return "redirect:/game";
//    }

    @GetMapping("/new")
    public String startNewGame(HttpSession session) {
        Random rand = new Random();
//        String selectedMovie = "";
        String movieType = "";

        // रैंडम तय करेंगे कि इस बार बॉलीवुड गेम खेलना है या हॉलीवुड
        boolean pickBollywood = rand.nextBoolean();

        if (pickBollywood && !bollywoodPool.isEmpty()) {
            randomMovie = bollywoodPool.get(rand.nextInt(bollywoodPool.size()));
            movieType = "bollywood";
        } else if (!hollywoodPool.isEmpty()) {
            randomMovie = hollywoodPool.get(rand.nextInt(hollywoodPool.size()));
            movieType = "hollywood";
        } else {
            // अगर दोनों पूल खाली हों
            return "redirect:/game";
        }

        if (randomMovie.contains("-"))
            randomMovie = randomMovie.replace("-"," ");

        session.setAttribute("gameState", new GameState(randomMovie, movieType));
        return "redirect:/game";
    }

    @GetMapping
    public String showGame(HttpSession session, Model model) {
        GameState gameState = (GameState) session.getAttribute("gameState");
        if (gameState == null) {
            return "redirect:/game/new";
        }

//        model.addAttribute("displayedName", gameState.getDisplayedName());
        model.addAttribute("movieLetters", gameState.getDisplayedNameList()); // 💡 लिस्ट पास की
        model.addAttribute("attempts", gameState.getRemainingAttempts());
        model.addAttribute("isGameOver", gameState.isGameOver());
        model.addAttribute("isWon", gameState.isWon());
        model.addAttribute("guessedLetters", gameState.getGuessedLetters());
        model.addAttribute("orginalMovie", randomMovie);
        model.addAttribute("movieType", gameState.getMovieType());
        return "game"; // game.html टेम्पलेट लोड होगा
    }

    @PostMapping("/guess")
    public String guessLetter(@RequestParam char letter, HttpSession session) {
        GameState gameState = (GameState) session.getAttribute("gameState");
        if (gameState != null && !gameState.isGameOver()) {
            gameState.makeGuess(letter);
        }
        return "redirect:/game";
    }
}

