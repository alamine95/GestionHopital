package alamine.sow.gestionhospitalier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText txtLogin, txtPassword;
    private Button btnConnect, btnSignUp;
    private String password, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        btnConnect = findViewById(R.id.btnConnect);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = txtLogin.getText().toString().trim();
                password = txtPassword.getText().toString().trim();

                if (login.isEmpty() || password.isEmpty()){

                    String message = getString(R.string.error_fields);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                else {

                    String url="http://172.20.10.4/GestionHospitalier/connexion.php?login="+login+"&password="+password;
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    String message =getString(R.string.error_connection);
                                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {

                                        JSONObject jo = new JSONObject(response.body().string());
                                        String status = jo.getString("status");
                                        if (status.equalsIgnoreCase("OK"))
                                        {
                                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            String message =getString(R.string.error_parameters);
                                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e)
                                    {

                                        e.printStackTrace();
                                    }
                                }
                            });

                        }
                    });



                    }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, PatientActivity.class);
                startActivity(intent);
            }
        });
    }
}