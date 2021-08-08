package alamine.sow.gestionhospitalier.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import alamine.sow.gestionhospitalier.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private String[] tabPresentation, tabDetails;
    private String presentation, details;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView ListPresentation = root.findViewById(R.id.ListPresentation);
        tabPresentation = getResources().getStringArray(R.array.tab_presentation);
        tabDetails = getResources().getStringArray(R.array.tab_details);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tabPresentation);
        ListPresentation.setAdapter(adapter);

        ListPresentation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                presentation = tabPresentation[position];
                details = tabDetails[position];
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle(presentation);
                dialog.setMessage(details);
                dialog.setNegativeButton(getString(R.string.cancel), null);
                dialog.setPositiveButton(getString(R.string.ok), null);
                dialog.show();
            }

        });

        return root;
    }
}