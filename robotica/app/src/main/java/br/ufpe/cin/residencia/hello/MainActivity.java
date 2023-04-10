package br.ufpe.cin.residencia.hello;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//XML inflation
        //pegando referências para os objetos exibidos na tela
        EditText campoTextoDigitado = findViewById(R.id.campoTexto);
        Button botaoParaClicar = findViewById(R.id.botao);
        TextView mensagemExibida = findViewById(R.id.mensagem);
//        botaoParaClicar.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mensagemExibida.setText("Clicou no botão");
//                    }
//                }
//        );
        botaoParaClicar.setOnClickListener(
                v -> {
                    String oQueFoiDigitado = campoTextoDigitado.getText().toString();
                    if (oQueFoiDigitado.isEmpty()) {
                        mensagemExibida.setText(R.string.msg_erro);
                    }
                    else {
                        String msg = getString(R.string.msg_nome,oQueFoiDigitado);
                        mensagemExibida.setText(msg);
                    }
                }
        );
    }
}





