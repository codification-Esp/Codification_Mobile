package com.aby.roomforall.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.aby.roomforall.R;
import com.aby.roomforall.activity.BookingActivity;
import com.aby.roomforall.dto.Planning;
import com.aby.roomforall.dto.Trip;
import com.aby.roomforall.helper.FontTools;

import java.util.List;



public class SearchResultAdapter extends BaseAdapter {

    private Context context;
    private List<Trip> items;

    public SearchResultAdapter(Context context, List<Trip> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null) {
            view = new View(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.search_result, null);
        } else {
            view = convertView;
        }

        final Trip trip = (Trip) getItem(i);

        TextView outboundDateField = (TextView) view.findViewById(R.id.outbound_date_field);
        TextView outboundDepartureField = (TextView) view.findViewById(R.id.outbound_departure_field);
        TextView outboundDestinationField = (TextView) view.findViewById(R.id.outbound_destination_field);
        TextView outboundPlacesField = (TextView) view.findViewById(R.id.outbound_places_field);
        TextView outboundPriceField = (TextView) view.findViewById(R.id.outbound_price_field);

        TextView inboundDateField = (TextView) view.findViewById(R.id.inbound_date_field);
        TextView inboundDepartureField = (TextView) view.findViewById(R.id.inbound_departure_field);
        TextView inboundDestinationField = (TextView) view.findViewById(R.id.inbound_destination_field);
        TextView inboundPlacesField = (TextView) view.findViewById(R.id.inbound_places_field);
        TextView inboundPriceField = (TextView) view.findViewById(R.id.inbound_price_field);

        Planning outboundPlanning = trip.getOutbound();

        outboundDateField.setText(new StringBuilder().append(outboundPlanning.getDate().getDate()).append(" | ").append(outboundPlanning.getDate().getTime()).toString());
        outboundDepartureField.setText(outboundPlanning.getDeparture().toUpperCase());
        outboundDestinationField.setText(outboundPlanning.getDestination().toUpperCase());
        outboundPlacesField.setText(new StringBuilder().append(outboundPlanning.getPlaces()).append(" Places (adulte)").toString());
        outboundPriceField.setText(new StringBuilder().append(outboundPlanning.getPrice()).append(" FCFA").toString());

        LinearLayout inboundLayout = (LinearLayout) view.findViewById(R.id.inbound_layout);
        ImageView inboundDivider = (ImageView) view.findViewById(R.id.inbound_divider);
        if (!trip.isRoundtrip()) {
            inboundLayout.setVisibility(View.GONE);
            inboundDivider.setVisibility(View.GONE);
        } else {
            Planning inboundPlanning = trip.getInbound();

            inboundDateField.setText(new StringBuilder().append(inboundPlanning.getDate().getDate()).append(" | ").append(inboundPlanning.getDate().getTime()).toString());
            inboundDepartureField.setText(inboundPlanning.getDeparture().toUpperCase());
            inboundDestinationField.setText(inboundPlanning.getDestination().toUpperCase());
            inboundPlacesField.setText(new StringBuilder().append(inboundPlanning.getPlaces()).append(" Places (adulte)").toString());
            inboundPriceField.setText(new StringBuilder().append(inboundPlanning.getPrice()).append(" FCFA").toString());
        }

        Button bookButton = (Button) view.findViewById(R.id.book_button);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog b;
                AlertDialog.Builder dialogBuilder;
                dialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.booking_popup, null);
                EditText dateField = (EditText) dialogView.findViewById(R.id.date_field);
                EditText returnDateField = (EditText) dialogView.findViewById(R.id.return_date_field);
                EditText departureField = (EditText) dialogView.findViewById(R.id.departure_field);
                EditText destinationField = (EditText) dialogView.findViewById(R.id.destination_field);
                EditText placesField = (EditText) dialogView.findViewById(R.id.places_field);
                EditText priceField = (EditText) dialogView.findViewById(R.id.price_Field);
                LinearLayout returnBlock = (LinearLayout) dialogView.findViewById(R.id.return_block);
                dateField.setText(new StringBuilder().append(outboundPlanning.getDate().getDate()).append(" | ").append(outboundPlanning.getDate().getTime()).toString());
                int price = outboundPlanning.getPrice();
                if (!trip.isRoundtrip()) {
                    returnBlock.setVisibility(View.GONE);
                } else {
                    Planning inboundPlanning = trip.getInbound();
                    returnDateField.setText(new StringBuilder().append(inboundPlanning.getDate().getDate()).append(" | ").append(inboundPlanning.getDate().getTime()).toString());
                    price += inboundPlanning.getPrice();
                }
                departureField.setText(outboundPlanning.getDeparture());
                destinationField.setText(outboundPlanning.getDestination());
                placesField.setText("4 Adultes - 4 Enfants");
                priceField.setText(new StringBuilder().append(price).append(" FCFA").toString());
                dialogBuilder.setView(dialogView);
                dialogBuilder.setCancelable(true);
                b = dialogBuilder.create();
                b.show();
            }
        });

        Typeface RobotoCondensed = FontTools.getInstance().getFont("Roboto/RobotoCondensed_Regular", context);
        outboundDepartureField.setTypeface(RobotoCondensed);
        outboundDestinationField.setTypeface(RobotoCondensed);
        outboundDateField.setTypeface(RobotoCondensed);
        outboundPriceField.setTypeface(RobotoCondensed);
        outboundPlacesField.setTypeface(RobotoCondensed);
        inboundDepartureField.setTypeface(RobotoCondensed);
        inboundDestinationField.setTypeface(RobotoCondensed);
        inboundDateField.setTypeface(RobotoCondensed);
        inboundPriceField.setTypeface(RobotoCondensed);
        inboundPlacesField.setTypeface(RobotoCondensed);



        return view;
    }
}
