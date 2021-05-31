package com.example.prono_doctor_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.prono_doctor_app.static_data.fragment_4_data;
import static com.example.prono_doctor_app.static_data.patient_id_for_problem_details;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link problem_details_4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class problem_details_4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public problem_details_4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment problem_details_4.
     */
    // TODO: Rename and change types and number of parameters
    public static problem_details_4 newInstance(String param1, String param2) {
        problem_details_4 fragment = new problem_details_4();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_problem_details_4, container, false );
        ListView L11=(ListView)view.findViewById( R.id.l1 );
        static_data st=new static_data();
        ArrayAdapter<String > arrayAdapter=new ArrayAdapter<String>( getContext(),R.layout.support_simple_spinner_dropdown_item,st.s );
        L11.setAdapter( arrayAdapter );
        L11.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText( getContext(),"you have clicked on "+i,Toast.LENGTH_SHORT).show();
                fragment_4_data=Integer.toString( i );
                Intent in =new Intent( getContext(),problem_details_page.class );
                in.putExtra( "destination",2 );
                in.putExtra( "patient_id",patient_id_for_problem_details );
                startActivity( in );

            }
        } );
        return  view;
    }
}