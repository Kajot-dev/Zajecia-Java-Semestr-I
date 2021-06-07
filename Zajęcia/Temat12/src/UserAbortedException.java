
class UserAbortedException extends Exception {
    UserAbortedException() {
        super("Operacja przerwana przez uzytkownika!");
        Odtwarzacz.printYellow("Operacja przerwana przez uzytkownika!");
    }
}
