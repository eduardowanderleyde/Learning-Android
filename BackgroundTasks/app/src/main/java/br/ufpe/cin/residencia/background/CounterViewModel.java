package br.ufpe.cin.residencia.background;

import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    //O que vai entrar aqui no ViewModel?
    // tudo aquilo que queremos guardar de dados,
    // independentemente de mudança de configuração
    // - qtde de toasts gerados
    // - está contando tempo atualmente?
    // - quantos segundos já passaram?

    int toastsGerados = 0;
}
