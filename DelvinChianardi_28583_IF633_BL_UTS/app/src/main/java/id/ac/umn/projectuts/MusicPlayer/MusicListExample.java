package id.ac.umn.projectuts.MusicPlayer;

import java.io.Serializable;

public class MusicListExample implements Serializable {
    private String mJudul;
    private String mMusicURI;

    public MusicListExample(String judul, String musicURI){
        mJudul = judul;
        mMusicURI = musicURI;
    }
    public String getJudul() { return mJudul; }
    public String getMusicURI() { return mMusicURI; }
}
