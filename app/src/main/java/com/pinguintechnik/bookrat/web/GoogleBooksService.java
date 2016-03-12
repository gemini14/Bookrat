package com.pinguintechnik.bookrat.web;

import com.pinguintechnik.bookrat.models.Book;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Tarik on 3/10/2016.
 */
public class GoogleBooksService implements BookInfoService {
    private final String googleBooksURL = "https://www.googleapis.com/books/v1/";

    @Override
    public Book getInfo(Book book) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(googleBooksURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GoogleService googleService = retrofit.create(GoogleService.class);
        Call<Book> call = googleService.bookInfoByISBN(book.getISBN());
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });
        return null;
    }

    interface GoogleService {
        @GET("/volumes/?q=isbn:{isbn}")
        Call<Book> bookInfoByISBN(
                @Path("isbn") String isbn);
    }
}
