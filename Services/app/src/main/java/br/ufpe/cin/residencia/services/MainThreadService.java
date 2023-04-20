package br.ufpe.cin.residencia.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Random;

//Um Service é definido por uma classe que estende a superclasse Service
public class MainThreadService extends Service {
    String serviceID = Integer.toHexString(new Random().nextInt());
    int numChamadasStartService = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        String msg = "Service " + serviceID + " foi criado na memória.";
        MainActivity.log(this.getClass().getSimpleName(), msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //o código aqui roda na Main Thread/UI Thread
        numChamadasStartService++;
        String msg = "Service " + serviceID + " está iniciando nova execução (#"+numChamadasStartService+").";
        MainActivity.log(this.getClass().getSimpleName(), msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        try {
            //Abstraindo uma operação que leva cerca de 4s para acontecer
            Thread.sleep(4000);
            //System.out.println
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
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