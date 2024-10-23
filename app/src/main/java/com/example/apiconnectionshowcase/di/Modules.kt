package com.example.apiconnectionshowcase.di

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.apiconnectionshowcase.data.CategoryRepository
import com.example.apiconnectionshowcase.data.CategoryRepositoryImpl
import com.example.apiconnectionshowcase.data.ProductRepository
import com.example.apiconnectionshowcase.data.ProductRepositoryImpl
import com.example.apiconnectionshowcase.data.ShopAPI
import com.example.apiconnectionshowcase.data.ShopDatabase
import com.example.apiconnectionshowcase.viewmodel.CategoryViewModel
import com.example.apiconnectionshowcase.viewmodel.ProductViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

val appModules = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single { Dispatchers.IO }

    single<CategoryRepository> { CategoryRepositoryImpl(get(), get()) }
    viewModel { CategoryViewModel(get()) }

    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    viewModel { ProductViewModel(get()) }

    single {
        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .client(get<OkHttpClient>())
            .baseUrl("https://fakestoreapi.com/")
            .build()
    }
    single { get<Retrofit>().create(ShopAPI::class.java) }

//    single {
//        Room.databaseBuilder(
//            androidContext(),
//            ShopDatabase::class.java,
//            "shop-database"
//        ).addCallback(object : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                Log.d("RoomDB", "Database created")
//            }
//
//            override fun onOpen(db: SupportSQLiteDatabase) {
//                super.onOpen(db)
//                Log.d("RoomDB", "Database opened")
//            }
//        })
//            .build()
//    }
//    single { get<ShopDatabase>().categoryDao() }
//    single { get<ShopDatabase>().productDao() }
}