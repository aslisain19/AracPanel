package com.example.myapplication.domain.usecase

interface LoginUseCase{
    //Kullanıcı adı ve şifre alacak, girişin başarılı olup olmadığını dönecek
    operator fun invoke(username: String, password: String): Boolean
}
//Neden Interface yaptık? TDD (Önce Test) yaparken, kodun kendisi henüz ortada yokken bile test yazabilmek ve ileride bu use-case'i kolayca taklit (Mock) edebilmek için her zaman interface ile başlarız.