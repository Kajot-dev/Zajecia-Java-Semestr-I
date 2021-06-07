public class EmptyPlaylistException extends Exception{
    EmptyPlaylistException() {
        super("Playlista nie zawiera utworow!");
    }
}
