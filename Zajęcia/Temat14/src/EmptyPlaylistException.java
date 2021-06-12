public class EmptyPlaylistException extends Exception{

	EmptyPlaylistException() {
        super("This playlist is empty!");
    }
}
