
class UserAbortedException extends Exception {
    UserAbortedException() {
        super("Operacja przerwana przez uzytkownika!");
    }
}
