class UserAbortedException extends Exception {

	UserAbortedException() {
        super("Operation interrupted by user!");
    }
}
