package alamine.sow.gestionhospitalier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ViewAppointmentActivity extends AppCompatActivity {

    private String resume ="";
    String chaine="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
        Button btnViewAppointment = findViewById(R.id.btnViewAppointment);

        btnViewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chaine="";
                loadAppointment();
            }
        });

    }

    public void loadAppointment(){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://172.20.10.8/GestionHospitalier/ViewAppointment.php?login="+PatientActivity.login)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(ViewAppointmentActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {

                    JSONObject jo = new JSONObject(response.body().string());
                    JSONArray array = jo.getJSONArray("appointments");

                    for (int i = 0; i <array.length() ; i++) {

                        JSONObject element = array.getJSONObject(i);
                        String date = element.getString("date");
                        String hour = element.getString("hour");
                        String doctor_name = element.getString("doctor_name");
                        chaine+=date+": "+hour+": "+doctor_name+"\n\n\n";

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ViewAppointmentActivity.this, chaine, Toast.LENGTH_SHORT).show();
                        }
                    });

                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
    }
}