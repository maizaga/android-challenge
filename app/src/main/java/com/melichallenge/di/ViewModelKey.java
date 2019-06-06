package com.melichallenge.di;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

import java.lang.annotation.*;

/**
 * Created by maizaga on 17/04/2019.
 *
 * Workaround in Java due to Dagger/Kotlin not playing well together as of now
 * https://github.com/google/dagger/issues/1478
 */
@MapKey
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}