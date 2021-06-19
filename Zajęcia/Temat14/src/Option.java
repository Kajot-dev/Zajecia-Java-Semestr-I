
interface Option {
    void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException;
    default void onError(OptionRunner current, Exception e) {
        OptionRunner.errHandler.handle(current, e);
    }
    default boolean accept() {
        return true;
    }


}
