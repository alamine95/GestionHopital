package alamine.sow.gestionhospitalier;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConsultationActivity extends AppCompatActivity {
    private EditText txtFirstName, txtLastName, txtAge, txtSymptom, txtWeight, txtTemperature, txtPressure, txtLogin;
    private Button btnConsultation;
    private String firstName, lastName, age, symptom, weight, temperatur, pressure, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtAge = findViewById(R.id.txtAge);
        txtSymptom = findViewById(R.id.txtSymptom);
        txtWeight = findViewById(R.id.txtWeight);
        txtTemperature = findViewById(R.id.txtTemperature);
        txtPressure = findViewById(R.id.txtPressure);
        txtLogin = findViewById(R.id.txtLogin);
        btnConsultation = findViewById(R.id.btnConsultation);

        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName = txtFirstName.getText().toString().trim();
                lastName = txtLastName.getText().toString().trim();
                age = txtAge.getText().toString().trim();
                symptom = txtSymptom.getText().toString().trim();
                weight = txtWeight.getText().toString().trim();
                temperatur = txtTemperature.getText().toString().trim();
                pressure = txtPressure.getText().toString().trim();
                login = txtLogin.getText().toString().trim();

                //String resume = firstName+"\n\n"+lastName+"\n\n"+age+"\n\n"+symptom+"\n\n"+weight+"\n\n"+temperatur+"\n\n"+pressure;
                //Toast.makeText(ConsultationActivity.this, resume, Toast.LENGTH_SHORT).show();

                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                          .add("first_name", firstName)
                          .add("last_name", lastName)
                          .add("age", age)
                          .add("symptom", symptom)
                          .add("weight", weight)
                          .add("temperatur", temperatur)
                          .add("pressure", pressure)
                          .add("login", login)
                          .build();
                String url ="http://172.20.10.8/GestionHospitalier/consultation.php";

                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String message = getString(R.string.error_connection);
                                Toast.makeText(ConsultationActivity.this, message, Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(ConsultationActivity.this, getString(R.string.success),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(ConsultationActivity.this, getString(R.string.error_parameters),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){

                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}