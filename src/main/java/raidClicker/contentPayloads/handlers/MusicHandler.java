package raidClicker.contentPayloads.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import raidClicker.components.NoComponentRequired;
import raidClicker.contentPayloads.PayloadMusic;
import raidClicker.exceptions.SongNotFound;
import sun.audio.AudioStream;

import static raidClicker.contentPayloads.handlers.MusicHandler.Songs.MUSHROOM;
import static sun.audio.AudioPlayer.player;

public final class MusicHandler extends ComponentHandler<NoComponentRequired, PayloadMusic> {

    public MusicHandler(NoComponentRequired component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadMusic payload) {
        switch (payload.getSong()) {
            case MUSHROOM: {
                playSong(MUSHROOM);
                return;
            }
            default: throw new SongNotFound();
        }
    }

    private void playSong(Songs song) {
        player.start(loadSound(song.fileName));
    }

    private AudioStream loadSound(String fileName) {
        try {
            InputStream audio = getClass().getResourceAsStream(fileName);
            return new AudioStream(audio);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public enum Songs {
        MUSHROOM("mushroomSound.au");

        String fileName;

        Songs(String fileName) {
            this.fileName = fileName;
        }
    }
}
