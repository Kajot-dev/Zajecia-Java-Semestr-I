class NoPlaylistException extends Exception {
    NoPlaylistException() {
        super("Nie ma juz wiecej playlist! Utworz wiecej, aby wykonac operacje!");
        Odtwarzacz.printYellow("Nie ma juz wiecej playlist! Utworz wiecej, aby wykonac operacje!");
    }
}
