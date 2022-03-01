# MVVM-With-Retrofit-Recyclerview-and-ROOM-in-Kotlin
In this project you can learn how to impliment a simple MVVM project using Kotlin with retrofit for API's and ROOm for data base.

1. Adding dependencies for MVVM, Retrofit and Recyclerview
   Add the following dependencies in your app level build.gradle.
   
 //ViewModel and livedata
 
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
    
    //Retrofit
    
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    //Glide
    
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    kapt 'com.github.bumptech.glide:compiler:4.12.0'
    
  Also, don’t forget to add a internet permission in your manifeats.xml file.

    <uses-permission android:name="android.permission.INTERNET"/>
    
2. Setup the Data Layer
 In the data layer, we have to prepare the model for the data, and an API call needs to be implemented. In this example, I am using a Repository pattern to handle the data.
 
 Creating Model class.
For the response data, First we need to create model class. you can modify your model class according to your api response.

     data class BuyModel(val name: String, val price: String, val quantity: String, val type: String)

3. Setting up Retrofit
First, create the interface for the API call definition.

       interface RetrofitService {
           @GET("call")
           fun getAllCallList(): Call<List<CallListModel>>
          }

Next, Create the Retrofit service instance using the retrofit.

Both are part of the same class. RetrofitService.kt
   
       ompanion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/imkhan334/demo-1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
    
4. Setup Data Repository
I am using a repository pattern to handle the data from API. In the repository class, we need to pass the retrofit service instance to perform the network call.
We don’t need to handle the response here in the repository. That will be part of the ViewModel.

      class CallRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllCallList() = retrofitService.getAllCallList()
    }
    

5. Setup the ViewModel
  In the ViewModel setup, We need to create a class and extends the ViewModel. ViewModel class having the business logic and API call implementations.
  In the ViewModel constructor, we need to pass the data repository to handle the data.
  
        class CallListViewModel constructor(private val repository: CallRepository) : ViewModel() {

         val movieList = MutableLiveData<List<CallListModel>>()
         val errorMessage = MutableLiveData<String>()

            fun getAllCalls() {
              val response = repository.getAllCallList()
               response.enqueue(object : Callback<List<CallListModel>> {
                override fun onResponse(
                call: Call<List<CallListModel>>,
                response: Response<List<CallListModel>>
                 ) {
                movieList.postValue(response.body())
                }

                override fun onFailure(call: Call<List<CallListModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
           })
         }
       }
    
Live Data
Since LiveData respects Android Lifecycle, this means it will not invoke its observer callback unless activity or fragment is received onStart() but did not accept onStop() Adding to this, 
LiveData will also automatically remove the observer when its host receives onDestroy().

ViewModel Factory
We can not create ViewModel on our own. We need ViewModelProviders utility provided by Android to create ViewModels.

But ViewModelProviders can only instantiate ViewModels with no arg constructor.

So if I have a ViewModel with multiple arguments, then I need to use a Factory that I can pass to ViewModelProviders to use when an instance of MyViewModel is required.

          class CallListViewModelFactory constructor(private val repository: CallRepository) :
            ViewModelProvider.Factory {
              override fun <T : ViewModel> create(modelClass: Class<T>): T {
               return if (modelClass.isAssignableFrom(CallListViewModel::class.java)) {
                CallListViewModel(this.repository) as T
                  } else {
                 throw IllegalArgumentException("ViewModel Not Found")
                 }
            }
         }
         
6. After these stes you can simply set you UI  and adapters.
7. In this project sell list is totaly local and managed by ROOM. For this db package is here with all files. I will surely add its step by step documentation here soon.

