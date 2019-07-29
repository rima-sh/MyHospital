public class RepeatedPasswordIDException extends Exception
{
    @Override
    public void printStackTrace() {
        System.out.println("A user with this password ID already exists");
    }
}
