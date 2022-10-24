class Song(aName:String, anArtist:Artist, alength:String) {
    var name = aName;
    var artist = anArtist;
    var length = alength;

    fun showInfo() {
        println("Information about '$name':\nArtist: ${artist.firstName} ${artist.lastName}\nLength: $length")
    }
}