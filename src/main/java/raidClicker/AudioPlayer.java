package raidClicker;

import static java.util.Objects.isNull;
import static sun.audio.AudioPlayer.player;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import sun.audio.AudioStream;

public final class AudioPlayer {
    private static AudioStream mushroomSound;
    private static final String MUSHROOM_SOUND_FILENAME = "mushroomSound.au";

    private static InputStream getMushroomSound(){
        if (isNull(mushroomSound)){
            try {
                InputStream audio = AudioPlayer.class.getResourceAsStream(MUSHROOM_SOUND_FILENAME);
                mushroomSound = new AudioStream(audio);
            }
            catch (IOException e)
            {
                throw new UncheckedIOException(e);
            }
        }
        return  mushroomSound;
    }

    public static void playMushroomSound(){
        player.start(getMushroomSound());
    }

}
