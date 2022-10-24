class Artist(afirstName:String, aLastName:String, aCountry:String, aState:String, anAge:Int) {
    var firstName = afirstName;
    var lastName = aLastName;
    var country = aCountry;
    var state = aState;
    var age = anAge;
    var songs = ArrayList<Song>();

    fun addSong(song:Song) {
        songs.add(song)
    }

    fun showInfo() {
        println("Information about $firstName $lastName:\nAge: $age\nFrom: $state, $country")
    }
}