package dataprovider;

import org.testng.annotations.DataProvider;

public class TestDataProviders {

	@DataProvider(name = "loginData", parallel = true)
    public static Object[][] loginData() {

        return new Object[][]{
                {"standard_user", "secret_sauce"},
        };
    }
	
	@DataProvider(name = "invalidloginData", parallel = true)
    public static Object[][] invalidloginData() {

        return new Object[][]{
                {"standard_useer", "wrong_password"},
        };
    }
	
	@DataProvider(name = "checkoutValidationWithLoginData")
	public static Object[][] checkoutValidationWithLoginData() {
	    return new Object[][]{
	        // username, password, firstName, lastName, postalCode, expectedError
	        {"standard_user", "secret_sauce", "Rohan", "", "400612", "Last Name"},
	        {"standard_user", "secret_sauce", "Rohan", "Rawool", "", "Postal Code"},
	        {"standard_user", "secret_sauce", "", "Rawool", "400612", "First Name"}
	    };
	}
    


}
