package com.example.prono_doctor_app;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.LongFunction;

public class firebase_class {
    doctor_details doctor;
    schedule_id scheduleId;
    patient_details pat;
    disease_details did;
    private DatabaseReference db;
    public int broadcast_doctor(doctor_details doctor,int doc_id)
    {

        this.doctor=doctor;
        db=FirebaseDatabase.getInstance().getReference();

        db.child( "probono_database" ).child( "doctor_details" ).child( Integer.toString( doc_id) ).setValue( doctor );
        return 1;
    }
    public  int brodcast_schedule(int id,schedule_id scheduleId)
    {
        this.scheduleId=scheduleId;
        db=FirebaseDatabase.getInstance().getReference();
        db.child( "probono_database" ).child( "schedule_details" ).child(Integer.toString( id )).setValue( scheduleId );


        return 1;
    }
    public int broadcast_patient(String id,patient_details pat)
    {
        this.pat=pat;
        db=FirebaseDatabase.getInstance().getReference();
        db.child("probono_database").child( "patient_details" ).child( id ).setValue( pat );


        return 1;
    }
    public int broadcast_disease(int id,disease_details did)
    {
        this.did=did;
        try {
            db = FirebaseDatabase.getInstance().getReference();
            db.child( "probono_database" ).child( "disease_details" ).child( Integer.toString( id ) ).setValue( did );

           // disease_details diseaseDetails=db.child( "probono_database" ).child("disease_details"  ).child( "792" ).getClass();
            String s=did.type;
          //  Log.d("my_log"," F "+did.details3);
            fun(id,did);
            //Boolean s11=db.child( "probono_database" ).child( "schedule_details" ).getKey().contains( "2839" );
           // Log.d("firebase_log"," we reach here "+s);
            //query.addListenerForSingleValueEvent(queryValueListener);
            /* db.child( "probono_database" ).child( "disease_details" ).get().addOnCompleteListener( new OnCompleteListener<DataSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DataSnapshot> task) {
                     if(task.isSuccessful())
                     {
                         Log.d("firebase_log"," F "+String.valueOf(task.getResult().getChildren()));
                         //Iterable<DataSnapshot> iterable=task.getResult().getChildren();
                         //doctor_details doctorDetails=task.getResult().getValue(doctor_details.class);
                         try {
                             String ss="";
                            // disease_details diseaseDetails = task.getResult().getValue( disease_details.class );
                             Iterable<DataSnapshot> iterable=task.getResult().getChildren();
                             for(DataSnapshot dn:task.getResult().getChildren())
                             {
                                 ss+=dn.getKey().toString();
                                 ss+=" ";

                             }

                         Log.d("firebase_log"," "+ss);
                         }catch ( Exception e)
                         {
                             Log.d("firebase_log"," we e e "+e);
                         }

                     }

                 }
             } ).addOnFailureListener( new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     Log.d("firebase_log"," e e e e "+e);
                 }
             } );*/






            //Log.d("firebase_log","  d  "+query);



        }
        catch (Exception e)
        {
            Log.d("my_log"," we got error "+e);
        }

        return 1;

    }
    void fun(final int id,disease_details did)
    {
        final String sss="";
        final ArrayList<String > arr=new ArrayList<>(  );
        db=FirebaseDatabase.getInstance().getReference();
        db.child(  "probono_database").child( "schedule_details" ).get().addOnCompleteListener( new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d("firebase_log",task.getResult().getKey().toString());
                ArrayList<String> arrf=new ArrayList<>(  );

                for(DataSnapshot dt:task.getResult().getChildren())
                {
                    Log.d("firebase_log"," the size is "+dt.getKey().toString());
                    String ss=dt.getKey().toString();
                    arrf.add( ss );

                }
                Log.d("firebase_log","D "+arrf);
                get_doctor_id_(id,arrf);
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase_log"," failed  "+e);
            }
        } );
        Log.d("firebase_log"," my size"+arr+sss);
    }
    void get_doctor_id_(final  int disease_id,ArrayList<String> ar)
    {
        DatabaseReference mydb=FirebaseDatabase.getInstance().getReference();
        final ArrayList<String> my_doc_id=new ArrayList<>(  );
        for(int i=0;i<ar.size();i++) {
            mydb.child( "probono_database" ).child( "schedule_details" ).child(ar.get( i )  ).get().addOnCompleteListener( new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    try {
                        schedule_id scheduleId = task.getResult().getValue( schedule_id.class );

                        if(scheduleId.no_of_patient>0 )
                        {
                            String my_doctor_id=( Integer.toString(scheduleId.doctor_id));
                            String type=did.type;
                            Log.d("firebase_log"," it my did "+did.details3+" "+scheduleId.doctor_id);
                            Boolean t=check_the_doctor_type(disease_id,scheduleId.doctor_id,type);


                        }

                         Log.d("firebase_log","we "+scheduleId.no_of_patient+" the type "+did.type);
                    }
                    catch (Exception e)
                    {
                        Log.d("firebase_error","we hjjdjd got an error ");
                    }
                }
            } );
        }
    }
    Boolean check_the_doctor_type(final int disease_id,final int id, final String type)
    {
        DatabaseReference my_db22=FirebaseDatabase.getInstance().getReference();
        String id1;
        try
        {
            id1=Integer.toString( id );

        }
        catch (Exception e)
        {
            id1="43333";
        }
        try
        {
            my_db22.child( "probono_database").child( "doctor_details" ).child( id1).get().addOnSuccessListener( new OnSuccessListener<DataSnapshot>() {

                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                   // Log.d("my_log","we got data"+dataSnapshot.child( "speciality" ));
                    if(dataSnapshot.child( "speciality" ).getValue()!=null)
                    {
                        Log.d("my_log",dataSnapshot.child( "speciality" ).getValue().toString()+"  F " +id);
                        String  index1=dataSnapshot.child( "speciality" ).getValue().toString();
                        int index=0;
                        int index2=0;
                        try {
                            index=Integer.parseInt( index1 );
                            index2=Integer.parseInt( type );
                        }
                        catch (Exception e)
                        {
                            index=0;
                            index2=0;
                        }
                        String tp=static_data.s[index];
                        String typee=static_data.s[index2];
                        Log.d("my_log"," he e "+tp+" d d "+typee);
                        if(typee.equals( tp ))
                        {
                            Log.d("my_log","the sechdule is made for "+typee+" "+disease_id+" "+id+" ");
                        }
                    }
                }
            } );
           // Log.d("my_log","the data is "+)
        }
        catch (Exception e)
        {
            Log.d("my_log","we fot error "+e);
        }
        return true;

    }
    Boolean check_the_doctor_type2(int id, String type) {
        int kk = 1;
        int my_id=4433;
        DatabaseReference mydb=FirebaseDatabase.getInstance().getReference();
        mydb.child( "probono_database" ).child( "doctor_details" ).child( Integer.toString( my_id ) ).get().addOnCompleteListener( new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d("firebase_error","S S "+task.getResult());
            }
        });
        return true;

    }
}

