import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.my_fragment_app.R;

public class OverviewFragment extends androidx.fragment.app.Fragment{

    public interface OverviewActivity {
        public void onItemSelect(OverviewActivity overview_activity);
    }

    public OverviewFragment() {
        super(R.layout.overview_fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if ( getActivity() == null )
            return null;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.overview_fragment, container, false);

        String[] myStringArray = new String[] { "Bulbasaur", "Dragonite", "Pikachu" } ;
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, myStringArray);
        ListView listView = view.findViewById(R.id.ListView);
        listView.setAdapter(adapter);

        return view;
    }
}
