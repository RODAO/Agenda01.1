package br.com.alura.agenda01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda01.dao.AlunoDAO;
import br.com.alura.agenda01.modelo.Aluno;

import static android.R.attr.data;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 567;
    private FormularioHelper helper;
    private Button btnSalvar;
    private Button btnExcluir;
    private Aluno aluno;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        //Caso de uso alterar aluno
        if (aluno != null) {
            helper.preencherFormulario(aluno);
            btnExcluir.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Executar caso de uso excluir aluno
                    excluirAluno();
                }
            });
            btnSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alterarAluno();
                }
            });
        }
        //caso de uso inserir aluno
        else {
            btnExcluir.setVisibility(View.INVISIBLE);
            btnSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inserirAluno();
                }
            });
        }


        Button botaofoto = (Button) findViewById(R.id.formulario_botao_foto);
        botaofoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_CAMERA) {
             if (requestCode == CODIGO_CAMERA){
                 helper.carregaImagem(caminhoFoto);
             }
            // abrir a foto que a gente tirou
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaAluno();

                AlunoDAO dao = new AlunoDAO(this);
                if (aluno.getId() != null) {
                    dao.alterar(aluno);
                } else {
                    dao.insere(aluno);
                }
                dao.close();
 // teste git //Alteracao correta!   
                Toast.makeText(FormularioActivity.this, "Aluno" + aluno.getNome() + "salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void inserirAluno() {
        Aluno aluno = helper.pegaAluno();
        if (aluno != null) {

            AlunoDAO alunoDAO = new AlunoDAO(this);
            alunoDAO.insere(aluno);
            alunoDAO.close();
            Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " Inserido com Sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(FormularioActivity.this, "Preencha todos os campos com exclamação", Toast.LENGTH_SHORT).show();
        }
    }

    public void alterarAluno() {
        aluno = helper.pegaAluno();
        AlunoDAO alunoDAO = new AlunoDAO(this);
        alunoDAO.alterar(aluno);
        alunoDAO.close();
        Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " Alterado com Sucesso", Toast.LENGTH_SHORT).show();
    }

    public void excluirAluno() {
        aluno = helper.pegaAluno();
        AlunoDAO alunoDAO = new AlunoDAO(this);
        alunoDAO.excluir(aluno);
        alunoDAO.close();
        Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " Excluido com Sucesso", Toast.LENGTH_SHORT).show();
    }

}