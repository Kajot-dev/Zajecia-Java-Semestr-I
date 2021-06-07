class NoPlaylistException extends Exception {
    NoPlaylistException(String s) {
        super(s);
        Odtwarzacz.printYellow(s);
    }

    NoPlaylistException() {
        this("Nie ma juz wiecej playlist! Utworz wiecej, aby wykonac operacje!");
    }
}
