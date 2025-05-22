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
import android.widget.ImageButton;
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

        // ✅ BACK BUTTON HANDLING
        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        Furniture furniture = getArguments() != null ? getArguments().getParcelable("fur") : null;

        if (furniture != null) {
            Log.d("FurnitureDetails", "Received: " + furniture.getCategory());
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
                tvnameFur.setText(myFur.getCategory());
                tvmaterial.setText(myFur.getMaterial());
                tvFurCategory.setText(myFur.getColor());
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




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_navigation);
        if (bottomNav != null) bottomNav.setVisibility(View.VISIBLE);
    }


}