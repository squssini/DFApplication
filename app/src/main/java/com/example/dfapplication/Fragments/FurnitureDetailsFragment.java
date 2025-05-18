package com.example.dfapplication.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dfapplication.Classes.CartManager;
import com.example.dfapplication.Classes.Firebase;
import com.example.dfapplication.Classes.Furniture;
import com.example.dfapplication.R;
import com.example.dfapplication.Utilities.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FurnitureDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FurnitureDetailsFragment extends Fragment  {
    private static final int PERMISSION_SEND_SMS = 1;
    private static final int REQUEST_CALL_PERMISSION = 2;
    private Firebase fbs;
    private TextView tvnameFur,tvmaterial, tvPrice,tvFurCategory;
    private ImageView ivFurPhoto;
    private Button sendSMSButton, btnWhatsapp, btnCall;
    private Furniture myFur;

    private boolean isEnlarged = false; //משתנה כדי לעקוב אחרי המצב הנוכחי של התמונה (האם היא מגודלת או לא)




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FurnitureDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FurnitureDetailsFragment newInstance(String param1, String param2) {
        FurnitureDetailsFragment fragment = new FurnitureDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_furniture_details, container, false);

        Furniture furniture = getArguments() != null ? getArguments().getParcelable("fur") : null;

        if (furniture != null) {
            Log.d("FurnitureDetails", "Received: " + furniture.getName());
            // Set your views here using furniture data
        } else {
            Log.e("FurnitureDetails", "No furniture object received!");
        }

        return view;
    }




    @Override
    public void onStart() {
        super.onStart();
        init();
        ImageView ivFurPhoto = getView().findViewById(R.id.ivFurnitureDetailsFragment);

        ivFurPhoto.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                // Optional: Update image layout parameters if needed
                ViewGroup.LayoutParams layoutParams = ivFurPhoto.getLayoutParams();
                ivFurPhoto.setLayoutParams(layoutParams);

                // Create a cart item from the current furniture
                myFur.setCartQty(1); // Set default quantity to 1
                CartManager.getInstance().addToCart(myFur); // Add to cart manager

            }

        });


    }



    public void init()
    {


        fbs= Firebase.getInstance();
        tvnameFur=getView().findViewById(R.id.tvNameFur);
        tvmaterial=getView().findViewById(R.id.tvmaterial);
        tvFurCategory=getView().findViewById(R.id.tvFurCategory);
        tvPrice=getView().findViewById(R.id.tvprice);
        ivFurPhoto = getView().findViewById(R.id.ivFurnitureDetailsFragment);

        Bundle args = getArguments();
        if (args != null) {
            myFur = args.getParcelable("fur");
            if (myFur != null) {
                //String data = myObject.getData();
                // Now you can use 'data' as needed in FragmentB
                tvnameFur.setText(myFur.getName());
                tvmaterial.setText(myFur.getMaterial());
                tvFurCategory.setText(myFur.getCategory());
                tvPrice.setText(myFur.getPrice()+" ₪");
                if (myFur.getPhoto() == null || myFur.getPhoto().isEmpty())
                {
                    Picasso.get().load(R.drawable.ic_launcher_background).into(ivFurPhoto);
                }
                else {
                    Picasso.get().load(myFur.getPhoto()).into(ivFurPhoto);
                }
            }
        }


    }




    private void sendSMS() {
        String phoneNumber = myFur.getPhoneNum();
        String message = "I am Interested in your  "+myFur.getName()+"  fur: " + myFur.getNumOfFur();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getActivity(), "SMS sent.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "SMS sending failed.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSMS();
            } else {
                Toast.makeText(requireContext(), "Permission denied. Cannot send SMS.", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCall();
            }
        }
    }
    // TODO : check Phone number is not correct;
    public void sendWhatsAppMessage(View view) {

        String smsNumber = "+972"+myFur.getPhoneNum();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        //  Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, " I am Interested in your  " +myFur.getName()+ "  car:  "  + myFur.getNumOfFur());
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");

        startActivity(sendIntent);
    }
    //  888 whatsapp setting
    private boolean isAppInstalled(String s) {
        PackageManager packageManager= getActivity().getPackageManager();
        boolean is_installed;
        try{
            packageManager.getPackageInfo(s,PackageManager.GET_ACTIVITIES);
            is_installed=true;
        } catch (PackageManager.NameNotFoundException e) {
            is_installed=false;
            throw new RuntimeException(e);
        }
        return  is_installed;
    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            startCall();
        }

    }

    private void startCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + myFur.getPhoneNum()));

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        }




    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_navigation);
        if (bottomNav != null) bottomNav.setVisibility(View.VISIBLE);
    }


}