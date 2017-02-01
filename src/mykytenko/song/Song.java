package mykytenko.song;

public class Song {
    private String trackName;
    private String artist;
    private double rate;

    public String getTrackName() {
        return trackName;
    }

    public Song(String trackName, String artist, double rate) {
        this.trackName = trackName;
        this.artist = artist;
        this.rate = rate;
    }

    public static Song parse(String str){
        String[] fields = str.split("/");
        if(fields.length != 3){
            throw new RuntimeException("bad format for songs in file");
        }
        double rate = Double.parseDouble(fields[2]);
        return new Song(fields[0], fields[1], rate);
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Song{" +
                trackName + '/' +
                artist + '/' +
                rate +
                '}';
    }
}
