# FlcikrSearch

[![Build Status](https://app.bitrise.io/app/062a7e1028b4d5f1/status.svg?token=Dq-SQx4DkYk7WpM3ea5CqA&branch=master)](https://app.bitrise.io/app/062a7e1028b4d5f1)

Flickr을 이용한 검색 앱



## Concept

![concept](/art/concept)



## View

### Activity

모든 Activity는 BaseActivity를 구현해야하며 두 가지의 Generic Type을 사용한다.

```kotlin
internal abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : ... {
    ...
    lateinit var binding: VB

    protected val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        createViewModel(viewModelClass)
    }
    
    protected val disposables by lazy(LazyThreadSafetyMode.NONE) {
        CompositeDisposable()
    }
    
    protected abstract val layoutRes: Int

    protected abstract val viewModelClass: Class<VM>
    ...
}
```

**VB**는 Databinding을 위한 타입으로  **layoutRes**을 통하여 얻는다.

```kotlin
@CallSuper
override fun onCreate(savedInstanceState: Bundle?) {
    ...
    binding = DataBindingUtil.setContentView(this, layoutRes)
    ...
}
```

**VM**은 ViewModel을 위한 타입으로 **viewModelClass**를 통하여 얻는다.

```kotlin
protected fun <VM : ViewModel> createViewModel(viewModelClass: Class<VM>): VM {
    return ViewModelProviders.of(this, viewModelProviderFactory).get(viewModelClass)
}
```

**Rx**의 Lifecycle과 관련된 작업은 **disposables**를 통하여 관리한다.



### Databinding

Activity의 layout에 해당하는 xml에는 항상 Activity의 viewModel을 variable로 가지고 있어야한다.

```xml
<layout>
	<data>
    	<variable
        	name="viewModel"
    	    type="com.github.ojh102.flickrsearch.ui.xxx.XXXActivityViewModel"/>
	</data>
     <androidx.appcompat.widget.AppCompatTextView
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         android:text="@{viewModel.text}"/>
</layout>
```

**viewModel**의 **LiveData**를 통해 값을 binding하는 것을 기본으로 한다. 

다만, acitivty를 벗어나는 ViewHolder나 CustomView의 경우에는 일반적인 데이터바인딩 방식을 사용한다.



### DI

Activity와 같은 패키지 안에 해당 액티비티에 해당하는 Module을 만든다.

```kotlin
@Module(includes = [YourActivityModule.ProvideModule::class])
internal interface YourActivityModule {
    @Module
    class ProvideModule {
        @Provides
        fun provideFoo(): Foo {
            return FooImpl()
        }
    }

    @Binds
    @Singleton
    fun bindBar(bar: BarImpl): Bar
}
```

기본적으로 **Module**은 **interface**로 만들고 **@Provide가 필요할 경우**에는 내부에 ProvideModule Class를 선언하여 **include**시키는 방식으로 한다.

```kotlin
@Module
internal interface ActivityModule {
    @ContributesAndroidInjector(modules = [YourActivityModule::class])
    @ActivityScope
    fun bindYourActivity(): YourActivity
}
```

Activity의 DI는 **ActivityModule**에서@ContributesAndroidInjector을 사용한다.



## ViewModel

ViewModel은 BaseViewModel을 구현해야한다.

```kotlin
internal abstract class BaseViewModel : ViewModel() {
    protected val disposables: CompositeDisposable by lazy(LazyThreadSafetyMode.NONE){
        CompositeDisposable()
    }

    @CallSuper
    override fun onCleared() {
        disposables.clear()

        super.onCleared()
    }
}
```

ViewModel 안에서의 **Rx**의 Lifecycle과 관련된 작업은 **disposables**를 통하여 관리한다.



### Action & State

클릭이나, 완료와 같은 **이벤트**의 처리를 ViewModel에 있는 **Action**을 사용하여 처리한다.

**Action**은 **sealed class**로 생성한다.

```kotlin
internal class sealed class YourAction {
    sealed class Click : YourAction() {
        data class FooClicked(val foo: Foo): Click()
        
        object BarClicked : Click()
    }
    
    sealed class Complete : YourAction() {
        object Complete : Complete()
    }
}
```

**UI상태**의 업데이트는 **State**를 사용해서 처리한다.

**State**는 **sealed class**로 생성한다.

```kotlin
internal sealed class YourState {
    data class FooList(val fooList: List<Foo>) : YourState()

    data class Bar(val bar: Bar) : YourState()
}
```

ViewModel에서의 변수는 **Action, State, LiveData**로 이루어져있다.

LiveData는 databinding에서 사용되므로 public하게 만든다.

Action과 State의 사용을 위해 Relay와 그들을 사용할 수 있는 함수를 만든다.

```kotlin
internal class YourActivityViewModel @Inject constructor() : BaseViewModel() {
    val itemList: MutableLiveData<List<Item> = MutableLiveData()
    ...
    private val actionRelay = PublishRelay.create<YourAction>()
    private val stateRelay = PublishRelay.create<YourState>()
    ...
    fun toState(state: YourState) = stateRelay.accept(state)
    fun ofState(): Observable<YourState> = stateRelay

    fun toAction(action: YourAction) = actionRelay.accept(action)
    fun ofAction(): Observable<YourAction> = actionRelay
    ...
}
```

**LiveData의 초기화** 및 **Action상태에 따른 State의 변화** 같은 부분은 모두 **init**에서 처리한다.

각 Action과 State를 **ofType()**을 통해 transform하여 사용한다.

```kotlin
init {
    disposables.addAll(
            ofAction()
                    .ofType<SearchAction.Click.Keyword>()
                    .doOnNext { toState(SearchState.SelectedKeyword(it.keywordItem)) }
                    .switchMap { remoteRepository.search(it.keywordItem.keyword) }
                    .subscribeOf(onNext = { toState(SearchState.SearchedPhotoList(it)) }),

            ofState()
                    .ofType<SearchState.KeywordList>()
                    .subscribeOf(onNext = { keywordList.postValue(it.keywordList) }),

            ofState()
                    .ofType<SearchState.SelectedKeyword>()
                    .subscribeOf(onNext = { selectedKeyword.postValue(it.keyword) }),

            ofState()
                    .ofType<SearchState.SearchedPhotoList>()
                    .subscribeOf(onNext = { photoList.postValue(it.photoPagedList) })
    )

    val keywords = resourcesProvider.getStringArray(R.array.keywords).map { KeywordItem(it) }

    toState(SearchState.KeywordList(keywords))

    toAction(SearchAction.Click.Keyword(keywords.first()))
}
```



### DI

해당 Acitivity의 Module에서 **@ViewModelKey를 키로 사용하여 매핑**한다.

매핑된 ViewModel은 **ViewModelProviderFactory**를 통하여 Activity에서 가져온다.

```kotlin
@Module
internal interface YourActivityModule {
    ...
    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(YourActivityViewModel::class)
    fun bindYourActivityViewModel(viewModel: YourActivityViewModel): ViewModel
    ...
}

class ViewModelProviderFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<ViewModel>? = creators[modelClass]

        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        if (creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }

        @Suppress("UNCHECKED_CAST")
        try {
            return creator.get() as T
        } catch (e : Exception) {
            throw RuntimeException(e)
        }
    }
}
```



## Model

### RemoteService

Service는 Retrofit Api를 사용하여 Observable, Single과 같은 Reactive Stream을 가져오는데 사용된다.

```kotlin
internal class FlickrRemoteServiceImpl @Inject constructor(
    private val flickrApi: FlickrApi,
    private val schedulerProvider: SchedulerProvider

) : FlickrRemoteService {
    
    override fun search(keyword: String, page: Int): Single<FlickrSearchResponse> {
        return flickrApi.search(keyword, page).subscribeOn(schedulerProvider.io())
    }
    
}
```



### DataSource

DataSource는 Paging이 필요한 데이터를 가져오는데 사용된다.

```kotlin
internal class SearchDataSourceFactory(
        private val keyword: String,
        private val remoteService: FlickrRemoteService,
        private val disposables: CompositeDisposable
) : DataSource.Factory<Int, FlickrPhoto>() {

    override fun create(): DataSource<Int, FlickrPhoto> {
        return SearchDataSource(keyword, remoteService, disposables)
    }

}
```



### Repository

Repository는 RemoteService와 DataSource를 통해 Reactive한 Stream을 얻어오는데 사용된다.

또한, ViewModel에서 데이터를 가져올 때 직접적으로 사용되는 부분이다.

```kotlin
internal class RemoteRepositoryImpl @Inject constructor(
    private val remoteService: FlickrRemoteService,
    private val disposable: CompositeDisposable

) : RemoteRepository {

    override fun search(keyword: String): Observable<PagedList<FlickrPhoto>> {
        val dataSourceFactory = SearchDataSourceFactory(keyword, remoteService, disposable)

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(40)
            .setEnablePlaceholders(false)
            .build()

        return RxPagedListBuilder(dataSourceFactory, config).buildObservable()
    }
}
```



License
-------

    Copyright 2018 OH JAE HWAN
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.