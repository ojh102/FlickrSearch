package com.github.ojh102.flickersearch.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.ojh102.flickersearch.BR
import com.plug.android.utils.rx.SchedulerProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    lateinit var binding: VB

    protected val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        createViewModel(viewModelClass)
    }

    protected val disposables by lazy(LazyThreadSafetyMode.NONE) {
        CompositeDisposable()
    }

    protected abstract val layoutRes: Int

    protected abstract val viewModelClass: Class<VM>

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)

        binding.setLifecycleOwner(this)

        binding.setVariable(BR.viewModel, viewModel)
    }


    @CallSuper
    override fun onDestroy() {
        disposables.clear()

        super.onDestroy()
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> {
        return supportFragmentInjector
    }

    protected fun <VM : ViewModel> createViewModel(viewModelClass: Class<VM>): VM {
        return ViewModelProviders.of(this, viewModelProviderFactory).get(viewModelClass)
    }

}