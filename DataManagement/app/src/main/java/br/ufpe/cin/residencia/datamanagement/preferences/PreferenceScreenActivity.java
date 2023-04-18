package br.ufpe.cin.residencia.datamanagement.preferences;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import br.ufpe.cin.residencia.datamanagement.R;

public class PreferenceScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_screen);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new TelaPreferencias())
                .commit();
    }

    public static class TelaPreferencias extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.telapreferencias);
        }
    }

}