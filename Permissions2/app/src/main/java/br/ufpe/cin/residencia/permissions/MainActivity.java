package br.ufpe.cin.residencia.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_location;
    private Button btn_camera;
    private Button btn_internet;
    private Button btn_contacts;
    private Button btn_storage;
    private Button btn_calendar;
    private Button btn_microphone;
    private Button btn_sms;
    private TextView msg;

    private static final String[] CAMERA_PERMISSION = {
            Manifest.permission.CAMERA
    };
    private static final String[] LOCATION_PERMISSION = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private static final String[] STORAGE_PERMISSION = {
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
    };
    private static final String[] CONTACTS_PERMISSION = {
            Manifest.permission.READ_CONTACTS
    };

    private static final String[] CALENDAR_PERMISSION = {
            Manifest.permission.READ_CALENDAR
    };

    private static final String[] SMS_PERMISSION = {
            Manifest.permission.READ_SMS
    };
    private static final String[] MICROPHONE_PERMISSION = {
            Manifest.permission.RECORD_AUDIO
    };


    private static final String[] ONCREATE_PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
    };

    private static final int CALENDAR_REQUEST = 20;
    private static final int SMS_REQUEST = 21;
    private static final int MICROPHONE_REQUEST = 22;
    private static final int CAMERA_REQUEST = 42;
    private static final int STORAGE_REQUEST = 43;
    private static final int ONCREATE_REQUEST = 43;


    private static final int LOCALIZATION_REQUEST = 44;
    private static final int CONTACTS_REQUEST = 45;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_location = findViewById(R.id.btn_location);
        btn_camera = findViewById(R.id.btn_camera);
        btn_internet = findViewById(R.id.btn_internet);
        btn_contacts = findViewById(R.id.btn_contacts);
        btn_storage = findViewById(R.id.btn_storage);
        btn_calendar = findViewById(R.id.btn_calendar);
        btn_microphone = findViewById(R.id.btn_microphone);
        btn_sms = findViewById(R.id.btn_sms);
        msg = findViewById(R.id.mensagem);

        btn_camera.setOnClickListener(
                v -> {
                    if (podeUsarCamera()) {
                        acessarRecurso(R.string.msg_camera);
                    }
                    else {
                        requestPermissions(LOCATION_PERMISSION, LOCALIZATION_REQUEST);
                    }
                }
        );

        btn_location.setOnClickListener(
                v -> {
                    if (podeUsarLocalizacao()) {
                        acessarRecurso(R.string.msg_location);
                    }
                    else {
                        requestPermissions(CAMERA_PERMISSION, CAMERA_REQUEST);
                    }
                }
        );
        btn_storage.setOnClickListener(
                v -> {
                    if (podeUsarArmazenamento()) {
                        acessarRecurso(R.string.msg_armazenamento);
                    }
                    else {
                        requestPermissions(STORAGE_PERMISSION, STORAGE_REQUEST);
                    }
                }
        );
        btn_contacts.setOnClickListener(
                v -> {
                    if (podeUsarContatos()) {
                        acessarRecurso(R.string.msg_contacts);
                    }
                    else {
                        requestPermissions(CONTACTS_PERMISSION, CONTACTS_REQUEST);
                    }
                }
        );

        btn_calendar.setOnClickListener(
                v -> {
                    if (podeUsarCalendario()) {
                        acessarRecurso(R.string.msg_calendar);
                    }
                    else {
                        requestPermissions(CALENDAR_PERMISSION, CALENDAR_REQUEST);
                    }
                }
        );

        btn_sms.setOnClickListener(
                v -> {
                    if (podeUsarSms()) {
                        acessarRecurso(R.string.msg_sms);
                    }
                    else {
                        requestPermissions(SMS_PERMISSION, SMS_REQUEST);
                    }
                }
        );

        btn_microphone.setOnClickListener(
                v -> {
                    if (podeUsarMicrofone()) {
                        acessarRecurso(R.string.msg_microphone);
                    }
                    else {
                        requestPermissions(MICROPHONE_PERMISSION, MICROPHONE_REQUEST);
                    }
                }
        );


        btn_internet.setOnClickListener(
                v -> {
                    if (podeUsarInternet()) {
                        acessarRecurso(R.string.msg_internet);
                    }
                    else {
                        sem_permissao();
                    }
                }
        );

        requestPermissions(ONCREATE_PERMISSIONS,ONCREATE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                acessarRecurso(R.string.msg_camera);
            }
            else {
                sem_permissao();
            }

        }
        if (requestCode == LOCALIZATION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                acessarRecurso(R.string.msg_camera);
            } else {
                sem_permissao();
            }
        }
        if (requestCode == CONTACTS_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                acessarRecurso(R.string.msg_contacts);
            } else {
                sem_permissao();
            }
        }
        if (requestCode == STORAGE_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                acessarRecurso(R.string.msg_armazenamento);
            } else {
                sem_permissao();
            }
        }
        if (requestCode == CALENDAR_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                acessarRecurso(R.string.msg_calendar);
            } else {
                sem_permissao();
            }
        }
        if (requestCode == SMS_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                acessarRecurso(R.string.msg_sms);
            } else {
                sem_permissao();
            }
        }
        if (requestCode == MICROPHONE_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                acessarRecurso(R.string.msg_microphone);
            } else {
                sem_permissao();
            }
        }
    }

    private boolean podeUsarInternet() {
        return temPermissao(Manifest.permission.INTERNET);
    }

    private boolean podeUsarCamera() {
        return temPermissao(Manifest.permission.CAMERA);
    }
    private boolean podeUsarLocalizacao() {
        return temPermissao(Manifest.permission.ACCESS_FINE_LOCATION);
    }
    private boolean podeUsarArmazenamento() {
        return temPermissao(Manifest.permission.MANAGE_EXTERNAL_STORAGE);
    }

    private boolean podeUsarContatos() {
        return temPermissao(Manifest.permission.READ_CONTACTS);
    }

    private boolean podeUsarCalendario() {
        return temPermissao(Manifest.permission.READ_CALENDAR);
    }
    private boolean podeUsarSms() {
        return temPermissao(Manifest.permission.READ_SMS);
    }

    private boolean podeUsarMicrofone() {
        return temPermissao(Manifest.permission.RECORD_AUDIO);
    }


    private boolean temPermissao(String perm) {
        return (
                checkSelfPermission(perm)
                        ==
                        PackageManager.PERMISSION_GRANTED
        );
    }

    private void sem_permissao() {
        msg.setText(R.string.msg_sem_permissao);
    }

    private void acessarRecurso(@StringRes int id) {
        msg.setText(id);
    }

}