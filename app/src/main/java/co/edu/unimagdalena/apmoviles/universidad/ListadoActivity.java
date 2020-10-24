package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    ListView listado;
    EstudianteController ec;
    EstudianteController estudianteController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listado = findViewById(R.id.lstlistado);
        estudianteController = new EstudianteController(this);
        final Cursor c = estudianteController.allEstudiantes2();
        final EstudianteCursorAdapter ecursor = new EstudianteCursorAdapter(this,c,false);
        listado.setAdapter(ecursor);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent(ListadoActivity.this,EstudianteActivity.class);
                i.putExtra("codigo",c.getString(0));
               // Toast.makeText(ListadoActivity.this,c.getString(0),Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }
}
