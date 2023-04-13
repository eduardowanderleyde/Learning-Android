package br.ufpe.cin.residencia.recyclerview;

import android.os.SystemClock;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventoViewModel extends ViewModel {
    private final int viewModelID = new Random().nextInt();
    final List<Evento> eventos = new ArrayList<>();
    final long tempoInicial = SystemClock.elapsedRealtime();

    void adicionarEvento(String metodo, int activityID) {
        eventos.add(new Evento(metodo, activityID, viewModelID));
    }
}
