package com.shankaryadav.www.whatsappchatbox;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddChat extends AppCompatActivity {

RelativeLayout relativeLayout,aboutRelativeLayout,rootRelaLayo;

EditText name,about;

TextView cancel,save,nameTextview,abcancel,absave,abTextView;

String strName = "";
String strAbout = "";

    FirebaseFirestore fb;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    CollectionReference collectionReference;

    CircleImageView profile;


    final int ACTIVITY_SELECT_IMAGE = 1234;
    private Uri selectedImage;

    boolean checkSnackBar;


//
//    EditText name;
//    EditText lasttext;
//
//    TextView getn,getlm;
//
//    Button btn,getBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_chat);

        try {
            if (ActivityCompat.checkSelfPermission(AddChat.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AddChat.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

          fb = FirebaseFirestore.getInstance ();
        firebaseStorage = FirebaseStorage.getInstance ();

        storageReference = firebaseStorage.getReference ().child ("image");

         final CollectionReference collectionReference = fb.collection ("Notebook");

        relativeLayout = findViewById (R.id.name_Relative_layout);
        aboutRelativeLayout = findViewById (R.id.about_Relative_layout);



        nameTextview = findViewById (R.id.name);
        abTextView = findViewById (R.id.about);
        profile = findViewById (R.id.profile);



        relativeLayout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //  LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear_layout_root);

                handleSnackbarName (v);


            }
        });



        aboutRelativeLayout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {


                handleSnackbarAbout (v);

            }
        });


        profile.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);

            }
        });







//        name =findViewById (R.id.name);
//        lasttext =findViewById (R.id.lasmsg);
//        btn = findViewById (R.id.send);
//        getn= findViewById (R.id.getname);
//        getlm= findViewById (R.id.getlastmsg);
//        getBtn= findViewById (R.id.getdata);

//        btn.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//
//                String namestr = name.getText ().toString ();
//                String lasttextstr = name.getText ().toString ();
//
//                Chat chat = new Chat (namestr,lasttextstr);
//                collectionReference.add (chat).addOnCompleteListener (new OnCompleteListener<DocumentReference> () {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentReference> task) {
//                        if (task.isSuccessful ()){
//                            Toast.makeText (AddChat.this, "Item is added", Toast.LENGTH_SHORT).show ();
//                        }
//                    }
//                });
//            }
//        });
//
//
//
//        getBtn.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                collectionReference.get ()
//                        .addOnSuccessListener (new OnSuccessListener<QuerySnapshot> () {
//                            @Override
//                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                                if (!queryDocumentSnapshots.isEmpty ()){
//
//                                    List<DocumentSnapshot> l = queryDocumentSnapshots.getDocuments ();
//
//                                    for (DocumentSnapshot d:l){
//                                        Chat chat = d.toObject (Chat.class);
//                                        getn.setText (chat.name);
//                                        getlm.setText (chat.lastmsg);
//                                    }
//                                }
//
//                            }
//                        });
//            }
//        });




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissions, grantResults);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        switch(requestCode) {
            case 1234:
                if(resultCode == RESULT_OK){
                    selectedImage = data.getData();

                    if (selectedImage == null){
                        Toast.makeText (this, "please select an Image", Toast.LENGTH_SHORT).show ();
                        return;
                    }
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};


                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();


                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    /* Now you have choosen image in Bitmap format in object "yourSelectedImage". You can use it in way you want! */

                   // Log.i ("TAG------??kf",yourSelectedImage.toString ());
                    profile.setImageBitmap (yourSelectedImage);

                    if (((BitmapDrawable) profile.getDrawable ()).getBitmap () == null){
                        Picasso.get().load(selectedImage).into(profile);
                    }

                    storageReference.putFile (selectedImage).addOnCompleteListener (new OnCompleteListener<UploadTask.TaskSnapshot> () {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful ()){
                                Toast.makeText (AddChat.this, "image is uploaded to database", Toast.LENGTH_SHORT).show ();
                            }
                        }
                    });
                }
        }
    }


    public void handleSnackbarName(View v){
        final Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Inflate your custom view with an Edit Text
        LayoutInflater objLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View snackView = objLayoutInflater.inflate(R.layout.custom_snack, null);

        layout.addView(snackView, 0);
        snackView.setBackgroundColor (Color.WHITE);
        snackbar.show();

        checkSnackBar = true;

        name = snackView.findViewById (R.id.your_name);
        cancel = snackView.findViewById (R.id.cancel);
        save = snackView.findViewById (R.id.save);


        cancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                snackbar.dismiss ();

                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                try {
                    if (imm != null && imm.isAcceptingText ()) {
                        imm.hideSoftInputFromWindow (getCurrentFocus ().getWindowToken (), 0);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace ();
                }

                checkSnackBar = false;

            }
        });

        save.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                strName = name.getText ().toString ();

                nameTextview.setText (strName);

                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                try{
                    assert imm != null;
                    if(imm.isAcceptingText()) {
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace ();
                }


                snackbar.dismiss ();
                checkSnackBar = false;
            }
        });

    }

    public void handleSnackbarAbout(View v){

        final Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Inflate your custom view with an Edit Text
        LayoutInflater objLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View snackView = objLayoutInflater.inflate(R.layout.custom_snack_about, null);

        layout.addView(snackView, 0);
        snackView.setBackgroundColor (Color.WHITE);
        snackbar.show();

        checkSnackBar = true;

        abcancel = snackView.findViewById (R.id.cancel);
        absave = snackView.findViewById (R.id.save);
        about = snackView.findViewById (R.id.your_about);

        absave.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                strAbout = about.getText ().toString ();
                abTextView.setText (strAbout);


                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                try{
                    assert imm != null;
                    if(imm.isAcceptingText()) {
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace ();
                }


                snackbar.dismiss ();

                checkSnackBar = false;

            }
        });

        abcancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {


                snackbar.dismiss ();

                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                try {
                    if (imm != null && imm.isAcceptingText ()) {
                        imm.hideSoftInputFromWindow (getCurrentFocus ().getWindowToken (), 0);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace ();
                }

                checkSnackBar = false;

            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed ();

        //startActivity (new Intent (AddChat.this,ChatList.class));
        finish ();
    }

    @Override
    protected void onStop() {
        super.onStop ();



    }
}
