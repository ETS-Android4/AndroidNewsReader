# Android News Reader

An Android news reader implemented using MVVM (Model-View-ViewModel) design pattern.

![alt text](https://github.com/IonelaTurcuman/AndroidNewsReader/blob/main/banner.png?raw=true)

## General

News Reader is an Android mobile application build using Java. The application takes the latest news articles from www.newsapi.org and displays them to the user. To run the application you would need to generate your own api key on www.newsapi.org and add it inside class NewsRemoteSource.java. This application was created for educational purposes.
 

## Implementation details

The implementation is based on the MVVM (Model-View-ViewModel) design pattern.

The project is split in two main modules:
- The 'app' module where all the functionalities and design elements are implemented
- The 'data' module where the data is gathered, processed and stored

The main screen of the application uses a RecyclerView to hold the news articles. The design pattern Factory is used to get the corresponding view model: the model responsible for an article's behaviour or the model responsible for the list of articles.

Binding is the core of this project. Firstly, image and data binding is used for setting the values of each article: the image, the title and the content of the article and the implementation can be found in class ImageBinding.java. Secondly, data binding is used to add the articles in the RecyclerView and the implementation can be found in class RecyclerBindings.java.

The application downloads the latest articles, but if no internet connection is available it will load the articles it has saved in the local memory.


## Demo

![](https://github.com/IonelaTurcuman/AndroidNewsReader/blob/main/shortDemo.gif)


## Acknowledgements

This project was implemented during the course Advanced Mobile Programming by Google under the guidance of [Mecea Mihai](https://github.com/Misca).

