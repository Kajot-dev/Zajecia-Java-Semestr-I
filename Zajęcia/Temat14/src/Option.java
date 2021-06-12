
interface Option {
    void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException;
    default void onError(Exception e) {
        MusicPlayer.errorHandler(e);
    }
    default boolean accept() {
        return true;
    }


}
