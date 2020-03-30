package com.hrm.constants;

public class TestConstants {
	//This is the list of System Variables
    //Declared as 'public', so that it can be used in other classes of this project
    //Declared as 'static', so that we do not need to instantiate a class object
    //Declared as 'final', so that the value of this variable can be changed
    // 'String' & 'int' are the data type for storing a type of value 
	 //List of System Variables
	 public static final String URL = "http://www.store.demoqa.com";
	 public static final String Path_TestData = "D://Tools QA Projects//trunk//Hybrid KeyWord Driven//src//dataEngine//DataEngine.xlsx";
	 public static final String Path_OR = "D://Tools QA Projects//trunk//Hybrid KeyWord Driven//src//config//OR.txt";
	 public static final String File_TestData = "DataEngine.xlsx";
	 
	 //List of Data Sheet Column Numbers
	 public static final int Col_TestCaseID = 0; 
	 public static final int Col_TestScenarioID =1 ;
	 //This is the new column for 'Page Object'
	 public static final int Col_PageObject =3 ;
	 //This column is shifted from 3 to 4
	 public static final int Col_ActionKeyword =4 ;
	 
	 //List of Data Engine Excel sheets
	 public static final String Sheet_TestSteps = "Test Steps";
	 
	 //List of Test Data
	 public static final String UserName = "testuser_3";
	 public static final String Password = "Test@123";
	 
	 public static final String URL = "http://www.store.demoqa.com";
	 
     public static final String Username = "testuser_1";
 
     public static final String Password ="Test@123";
 
 public static final String Path_TestData = "D://ToolsQA//OnlineStore//src//testData//";
 
 public static final String File_TestData = "TestData.xlsx";
 
 //Test Data Sheet Columns
 
 public static final int Col_TestCaseName = 0; 
 
 public static final int Col_UserName =1 ;
 
 public static final int Col_Password = 2;
 
 public static final int Col_Browser = 3;
 
 public static final int Col_ProductType = 4;
 
 public static final int Col_ProductNumber = 5;
 
 public static final int Col_FirstName = 6;
 
 public static final int Col_LastName = 7;
 
 public static final int Col_Address = 8;
 
 public static final int Col_City = 9;
 
 public static final int Col_Country = 10;
 
 public static final int Col_Phone = 11;
 
 public static final int Col_Email = 12;
 
 public static final int Col_Result = 13;
 
 public static final String Path_ScreenShot = "D://ToolsQA//OnlineStore//src//Screenshots//";
}
