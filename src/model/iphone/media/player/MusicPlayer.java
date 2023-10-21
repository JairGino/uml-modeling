package model.iphone.media.player;

import model.iphone.media.Music;

public interface MusicPlayer {
    public Music findMusic(String musicName);
    public void playMusic(Music music);
    public void pauseMusic();
    public void terminateMusic();
    public void skipForward();
    public void skipBackward();
    public void changeVolume(Integer newVolume);
}
