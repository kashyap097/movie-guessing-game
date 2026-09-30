package com.example.moviegame.controller;

import com.example.moviegame.model.GameState;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/game")
public class GameController {

private final List<String> movies = Arrays.asList(

        // ================= BOLLYWOOD =================
        "KAHO NAA PYAAR HAI",
        "MOHABBATEIN",
        "MISSION KASHMIR",
        "DULHAN HUM LE JAYENGE",
        "REFUGEE",
        "JOSH",
        "FIZA",
        "HARA DIL JO PYAR KAREGA",
        "HUMARA DIL AAPKE PAAS HAI",
        "MELA",

        "KABHI KHUSHI KABHIE GHAM",
        "LAGAAN",
        "DIL CHAHTA HAI",
        "GHADAR EK PREM KATHA",
        "KABHI KHUSHI KABHIE GHAM",
        "DEVDAS",
        "RAAZ",
        "AANKHEN",
        "KAANTE",
        "MUNNA BHAI M.B.B.S",
        "KAL HO NAA HO",
        "KOI MIL GAYA",
        "BAGHBAN",
        "TALAASH",
        "MAIN HOON NA",
        "VEER-ZAARA",
        "DHOOM",
        "SWADES",
        "HUM TUM",
        "NO ENTRY",
        "SARKAR",
        "BUNTY AUR BABLI",
        "DUS",
        "DHOOM 2",
        "KABHI ALVIDA NAA KEHNA",
        "DON",
        "OM SHANTI OM",
        "TAARE ZAMEEN PAR",
        "JAB WE MET",
        "WELCOME",
        "BHOOL BHULAIYAA",
        "SINGH IS KINNG",
        "GHAJINI",
        "RAB NE BANA DI JODI",
        "3 IDIOTS",
        "LOVE AAJ KAL",
        "WAKE UP SID",
        "KAMINEY",
        "MY NAME IS KHAN",
        "DABANGG",
        "BAND BAAJA BAARAAT",
        "ROCKSTAR",
        "ZINDAGI NA MILEGI DOBARA",
        "THE DIRTY PICTURE",
        "SINGHAM",
        "BODYGUARD",
        "BARFI",
        "VICKY DONOR",
        "EK THA TIGER",
        "JAB TAK HAI JAAN",
        "YAMLA PAGLA DEEWANA 2",
        "YEH JAWAANI HAI DEEWANI",
        "CHENNAI EXPRESS",
        "DHOOM 3",
        "KRRISH 3",
        "RAM-LEELA",
        "BHAAG MILKHA BHAAG",
        "QUEEN",
        "2 STATES",
        "KICK",
        "PK",
        "HAIDER",
        "MARY KOM",
        "BABY",
        "TANU WEDS MANU RETURNS",
        "BAJIRAO MASTANI",
        "PREM RATAN DHAN PAYO",
        "BAJRANGI BHAIJAAN",
        "DILWALE",
        "AIRLIFT",
        "MS DHONI THE UNTOLD STORY",
        "DANGAL",
        "SULTAN",
        "KABALI",
        "SECRET SUPERSTAR",
        "PADMAAVAT",
        "RAAZI",
        "SANJU",
        "STREE",
        "ANDHADHUN",
        "BADHAI HO",
        "SIMMBA",
        "URI THE SURGICAL STRIKE",
        "GULLY BOY",
        "KABIR SINGH",
        "WAR",
        "GOOD NEWWZ",
        "TANHAJI",
        "SHUBH MANGAL ZYADA SAAVDHAN",
        "LUDO",
        "SOORYAVANSHI",
        "SHERSHAAH",
        "83",
        "GANGUBAI KATHIAWADI",
        "RRR",
        "KGF CHAPTER 2",
        "BRAHMĀSTRA",
        "VIKRAM VEDHA",
        "DRISHYAM 2",
        "PATHAAN",
        "JAWAN",
        "GADAR 2",
        "ANIMAL",
        "DUNKI",
        "FIGHTER",
        "CREW",
        "STREE 2",
        "SINGHAM AGAIN",
        "BHOLAA",
        "SAM BAHADUR",
        "CHHAAVA",
        "SIKANDAR",
        "SON OF SARDAR 2",
        "WAR 2",
        "BORDER 2",
        "DHURANDHAR",
        "DHURANDHAR THE REVENGE",
        "BHOOTH BANGLA",
        "MIRZAPUR THE MOVIE"

     /*   // ================= HOLLYWOOD =================
       , "GLADIATOR",
        "MISSION IMPOSSIBLE 2",
        "CAST AWAY",
        "X-MEN",
        "THE MATRIX",
        "THE LORD OF THE RINGS",
        "HARRY POTTER",
        "PEARL HARBOR",
        "BLACK HAWK DOWN",
        "SPIDER-MAN",
        "MINORITY REPORT",
        "THE LORD OF THE RINGS THE TWO TOWERS",
        "THE LORD OF THE RINGS THE RETURN OF THE KING",
        "PIRATES OF THE CARIBBEAN",
        "FINDING NEMO",
        "THE LAST SAMURAI",
        "TROY",
        "THE DAY AFTER TOMORROW",
        "SPIDER-MAN 2",
        "BATMAN BEGINS",
        "KING KONG",
        "WAR OF THE WORLDS",
        "CHARLIE AND THE CHOCOLATE FACTORY",
        "CASINO ROYALE",
        "THE DEPARTED",
        "300",
        "TRANSFORMERS",
        "RATATOUILLE",
        "IRON MAN",
        "THE DARK KNIGHT",
        "TWILIGHT",
        "QUANTUM OF SOLACE",
        "AVATAR",
        "INGLOURIOUS BASTERDS",
        "SHERLOCK HOLMES",
        "INCEPTION",
        "TOY STORY 3",
        "HARRY POTTER AND THE DEATHLY HALLOWS",
        "THE AVENGERS",
        "THE DARK KNIGHT RISES",
        "THE HUNGER GAMES",
        "SKYFALL",
        "IRON MAN 3",
        "MAN OF STEEL",
        "GRAVITY",
        "INTERSTELLAR",
        "GUARDIANS OF THE GALAXY",
        "CAPTAIN AMERICA THE WINTER SOLDIER",
        "DAWN OF THE PLANET OF THE APES",
        "THE HOBBIT",
        "JURASSIC WORLD",
        "MAD MAX FURY ROAD",
        "AVENGERS AGE OF ULTRON",
        "MISSION IMPOSSIBLE ROGUE NATION",
        "DEADPOOL",
        "CAPTAIN AMERICA CIVIL WAR",
        "DOCTOR STRANGE",
        "ROGUE ONE",
        "LA LA LAND",
        "WONDER WOMAN",
        "SPIDER-MAN HOMECOMING",
        "THOR RAGNAROK",
        "BLACK PANTHER",
        "AVENGERS INFINITY WAR",
        "BOHEMIAN RHAPSODY",
        "AQUAMAN",
        "CAPTAIN MARVEL",
        "AVENGERS ENDGAME",
        "JOKER",
        "TOY STORY 4",
        "JUMANJI THE NEXT LEVEL",
        "1917",
        "TENET",
        "SOUL",
        "BLACK WIDOW",
        "NO TIME TO DIE",
        "DUNE",
        "SPIDER-MAN NO WAY HOME",
        "THE BATMAN",
        "TOP GUN MAVERICK",
        "DOCTOR STRANGE IN THE MULTIVERSE OF MADNESS",
        "JURASSIC WORLD DOMINION",
        "THOR LOVE AND THUNDER",
        "BLACK PANTHER WAKANDA FOREVER",
        "AVATAR THE WAY OF WATER",
        "JOHN WICK CHAPTER 4",
        "GUARDIANS OF THE GALAXY VOL 3",
        "OPPENHEIMER",
        "BARBIE",
        "MISSION IMPOSSIBLE DEAD RECKONING",
        "THE MARVELS",
        "GODZILLA X KONG THE NEW EMPIRE",
        "DUNE PART TWO",
        "DEADPOOL AND WOLVERINE",
        "INSIDE OUT 2",
        "GLADIATOR II",
        "MOANA 2",
        "CAPTAIN AMERICA BRAVE NEW WORLD",
        "SUPERMAN",
        "FANTASTIC FOUR FIRST STEPS",
        "JURASSIC WORLD REBIRTH",
        "AVATAR FIRE AND ASH",
        "MISSION IMPOSSIBLE THE FINAL RECKONING",
        "SPIDER-MAN BRAND NEW DAY"*/
);


    @GetMapping("/new")
    public String startNewGame(HttpSession session) {
        Random rand = new Random();
        String randomMovie = movies.get(rand.nextInt(movies.size()));

        // सेशन में नया गेम स्टेट सेव करें
        session.setAttribute("gameState", new GameState(randomMovie));
        return "redirect:/game";
    }

    @GetMapping
    public String showGame(HttpSession session, Model model) {
        GameState gameState = (GameState) session.getAttribute("gameState");
        if (gameState == null) {
            return "redirect:/game/new";
        }

        model.addAttribute("displayedName", gameState.getDisplayedName());
        model.addAttribute("attempts", gameState.getRemainingAttempts());
        model.addAttribute("isGameOver", gameState.isGameOver());
        model.addAttribute("isWon", gameState.isWon());
        model.addAttribute("guessedLetters", gameState.getGuessedLetters());

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

