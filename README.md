# Scrawl - A Collaborative Note-Taking Android Application

## Objective
Scrawl is an Android application which allows the users to store their information within the app. The application requires Android OS version Marshmallow or above. This simple note taking application lets its users save their notes into their personal account. Moreover, they can archive their notes into different categories. For Instance, if the users want to take a note but want to add labels to archive it separately, they’ll be able to do so in this application.

This application allows users to create an account by providing their email id and password. The credential received will be handled in a secure manner. After the account is created successfully, users are taken to the main screen where all their notes are kept. If the users don’t have any note then the list will be empty. All the notes users take will be stored in a remote database. The users get to see a list of all the notes they have taken and have the ability to edit them anytime. Furthermore,  Scrawl allows the users to select any text from anywhere and add it as a new note.

The application has different bells and whistles like it provide the ability to user to share their notes, in collaboration mode, with other users. The collaborated note won’t have a delete option for the user who has receives the note from other users. Additionally, the application has an inbuilt bad-language filter which replaces bad words with an asterisk.

## Technology Used
**Programming Languages**
* Java (Android)
* XML (Android)
* PHP
* JavaScript (Postman Test)

**Tools**
* PHPStorm
* Android Studio
* MySQL Workbench
* Postman

**Database**
* MariaDB

**Logging**
* Firebase Crashlytics

**Continuous Integration**
* Jenkins CI

**Testing**
* JUnit4
* Roboelectric
* PHPUnit
* AndroidJUnit4
* Postman API Test Script (JavaScript)
* Amazon Web Service Device Farm

**Deployment**
* Diawi (App Deployment)
* Bluenose (API)

**Team Management**
* Trello
* Slack

## Workflow
We followed GitFlow [1] workflow for our project by assigning very specific roles to different branches of our GitHub repository and defining how and when they should interact. However, instead of making multiple feature branches we decided to create individual develop branches for every member of our team. We created three core branches, namely master, test, and devint. The devint branch was used to push new features and run on our development environment. The test branch was to test our application with Unit Test, Instrumentation Test, and Monkey Test (AWS Device Farm)[2]. The master branch was our release branch which was used to deploy our finished product to diawi.com [3]. We do have some hot fix branches.

![](http://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image4.png)

**_Fig. 1 GitFlow HotFix Branches [1]_**

### Code Review
For our project, we made our core branches (master, test, and devint) protected on GitHub so that each an every pull request will be reviewed by at least one other member of the team before merging it. 

![](http://res.cloudinary.com/dalcc/image/upload/v1534702609/QA_Project/image2.png)
**_Fig.2 Pull Request Review Sample_**

## Continuous Integration & Delivery
As we were working as a team, we needed to set up an environment where any member of the team can push their code to our core branches and it automatically builds the application, tests it and finally deploys it for public use. 
We decided to setup Jenkins [4] which helped us to automate the non-human part of our application development process, with integration and facilitating technical aspects of continuous delivery.

We setup three jobs in our Jenkins server with specific roles in association with our GitHub core branches.

![](http://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image21.png)
**_Fig.3 Jenkins Job List_**

### Job #1: Group 19

This job was responsible for building the application after any member of our team merge pull request to the devint branch. This job checks for any new merge every 2 minutes. If the build for any reason it sends out the mail to each member of the group along with the details on what was the reason for the failure. In this job we are also generating lint reports.

![](http://res.cloudinary.com/dalcc/image/upload/v1534702610/QA_Project/image3.png)
**_Fig.4 Successful Build Example_**

![](http://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image20.png)
**_Fig.5 Failed Build Example_**

![](http://res.cloudinary.com/dalcc/image/upload/v1534702608/QA_Project/image15.png)
**_Fig.6 Lint Report Sample_**



### Job #2: Group 19-TEST

This job was responsible for building the application after any member of our team merge pull request to the test branch. Its specific role is to sign the .apk (Android Application Package) file generated after the build is successfully finished. After the apk file is signed successfully it is sent to Amazon Web Service (AWS) Device Farm to carry out monkey testing on multiple devices with different versions of Android OS. AWS Device Farm is an app testing service that lets you test Android, iOS, and web apps on many devices at once, or reproduce issues on a device in real time. Our device pool is mentioned in the following screenshot. AWS Device Farm also provides a short video of the test carried out along with the timed log with an event or error details.

![](https://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image19.png)
**_Fig.7 Device Pool_**

![](https://res.cloudinary.com/dalcc/image/upload/v1534702608/QA_Project/image24.png)
**_Fig.8 AWS Device Farm Setup_**

![](https://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image23.png)
**_Fig.9 AWS Device Farm Test Video Sample_**

![](https://res.cloudinary.com/dalcc/image/upload/v1534702608/QA_Project/image16.png)
**_Fig.10 AWS Device Farm Log_**


### Job #3: Group 19-RELEASE

This job was responsible for building the application as we finalize to release and deploy the application. The specific job of this job is to deploy the signed apk to diwai.com [3]. Diawi is a tool for developers to deploy Development and In-house applications directly to the devices. However, the issue with diwai is that for a free account the link to application changes after every Jenkins build and the deployed application expires in 5 days. However, we have provided supporting screenshots below from Jenkins build and Diwai Portal.

![](https://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image22.png)
**_Fig.11 Jenkins Release Job Deployment_**

![](https://res.cloudinary.com/dalcc/image/upload/v1534702609/QA_Project/image30.png)
**_Fig. 12 Diwai Deployment Portal_**


## Logging and Crashlytics
### 1. Firebase + Crashlytics
![](https://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image7.png)

For logging and register user engagement with our application, we decided to use Firebase-SDK. Firebase[5] provides us with a dashboard where we can monitor the user’s engagement with each activity. This helps us to understand where user spends most of their time on the app and maybe we can use that opportunity to add Advertisement or give priority to add new features in that activity.

![](https://res.cloudinary.com/dalcc/image/upload/v1534702611/QA_Project/image11.png)

Similarly, we used crashlytics which catch the logs with the device name and time when the application crashes. This comes in handy because we don’t have direct control of the application once it gets deployed the developers cannot make changes to the app. Also, since Android is a versatile platform with many different devices, there are more chances of the app getting crashed. These bugs often go unnoticed due to lack of testing devices in the company.

![](https://res.cloudinary.com/dalcc/image/upload/v1534702609/QA_Project/image17.png)

Additionally, we get other features like the location of users who are using our application. This feature lets us control the Locale and language preference if our application needs one. Combining this with AdMob[6] we can also choose to filter the type of advertisement we can show in our application. A  really good example of this could be to ban the liquor advertisements in Muslim dominating countries since liquor is considered taboo in those countries.

![](https://res.cloudinary.com/dalcc/image/upload/v1534702608/QA_Project/image14.png)

### 2.Php (Backend) Logging

We also have implemented logger for our backend logic. We are using PHP to handle request from the device and generate a response in the form JSON which is read by the Android application using RetroFit library. 

**Login And Registration API Log**

![](https://res.cloudinary.com/dalcc/image/upload/v1534702608/QA_Project/image27.png)

File link: https://web.cs.dal.ca/~kamath/QA_Devint/log.txt

**HTTP Request Logger For Note API**

![](https://res.cloudinary.com/dalcc/image/upload/v1534702608/QA_Project/image26.png)

File Link: https://web.cs.dal.ca/~kamath/QA_Devint/NoteApi/includes/request.log 

**Tracker for Login and Registration**

![](https://res.cloudinary.com/dalcc/image/upload/v1534702609/QA_Project/image25.png)


File Link: https://web.cs.dal.ca/~kamath/QA_Devint/tracker.txt


**Error/Event Logging for Note API**

![](https://res.cloudinary.com/dalcc/image/upload/v1534702609/QA_Project/image8.png)


File Link: https://web.cs.dal.ca/~kamath/QA_Devint/NoteApi/includes/debug.log 

## Product Backlog
After submitting our project proposal we created a product backlog and took an internal vote for each an every user story and assigned story points. Based on the story points we started working on the stories.

![](https://res.cloudinary.com/dalcc/image/upload/v1534702609/QA_Project/image29.png)

## Sprint Details
We used Trello for managing our sprints. We divided our work in four sprints. 

![](https://res.cloudinary.com/dalcc/image/upload/v1534702610/QA_Project/image10.png)

Sprint 1:

* Setting up the development environment for the Android application.
* Setting up the development environment for PHP backend application.
* Setting up continuous integration using Jenkins. 
* Working on XML layouts for Android Application.
* Working on API (PHP) for CRUD operation of notes. 

Sprint 2:

* Working on API (PHP) for CRUD operation of labels.
* Working on the Android application (JAVA) for CRUD operation of notes.
* Working on API (PHP) for user login and registration.
* Working on the Android application (JAVA) for User Login and Registration.
* Adding Error Handling and loggers for all the backend API.

Sprint 3:

* Adding firebase crashlytics for error and event logging for Android Application.
* Setting up deployment environment on Jenkins.
* Setting up deployment on Diawi portal.
* Refactoring code for improving adding design patterns.
* Working on PHPUnit test cases for all the API’s.
* Working on Postman API test.
* Working on note sharing API (PHP).
* Working on Android application activities (JAVA) for implementing the sharing feature.

Sprint 4: 

* Working on Android activities (JAVA) for Adding, editing and deleting labels.
* Working on expresso tests for android activities.
* Woking on Postman API tests.
* Working on Unit Test for Android POJO classes.
* Working on Unit Test for PHP backend API.
* Optimizing imports for Java code.


## Team Roles

Sprint A (2 Weeks)

|Members   	|Roles   	|
|---	|---	|
|Aman Tewary   	|Scrum Master	|
|Nikhil Kamath  	|Developer   	|
|Haofan Hou   	|Developer/Tester   	|

Sprint B (2 Weeks)

|Members   	|Roles   	|
|---	|---	|
|Aman Tewary   	|Developer	|
|Nikhil Kamath  	|Scrum Master  	|
|Haofan Hou   	|Developer  	|


Sprint C (2 Weeks)

|Members   	|Roles   	|
|---	|---	|
|Aman Tewary   	|Scrum Master/Developer	|
|Nikhil Kamath  	|Developer /Tester  |
|Haofan Hou   	|Developer   |

Sprint D (2 Weeks)

|Members   	|Roles   	|
|---	|---	|
|Aman Tewary   	|Developer/Tester	|
|Nikhil Kamath  	|Developer/Tester   	|
|Haofan Hou   	|Scrum Masterr   	|

## Separation of Logic

Since we were developing in this project with scrutiny, we have made sure we try to separate the presentation layer, data layer and business layer. Android application development is beautiful because even though if someone is developing an application without even keeping design patterns in his/her head, the process of development will make sure that we follow some pattern. 

In this project, we have used Interface Segregation and Single responsibility principle where we felt it was required. There’s an entire folder full of interfaces in our project which keeps our project organized. Even our backend has separate files for getting GET or POST data and generate a response and calling stored procedures along with password hashing.

For instance, in Android when the user tries to add a new note, he opens the AddNotesActivity.java (Presentation layer) and writes something. The censor that checks every letter (Business Layer) is present in a separate file which is called InputHandler.java. Finally when the user enters the post button, the file that sends the data to the backend is written in a separate file called NotesRequestHandler.java.


## Examples of Refactoring

There were  a lot of changes over the period of 3 weeks in both backend and Android application. We  have many examples when we made changes and refactoring but to list a 

### 1. Add Notes and Edit Notes (Extract Method)
	
**_InputHandler.java_**
![](https://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image13.png)
The image above shows a function that was inside the functions shown in the image below.

**_AddNoteActivity.java_**
![](https://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image6.png)

**_EditNoteActivity.java_**
![](https://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image12.png)


### 2. Sharedpreference (Long parameter list)

![](https://res.cloudinary.com/dalcc/image/upload/v1534702609/QA_Project/image18.png)

The image above shows how we were using a long parameter inside createLoginSession() method (which was going to get bigger). We decided to send the object of User class as parameter instead of a many parameters. The image below shows how we refactored it.

![](https://res.cloudinary.com/dalcc/image/upload/v1534702607/QA_Project/image5.png)
 

### 3. doRealtimeCheck (Method Extraction)

![](https://res.cloudinary.com/dalcc/image/upload/v1534702609/QA_Project/image31.png)

Previously this method was used check two edittexts and see whether they had bad word or not. This was happening in AddNotesActivity and EditNotesActivity. But then we decided to extract the method and wrap it around and the image below shows how we were able to achieve it.

![](https://res.cloudinary.com/dalcc/image/upload/v1534702609/QA_Project/image1.png)


We decided to send the Edittext object instead which could be called in both AddNotesActivity and EditNotesActivity.

## Design Patterns

### 1. Singletons

We have implemented 4 Singletons patterns in this application. The use of each of them are explained below:

#### AppURL

We are accessing the base url where we have php files to handle incoming information, via POST and GET, to generate JSON response. We decided to use singleton to store the base url so that it can be accessible within the app at the same time making sure that no one can modify it. This pattern can be easily found in the AppURLs.java file.

https://github.com/DalhousieUniversityCSCI5308/Group19/blob/master/app/src/main/java/com/example/amantewary/scrawl/AppURLs.java

#### Retrofit Instance
		
Gone are the days when we could connect to a database through Android application via JDBC/ODBC connections. They were not secure and hence were deprecated. We have used Retrofit to parse the JSON response from our weburl. Retrofit converts the JSON response into Java Objects. Since, the core retrofit was used widely used throughout the application, we decided to make it a singleton to save us the hassle of rewriting it again and again.

This file shows the implementation of singleton RetroFitInstance.java

#### Label Loader

The labels in our applications are stored in SharedPreferences. SharedPreferences are basically a database which is unstructured and non relational where people can store data in the OS which will work even when the app is closed or even uninstalled. They work like key value pairs and are not suitable for storing crucial information. We are using it store the information about labels in this case. To access the SharedPreferences from anywhere, we have used Singleton This class also lets us to add and fetch the list of labels and hence makes everything a lot easier. 

The implementation can be found here in LabelLoader.java

### 2. Observer

#### The Bad-Word filter

When the user tries to enter a content inside the EditText, a listener listens to the changes happening to the Edittext and sends the text to a function which constantly checks whether the entered text is a bad abusive word or not. The Observer comes into the action when our bad language censor detects a bad word and notifies the the activity which was letting the user to add notes. The activity reads the update and generate an Android toast saying “bad words will be censored”.

The files that showcases this pattern can be found in InputHandler.java, AddNotesActivity.java and EditNotesActivity.java

#### Custom Navigation Drawer

When the user opens the navigation drawer in the application, they can add	new labels from the drawer by clicking the Add Label button. Since this is a		custom navigation drawer and placed in a separate java file, there was no way to add a visual feedback that a new label is created. To notify the class that the button is clicked, we have used a observer that will notify the class which has the logic to handle the UI change.

The classes where we have implemented Observer pattern are 
NavigationDrawerAdapter.java and NavObserver.java

		
#### State

In this app, we have types of note - the notes created by the current user and the notes shared by others, and for each type of notes we hope to give user different level of permission to operate with the note. For example, for the owned notes, users are allowed to share the notes and delete the notes, while for the shared notes, they are not supposed to do so as the notes are not owned by themselves.
In this case, the app needs to run different activities with different UI and business logic for viewing notes. We regard the two types of notes as two states of notes. When users click a note on their note list, the app will direct them to the proper activity based on the state of the clicked note. The files used to implement State Pattern can be seen below:

* **NoteState**
NoteState is an interface includes a method with which the app will direct user to the proper activity.

* **NoteContext**
NoteContext is a class which stores and indicates the current state of the notes.

* **SharedNote**
SharedNote is a class which implements NoteState interface and complete the codes for directing users to ViewSharedNoteActivity. ViewSharedNoteActivity is the activity for viewing shared notes.

* **ViewNote**
ViewNote is a class which implements NoteState interface and complete the codes for directing users to ViewNotesActivity. ViewNotesActivity is the activity for viewing owned notes.



#### Data Access Object (DAO)

Since this is an Android application, whenever we wanted to store any data from the response from the server, we have used DAO because that is how Retrofit needs to work. There are ample of examples in this project where we have DAOs. There’s a seperate folder where we have kept all the files which also helps in maintaining the project files.

This folder contains most of the DAOs. Handlers.

## Tests
### PHPUnit Test

We created server-side API’s for notes (CRUD), labels(CRUD), sharing notes, user login and registration using PHP. We wrote tests to cover every CRUD operation for notes and labels. We are also covering PHP methods responsible for login and registration of the user. This helped us in writing error-free code for our data layer to avoid any unexpected crash. 

### Postman Test

Apart from the PHP unit test cases, we also covered REST api tests using Postman. We tested all the endpoints where we generating the JSON response and wrote different tests cases to test the schema and whether we are actually receiving a the response we are expecting. For this particular test, we didn’t follow TDD because it is impossible to test the responses without actually writing the code to generate the response. 

![](https://res.cloudinary.com/dalcc/image/upload/v1534702610/QA_Project/image9.png)

### AndroidJUnit4 Test (Espresso):

We did expresso test for every function in our android application. With espresso test, we tried to cover UI as well as functionalities on a higher level. This step was taken mostly because most of our methods in Android have no return value and are interconnected together.

NOTE: Before running Espresso Tests (except for LoginTest and ActivityRegisterTest) you need to first login in the application. We know that it is not a good practice to add a condition for running test. However, to make it simple for evaluation we didn’t mock the login. This was done to avoid a crash in case the user is already logged in during evaluation.

### JUnit Test:
We added the unit test for simple POJO classes in our android application. We didn’t write many unit tests because most of the activities, classes, and methods are interconnected and it was impossible to break it into a singular unit. Therefore, we added an espresso test which combines multiple units together to test a single functionality.


## Functionalities
* User Authentication
    * Login
    * Logout
    * Registration
* CRUD operations of notes
    * Creating notes
    * Adding link in a note
    * Updating notes
    * Deleting notes
    * Showing all notes in a list
    *Viewing the detail of a specific note
* Note searching and labeling
    * Searching for specific notes
    * Filtering notes by labels
    * Labeling notes
    * Creating labels and renaming labels
* Note Sharing
    * Sharing notes with third-party apps
    * Sharing notes within the app and collaborating on notes edit with other users
* Other functionalities
    * Setting reminder for notes
    * Bad language filter


## Technical Debts

### Real-time collaboration

We were planning to make the collaboration real-time, similar to google doc so that the users collaborating editing a note can see the changes made by others synchronously. However, since the project is required to use the MySQL database which is not a real-time database, it will be really hard to achieve this purpose. If we want the notes to be updated synchronously, the page needs to be refreshed every second at the back end, which will significantly reduce the performance of the app. Thus, we have to give up this plan. In the future, if we could move our database to Firebase real-time database, this function will be possible.

### Custom Navigation Drawer

This is the part where we had to make navigation drawer from scratch and then we had to make sure that users were able to edit the labels within the drawer. Since, we had little to none experience in working with navigation let alone, creating a custom one was quite a task. The code in the NavigationDrawerHandler is not at all modifiable and it’s highly likely that the logic will break even if we had to new functionality in future. The entire code in that file is written to cater only logic and it’s rigid and highly coupled. 

### Single Request Handler Class

In our application we created four separate request handlers namely, NotesRequestHandler, LabelRequestHandler, ShareRequestHandler and UserRequestHandler. Even Though, each method inside these classes are specific role and in no way repetitive, there are certain part of code inside these methods that is repeating. We tried to rectify this using generic class and methods but was not successful. This is mainly because that part of code is inbuilt method of RetroFit. If had more time we could have tried to implement command pattern. 

## Configurable Business Logic

Configurable business logic allows business users to make some changes to cater the needs of businesses without reprogramming or changing information in the application[7]. In our Android application, we have four labels which are fixed for all the users. Which means, all the users of our application can see those four labels which are “Personal”, “Important”, “Work” and “Reminder”. However, if we choose to add or rather remove a label from the application, we can do that directly from our side, without altering the application or releasing a new version. 

Furthermore, we can also alter the language of these default labels depending the user’s locale. New Labels with the user’s preferred language can be added by them since keyboards have different language options. This is when our Firebase Analytics come into play where we can see where most of our users are located. A good example of this would be of a user who is living in Japan and has an Android phone with Japanese keyboard. We can change our default labels into Japanese so that the users can read the default labels. With the Japanese keyboard, the user can add new labels which won’t crash the application.[7]




## References

[1]"Gitflow Workflow | Atlassian Git Tutorial", Atlassian, 2018. [Online]. Available: https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow. [Accessed: 03- Aug- 2018]

[2]"Mobile App Testing on Devices – AWS Device Farm", Amazon Web Services, Inc., 2018. [Online]. Available: https://aws.amazon.com/device-farm/. [Accessed: 03- Aug- 2018]

[3]"Upload your App - Diawi - Development and In-house Apps Wireless Installation", Diawi.com, 2018. [Online]. Available: https://www.diawi.com/. [Accessed: 03- Aug- 2018]

[4]"Jenkins User Documentation", Jenkins User Documentation, 2018. [Online]. Available: https://jenkins.io/doc/. [Accessed: 03- Aug- 2018]

[5]"Firebase", Firebase, 2018. [Online]. Available: https://firebase.google.com/products/. [Accessed: 03- Aug- 2018]

[6]"Google AdMob - Mobile App Monetization & In App Advertising", Google.com, 2018. [Online]. Available: https://www.google.com/admob/. [Accessed: 03- Aug- 2018]

[7] M. Perroni, "How Can Configurable Business Logic Improve Labeling?", Loftware.com, 2018. [Online]. Available: https://www.loftware.com/blog/how-can-configurable-business-logic-improve-responsiveness-for-labeling. [Accessed: 03- Aug- 2018].
