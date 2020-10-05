# TestApp
The project for AppCenter UI tests. 

The project consist of test application itself and the UI test for it (AppiumTests folder).  

- The application was created in accordance with the guide from the android portal. (_https://developer.android.com/training/basics/firstapp_).  
- The main activity contains some controls that allows to shows the entered text (picture 1).
- If you put your text in textbox and click 'Send' button, you will see the new activity with this text (picture 2).
- If you do the same actions but 'Show as alert' checkbox is activated, you will see the alert with this text (picture 3):
![image](https://user-images.githubusercontent.com/56116604/95071078-ca038980-0711-11eb-9c16-e5bb7eabedf3.png)
- If you click the 'Reset' button, the textbox and the checkbox will be reseted.

# UI test
About test:
This was written using Appium test framework. The est may be executed either on local machine or in appcenter cloud.

To test this application you should:

- Install npm package _appcenter_ globally:
  `npm install -g appcenter-cli`

- Build the apk file of the test application (it must be signed).

- Execute the follow command in AppiumTests folder:
  `mvn -DskipTests -P prepare-for-upload package`

- Execute one more commnad in AppiumTests folder:
  `appcenter test run appium --app "APP_ID" --devices "DEVICE_SET_ID" --app-path PATH_TO_FILE.apk  --test-series "master" --locale "en_US" --build-dir target/upload`
where  _APP_ID_, _DEVICE_SET_ID_ and _PATH_TO_FILE.apk_ must be replaced with real values.
You may get  _APP_ID_ and _DEVICE_SET_ID_ values from appcenter if you go to appcenter in Test section and click 'New test run'. You should select  necessary devises there and select 'Appium' as test framework. You will see description like this:
![image](https://user-images.githubusercontent.com/56116604/95076182-1fdc2f80-071a-11eb-92e9-1967dae49ccf.png)

- Wait for completion. It may take a few minutes.
- If you do all correctly, you will see result of the tests in appcenter in Test section:

![image](https://user-images.githubusercontent.com/56116604/95076815-215a2780-071b-11eb-9988-a4e86cfcf030.png)
