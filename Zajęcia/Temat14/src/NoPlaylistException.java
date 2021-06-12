class NoPlaylistException extends Exception {

	NoPlaylistException(String s) {
        super(s);
    }

    NoPlaylistException() {
        this("There are no playlists! Go, create some!");
    }
}
