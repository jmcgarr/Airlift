package airlift.servlet.rest;

public class GoogleUserService
   implements UserService
{
	private com.google.appengine.api.users.UserService userService;
	
	private com.google.appengine.api.users.UserService getUserService() { return userService; }
	private void setUserService(com.google.appengine.api.users.UserService _userService) { userService = _userService; }

	protected GoogleUserService() { setUserService(com.google.appengine.api.users.UserServiceFactory.getUserService()); }
	
	//Returns a URL that can be used to display a login page to the user.
	public java.lang.String	createLoginURL(java.lang.String _destinationURL)
	{
		return getUserService().createLoginURL(_destinationURL);
	}

	//Returns a URL that can be used to display a login page to the user.
	public java.lang.String	createLoginURL(java.lang.String _destinationURL, java.lang.String _authDomain)
	{
		return getUserService().createLoginURL(_destinationURL, _authDomain);
	}

	//Returns a URL that can be used to redirect the user to for third party login for federated login the user.
	public java.lang.String	createLoginURL(java.lang.String _destinationURL, java.lang.String _authDomain, java.lang.String _federatedIdentity, java.util.Set<java.lang.String> _attributesRequest)
	{
		return getUserService().createLoginURL(_destinationURL, _authDomain, _federatedIdentity, _attributesRequest);
	}

	//Returns a URL that can be used to log the current user out of this app.
	public java.lang.String	createLogoutURL(java.lang.String _destinationURL)
	{
		return getUserService().createLogoutURL(_destinationURL);
	}

	//Returns a URL that can be used to log the current user out of this app.
	public java.lang.String	createLogoutURL(java.lang.String _destinationURL, java.lang.String _authDomain)
	{
		return getUserService().createLogoutURL(_destinationURL, _authDomain);
	}

	//If the user is logged in, this method will return a User that contains information about them.
	public AbstractUser	getCurrentUser()
	{
		return new GoogleUser(getUserService().getCurrentUser());
	}

	//Returns true if the user making this request is an admin for this application, false otherwise.
	public  boolean	isUserAdmin()
	{
		return getUserService().isUserAdmin();
	}

	//Returns true if there is a user logged in, false otherwise.
	public boolean isUserLoggedIn()
	{
		return getUserService().isUserLoggedIn();
	}
}