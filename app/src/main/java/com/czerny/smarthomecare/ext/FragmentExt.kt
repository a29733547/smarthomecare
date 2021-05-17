package com.czerny.smarthomecare.ext

import androidx.fragment.app.Fragment

import app.appworks.school.publisher.factory.ViewModelFactory
import com.czerny.smarthomecare.SmartHomeCareApplication
import com.czerny.smarthomecare.data.Author

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Extension functions for Fragment.
 */
fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as SmartHomeCareApplication).repository
    return ViewModelFactory(repository)
}

//fun Fragment.getVmFactory(author: Author?): AuthorViewModelFactory {
//    val repository = (requireContext().applicationContext as PublisherApplication).repository
//    return AuthorViewModelFactory(repository, author)
//}