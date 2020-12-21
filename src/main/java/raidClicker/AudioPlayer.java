package raidClicker;

import java.io.InputStream;
import sun.audio.AudioStream;

public class AudioPlayer {

    public void playFile(String filename){
        AudioStream audioStream = getAudioFile(filename);
        sun.audio.AudioPlayer.player.start(audioStream);
    }

    private AudioStream getAudioFile(String filename)
    {
        try {
            // the sound file must be in the same directory as this class file.
            InputStream inputStream = getClass().getResourceAsStream(filename);
            return new AudioStream(inputStream);
        }
        catch (Exception e)
        {
            System.out.println("Failed to run audio file \n" + e);
        }
        return null;
    }
}
