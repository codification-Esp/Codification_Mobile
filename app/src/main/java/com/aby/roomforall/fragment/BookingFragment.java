package com.aby.roomforall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.aby.roomforall.Application;
import com.aby.roomforall.R;
import com.aby.roomforall.activity.SearchResultActivity;
import com.aby.roomforall.dto.Idate;
import com.aby.roomforall.dto.Planning;
import com.aby.roomforall.dto.Trip;
import com.aby.roomforall.event.AsyncTaskEvent;
import com.aby.roomforall.helper.AsyncTaskTools;
import com.aby.roomforall.model.AsyncTaskResponse;
import com.aby.roomforall.task.BookingTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BookingFragment extends Fragment implements AsyncTaskEvent {

    private EditText returnDateField;
    private EditText departureTextField;
    private EditText arrivalTextField;
    private EditText firstnameTextField;
    private EditText nameTextField;
    private EditText departureDateField;
    private EditText placesField;
    private EditText childPlacesField;
    private CheckBox returnCheckbox;
    private Button searchButton;

    private BookingTask task = null;
    private Application application;


    public static BookingFragment newInstance() {
        BookingFragment fragment = new BookingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        application = (Application) getActivity().getApplication();
        application.prepare(getContext());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
//        prepareForm();
    }

    private void prepareForm() {
       /* returnDateField = (EditText) getView().findViewById(R.id.returning_date_field);
        returnCheckbox = (CheckBox) getView().findViewById(R.id.return_checkbox);
        departureTextField = (EditText) getView().findViewById(R.id.departure_text_field);
        arrivalTextField = (EditText) getView().findViewById(R.id.arrival_text_field);
        firstnameTextField = (EditText) getView().findViewById(R.id.firstname_text_field);
        nameTextField = (EditText) getView().findViewById(R.id.name_text_field);
        departureDateField = (EditText) getView().findViewById(R.id.departure_date_field);
        placesField = (EditText) getView().findViewById(R.id.places_field);
        childPlacesField = (EditText) getView().findViewById(R.id.child_places_field);*/
        searchButton = (Button) getView().findViewById(R.id.search_button);
        returnDateField.setEnabled(false);
        returnCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                returnDateField.setEnabled(b);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                precheckSearch();
            }
        });
    }

    private void precheckSearch() {
        if (isFormValid()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("departure", departureTextField.getText().toString());
            data.put("destination", arrivalTextField.getText().toString());
            data.put("date", departureDateField.getText().toString());
            data.put("childPlaces", Integer.valueOf(childPlacesField.getText().toString()));
            data.put("places", Integer.valueOf(placesField.getText().toString()));
            data.put("returning", returnCheckbox.isChecked());
            data.put("returnDate", returnDateField.getText().toString());
            doSearchTask(data);
        }
    }

    private void doSearchTask(Object data) {
        if (task != null)
            return;
        task = new BookingTask(application, getContext(), this);
        AsyncTaskTools.execute(task, data);
    }

    private boolean isFormValid() {
        boolean res = true;

        if (departureTextField.getText().length() <= 0) {
            departureTextField.setError("Veuillez specifier le lieu de départ.");
            departureDateField.requestFocus();
            res = false;
        }

        if (arrivalTextField.getText().length() <= 0) {
            arrivalTextField.setError("Veuillez specifier votre lieu de destination.");
            arrivalTextField.requestFocus();
            res = false;
        }

        if (departureDateField.getText().length() <= 0) {
            departureDateField.setError("Veuillez renseigner votre date de départ souhaitée.");
            departureDateField.requestFocus();
            res = false;
        }

        if (firstnameTextField.getText().length() <= 0) {
            firstnameTextField.setError("Veuillez renseigner votre prénom.");
            firstnameTextField.requestFocus();
            res = false;
        }

        if (nameTextField.getText().length() <= 0) {
            nameTextField.setError("Veuillez renseigner votre nom.");
            nameTextField.requestFocus();
            res = false;
        }

        if (returnCheckbox.isChecked()) {
            if (returnDateField.getText().length() <= 0) {
                returnDateField.setError("Veuillez specifier votre date de retour souhaitée.");
                returnDateField.requestFocus();
                res = false;
            }
        }

        if (placesField.getText().length() <= 0 || (Integer.valueOf(placesField.getText().toString()) <= 0)) {
            placesField.setError("Veuillez specifier le nombre de places adulte (> 5ans) souhaité.");
            placesField.requestFocus();
            res = false;
        }

        if (childPlacesField.getText().length() <= 0 || (Integer.valueOf(placesField.getText().toString()) < 0)) {
            childPlacesField.setError("Veuillez specifier le nombre de places enfant souhaité.");
            childPlacesField.requestFocus();
            res = false;
        }

        return res;
    }

    @Override
    public void onSuccess(AsyncTaskResponse response) {
        task = null;
        /*if (response.getResponse().getCode() == 200) {*/
            try {
                List<Trip> results = new ArrayList<>();

                Trip trip = new Trip();
                Planning inP = new Planning();
                Planning ouP = new Planning();

                inP.setDate(new Idate("22-08-2017", "15h:00"));
                inP.setDeparture("Dakar");
                inP.setDestination("Diourbel");
                inP.setPlaces(8);
                inP.setPlacesLeft(8);
                inP.setPrice(5000);

                ouP.setDate(new Idate("24-08-2017", "09h:45"));
                ouP.setDeparture("Diourbel");
                ouP.setDestination("Dakar");
                ouP.setPlaces(10);
                ouP.setPlacesLeft(8);
                ouP.setPrice(5000);

                trip.setInbound(inP);
                trip.setOutbound(ouP);
                trip.setRoundtrip(true);

                results.add(trip);

                inP.setDate(new Idate("22-08-2017", "09h:00"));
                inP.setDeparture("Dakar");
                inP.setDestination("Diourbel");
                inP.setPlaces(5);
                inP.setPlacesLeft(4);
                inP.setPrice(5000);

                ouP.setDate(new Idate("25-08-2017", "09h:45"));
                ouP.setDeparture("Diourbel");
                ouP.setDestination("Dakar");
                ouP.setPlaces(8);
                ouP.setPlacesLeft(4);
                ouP.setPrice(5000);

                trip.setInbound(inP);
                trip.setOutbound(ouP);
                trip.setRoundtrip(true);

                results.add(trip);

                inP.setDate(new Idate("23-08-2017", "10h:00"));
                inP.setDeparture("Dakar");
                inP.setDestination("Diourbel");
                inP.setPlaces(5);
                inP.setPlacesLeft(4);
                inP.setPrice(5000);

                ouP.setDate(new Idate("26-08-2017", "15h:00"));
                ouP.setDeparture("Diourbel");
                ouP.setDestination("Dakar");
                ouP.setPlaces(8);
                ouP.setPlacesLeft(4);
                ouP.setPrice(5000);

                trip.setInbound(inP);
                trip.setOutbound(ouP);
                trip.setRoundtrip(true);

                results.add(trip);
                Intent intent = new Intent(getContext(), SearchResultActivity.class);
                Trip[] items = new Trip[results.size()];
                for (int i = 0;  i < results.size(); i++) {
                    items[i] = results.get(i);
                }
                intent.putExtra("resultList", items);
                startActivity(intent);
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        //}

    }

    @Override
    public void onProgress(Integer... progress) {

    }

    @Override
    public void onError(AsyncTaskResponse response) {
        task = null;
        System.out.println(response.getError());
    }

    @Override
    public void onCanceled() {

    }
}
