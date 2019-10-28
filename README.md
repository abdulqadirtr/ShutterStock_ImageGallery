# ShutterStock_ImageGallery

 ** Description for the problem.
 
 1. ** Introduction: **
This is a Java android application for getting images from Shutterstock Web Api through the following Api link  https://api.shutterstock.com/v2/. The images are displayed in Recycleview and it can also be previewed.
Note: In order to get the images through Shutterstock API we need to get the Token or secret key for authentication, which can be obtained through the following link. https://developers.shutterstock.com/documentation

 2. ** FrameWork Used: **
Dagger2
Retrofi
Picasso
OkHttp
RxAndroid
Timber                          

**Whether the solution focuses on mobile, back-end, front-end or if it's full-stack?
The application provides smooth solution for full-stack.

**Backend Solution.
 WebServices **
 
For sending and receiving requests and uploading JSON from shutterstock API Retrofit and OkHttp frameworks is used in ShutterStock_ImageGallery application which make the application code easy and also relaible in terms of connection pooling, requesting, loading, threading, synchronization caching and network problems.


**Wiring up different dependencies using Dagger2 **

The Application is also using one of the most important Framework Dagger2 in order to wire up all the  Dependencies or frameworks for better performance. These are the ShutterStock_ImageGallery app beneafiths by applying  Dagger2 framework.
 Simple access to shared implementations.
There are many dependencies used in this application Dagger 2 allows to control all dependences easy.
Simple unit testing and integration testing. 


**FrontEnd Solution:**

 RxJava and RxAndroid (UI and View-Handling)
framewoks in the application make the application able to provide the user a smooth UI wihouth delay and hanging display. 
The application is managing threads and avoid using main threads for different task in order to make the User activity smooth.

                                  
                                  **********************************************
                                  
                                  

**The reasoning behind your technical choices, including architectural. ?

**For UI **
 
For the smooth UI and View i used RxJava and RxAndroid to make the  display smoot without hanging or delays because normal if we are running different activities or framgments on main threads, it cause crash or delays in the display because of threads mangement or main thread is not free. these frameworks handles threads and work for different task with different threads and free up the main thread.

RecylerView: As there are too many pictures soo i used recyclerView which shows the images in Grid and easy for user to go through all the images in a short time.

**For WebServices:**
This application is dealing with ShutterStock Web API's and uploadinig JSon files,  normally in Android it's difficult to write the client, server code, that's why i used Retrofit and OkHttp to overcome this problem and make the code simple.

**Handling the Dependencies:**
As there are many dependencie in this application which was very difficult to manage and can also impact the performance.
To solve this problem i used Dagger2 which combined all the dependencie in one Module, which will boost the performance.

**Lose Coupling**
 Loose coupling is concept is applied in the application to reduce the inter-dependencies between components of a system with the goal of reducing the risk that changes in one component will require changes in any other component.
 This is one of the good reasons I used Dagger 2 for dependency injection. If the dependency was injected to the presenter without view’s intervention, the code becomes more modular and loosely connected which helps in easily changing one component without affecting another.
 
 
**Trade-offs you might have made, anything you left out, or what you might do differently if you were to spend additional time on the project                  
.**


 **Language:** 
I personally prefer Kotlin for Android application developement as it's more compatible for Android.
Unfortunatly i was not able to do this task in Kotlin,which i missed because of the reason lack of time and the frameworks like dagger2 which i never used before so that's why i chose Java.

**Things which i missed and want to add:**
 I would like to add downloading and storage features for Images in this task .
 I would avoid to use Recyclerview as it's more complicated.
 I would developed the application in Kotlin too.
 I would use Gson Library
 
 
 
                               **********************************************
 
 # Linkedin Profile:
 www.linkedin.com/in/abdul-qadir-009607bb
 
 
                               **********************************************
 
 
 # Hosted Application:
 https://github.com/abdulqadirtr/JavaAndroid_Retrofit_RestApi
 https://github.com/WeRockStar/Dagger2
 
 




