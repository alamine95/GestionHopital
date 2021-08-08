package alamine.sow.gestionhospitalier;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static android.app.PendingIntent.getActivity;

public class HelpActivity extends AppCompatActivity {

    private String [] tab_help, tab_detail_help;
    private String help, details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        final ListView ListHelp = findViewById(R.id.ListHelp);
        tab_help = getResources().getStringArray(R.array.tab_help);
        tab_detail_help = getResources().getStringArray(R.array.tab_detail_help);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(HelpActivity.this, android.R.layout.simple_list_item_1, tab_help);
        ListHelp.setAdapter(adapter);

        ListHelp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                help = tab_help[position];
                details = tab_detail_help[position];
                AlertDialog.Builder dialog = new AlertDialog.Builder(HelpActivity.this);
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle(help);
                dialog.setMessage(details);
                dialog.setPositiveButton(getString(R.string.ok), null);
                dialog.show();
            }
        });
    }

}