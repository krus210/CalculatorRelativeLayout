package ru.netology.korolev.calculatorrelativelayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Settings extends AppCompatActivity {

    public static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 10;
    EditText editPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        int permissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {

            initViews();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    initViews();
                } else {
                    // permission denied
                }
                return;
        }
    }

    private void initViews() {
        editPath = findViewById(R.id.editPath);
        Button buttonOK = findViewById(R.id.buttonOK);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = editPath.getText().toString();
                if (!path.equals("")) {
                    if (isExternalStorageWritable()) {
                        File file = new File(Environment.
                                getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                                path);
                        if (file.exists()) {
                            Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
                            Intent intent = new Intent(Settings.this, MainActivity.class);
                            intent.putExtra("BitmapImage", b);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Settings.this,
                                    getString(R.string.error_file_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Settings.this,
                                getString(R.string.error_reading),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Settings.this,
                            getString(R.string.don_write_text),
                            Toast.LENGTH_LONG).
                            show();
                }
            }
        });
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}
