package models.exceptions;
public class UserIsNotAuthenticatedException extends Exception{
	public UserIsNotAuthenticatedException( String message, Throwable e) {
		super (message, e);
	}
}
