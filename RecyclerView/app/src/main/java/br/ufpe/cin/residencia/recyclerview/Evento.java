package br.ufpe.cin.residencia.recyclerview;

import android.os.SystemClock;

public class Evento {
    final long timestamp = SystemClock.elapsedRealtime();
    final String metodo;
    final int activityId;
    final int viewModelId;

    public Evento(String m, int aId, int vmId) {
        this.metodo = m;
        this.activityId = aId;
        this.viewModelId = vmId;
    }
}