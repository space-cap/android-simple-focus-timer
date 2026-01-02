package com.ezlevup.simplefocustimer.di

import androidx.room.Room
import com.ezlevup.simplefocustimer.data.local.SimpleFocusDatabase
import com.ezlevup.simplefocustimer.data.repository.FocusRepositoryImpl
import com.ezlevup.simplefocustimer.domain.repository.FocusRepository
import com.ezlevup.simplefocustimer.domain.usecase.GetFocusHistoryUseCase
import com.ezlevup.simplefocustimer.domain.usecase.SaveFocusSessionUseCase
import com.ezlevup.simplefocustimer.domain.usecase.StartTimerUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
                        androidContext(),
                        SimpleFocusDatabase::class.java,
                        "simple_focus_timer.db"
                )
                .build()
    }
    single { get<SimpleFocusDatabase>().focusSessionDao() }
}

val repositoryModule = module { single<FocusRepository> { FocusRepositoryImpl(get()) } }

val useCaseModule = module {
    factory { StartTimerUseCase() }
    factory { SaveFocusSessionUseCase(get()) }
    factory { GetFocusHistoryUseCase(get()) }
}

val viewModelModule =
        module {
            // ViewModels will be added here
        }

val appModule = listOf(databaseModule, repositoryModule, useCaseModule, viewModelModule)
