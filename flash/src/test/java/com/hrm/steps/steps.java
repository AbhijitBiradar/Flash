package com.hrm.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class steps {
	@Given("^user is on Login page$")
	public void user_is_on_Login_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	  System.out.println("user is on Login page");
	}

	@When("^user enter valid user name$")
	public void user_enter_valid_user_name() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 System.out.println("user enter valid user name");
	}

	@When("^user enter valid password$")
	public void user_enter_valid_password() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 System.out.println("user enter valid password");
	}

	@When("^user click on Login button$")
	public void user_click_on_Login_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 System.out.println("user click on Login button");
	}

	@Then("^application should login successfully$")
	public void application_should_login_successfully() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("application should login successfully");
	}
}
