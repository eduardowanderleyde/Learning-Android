package br.ufpe.cin.residencia.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MusicPlayerBindingService extends Service {
    String serviceID = Integer.toHexString(new Random().nextInt());
    int numExecucoes = 0;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        String msg = "Service " + serviceID + " foi criado na memória";
        log(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(this, R.raw.moonlightsonata);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnCompletionListener(
                o -> stopSelf()
        );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        numExecucoes++;
        String msg = "Service " + serviceID + " está iniciando nova execução (número "+numExecucoes+")";
        log(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    public void playMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        String msg = "Service " + serviceID + " prestes a ser removido da memória";
        log(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void log(String msg) {
        Log.d(this.getClass().getSimpleName(), msg);
    }

    public class MusicBinder extends Binder {
        MusicPlayerBindingService pegarInstancia() {
            return MusicPlayerBindingService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        String msg = "Service " + serviceID + " está executando o onBind()";
        log(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return new MusicBinder();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        String msg = "Service " + serviceID + " prestes a desfazer binding.";
        MainActivity.log(this.getClass().getSimpleName(), msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}