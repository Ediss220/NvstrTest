# NvstrTest
NVSTR Project
Hi,

This archive contains: 

Test cases: Nvstr Test Cases.pdf or Nvstr Test Cases.xlsx

Postman file: Nvstr.postman_collection.json

Before running the tests, you must install the software using this link https://www.jetbrains.com/idea/download/ 

You must select the type of operating system and install the product version on the local Community computer.

After successful installation of IDEA, you need to open the NvstrTest project (https://github.com/Ediss220/NvstrTest.git)specifying the path to the location of this folder on the local computer.

All dependencies are written and imported in the project; installation of additional components is not required.

All test cases described in the TC file - Nvstr Test Cases.pdf or Nvstr Test Cases.xlsx are automated. The NvstrTest provides examples of several test cases.

There are three files in the main project directory: 
WebDriverSettings - Data and Setting for Test 
NvstrUI - testing UI 
NvstrAPI - testing API 

To start UI test cases:

1.Select file NvstrUI
2.On the 13th lines click on the double green arrow and select RUN, or right-click on the corresponding file and select RUN.

To start API test cases:
1. Select file NvstrAPI 
2. On the 17th lines click on the green arrow and select RUN
3. When you get a result you should take info from Test result HEADERS and change three variables(accesstoken,client,expiry) you can find them in WebDriverSettings file, line 17th, 18th ,19th.
4. Then select file NvstrAPI
5. On the 10th lines click on the double green arrow and select RUN, or right-click on the corresponding file and select RUN.

Tests are performed automatically. The result of the test execution is displayed in the console.
I Added Video with tests execution and results 

If you have any questions please let me know

Thank you.
