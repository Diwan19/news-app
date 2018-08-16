#News App
--------------
This News App is the eighth project in Udacidy's Android Basics Nanodegree.


The app consists a single activity which loads latest news from the [guardian_api](http://content.guardianapis.com/search?page-size=10&q=news&api-key=0fc305c0-c13b-40dd-b619-cc5149f58189).


The loading takes place on a background thread and uses AsyncTask Loader.


Meanwhile the loader loads the news, a progress bar can be seen on the top of the activity.


The app provides an EmptyView and a NoConnectionView whenever there is no news to show and no internet connection respectively.
