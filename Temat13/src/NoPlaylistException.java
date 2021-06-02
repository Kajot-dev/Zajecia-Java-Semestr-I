class NoPlaylistException extends Exception {
    NoPlaylistException(String s) {
        super(s);
    }

    NoPlaylistException() {
        this("Nie ma juz wiecej playlist! Utworz wiecej, aby wykonac operacje!");
    }
}
