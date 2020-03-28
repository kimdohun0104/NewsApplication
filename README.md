# NewsApplication
My real trip, blind coding test  
This is an extraordinary news application using [Google news RSS](https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko)  

### Functions
1. Splash - Configure layout with some rules
2. News list - Include image, title, description, tags from RSS and Web page
3. News detail - Read original news from WebView  

### Module structure
![module structure image](https://user-images.githubusercontent.com/36754680/77821836-94f9f700-7130-11ea-9515-49adc1c2ce44.png)  
_(Blank arrow means implement or expand)_  
This project has been multi modularized.  
I must control complexity of module dependencies for meet requirements.  

### Architecture
Use MVVM pattern for Presentation logic based on [AndroidJetpack](https://developer.android.com/jetpack/?gclid=Cj0KCQiAwP3yBRCkARIsAABGiPoQ4aLdFUSMcbfMnK9F39SH7PUfBiX9eUtjrwwH0w_oZPKtGnmGzfgaAq1FEALw_wcB)(ViewModel, DataBinding, LiveData).  
Every module follow **changeable architecture policy**(I made up this term).  
More detail in my [blog](https://medium.com/@dikolight203/%EA%B5%90%EC%B2%B4-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-af1bff55715).
  
![architecture](https://user-images.githubusercontent.com/36754680/77822577-66334f00-7137-11ea-945b-448a25014fce.png)  
**NewsApplication support offline mode**  
Use interface for changeable architecture. It helps to handle details like third-party libraries, algorithms.  

### Data flow
![](https://user-images.githubusercontent.com/36754680/77823110-1efb8d00-713c-11ea-8490-bc1725d613e0.png)

### Library
