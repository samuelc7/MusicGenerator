fun main(args: Array<String>) {
    // Create reference map
    var refMap: Map<String, MutableList<Song>> = initializeSongs()
    println("Welcome to the Music Selection Program! Enter 'q' to quit at any time")
    // gameLoop to keep track of if we should continue in the main loop
    var gameLoop = true
    while (gameLoop) {
        // moodLoop to keep track if we should continue asking user for their mood...
        var moodLoop = true
        while (moodLoop) {
            moodLoop = false
            // Get the user's mood
            var userMood = getCurrentMood()
            if (userMood == "quit") {
                println("Bye Bye.")
                return
            }
            // Using the user's mood, select a song to send to the user
            var song = refMap[userMood]?.random()
            if (song != null) {
                println("I suggest you listen to '${song.name}' by ${song.artist.firstName} ${song.artist.lastName}")
            }
            // Find out what to do next
            var nextMove = getNextMove()
            when (nextMove) {
                "s" -> song?.showInfo()
                "a" -> song?.artist?.showInfo()
                "n" -> addNewSong(refMap)
                "m" -> moodLoop = true
                "q" -> {
                    println("Bye Bye.")
                    return
                }
            }
        }
        // Ask user if they want to play again
        println("Would you like to play again? (y/n)")
        var playAgain = readln()
        if (playAgain.lowercase() == "n" || playAgain.lowercase() == "q") {
            println("Bye Bye.")
            gameLoop = false
        }
    }
}

/**
 * This will interact with the user to get the necessary values to create new
 * Artist and Song objects to enter into the refMap.
 */
fun addNewSong(refMap: Map<String, MutableList<Song>>) {
    var moods = refMap.keys
    var invalidMood = true
    while (invalidMood) {
        println("Which mood does your song fall under?")
        for (m in moods) {
            println(m)
        }
        var inputMood = readln()
        if (inputMood in moods) {
            invalidMood = false
            println("What is the Song name: ")
            var name = readln()
            println("How long is the song (in mins)?")
            var length = readln()
            println("What is the Artist's first name?")
            var firstName = readln()
            println("What is the Artist's last name?")
            var lastName = readln()
            println("What country is $firstName $lastName from?")
            var country = readln()
            println("What state/city is $firstName $lastName from?")
            var state = readln()
            println("How old is $firstName $lastName?")
            var age = readln()

            var newArtist = Artist(firstName, lastName, country, state, age.toInt())
            var newSong = Song(name, newArtist, length)
            refMap[inputMood]!!.add(newSong)
        } else {
            println("Not a valid mood. Please try again.")
        }
    }
}

/**
 * This will retrieve the user's current mood, parse it, and return the mood
 * that matches one of the keys in the refMap the most
 */
fun getCurrentMood() : String {
    var options = arrayOf("h", "s", "l", "u", "q")
    var selection = "none"
    var validInput = false
    while (!validInput) {
        println(
            "Please select one of the following moods listed that fits your current mood the best:\n" +
                    "Happy (h)\nSad (s)\nIn Love (l)\nUnmotivated (u)\n"
        )
        selection = readln()
        validInput = selection.lowercase() in options
        if (!validInput)
            println("Not a valid option. Please Try again.")
    }

    var moodMap = mapOf("h" to "Happy", "s" to "Sad", "l" to "In Love", "u" to "Unmotivated")

    var mood:String
    if (selection != "q") {
        mood = moodMap[selection.lowercase()].toString()
    } else {
        mood = "quit"
    }
    return mood
}

/**
 * This finds out the next move.
 * The current options are:
 *  - Select another mood
 *  - View more info about the song
 *  - View more info about the artist
 *  - Add a new song
 */
fun getNextMove() : String {
    var options = arrayOf("m", "s", "a", "n", "q")
    var validInput = false
    var nextMove = "none"
    while (!validInput) {
        println(
            "\nWhat would you like to do next?\nSelect another mood (m)\n" +
                    "View more info about the song (s)\nView more info about the Artist (a)\n" +
                    "Add a new song for any of the moods listed (n)"
        )
        nextMove = readln()
        validInput = nextMove.lowercase() in options
        if (!validInput)
            println("Not a valid option. Please Try again.")
    }
    return nextMove
}


/**
 * This initializes the map of songs
 */
fun initializeSongs() : Map<String, MutableList<Song>> {
    // Start with a few songs for each category/mood
    // Happy
    var artist1 = Artist("Nicky", "Youre", "USA", "California", 23)
    var happy1 = Song("Sunroof", artist1, "2:40")
    var songMap = mapOf("Happy" to mutableListOf(happy1))

    // Sad
    var artist2 = Artist("Daniel", "Richard", "Canada", "Vernon", 51)
    var sad1 = Song("Bad Day", artist2, "3:55")
    songMap = songMap.plus(Pair("Sad", mutableListOf(sad1)))

    // In Love
    var artist3 = Artist("Calum", "Scott", "UK", "Kingston upon Hull", 34)
    var love1 = Song("You Are The Reason", artist3, "3:24")
    songMap = songMap.plus(Pair("In Love", mutableListOf(love1)))

    // Unmotivated
    var artist4 = Artist("Shakira", "Ripoll", "Colombia", "Barranquilla", 45)
    var unmotivated = Song("Try Everything", artist4, "3:22")
    songMap = songMap.plus(Pair("Unmotivated", mutableListOf(unmotivated)))

    return songMap
}