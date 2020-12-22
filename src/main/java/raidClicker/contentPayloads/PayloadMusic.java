package raidClicker.contentPayloads;

import raidClicker.contentPayloads.handlers.MusicHandler;

public final class PayloadMusic implements IPayloadInformation {
    private final MusicHandler.Songs song;

    public PayloadMusic(MusicHandler.Songs song) {
        this.song = song;
    }

    public MusicHandler.Songs getSong() {
        return song;
    }
}
