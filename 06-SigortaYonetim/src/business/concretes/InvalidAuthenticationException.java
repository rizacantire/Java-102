package business.concretes;

public class InvalidAuthenticationException extends Exception{
    public InvalidAuthenticationException() {

    }

    public String getError(){
        return "Hatalı kullanıcı adı veya şifre girdiniz.";
    }
}
