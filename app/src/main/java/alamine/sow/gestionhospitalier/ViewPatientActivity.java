package alamine.sow.gestionhospitalier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewPatientActivity extends AppCompatActivity {

    private Button btnConsultation, btnAppointement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);

        btnConsultation = findViewById(R.id.btnConsultation);
        btnAppointement = findViewById(R.id.btnAppointement);

        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPatientActivity.this, ViewConsultationActivity.class);
                startActivity(intent);
            }
        });

        btnAppointement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPatientActivity.this, ViewAppointmentActivity.class);
                startActivity(intent);
            }
        });
    }
}