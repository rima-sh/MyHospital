public class WrongInputException extends Exception{
    @Override
    public void printStackTrace() {
        System.out.println("Your input is not valid!!!!");
    }
}
