package com.prueba.tecnica.marvellistheros.core.di.key

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_GETTER)
annotation class ViewModelKey(val value: KClass<out ViewModel>)
