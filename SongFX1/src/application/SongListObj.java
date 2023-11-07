
/*
 *  Done by: Aditi Patel & Aakaash Prakash Hemdev 
 */

package application;

public class SongListObj
{
	private String songName;
    private String artistName;
    private String songYear;
    private String songAlbum;

    public SongListObj(String songName, String artistName,String songAlbum, String songYear) {
        this.songName = songName;
        this.artistName = artistName;
        this.songAlbum = songAlbum;
        this.songYear = songYear;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongYear() {
        return songYear;
    }

    public void setSongYear(String songYear) {
        this.songYear = songYear;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }
}