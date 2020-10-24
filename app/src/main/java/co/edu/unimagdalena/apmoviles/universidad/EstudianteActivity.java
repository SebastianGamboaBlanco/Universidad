package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EstudianteActivity extends AppCompatActivity implements View.OnClickListener {
    TextView codigo;
    Estudiante e;
    EstudianteController ec;
    EditText nombre, programa;
    Button eliminar,actualizar;
    BaseDatos baseDatos=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);

        codigo = findViewById(R.id.textCodigo);
        nombre = findViewById(R.id.editnombre);
        programa = findViewById(R.id.editPrograma);
        eliminar = findViewById(R.id.btnEliminar);
        actualizar = findViewById(R.id.btnActualizar);
        eliminar.setOnClickListener(this);
        actualizar.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        String codi = bundle.getString("codigo");
        codigo.setText(codi);
       // e= new Estudiante(codi.toString(),nombre.getText().toString(),programa.getText().toString());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnActualizar:
                if(TextUtils.isEmpty(nombre.getText().toString())||TextUtils.isEmpty(codigo.getText().toString())){
                    Toast.makeText(this,"Complete los campos",Toast.LENGTH_LONG).show();
                }else{
                    baseDatos = new BaseDatos(this,1);
                    SQLiteDatabase bd= baseDatos.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    valores.put(DefBD.col_nombre, nombre.getText().toString());
                    valores.put(DefBD.col_programa, programa.getText().toString());
                    String whereArgs[] = {codigo.getText().toString()};
                    bd.update(DefBD.tabla_est,valores,DefBD.col_codigo+"=?",whereArgs);
                    bd.close();
                    Toast.makeText(getApplicationContext(), "Estudiante actualizado correctamente", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this,MainActivity.class);
                    startActivity(i);
                }
                break;
            case  R.id.btnEliminar:
                baseDatos = new BaseDatos(this,1);
                SQLiteDatabase bd= baseDatos.getWritableDatabase();
                bd= baseDatos.getWritableDatabase();
                String parametro[] = {codigo.getText().toString()};
                bd.delete(DefBD.tabla_est,DefBD.col_codigo+"=?",parametro);
                bd.close();
                Toast.makeText(getApplicationContext(), "Estudiante se Elimino correctamente", Toast.LENGTH_LONG).show();
                Intent j = new Intent(this,MainActivity.class);
                startActivity(j);
                break;
        }
    }
}
