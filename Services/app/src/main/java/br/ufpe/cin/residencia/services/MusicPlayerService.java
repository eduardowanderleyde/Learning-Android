package br.ufpe.cin.residencia.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Random;

public class MusicPlayerService extends Service {
    String serviceID = Integer.toHexString(new Random().nextInt());
    int numChamadasStartService = 0;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        String msg = "Service " + serviceID + " foi criado na memória.";
        MainActivity.log(this.getClass().getSimpleName(), msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(this,R.raw.moonlightsonata);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //o código aqui roda na Main Thread/UI Thread
        numChamadasStartService++;
        String msg = "Service " + serviceID + " está iniciando nova execução (#"+numChamadasStartService+").";
        MainActivity.log(this.getClass().getSimpleName(), msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        mediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        String msg = "Service " + serviceID + " prestes a ser removido da memória.";
        MainActivity.log(this.getClass().getSimpleName(), msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}